package com.example.myapplication.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Entity.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id IN (:Ids)")
    fun loadAllByIds(Ids: IntArray): List<Movie>

    @Query("SELECT * FROM Movie WHERE title LIKE :title AND release_date LIKE :release_date LIMIT 1")
    fun findByName(title: String, release_date: String): Movie

    @Insert
    fun insertAll(vararg Movie: Movie)

    @Insert
    fun insert(vararg movies: Movie)

    @Delete
    fun delete(movie: Movie)
}