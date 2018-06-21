package com.ilnur.steckoverflow_kotlin.data

import android.arch.persistence.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoriteData")
    fun getAll(): List<FavoriteData>

    @Query("SELECT * FROM favoriteData WHERE question_id = :question_id")
    fun getFavoriteData(question_id: Int?): FavoriteData

    @Delete
    fun deleteFavoriteData(favoriteData: FavoriteData)

    @Insert
    fun insert(favorite: FavoriteData)
}