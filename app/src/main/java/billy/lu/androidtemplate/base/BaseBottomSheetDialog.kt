package billy.lu.androidtemplate.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetDialog: BottomSheetDialogFragment() {

    protected lateinit var mDialog: BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = LayoutInflater.from(requireContext()).inflate(setLayout(), null)
        mDialog.setContentView(view)
        val layoutParams = view.layoutParams

        if (setHeightPercentage() > 0) {
            val height: Float =
                (requireContext().resources.displayMetrics.heightPixels * setHeightPercentage())
            layoutParams.height = height.toInt()
            view.layoutParams = layoutParams
        }

        initView(view)

        return mDialog
    }

    abstract fun initView(view: View)

    abstract fun setLayout(): Int

    abstract fun setHeightPercentage(): Float

}