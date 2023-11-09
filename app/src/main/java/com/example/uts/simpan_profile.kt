package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts.databinding.ActivitySimpanProfileBinding
import com.example.uts.databinding.ActivityProfileBinding

class simpan_profile : AppCompatActivity() {
    private lateinit var binding: ActivitySimpanProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpanProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEdit.setOnClickListener {
            callActivity()

        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }

    private fun callActivity(){
        val message = binding.nama2Profile.text.toString()
        val message1 = binding.email2Profile.text.toString()
        val message2 = binding.nim2Profile.text.toString()
        val message3 = binding.mk2Profile.text.toString()

        val intent = Intent(this, Profile::class.java).also {
            it.putExtra("Nama", message)
            it.putExtra("Email", message1)
            it.putExtra("NIM", message2)
            it.putExtra("Mata Kuliah", message3)
            startActivity(it)
        }
    }
}