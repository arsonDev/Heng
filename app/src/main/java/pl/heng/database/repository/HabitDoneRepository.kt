package pl.heng.database.repository

import android.app.Application
import kotlinx.coroutines.runBlocking
import pl.heng.database.DatabaseHeng
import pl.heng.database.dao.HabitHistoryDao
import pl.heng.database.model.HabitDoneHistory
import java.time.LocalDate

class HabitDoneRepository(application: Application) {

    private var habitDoneDao: HabitHistoryDao
    var allHabitDones: List<HabitDoneHistory>

    init {
        val database = DatabaseHeng.GetDatabase(application.applicationContext)
        habitDoneDao = database.habitDoneDao()
        allHabitDones = runBlocking { habitDoneDao.getHabitHistoryList() }
    }

    suspend fun insert(habitDone: HabitDoneHistory) {
        habitDoneDao.insertHabitDone(habitDone)
    }

    suspend fun delete(habitDoneId: Int) {
        habitDoneDao.deleteHabitDone(habitDoneId)
    }

    suspend fun getDoneCount(habitDoneId: Int) = habitDoneDao.getHabitDoneCount(habitDoneId)

    suspend fun getHabitDone(habitDoneId: Int) = habitDoneDao.getHabitDoneHistory(habitDoneId)

    suspend fun getHabitDoneToday(habitId : Int, date : LocalDate) = (habitDoneDao.getTodayDone(habitId,date.toString()) == 1)


}