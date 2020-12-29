package com.eloam.mvvm.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eloam.mvvm.ui.home.HomeViewModel
import com.vikas.paging3.repository.local.RemoteKeys


@Database(
    version = 1,
    entities = [HomeViewModel.DoggoImageModel::class, RemoteKeys::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getDoggoImageModelDao(): DoggoImageModelDao


    companion object {
        val DOGGO_DB = "dogs.db"

        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DOGGO_DB //数据库名称
                ).allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }


    }
}