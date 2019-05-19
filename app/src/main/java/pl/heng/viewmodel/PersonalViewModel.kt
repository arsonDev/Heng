package pl.heng.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

class PersonalViewModel(application: Application) : AndroidViewModel(application) {

    val sex = ObservableBoolean(false)
    var sexText = Sex.Kobieta;

    fun onSexChange(){
        sex.set(!sex.get())
        sexText = Sex.valueOf(Sex.values().equals(sexText).not().toString())
    }

    enum class Sex{
        Kobieta, Mężczyzna
    }
}
