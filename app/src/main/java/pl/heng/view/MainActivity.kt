package pl.heng.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_intro.*
import pl.heng.R
import pl.heng.fragment.IntroEnd
import pl.heng.database.DatabaseHeng
import pl.heng.fragment.AboutAppFragment
import pl.heng.fragment.AboutNewHabitFragment
import pl.heng.view.animations.FadeOutPageTransformer


class MainActivity : AppCompatActivity() {

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
        tabLayout.setBackgroundColor(Color.TRANSPARENT)
        tabLayout.setupWithViewPager(pager)
        pager.adapter = pagerAdapter
        pager.setPageTransformer(true, FadeOutPageTransformer())
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0)
            super.onBackPressed()
        else
            pager.setCurrentItem(pager.currentItem - 1, true)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragmentList = listOf(
            AboutAppFragment(),
            AboutNewHabitFragment(),
            IntroEnd()
        )

        override fun getCount(): Int = fragmentList.size

        override fun getItem(position: Int): Fragment = fragmentList[position]
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun disableSwipe(vpager: ViewPager){
        vpager.setOnTouchListener { v, event -> true }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun enableSwipe(vpager: ViewPager){
        vpager.setOnTouchListener { v, event -> false }
    }

    private fun onClickNextButton() {
        if (pager.currentItem < (pager.adapter as ScreenSlidePagerAdapter).fragmentList.size-1)
            pager.setCurrentItem(pager.currentItem + 1, true)
        else if (pager.currentItem == (pager.adapter as ScreenSlidePagerAdapter).fragmentList.size-1) {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }
}
