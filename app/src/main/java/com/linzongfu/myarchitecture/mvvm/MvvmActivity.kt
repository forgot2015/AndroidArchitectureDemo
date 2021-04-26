package com.linzongfu.myarchitecture.mvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val TAG = "MvvmActivity"
    private lateinit var viewModel: MvvmViewModel
    private lateinit var binding: ActivityMvvmBinding

    private var llSucceed: LinearLayout? = null
    private lateinit var rvRecord: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        binding.isNight = true
        binding.account = "default"

        viewModel = MvvmViewModel(this)

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
            initRecycler()
            viewModel.loadRecyclerData()
        }

        binding.llLogin.visibility = View.INVISIBLE
        llSucceed?.visibility = View.VISIBLE
    }

    private fun inflateViewStub() {
        try {
            val vsSucceed = findViewById<ViewStub>(R.id.vsSucceed)
            val view = vsSucceed.inflate()
            llSucceed = view.findViewById(R.id.llSucceed)
            rvRecord = view.findViewById(R.id.rvRecord)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initRecycler() {
        rvRecord.adapter = MvvmAdapter(ArrayList())
        rvRecord.layoutManager = LinearLayoutManager(this)
    }

    fun loadRecyclerData(list: List<MvvmRecord>) {
        val adapter = rvRecord.adapter as MvvmAdapter
        adapter.replaceList(list)
    }
}