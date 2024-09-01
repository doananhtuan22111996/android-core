package vn.core.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import timber.log.Timber
import vn.core.domain.ResultModel
import vn.core.ext.setupViewClickHideKeyBoard
import vn.core.ui.base.databinding.BaseActivityBinding

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val viewModel: VM

    protected lateinit var viewBinding: BaseActivityBinding
        private set

    abstract fun onInit(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = BaseActivityBinding.inflate(layoutInflater)
        super.setContentView(viewBinding.root)
        window?.setupViewClickHideKeyBoard(viewBinding.root)
        onInit(savedInstanceState)
        bindViewModel()
    }

    @CallSuper
    open fun bindViewModel() {
        viewModel.loadingOverlay.observe(this, this::executeLoadingOverlay)
        viewModel.exception.observe(this, this::executeAppException)
    }

    private fun executeAppException(appException: ResultModel.AppException?) {
        appException?.run {
            Timber.d("executeAppException: ${this.message}")
            Toast.makeText(this@BaseActivity, this.message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    private fun executeLoadingOverlay(isLoading: Boolean) {
        Timber.d("executeLoadingOverlay: $isLoading")
        viewBinding.layoutLoading.rlProgressbar.isVisible = isLoading
    }
}