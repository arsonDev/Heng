package pl.heng.viewmodel

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableByte
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.database.DatabaseHeng
import pl.heng.database.model.Habit

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {
    val hour = ObservableInt(10)
    val min = ObservableInt(30)
    val taskName = ObservableField("")
    val categoryName = ObservableField("")
    val weekCount = ObservableByte(4)
    private val app = application

    fun addNewTask() {
        //todo : Dodac final date
        val habit = Habit(taskName.get()!!, categoryName.get()!!, weekCount.get(), "", "${hour.get()}:${min.get()}")
        viewModelScope.launch {
            DatabaseHeng.GetDatabase(app.baseContext).habitDao().insertHabit(habit)
        }
    }
}
