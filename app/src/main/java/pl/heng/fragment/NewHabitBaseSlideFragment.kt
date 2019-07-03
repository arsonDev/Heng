package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.heng.R
import pl.heng.databinding.SlideFragmentHabitCreateBinding
import pl.heng.viewmodel.NewHabitBaseViewModel

class NewHabitBaseSlideFragment : Fragment() {

    val viewModel : NewHabitBaseViewModel by lazy { ViewModelProviders.of(this).get(NewHabitBaseViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : SlideFragmentHabitCreateBinding = DataBindingUtil.inflate(inflater, R.layout.slide_fragment_habit_create,container,false);
        binding.vm = viewModel

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        //todo: dodac warunek zeby to uzupelnic lub wstawic tylko raz i tylko jesli dane sa uzupelnione, w innym wypadku blad lub powiadomienie
        viewModel.createHabit()
    }
}