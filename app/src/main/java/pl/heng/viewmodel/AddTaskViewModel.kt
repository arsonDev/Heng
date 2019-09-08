package pl.heng.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.database.model.Habit
import pl.heng.database.repository.HabitRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class AddTaskViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Bindable
    val taskName = ObservableField<String>()
    @Bindable
    val categoryName = ObservableField<String>()
    @Bindable
    val endDate = ObservableField<LocalDate>().apply {
        set(LocalDate.now())
    }
    @Bindable
    val notifyTime = ObservableField<LocalTime>().apply {
        set(LocalTime.now())
    }
    private val repository by lazy { HabitRepository(application) }

    fun addNewTask() {
        val habit =
            Habit(
                taskName.get() ?: "TestZadania",
                categoryName.get() ?: "TestKategori",
                endDate.get().toString(),
                notifyTime.get().toString(),
                LocalDate.now().toString()
            )
        viewModelScope.launch {
            repository.insert(habit)
        }
    }

    @delegate:Transient
    private val mCallBacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.add(callback)
    }
}

