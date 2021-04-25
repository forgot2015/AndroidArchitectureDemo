package com.linzongfu.myarchitecture.mvc

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
 * @desc   在 MVC 架构中,Activity 作为 controller 层, layout 页面作为 view 层, Model 作为 model 层
 *
 * @author zongfulin
 * @date   4/25/21 10:45 AM
 */
class MvcActivity : AppCompatActivity() {

    lateinit var llLogin: LinearLayout
    lateinit var etAccount: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button

    var llSucceed: LinearLayout? = null

    lateinit var model: MvcModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)

        llLogin = findViewById(R.id.llLogin)
        etAccount = findViewById(R.id.etAccount)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val account = etAccount.text.toString().trim()
            val password = etPassword.text.toString().trim()
            tryLogin(account, password)
        }

        model = MvcModel()

    }

    private fun tryLogin(account: String, password: String) {
        if (!model.isAccountValid(account)) {
            Snackbar.make(etAccount, "账号非法", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (!model.isPasswordValid(password)) {
            Snackbar.make(etAccount, "密码非法", Snackbar.LENGTH_SHORT).show()
            return
        }

        showLoginSucceed()
    }

    private fun showLoginSucceed() {
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