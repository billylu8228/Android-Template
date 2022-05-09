package billy.lu.androidtemplate

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import billy.lu.androidtemplate.base.BaseActivity
import billy.lu.androidtemplate.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {



    override fun setupViews() {

    }

    override fun observeViewModel() {

    }

    override fun commandLine() {

    }

    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }


}