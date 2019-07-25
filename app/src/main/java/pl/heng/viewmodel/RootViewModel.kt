package pl.heng.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class RootViewModel : ViewModel() {
    val IsCorrect : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>().apply { value = false } }
}