package com.example.chua.prelimexam

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Chua on 12/17/2017.
 */
data class Music (val musicTitle: String = "", val musicSinger: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(musicTitle)
        parcel.writeString(musicSinger)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Music> {
        override fun createFromParcel(parcel: Parcel): Music {
            return Music(parcel)
        }

        override fun newArray(size: Int): Array<Music?> {
            return arrayOfNulls(size)
        }
    }
}