package com.example.dogaplication.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
data class UserFavPerrito(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "perritoId",
        associateBy = Junction(UserFavPerritoCrossRef::class)
    )
    val perritos: List<Perrito>
)