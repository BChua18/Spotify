package com.example.chua.prelimexam

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList

/**
 * Created by Chua on 12/17/2017.
 */
class CustomAdapter(SongModel: ArrayList<MusicModel>, context: Context, mainActivity: MainActivity): RecyclerView.Adapter<CustomAdapter.SongListViewHolder>() {

    var mContext = context
    var mSongModel = SongModel
    var allMusicList: ArrayList<String> = ArrayList()

    companion object {
        val MUSICLIST = "musiclist"
        val MUSICITEMPOS = "pos"
    }

    override fun onBindViewHolder(holder: SongListViewHolder?, position: Int) {
        var song = mSongModel[position]
        var songTitle = song.title
        var songSinger = song.singer

        holder!!.songTitle.text = songTitle
        holder!!.songSinger.text = songSinger
        holder.setOnCustomItemClickListener(object: CustomItemClickListener{
            override fun onCustomItemClick(view: View, pos: Int) {
                for (i in 0 until mSongModel.size){
                    allMusicList.add(mSongModel[i].path)

                }
                var musicDataIntent = Intent(mContext, PlayMusicService::class.java)
                musicDataIntent.putStringArrayListExtra(MUSICLIST,allMusicList)
                musicDataIntent.putExtra(MUSICITEMPOS,pos)
                mContext.startService(musicDataIntent)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongListViewHolder {
        val inflater = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return SongListViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return mSongModel.size
    }

    class SongListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var songTitle: TextView
        var songSinger: TextView
        var mCustomItemClickListener: CustomItemClickListener? = null
        init {
            songTitle = itemView.findViewById(R.id.title)
            songSinger = itemView.findViewById(R.id.artist)
            itemView.setOnClickListener(this)
        }
        fun setOnCustomItemClickListener(customItemClickListener: CustomItemClickListener){
            this.mCustomItemClickListener = customItemClickListener
        }

        override fun onClick(view: View?) {
            this.mCustomItemClickListener!!.onCustomItemClick(view!!,adapterPosition)
        }
    }
}