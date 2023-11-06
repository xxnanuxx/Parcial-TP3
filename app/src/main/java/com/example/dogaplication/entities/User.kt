package com.example.dogaplication.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(id: Int?, usuario:String?, nombre:String?, contrasena:String?, telefono:String?, imageUrl:String?, ubicacion:String?):
    Parcelable {

    @PrimaryKey
    var id: Int = 0
    @ColumnInfo(name = "nombre")
    var nombre: String = ""
    @ColumnInfo(name = "usuario")
    var usuario: String = ""
    @ColumnInfo(name = "contrasena")
    var contrasena: String = ""
    @ColumnInfo(name = "telefono")
    var telefono: String = ""
    @ColumnInfo(name = "ubicacion")
    var ubicacion: String = ""
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = ""

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        )

    init {
        this.id = id!!
        this.nombre = nombre!!
        this.usuario = usuario!!
        this.contrasena = contrasena!!
        this.telefono = telefono!!
        this.ubicacion = ubicacion!!
        this.imageUrl = imageUrl!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(usuario)
        parcel.writeString(contrasena)
        parcel.writeString(telefono)
        parcel.writeString(ubicacion)
        parcel.writeString(imageUrl)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}