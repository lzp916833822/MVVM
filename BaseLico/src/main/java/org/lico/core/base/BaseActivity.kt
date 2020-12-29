package org.lico.core.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author: lzp
 * @Desc:
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel, DATA : Any?> :
    AppCompatActivity() {
    lateinit var mContext: Context

    var viewModels: VM? = null
    var binding: V? = null
    var datas: DATA? = null
    protected abstract fun getViewModel(): VM?
    protected abstract fun getData(): DATA?
    protected abstract fun getVariableId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        super.onCreate(savedInstanceState)
        intiBinding()
        initData()
        intiView()
    }

    private fun intiBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId())
        if (viewModels == null) {
            viewModels = getViewModel()
        }

        if (datas == null) {
            datas = getData()
        }

        binding?.setVariable(getVariableId(), datas)
    }


    @LayoutRes
    protected abstract fun layoutId(): Int
    protected abstract fun initData()
    protected abstract fun intiView()


    protected fun readyGo(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    fun backClick(view: View) {
        this.finish()
    }

}