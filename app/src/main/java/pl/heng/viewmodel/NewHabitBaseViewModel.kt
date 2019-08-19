package pl.heng.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableByte
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.heng.R
import pl.heng.database.DatabaseHeng
import pl.heng.database.model.Habit
import pl.heng.database.repository.HabitRepository
import pl.heng.view.MainMenu
import java.util.*

@Suppress("UNCHECKED_CAST")
class NewHabitBaseViewModel(application: Application) : RootViewModel() {

    val notifyHour : ObservableField<String> = ObservableField("")
    val dateEnd : ObservableField<String> = ObservableField("")
    val isEnd : ObservableBoolean = ObservableBoolean((((dateEnd.get() != "") && (notifyHour.get() != "")) && (notifyHour.get() != "")))
    val name : ObservableField<String> = ObservableField("")
    val desc : ObservableField<String> = ObservableField("")
    val countOfWeek : ObservableByte = ObservableByte(0)
    private val calendar = Calendar.getInstance()

    private val _reporsitory: HabitRepository by lazy {
        HabitRepository(application)
    }

    fun onClickEndDate(v : View){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(v.context, R.style.DialogTheme, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateEnd.set(String.format("%d-%d-%d",dayOfMonth,month,year))
        }, year, month, day)
        calendar.add(Calendar.YEAR,3)
        dpd.datePicker.maxDate = calendar.timeInMillis
        calendar.add(Calendar.YEAR,-9)
        dpd.datePicker.minDate = calendar.timeInMillis

        dpd.show()
    }

    fun onClickNotifyHour(v : View){
        TimePickerDialog(v.context, R.style.DialogTheme, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            notifyHour.set("$hourOfDay:$minute")}
            ,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
    }

    fun startNewActivity(v : View){
        v.context.startActivity(Intent(v.context, MainMenu::class.java))
    }

    fun createHabit() {
//        insert(Habit(name.get()!!, desc.get()!!, countOfWeek.get(), dateEnd.get()!!, notifyHour.get()!!))
    }

    private fun insert(habit: Habit) = viewModelScope.launch {
        _reporsitory.insert(habit)
    }

    class ViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewHabitBaseViewModel(app) as T
        }
    }
}