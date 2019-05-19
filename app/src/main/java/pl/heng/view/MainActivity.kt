package pl.heng.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import pl.arson_pjoter.heng.view.fragment.SplashScreenSlideFragment
import pl.heng.R

private const val NUM_PAGES = 5;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        val mPager = findViewById<ViewPager>(R.id.pager)
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(true, DepthPageTransformer())
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = SplashScreenSlideFragment()
    }
}
