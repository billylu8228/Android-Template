package billy.lu.androidtemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import billy.lu.androidtemplate.tools.FragmentController

abstract class BaseActivity<viewBinding : ViewBinding, viewModel :ViewModel?>: AppCompatActivity() {

    protected lateinit var mViewBinding: viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding(layoutInflater)
        setContentView(mViewBinding.root)
        setupViews()
        observeViewModel()
        commandLine()
    }

    abstract fun setupViews()
    abstract fun observeViewModel()
    abstract fun commandLine()

    abstract fun getViewBinding(layoutInflater: LayoutInflater): viewBinding
    abstract fun getViewModel(): viewModel
    abstract fun getFragmentController(): FragmentController
}