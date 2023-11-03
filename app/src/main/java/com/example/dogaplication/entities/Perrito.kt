package com.example.dogaplication.entities

import android.os.Parcel
import android.os.Parcelable

class Perrito(nombre:String?, raza: String?, subRaza: String?, edad: Int?, macho: Byte, favorito:Byte, imagen: String?): Parcelable {

    var nombre: String = ""
    var raza: String = ""
    var subRaza: String = ""
    var edad: Int = 0
    var macho: Byte = 0
    var favorito: Byte = 0
    var imagen: String = ""


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte(),
        parcel.readByte(),
        parcel.readString(),

    )
    init {
        this.nombre = nombre!!
        this.edad = edad!!
        this.imagen = imagen!!
        this.raza = raza!!
        this.subRaza = subRaza!!
        this.macho = macho!!
        this.favorito = favorito!!

    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(raza)
        parcel.writeString(subRaza)
        parcel.writeInt(edad)
        parcel.writeByte(macho)
        parcel.writeByte(favorito)
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Perrito> {
        override fun createFromParcel(parcel: Parcel): Perrito {
            return Perrito(parcel)
        }

        override fun newArray(size: Int): Array<Perrito?> {
            return arrayOfNulls(size)
        }
    }




}