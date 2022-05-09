package billy.lu.androidtemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<viewBinding: ViewBinding, viewModel: ViewModel>: Fragment() {

    protected val TAG = "####"

    protected lateinit var mBinding: viewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = getViewBinding(inflater, container)
        setupViews()
        observeViewModel()
        commandLine()
        return mBinding.root
    }

    abstract fun setupViews()
    abstract fun observeViewModel()
    abstract fun commandLine()
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): viewBinding
    abstract fun getViewModel(): viewModel
}