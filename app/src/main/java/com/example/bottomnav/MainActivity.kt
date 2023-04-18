package com.example.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.bottomnav.databinding.ActivityMainBinding
import com.example.bottomnav.fragments.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.topAppBar)

        supportFragmentManager.beginTransaction().replace(R.id.contentFragment, HomeFragment()).commit()
        binding?.nav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ItemBottomNavHome -> supportFragmentManager.beginTransaction().replace(R.id.contentFragment, HomeFragment()).commit()
                R.id.ItemBottomNavCatalog -> supportFragmentManager.beginTransaction().replace(R.id.contentFragment, ShopCatalogFragment()).commit()
                R.id.ItemBottomNavDelivery -> supportFragmentManager.beginTransaction().replace(R.id.contentFragment, DeliveryFragment()).commit()
                R.id.ItemBottomNavContacts -> supportFragmentManager.beginTransaction().replace(R.id.contentFragment, ContactsFragment()).commit()
            }
            return@setOnItemSelectedListener true
        }


        binding?.topAppBar?.setOnMenuItemClickListener{ menuItem: MenuItem ->
            when(menuItem.itemId) {
                R.id.topNavFavorite -> {supportFragmentManager.beginTransaction().replace(R.id.contentFragment, FavoriteFragment()).commit()
                true}
                R.id.topNavSetting -> {supportFragmentManager.beginTransaction().replace(R.id.contentFragment, SettingFragment()).commit()
                true}
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val mainMenu = MainMenu()
                mainMenu.show(
                    supportFragmentManager,
                    "main_menu"
                )
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

}