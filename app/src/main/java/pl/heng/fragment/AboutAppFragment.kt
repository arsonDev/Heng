package pl.heng.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.about_slide_fragment.*
import pl.heng.R
import pl.heng.databinding.AboutSlideFragmentBinding
import pl.heng.viewmodel.AboutAppViewModel

class AboutAppFragment : Fragment() {

    private val viewmodel by lazy { ViewModelProviders.of(this).get(AboutAppViewModel::class.java) }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding : AboutSlideFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.about_slide_fragment,container,false)
        binding.viewModel = viewmodel

        return binding.root
    }
}