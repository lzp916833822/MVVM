package org.lico.core.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * @author: lzp
 * @Desc:
 */
abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel, DATA : Any?> : Fragment() {

    lateinit var mContext: Context
    var viewModels: VM? = null
    lateinit var binding: V
    var datas: DATA? = null
    protected abstract fun getViewModel(): VM?
    protected abstract fun getData(): DATA?
    protected abstract fun getVariableId(): Int
    protected abstract fun getViewBinding(): V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mContext = activity as Context
        intiBinding()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun readyGo(clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }

    private fun intiBinding() {

        if (viewModels == null) {
            viewModels = getViewModel()
        }
        if (datas == null) {
            datas = getData()
        }

        binding.setVariable(getVariableId(), datas)
    }

    @LayoutRes
    protected abstract fun layoutId(): Int
    protected abstract fun initData()
}