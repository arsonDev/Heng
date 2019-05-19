package pl.heng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pl.heng.R

class AboutSlideFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.about_slide_fragment, container, false
        )
        val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fab = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab?.startAnimation(animFadeIn)
        return view
    }
}