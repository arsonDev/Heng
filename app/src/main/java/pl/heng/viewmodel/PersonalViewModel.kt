package pl.heng.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.heng.database.DatabaseHeng
import pl.heng.database.model.User
import pl.heng.database.repository.UserRepository


class PersonalViewModel(application: Application) : AndroidViewModel(application) {

    val sex = ObservableBoolean(false)
    val sexText = ObservableField<Sex>(Sex.Kobieta)
    private val _reporsitory : UserRepository

    init {
        val userDao = DatabaseHeng.GetDatabase(application).userDao()
        _reporsitory = UserRepository(userDao)
    }

    fun insert(user : User) = viewModelScope.launch(Dispatchers.IO) {
        _reporsitory.insert(user)
    }
    ""
    fun onSexChange() {
        sex.set(!sex.get())
        if (sex.get()) {
            sexText.set(Sex.Mężczyzna)
        } else {
            sexText.set(Sex.Kobieta)
        }
    }

    enum class Sex{
        Kobieta, Mężczyzna
    }
}
