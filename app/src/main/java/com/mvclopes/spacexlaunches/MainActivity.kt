package com.mvclopes.spacexlaunches

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.mvclopes.spacexlaunches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filter_menu_items, menu)
        return true
    }
}
