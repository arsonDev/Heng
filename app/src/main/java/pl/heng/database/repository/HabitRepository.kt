package pl.heng.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import pl.heng.database.DatabaseHeng
import pl.heng.database.dao.HabitDao
import pl.heng.database.model.Habit

class HabitRepository(application: Application) {

    private var habitDao : HabitDao
    private var allHabits : LiveData<List<Habit>>

    init {
        val database = DatabaseHeng.GetDatabase(application.applicationContext)
        habitDao = database.habitDao()
        allHabits = habitDao.getHabits()
    }

    suspend fun insert(habit: Habit) {
        habitDao.insertHabit(habit)
    }

    fun getHabitList() = habitDao.getHabits()

}