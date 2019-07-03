package pl.heng.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_intro.*
import pl.heng.database.DatabaseHeng
import pl.heng.fragment.AboutAppFragment
import pl.heng.fragment.AboutNewHabitFragment
import pl.heng.fragment.NewHabitBaseSlideFragment
import pl.heng.fragment.PersonalSlideFragment
import pl.heng.view.animations.ZoomOutPageTransformer
import pl.heng.viewmodel.RootViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pl.heng.R.layout.activity_intro)

        val db = Room.databaseBuilder(
            this,
            DatabaseHeng::class.java,
            "databaseHeng"
        ).build()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)

        actionBar?.hide()
        supportActionBar?.hide()
        pager.adapter = pagerAdapter
        pager.setPageTransformer(true, ZoomOutPageTransformer())

    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0)
            super.onBackPressed()
        // show do you want to close the app
        else
            mPager.setCurrentItem(mPager.currentItem - 1, true)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragmentList = listOf(
            AboutAppFragment(),
            PersonalSlideFragment(),
            AboutNewHabitFragment(),
            NewHabitBaseSlideFragment()
        )

        override fun getCount(): Int = fragmentList.size

        override fun getItem(position: Int): Fragment = fragmentList.get(position)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun disableSwipe(vpager: ViewPager){
        vpager.setOnTouchListener { v, event -> true }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun enableSwipe(vpager: ViewPager){
        vpager.setOnTouchListener { v, event -> false }
    }
}
