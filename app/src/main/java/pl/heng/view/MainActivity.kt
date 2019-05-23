package pl.heng.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import pl.heng.R
import pl.heng.database.DatabaseHeng
import pl.heng.fragment.AboutNewHabitFragment
import pl.heng.fragment.AboutAppFragment
import pl.heng.fragment.NewHabitBaseSlideFragment
import pl.heng.fragment.PersonalizationScreenSlideFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            DatabaseHeng::class.java,
            "databaseHeng"
        ).build()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager = findViewById(R.id.pager)
        actionBar?.hide()
        supportActionBar?.hide()
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        floatingActionButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                runNextSlide()
            }
        })
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

    fun runNextSlide(){
        if (mPager.currentItem < mPager.childCount)
            mPager.setCurrentItem(mPager.currentItem+1,true)
        else
//            startActivity();
            Toast.makeText(this,"Start new activity",Toast.LENGTH_SHORT).show()
    }
}
