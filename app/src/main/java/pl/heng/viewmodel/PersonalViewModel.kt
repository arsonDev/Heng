package pl.heng.viewmodel

import android.database.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {

    val sex = ObservableBoolean(false)
    var sexText = ObservableField<Sex>(Sex.Kobieta)

    fun onSexChange() {
        sex.set(!sex.get())
        if (sex.get()) sexText.set(Sex.Mężczyzna) else sexText.set(Sex.Kobieta)
    }

    enum class Sex{
        Kobieta, Mężczyzna
    }
}
