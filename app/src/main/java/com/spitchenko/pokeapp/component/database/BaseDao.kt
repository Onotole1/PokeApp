package com.spitchenko.pokeapp.component.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(value: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertOrUpdate(values: List<T>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(values: List<T>)

    @Delete
    suspend fun delete(value: T)

    @Delete
    suspend fun delete(vararg values: T)

    @Delete
    @JvmSuppressWildcards
    suspend fun delete(values: List<T>)
}