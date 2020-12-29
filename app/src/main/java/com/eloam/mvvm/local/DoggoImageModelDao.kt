package com.eloam.mvvm.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloam.mvvm.ui.home.HomeViewModel

@Dao
interface DoggoImageModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doggoModel: List<HomeViewModel.DoggoImageModel>)

    @Query("SELECT * FROM doggoimagemodel")
    fun getAllDoggoModel(): PagingSource<Int, HomeViewModel.DoggoImageModel>

    @Query("DELETE FROM doggoimagemodel")
    suspend fun clearAllDoggos()

}