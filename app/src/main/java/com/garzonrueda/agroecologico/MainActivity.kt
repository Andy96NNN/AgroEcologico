package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.garzonrueda.agroecologico.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setup()
    }

    private fun setup() {

        viewBinding.button4.setOnClickListener {
            goAdminLogin()
        }

        viewBinding.button5.setOnClickListener {
            goSellerLogin()
        }

        viewBinding.btnSearchRole.setOnClickListener {

            val input = viewBinding.etUsername.text

            if (input.isNotEmpty()) {

                val db = FirebaseDatabase.getInstance().reference

                db.child("Users").child(input.toString()).get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, it.result?.child("rol")?.value.toString(), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "fallido", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Ingrese nombre de usuario", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun goSellerLogin() {
        val sellerLoginIntent = Intent(this, SellerAuthActivity::class.java).apply {

        }
        startActivity(sellerLoginIntent)
    }

    private fun goAdminLogin() {
        val adminLoginIntent = Intent(this, AdminAuthActivity::class.java).apply {

        }
        startActivity(adminLoginIntent)
    }
}