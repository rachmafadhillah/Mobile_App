package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.uts.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var  binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("Nama")
        val message1 = intent.getStringExtra("Email")
        val message2 = intent.getStringExtra("NIM")
        val message3 = intent.getStringExtra("Mata Kuliah")


        val namalengkap = findViewById<TextView>(R.id.tv_1).apply {

            text = message
        }
        val email = findViewById<TextView>(R.id.tv_2).apply {

            text = message1
        }
        val nim = findViewById<TextView>(R.id.tv_3).apply {

            text = message2
        }
        val matakuliah = findViewById<TextView>(R.id.tv_4).apply {

            text = message3
        }


        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, simpan_profile::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

    }
    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to log out?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        alertDialog.setNegativeButton("No") { _, _ ->

        }
        alertDialog.show()
    }

}