package com.example.codequest.util

import android.os.Parcel
import android.os.Parcelable


data class product(
    val img: String,
    val name: String,
    val rating: String,
    val date: String,
    val category: String="my movies"
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    ) {
    }

    constructor() : this("","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(name)
        parcel.writeString(rating)
        parcel.writeString(date)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<product> {
        override fun createFromParcel(parcel: Parcel): product {
            return product(parcel)
        }

        override fun newArray(size: Int): Array<product?> {
            return arrayOfNulls(size)
        }
    }
}


