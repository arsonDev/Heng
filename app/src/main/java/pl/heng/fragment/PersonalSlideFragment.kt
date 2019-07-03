package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.heng.R
import pl.heng.databinding.SlideFragmentPersonalBinding
import pl.heng.viewmodel.PersonalViewModel

class PersonalSlideFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this,PersonalViewModel.ViewModelFactory(this.requireActivity().application)).get(PersonalViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: SlideFragmentPersonalBinding =
            DataBindingUtil.inflate(inflater, R.layout.slide_fragment_personal, container, false)

        binding.vm = viewModel

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveUser()
    }
}