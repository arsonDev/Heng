package pl.heng.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.heng.database.model.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit ORDER BY createDate DESC")
    fun getHabits() : LiveData<List<Habit>>

    @Query("SELECT * FROM habit WHERE uid = :idHabit ")
    suspend fun getHabitById(idHabit : Int) : Habit

    @Query("SELECT * FROM habit WHERE name = :name")
    suspend fun getHabitByName(name : String) : Habit

    @Insert
    suspend fun insertHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}