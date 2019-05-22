package pl.heng.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import pl.heng.R
import pl.heng.fragment.AboutNewHabitFragment
import pl.heng.fragment.AboutAppFragment
import pl.heng.fragment.NewHabitBaseSlideFragment
import pl.heng.fragment.PersonalizationScreenSlideFragment

private const val NUM_PAGES = 5;

class MainActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager = findViewById(R.id.pager)
        actionBar?.hide()
        supportActionBar?.hide()
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
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
            PersonalizationScreenSlideFragment(),
            AboutNewHabitFragment(),
            NewHabitBaseSlideFragment()
        )

        override fun getCount(): Int = fragmentList.size

        override fun getItem(position: Int): Fragment = fragmentList.get(position)
    }
}
