package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.heng.R
import pl.heng.databinding.PersonalSlideFragmentBinding
import pl.heng.viewmodel.PersonalViewModel

class PersonalizationScreenSlideFragment : Fragment() {

    private val viewmodel by lazy { ViewModelProviders.of(this).get(PersonalViewModel::class.java) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: PersonalSlideFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.personal_slide_fragment, container, false)
        binding.vm = viewmodel
        return binding.root
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        model = activity?.run {
//            ViewModelProviders.of(this).get(PersonalViewModel()::class.java)
//        }?: throw Exception("Invalid Viewmodel")
//        super.onCreate(savedInstanceState)
//    }
}