package pl.heng.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import pl.heng.R

class PersonalViewModel : ViewModel() {

    val sex = ObservableBoolean(false)
    val sexText = ObservableField<Sex>(Sex.Kobieta)

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
