package pl.heng.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.heng.database.repository.HabitRepository

class MainMenuViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = HabitRepository(application)
    private val allHabits =  repository.getHabitList()

    fun getAllHabits() = allHabits
}