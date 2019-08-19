package pl.heng.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.database.DatabaseHeng
import pl.heng.database.model.Habit
import pl.heng.database.repository.HabitRepository

class AddTaskViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Bindable
    val taskName = MutableLiveData<String>().apply { value = "" }
    @Bindable
    val categoryName = MutableLiveData<String>().apply { value = "" }
    @Bindable
    val endDate = MutableLiveData<String>().apply { value = "20-05-19" }
    @Bindable
    val notifyTime = MutableLiveData<String>().apply { value = "10:30" }
    @Bindable
    val countDay = MutableLiveData<Int>().apply { value = 4 }

    private val app = application
    private val repository by lazy { HabitRepository(application) }

    fun addNewTask() {
        val habit =
            Habit(taskName.value ?: "", categoryName.value!!, countDay.value!!, endDate.value!!, notifyTime.value!!)
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

