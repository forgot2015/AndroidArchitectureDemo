package com.linzongfu.myarchitecture.mvc

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.linzongfu.myarchitecture.R
import com.linzongfu.myarchitecture.databinding.ActivityMvcBinding

/**
 * @desc   在 MVC 架构中,Activity 作为 controller 层, layout 页面作为 view 层, Model 作为 model 层
 * 使用 ViewBinding来替换 findViewById,不必再用 ButterKnife 或者 Kotlin Extension了
 * 与 DataBinding 相比,ViewBinding 加载页面更快,但缺点是没法实现数据绑定
 * 总结,如果是写 MVP 架构的话,用 ViewBinding 即可; 如果是写 MVVM 架构,就要用 DataBinding,
 * 部分不用到数据绑定的页面也可以用 ViewBinding
 * @author zongfulin
 * @date   4/25/21 10:45 AM
 */
class MvcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvcBinding

    private lateinit var model: MvcModel

    private var llSucceed: LinearLayout? = null
    private lateinit var rvRecord: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        使用 ViewBinding
        binding = ActivityMvcBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        model = MvcModel()

        val includeTitle = binding.includeTitle.tvTitle
        includeTitle.text = "用了 ViewBinding 与 include 标签的 MVC 架构"

        binding.btnLogin.setOnClickListener {
            val account = binding.etAccount.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            tryLogin(account, password)
        }
    }

    private fun tryLogin(account: String, password: String) {
        if (!isAccountValid(account)) {
            Snackbar.make(binding.etAccount, "账号非法", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (isPasswordValid(password)) {
            Snackbar.make(binding.etAccount, "密码非法", Snackbar.LENGTH_SHORT).show()
            return
        }

        showLoginSucceed()
    }

    private fun isAccountValid(account: String): Boolean =
        !TextUtils.isEmpty(account) && account.length >= 6

    private fun isPasswordValid(password: String): Boolean =
        !TextUtils.isEmpty(password) && password.length >= 6

    private fun showLoginSucceed() {
        if (llSucceed == null) {
            inflateViewStub()
            initRecycler()
            loadRecyclerData(model.getRecordList())
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
        rvRecord.adapter = MvcAdapter(ArrayList())
        rvRecord.layoutManager = LinearLayoutManager(this)
    }

    private fun loadRecyclerData(list: List<MvcRecord>) {
        val adapter = rvRecord.adapter as MvcAdapter
        adapter.replaceList(list)
    }

}