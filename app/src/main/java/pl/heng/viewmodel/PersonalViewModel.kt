package pl.heng.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.heng.database.DatabaseHeng
import pl.heng.database.model.User
import pl.heng.database.repository.UserRepository


class PersonalViewModel(application: Application) : ViewModel() {

    private val _reporsitory: UserRepository by lazy {
        val userDao = DatabaseHeng.GetDatabase(application).userDao()
        UserRepository(userDao)
    }
    val age = ObservableInt(25)
    val sex = ObservableBoolean(false)
    val sexText = ObservableField<Sex>(Sex.Kobieta)
    val name = ObservableField<String>("")

    fun insert(user : User) = viewModelScope.launch(Dispatchers.IO) {
        _reporsitory.insert(user)
    }

    fun onSexChange() {
        sex.set(!sex.get())
        if (sex.get()) {
            sexText.set(Sex.Mężczyzna)
        } else {
            sexText.set(Sex.Kobieta)
        }
    }

    fun saveUser() {
        insert(User(name.get()!!, age.get(), if (sex.get()) 0 else 1))
    }

    enum class Sex{
        Kobieta, Mężczyzna
    }

    class ViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PersonalViewModel(app) as T
        }
    }
}
