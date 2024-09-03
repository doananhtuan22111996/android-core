package vn.core.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object CoreDatabase {

    inline fun <reified T : RoomDatabase> build(context: Context, name: String) =
        builder<T>(context, name).build()

    inline fun <reified T : RoomDatabase> builder(context: Context, name: String) =
        Room.databaseBuilder(context.applicationContext, T::class.java, name)
}