package pl.heng.viewmodel

import android.app.TaskStackBuilder
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import kotlin.concurrent.timerTask

class AboutAppViewModel : ViewModel() {
    // tutaj polaczenie z modelem i utworzenie lub wczytanie bazy danych, jesli dane beda istnialy po slash screen nie
    // bedzie dalej ekranow ale juz main activity
    val isLoading : ObservableBoolean = ObservableBoolean(true)

    fun AboutAppViewModel(){
        Thread.sleep(3000)
        isLoading.set(true)
    }
}