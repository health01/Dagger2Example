package com.jasonkst.daggerexample.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.jasonkst.daggerexample.BaseActivity
import com.jasonkst.daggerexample.R
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setToolBar()
        init()

//        Crashes.generateTestCrash()
    }

    private fun init() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setToolBar() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination!!
            .id
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logout -> {
                sessionManager.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                // nav options to clear backstack
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph, true)
                    .build()

                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOptions)
            }

            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen)) {

                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
                }
            }
        }
        //close navigation drawer
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}