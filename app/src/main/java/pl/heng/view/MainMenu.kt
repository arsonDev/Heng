package pl.heng.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main_menu.*
import pl.heng.R
import pl.heng.fragment.AddTaskFragment
import kotlin.system.exitProcess

class MainMenu : AppCompatActivity() {

    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
//            R.id.navigation_home -> {
//                val fragment = FragmentHabitList()
//                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
//                    .commit()
//                return@OnNavigationItemSelectedListener true
//            }
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
        var bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        setSupportActionBar(bottomAppBar)
        configureBackdrop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onBackPressed() {
        mBottomSheetBehavior?.let {
            if (it.state == BottomSheetBehavior.STATE_EXPANDED) {
                it.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                super.onBackPressed()
            }
        } ?: exitProcess(1)
    }

    private fun configureBackdrop() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_new_task)
        fragment?.let {
            BottomSheetBehavior.from(it.view)?.let { bsb ->
                bsb.state = BottomSheetBehavior.STATE_HIDDEN
                fab.setOnClickListener {
                    val dialog = AddTaskFragment.newInstance()
                    dialog.show(supportFragmentManager,AddTaskFragment::class.java.simpleName)
                }
                mBottomSheetBehavior = bsb
            }

        }
    }

}
