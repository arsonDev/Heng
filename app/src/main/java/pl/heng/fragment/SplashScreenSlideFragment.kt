package pl.arson_pjoter.heng.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pl.heng.R


class SplashScreenSlideFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflanter = inflater.inflate(R.layout.splash_screen_slide_fragment, container, false)

        return inflanter
    }
}