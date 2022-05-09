package billy.lu.androidtemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM :ViewModel?>: AppCompatActivity() {

    private lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding(layoutInflater)
        setContentView(mBinding.root)
        setupViews()
        observeViewModel()
        commandLine()
    }

    abstract fun setupViews()
    abstract fun observeViewModel()
    abstract fun commandLine()

    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB
    abstract fun getViewModel(): VM
}