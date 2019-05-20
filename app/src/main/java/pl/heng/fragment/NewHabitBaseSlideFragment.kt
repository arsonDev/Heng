package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import pl.heng.R
import pl.heng.databinding.HabitCreateSlideFragmentBinding
import pl.heng.viewmodel.NewHabitBaseViewModel

class NewHabitBaseSlideFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding : HabitCreateSlideFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.habit_create_slide_fragment,container,false);
        binding.vm = NewHabitBaseViewModel()

        return binding.getRoot()
    }
}