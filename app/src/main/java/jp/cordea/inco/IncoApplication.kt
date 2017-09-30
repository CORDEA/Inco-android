package jp.cordea.inco

import android.app.Application
import android.arch.persistence.room.Room

class IncoApplication : Application() {

    companion object {
        lateinit var Database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        Database = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "inco").build()
    }

}