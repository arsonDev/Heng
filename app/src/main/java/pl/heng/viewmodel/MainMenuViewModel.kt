package pl.heng.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.database.repository.HabitRepository

class MainMenuViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = HabitRepository(application)
    private val allHabits =  repository.getHabitList()

    fun getAllHabits() = allHabits
}