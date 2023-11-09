package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uts.databinding.ActivityLoginBinding
import com.example.uts.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(fragment_calculator())

        // definisi widget
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomnavview)
        bottomnav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.bot_menu_calculator -> {
                    loadFragment(fragment_calculator())
                    true
                }

                R.id.bot_menu_suhu -> {
                    loadFragment(fragment_suhu())
                    true
                }

                R.id.bot_menu_user-> {
                    loadFragment(fragment_bmi())
                    true
                }

                R.id.bot_menu_money-> {
                    loadFragment(fragment_uang())
                    true
                }

                else -> {false}
            }
        }
        binding.iv1.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container,fragment)
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        transaction.commit()
    }
}