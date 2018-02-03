package com.example.chua.prelimexam

import android.Manifest
import android.app.ActionBar
import android.content.pm.PackageManager
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var musicModelData: ArrayList<MusicModel> = ArrayList()
    var songListAdapter: CustomAdapter? = null

    companion object {
        val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)

        //Permission request
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE)
        } else {
            loadData()
        }

    }

    //getting the data
    fun loadData() {
        val musicCursor: Cursor? = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        while (musicCursor != null && musicCursor.moveToNext()) {
            var Name = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            var Singer = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            var Path = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA))

            musicModelData.add(MusicModel(Name, Singer, Path))
        }
        songListAdapter = CustomAdapter(musicModelData, applicationContext, this)
        var layoutManager = LinearLayoutManager(applicationContext)
        recycler_view?.layoutManager = layoutManager
        recycler_view?.adapter = songListAdapter
    }

    //Checking permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                loadData()
            }
        }
    }
}
    /*

    private var mRecyclerView: RecyclerView? = null
    private var mNestedScrollView: NestedScrollView? = null
    private var mShuffle: Button? = null
    private var mFilter: EditText? = null
    private var mProfilePicture: ImageView? = null
    private var mDiscover: TextView? = null
    private var mFollower: TextView? = null
    private var mPoint: TextView? = null
    private var mSpotify: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCollapsingToolbar()
        findView()

        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val musicList = ArrayList<Music>()

        musicList.add(Music("Perfect", "Ed Sheeran"))
        musicList.add(Music("Dive", "Ed Sheeran"))
        musicList.add(Music("Hayaan Mo Sila", "Ex Battalion"))
        musicList.add(Music("Come With Me", "Ex Battalion"))
        musicList.add(Music("ILYSB", "LANY"))
        musicList.add(Music("I Like Me Better", "LANY"))
        musicList.add(Music("Lost Stars", "Adam Lavigne"))
        musicList.add(Music("Thank You For The Broken Heart", "J-Rice"))
        musicList.add(Music("Versace On The Floor", "Bruno Mars"))
        musicList.add(Music("Closer", "The Chainsmokers"))
        musicList.add(Music("Something Just Like This", "The Chainsmokers"))
        musicList.add(Music("Hymn For The Weekend", "Coldplay"))

        val adapter = CustomAdapter(musicList)

        mRecyclerView !!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initCollapsingToolbar() {
        val collapsingToolbar = findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbar.title = " "
        val appBarLayout = findViewById<View>(R.id.appbar) as AppBarLayout
        appBarLayout.setExpanded(true)

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            @SuppressLint("ResourceAsColor", "ResourceType")
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = getString(R.string.page)
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.CustomTitle)
                    collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }

    fun findView() {
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        mNestedScrollView = findViewById<NestedScrollView>(R.id.scroll)
        mShuffle = findViewById<Button>(R.id.shuffle_button)
        mFilter = findViewById<EditText>(R.id.filter)
        mProfilePicture = findViewById<ImageView>(R.id.profile_photo)
        mDiscover = findViewById<TextView>(R.id.discover_weekly)
        mFollower = findViewById<TextView>(R.id.following)
        mPoint = findViewById<TextView>(R.id.dot)
        mSpotify = findViewById<TextView>(R.id.spotify)
    }

}*/