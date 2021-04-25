package com.linzongfu.myarchitecture

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.linzongfu.myarchitecture.mvc.MvcActivity
import com.linzongfu.myarchitecture.mvp.MvpActivity
import com.linzongfu.myarchitecture.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMvc = findViewById<Button>(R.id.btnMvc)
        val btnMvp = findViewById<Button>(R.id.btnMvp)
        val btnMvvm = findViewById<Button>(R.id.btnMvvm)

        btnMvc.setOnClickListener {
            startActivity(Intent(this@MainActivity, MvcActivity::class.java))
        }
        btnMvp.setOnClickListener {
            startActivity(Intent(this@MainActivity, MvpActivity::class.java))
        }
        btnMvvm.setOnClickListener {
            startActivity(Intent(this@MainActivity, MvvmActivity::class.java))
        }
    }

}