package com.example.dogaplication.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "perritos")
class Perrito(nombre:String?, raza: String?, subRaza: String?, edad: Int?, peso: Int? ,macho: Byte, favorito:Byte, imagen: String?): Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "nombre")
    var nombre: String = ""
    @ColumnInfo(name = "raza")
    var raza: String = ""
    @ColumnInfo(name = "subRaza")
    var subRaza: String = ""
    @ColumnInfo(name = "edad")
    var edad: Int = 0
    @ColumnInfo(name = "peso")
    var peso: Int = 0
    @ColumnInfo(name = "macho")
    var macho: Byte= 0
    @ColumnInfo(name = "favorito")
    var favorito: Byte = 0
    @ColumnInfo(name = "imagen")
    var imagen: String = ""


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
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
        this.peso = peso!!
        this.subRaza = subRaza!!
        this.macho = macho!!
        this.favorito = favorito!!

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(raza)
        parcel.writeString(subRaza)
        parcel.writeInt(edad)
        parcel.writeInt(peso)
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