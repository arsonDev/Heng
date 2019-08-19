package pl.heng.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import pl.heng.R
import pl.heng.adapter.HabitListAdapter
import pl.heng.database.model.Habit
import pl.heng.fragment.AddTaskFragment
import pl.heng.viewmodel.MainMenuViewModel
import kotlin.coroutines.CoroutineContext
import kotlin.system.exitProcess


class MainMenu : AppCompatActivity(), CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    private lateinit var mainMenuViewModel: MainMenuViewModel


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
        mJob = Job()
        setContentView(R.layout.activity_main_menu)

        var bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        setSupportActionBar(bottomAppBar)
        configureBackdrop()

        val adapter = HabitListAdapter(applicationContext)
        recycleList.adapter = adapter
        recycleList.layoutManager = LinearLayoutManager(applicationContext)
        mainMenuViewModel = ViewModelProviders.of(this).get(MainMenuViewModel::class.java)
        mainMenuViewModel.getAllHabits().observe(this,
            Observer<List<Habit>> {
                    t -> adapter.setHabits(t)
            })
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
