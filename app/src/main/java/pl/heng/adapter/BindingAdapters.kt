package pl.heng.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import kotlinx.android.synthetic.main.about_slide_fragment.*
import pl.heng.R

object BindingAdapters {
    @BindingAdapter("setVisibility")
    @JvmStatic
    fun setVisibility(v : View){
        val animFadeIn = AnimationUtils.loadAnimation(v.context, R.anim.fade_in)
        animFadeIn.duration = 2000
        animFadeIn.fillAfter = true

        v.startAnimation(animFadeIn)
    }
}