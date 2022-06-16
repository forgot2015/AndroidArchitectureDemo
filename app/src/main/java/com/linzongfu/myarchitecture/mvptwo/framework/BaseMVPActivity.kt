package com.waisongwang.share.mvp

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.ToastUtils
import com.linzongfu.myarchitecture.R
import com.waisongwang.share.R
import com.waisongwang.share.view.LoadingDialog
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author zongfulin
 * Date: 2021-05-20
 * Time: 10:02
 * Description:
 */
/**
 * 功能：Activity的基类，封装一些通用方法
 * 描述：
 *
 * @author Lin Zongfu
 * @date 2020/03/19
 */
abstract class BaseMVPActivity<T : IPresenter> : AppCompatActivity(), IView {

    protected lateinit var presenter: T

    /**
     * 数据加载对话框
     */
    protected var loadingDialog: LoadingDialog? = null

    /**
     * 顶部栏标题
     */
    protected var toolbarTitle: TextView? = null

    /**
     * 顶部栏
     */
    protected var toolbar: ConstraintLayout? = null

    protected var compositeDisposable: CompositeDisposable? = null

    /**
     * 界面布局id
     *
     * @return 返回布局id
     */
    @get:LayoutRes
    protected abstract val layoutResId: Int

    /**
     * 初始化页面标题
     *
     * @return 返回标题名字
     */
    @get:StringRes
    protected abstract val titleResId: Int

    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //去除主题默认背景色
        this.window.setBackgroundDrawable(null)

        if (layoutResId != 0) {
            setContentView(layoutResId)
        }

        presenter = createPresenter()

        beforeInitToolBar()

        if (hasToolBar()) {
            initToolBar()
        }

        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }

        initView()
        initData()
    }

    //    在初始化顶部标题栏之前执行,如果要取消标题里,可以重写本方法
    protected open fun beforeInitToolBar() {

    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()

        loadingDialog?.dismiss()
        compositeDisposable?.clear()
        presenter.destroy()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            //重写左上角返回键功能
//            android.R.id.home -> finish()
//            else -> {
//            }
//        }
//        return true
//    }

    /**
     * 解决点击EditText点击外部区域软键盘隐藏
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            //需要隐藏软键盘
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    /**
     * 判断当前点击的位置是否为EditText
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        //如果点击的view是EditText
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return (event.x <= left || event.x >= right
                    || event.y <= top || event.y >= bottom)
        }
        return false
    }

    protected var toolbarFlag = true

    /**
     * 是否有toolBar栏
     *
     * @return
     */
    protected open fun hasToolBar(): Boolean {
        return toolbarFlag
    }

    /**
     * 初始化view
     */
    protected abstract fun initView()

    /**
     * 初始化data
     */
    protected abstract fun initData()

    /**
     * 通用的网络加载失败提示
     */
    protected fun showNetworkError() {
        ToastUtils.showShort(getString(R.string.api_error_network_exception))
    }

    /**
     * 通用的操作成功提示
     */
    protected fun showOperationSuccess() {
        ToastUtils.showShort(getString(R.string.operate_success))
    }

    protected fun showToastMsg(msg: String?) {
        ToastUtils.showShort(msg)
    }

    protected fun showToastMsg(strRes: Int?) {
        ToastUtils.showShort(getText(strRes!!))
    }

    protected fun showHttpFailed(msg: String) {
        ToastUtils.showShort(getString(R.string.operate_failed) + msg)
//        ToastUtils.showShort("操作失败：$msg")
    }

    protected fun showToastLong(msg: String) {
        ToastUtils.showLong(msg)
    }

//    protected fun showApiErrorMsg(errorCode: Int) {
//        ToastUtils.showShort(NetUtils.getErrorCodeMsg(errorCode))
//    }

    /**
     * @param view view必须是VISIBLE的EditText，如果不是VISIBLE的，需要先将其设置为VISIBLE。
     * 当前界面必须已经加载完成，不能直接在Activity的onCreate()，onResume()，onAttachedToWindow()中使用，
     * 可以在这些方法中通过postDelayed的方式来延迟执行showSoftInput()。
     */
    protected fun showSoftInput(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        imm.showSoftInput(view, 0)
    }

    /**
     * 强制隐藏软键盘
     * 在dialog.dismiss()前执行
     *
     * @param view 推荐类型是EditText，view可以当前布局中已经存在的任何一个View，如果找不到可以用getWindow().getDecorView()。
     */
    protected fun hideSoftInput(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    var tvBack: ImageView? = null

    /**
     * 配置顶部toolbar
     */
    private fun initToolBar() {
        toolbar = findViewById(R.id.clTitle)
        toolbarTitle = findViewById(R.id.tvTitle)
//        不一定有返回按钮,如果是 include_title 布局,就没有返回键
        tvBack = findViewById(R.id.tvBack)

        toolbarTitle?.setText(titleResId)
        tvBack?.setOnClickListener { finish() }
//
//        setSupportActionBar(toolbar)
//        if (supportActionBar != null) {
//            //由传过来的intent来决定显示哪个标题
//            if (toolbarTitle != null) {
//                toolbarTitle!!.setText(titleResId)
//            }
//            //显示左上角返回图标
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//            //隐藏toolbar的标题栏, 因为默认是靠左显示的，需求是要居中显示
//            supportActionBar!!.setDisplayShowTitleEnabled(false)
//        }
    }

    //    重新设置左上角返回键要返回的页面?
//    protected fun setBackClickFinishTo(activity: Activity) {
//        tvBack?.setOnClickListener { ActivityUtils.finishToActivity(activity, false) }
//    }

    protected fun showLoadingDialog() {
        runOnUiThread {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog(this)
            }
            loadingDialog?.show()
        }
    }

    protected fun hideLoadingDialog() {
        runOnUiThread { loadingDialog?.dismiss() }
    }

    companion object {
        const val TAG = "BaseActivity"
        val DEBUG = true
    }

}