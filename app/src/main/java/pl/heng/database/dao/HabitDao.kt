package pl.heng.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.heng.database.model.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getHabit() : Habit

    @Query("SELECT * FROM habit WHERE uid = :idHabit ")
    fun getHabitById(idHabit : Int) : Habit

    @Query("SELECT * FROM habit WHERE name = :name")
    fun getHabitByName(name : String) : Habit

    @Insert
    suspend fun insertHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}