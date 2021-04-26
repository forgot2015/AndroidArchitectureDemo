package com.linzongfu.myarchitecture.mvp

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.linzongfu.myarchitecture.R
import com.linzongfu.myarchitecture.databinding.ActivityMvpBinding

/**
 * @desc   在 MVP 架构中,Activity 作为 view 层处理页面变化, Presenter 页面作为 p 层处理业务逻辑, Model 作为 model 层
 *
 * @author zongfulin
 * @date   4/25/21 11:11 AM
 */
class MvpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvpBinding

    private lateinit var presenter: MvpPresenter

    private var llSucceed: LinearLayout? = null
    private lateinit var rvRecord: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        使用 ViewBinding
        binding = ActivityMvpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = MvpPresenter(this)

        binding.btnLogin.setOnClickListener {
            val account = binding.etAccount.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            presenter.tryLogin(account, password)
        }
    }

    fun showError(error: String) {
        Snackbar.make(binding.etAccount, error, Snackbar.LENGTH_SHORT).show()
    }

    fun showLoginSucceed() {
        if (llSucceed == null) {
            inflateViewStub()
            initRecycler()
            presenter.loadRecyclerData()
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
        rvRecord.adapter = MvpAdapter(ArrayList())
        rvRecord.layoutManager = LinearLayoutManager(this)
    }

    fun loadRecyclerData(list: List<MvpRecord>) {
        val adapter = rvRecord.adapter as MvpAdapter
        adapter.replaceList(list)
    }

}