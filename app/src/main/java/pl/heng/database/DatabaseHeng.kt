package pl.heng.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.heng.database.dao.UserDao
import pl.heng.database.model.Habit
import pl.heng.database.model.User

@Database(entities = arrayOf(User::class,Habit::class),version = 1)
abstract class DatabaseHeng : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHeng? = null

        fun GetDatabase(context: Context): DatabaseHeng {
            return INSTANCE ?: synchronized(this) {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHeng::class.java,
                    "DatabaseHeng"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}