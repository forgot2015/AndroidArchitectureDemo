package com.linzongfu.myarchitecture.mvp

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.linzongfu.myarchitecture.R

/**
 * @desc   在 MVP 架构中,Activity 作为 view 层处理页面变化, Presenter 页面作为 p 层处理业务逻辑, Model 作为 model 层
 *
 * @author zongfulin
 * @date   4/25/21 11:11 AM
 */
class MvpActivity : AppCompatActivity() {
    
    lateinit var llLogin: LinearLayout
    lateinit var etAccount: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button

    var llSucceed: LinearLayout? = null

    lateinit var presenter: MvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        presenter = MvpPresenter(this)

        llLogin = findViewById(R.id.llLogin)
        etAccount = findViewById(R.id.etAccount)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val account = etAccount.text.toString().trim()
            val password = etPassword.text.toString().trim()
            presenter.tryLogin(account, password)
        }
    }

    public fun showError(error: String) {
        Snackbar.make(etAccount, error, Snackbar.LENGTH_SHORT).show()
    }

    public fun showLoginSucceed() {
        if (llSucceed == null) {
            inflateViewStub()
        }

        llLogin.visibility = View.INVISIBLE
        llSucceed?.visibility = View.VISIBLE
    }

    private fun inflateViewStub() {
        try {
            val vsSucceed = findViewById<ViewStub>(R.id.vsSucceed)
            llSucceed = vsSucceed.inflate() as LinearLayout
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}