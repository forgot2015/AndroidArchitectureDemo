package com.linzongfu.myarchitecture.mvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.linzongfu.myarchitecture.R
import com.linzongfu.myarchitecture.databinding.ActivityMvvmBinding

/**
 * @desc   在 MVVM 架构中,Activity 作为 view 层处理页面变化, ViewModel 页面作为 VM 层处理业务逻辑, Model 作为 M 层
 * 
 * @author zongfulin
 * @date   4/25/21 4:39 PM
 */
class MvvmActivity : AppCompatActivity() {
    val TAG = "MvvmActivity"
    lateinit var viewModel: MvvmViewModel
    lateinit var binding: ActivityMvvmBinding

    var llSucceed: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_mvvm)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        viewModel = MvvmViewModel(this)
//        binding.user = User("", "")

        binding.btnLogin.setOnClickListener {
            Log.e(TAG, "account =${binding.account},password = ${binding.password}")
            viewModel.tryLogin(binding.account, binding.password)
        }
    }

    public fun showError(error: String) {
        Snackbar.make(binding.etAccount, error, Snackbar.LENGTH_SHORT).show()
    }

    public fun showLoginSucceed() {
        if (llSucceed == null) {
            inflateViewStub()
        }

        binding.llLogin.visibility = View.INVISIBLE
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