package pl.heng.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_intro.*
import pl.heng.R
import pl.heng.fragment.FragmentHabitList
import pl.heng.generated.callback.OnClickListener
import kotlin.system.exitProcess

class MainMenu : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = FragmentHabitList()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
//
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
    }

    override fun onBackPressed() {
        exitProcess(1)
    }
}
