package com.example.chua.prelimexam

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by Chua on 12/17/2017.
 */
class CustomAdapter(val musicList: ArrayList<Music>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val music: Music = musicList[position]

        holder?.mTitle?.text = music.musicTitle
        holder?.mArtist?.text = music.musicSinger

        holder?.rLayout?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View) {

            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rLayout = itemView.findViewById<RelativeLayout>(R.id.item) as RelativeLayout
        val mTitle = itemView.findViewById<TextView>(R.id.title) as TextView
        val mArtist = itemView.findViewById<TextView>(R.id.artist) as TextView
        val mSetting = itemView.findViewById<ImageView>(R.id.item_setting) as ImageView
    }
}