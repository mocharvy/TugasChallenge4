package com.programmer.challenge4

import android.app.Application
import androidx.room.Room
import com.programmer.challenge4.database.CartDatabase

class Application : Application() {
    companion object {
        lateinit var database: CartDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // Inisialisasi database Room
        database = Room.databaseBuilder(
            applicationContext,
            CartDatabase::class.java, "database"
        ).build()
    }
}
