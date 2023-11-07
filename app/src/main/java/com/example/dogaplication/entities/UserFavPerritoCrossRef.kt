package com.example.dogaplication.entities

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "perritoId"])
data class UserFavPerritoCrossRef(
    val userId: Int,
    val perritoId: Int
)

