package pl.heng.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.heng.R
import pl.heng.databinding.SlideFragmentAboutAppBinding
import pl.heng.viewmodel.AboutAppViewModel

class AboutAppFragment : Fragment() {

    val viewmodel by lazy { ViewModelProviders.of(this).get(AboutAppViewModel::class.java) }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: SlideFragmentAboutAppBinding =
            DataBindingUtil.inflate(inflater, R.layout.slide_fragment_about_app, container, false)
        binding.viewModel = viewmodel
        return binding.root
    }
}