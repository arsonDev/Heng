package pl.heng.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.R
import pl.heng.adapter.HabitListAdapter
import pl.heng.database.model.Habit
import pl.heng.database.model.HabitDoneHistory
import pl.heng.database.repository.HabitDoneRepository
import pl.heng.database.repository.HabitRepository
import java.time.LocalDate

class HabitsViewModel(application: Application) : AndroidViewModel(application) {

    private val habitRepo = HabitRepository(application)
    private val habitDoneRepository = HabitDoneRepository(application)
    private val adapter = HabitListAdapter(R.layout.habit_item, this)
    val selected = MutableLiveData<Habit>()
    val emptyVisible = ObservableBoolean(true)
    val notifiyMessage = MutableLiveData<String>("")

    fun getHabits() = habitRepo.getHabitList()

    fun getAdapter() = adapter

    fun getHabitAt(position: Int) = adapter.getHabit(position)

    fun setHabitsInAdapter(breeds: List<Habit>) {
        this.adapter.setHabits(breeds)
        this.adapter.notifyDataSetChanged()
    }

    fun onItemClick(position: Int) {
        selected.value = getHabitAt(position)
    }

    fun onItemClickDone(position: Int) {
        val habit = getHabitAt(position)
        viewModelScope.launch {
            val done = habitDoneRepository.getHabitDoneToday(habit.uid, LocalDate.now())
            if (!done) {
                val habitDone = HabitDoneHistory(habit.uid,true,LocalDate.now().toString())
                habitDoneRepository.insert(habitDone)
                notifiyMessage.value = "Brawo za wykonanie zadania : ${habit.name} #${habit.category}"
            }else{
                notifiyMessage.value = "Dzisiaj wykonano już zadanie : ${habit.name} #${habit.category}"
            }
        }
    }
}