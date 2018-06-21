package com.ilnur.steckoverflow_kotlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favoriteData")
data class FavoriteData(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "view_count") val viewCount: Int,
        @ColumnInfo(name = "answer_count") val answerCount: Int,
        @ColumnInfo(name = "score") val scope: Int,
        @ColumnInfo(name = "creation_date") val creationDate: Long,
        @ColumnInfo(name = "question_id") val questionId: Int,
        @ColumnInfo(name = "link") val link: String,
        @ColumnInfo(name = "title") val title: String,

        @ColumnInfo(name = "reputation") var reputation: Int,
        @ColumnInfo(name = "user_id") var user_id: Int,
        @ColumnInfo(name = "profile_image") var profile_image: String,
        @ColumnInfo(name = "display_name") var display_name: String
)
