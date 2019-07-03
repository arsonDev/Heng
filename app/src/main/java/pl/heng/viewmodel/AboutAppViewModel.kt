package pl.heng.viewmodel

import androidx.databinding.ObservableBoolean
import org.jetbrains.anko.doAsync

class AboutAppViewModel : RootViewModel() {

    val isLoading : ObservableBoolean = ObservableBoolean(true)

    init {
        aboutAppViewModel()
    }

    private fun aboutAppViewModel(){
        doAsync {
            // tutaj polaczneie z baza danych i sprawdzenie czy sa dany, jesli sa to otworzyc nowa kart, jesli nie to tutorial

            Thread.sleep(3500)
            isLoading.set(false)
        }
    }
}