package pl.heng.database.repository

import pl.heng.database.dao.HabitDao
import pl.heng.database.model.Habit

class HabitRepository(private val habitDao: HabitDao) {

    val habits = habitDao.getHabits()

    suspend fun insert(habit: Habit) {
        habitDao.insertHabit(habit)
    }
}