package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.heng.R
import pl.heng.viewmodel.PersonalViewModel

class PersonalizationScreenSlideFragment : Fragment() {

    private lateinit var model : PersonalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(
            R.layout.personal_slide_fragment, container, false
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(PersonalViewModel(application)::class.java)
        }?: throw Exception("Invalid Viewmodel")
    }
}