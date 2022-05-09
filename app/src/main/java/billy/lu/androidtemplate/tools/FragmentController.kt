package billy.lu.androidtemplate.tools

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.lang.ref.WeakReference


open class FragmentController(private val container: Int, activity: AppCompatActivity) {

    private var mFragmentManager = activity.supportFragmentManager
    private var mFragmentTransaction: FragmentTransaction? = null

    open fun add(fragment: Fragment) {
        add(fragment, false)
    }

    open fun add(fragment: Fragment, withAnim: Boolean) {
        beingTransaction()
        if (withAnim) {
//            addAnimation()
        }
        mFragmentTransaction?.add(container, fragment, "tag_" + fragment.javaClass.simpleName)
        mFragmentTransaction?.commitAllowingStateLoss()
        mFragmentTransaction = null
        showOnly(fragment)
    }

    open fun addToStack(fragment: Fragment) {
        addToStack(fragment, false)
    }

    open fun addToStack(fragment: Fragment, withAnim: Boolean) {
        beingTransaction()
        if (withAnim) {
//            addAnimation()
        }
        mFragmentTransaction?.add(container, fragment, "tag_" + fragment.javaClass.simpleName)
        mFragmentTransaction?.addToBackStack("fragment_" + fragment.javaClass.simpleName)
        mFragmentTransaction?.commitAllowingStateLoss()
        mFragmentTransaction = null
        showOnly(fragment)
    }

    open fun addToStackUpDown(fragment: Fragment) {
        beingTransaction()
//        addBottomToTopAnimation()
        mFragmentTransaction?.add(container, fragment, "tag_" + fragment.javaClass.simpleName)
        mFragmentTransaction?.addToBackStack("fragment_" + fragment.javaClass.simpleName)
        mFragmentTransaction?.commitAllowingStateLoss()
        mFragmentTransaction = null
        showOnly(fragment)
    }

    open fun replace(fragment: Fragment) {
        replace(fragment, false)
    }

    open fun replace(fragment: Fragment, withAnim: Boolean) {
        beingTransaction()
        if (withAnim) {
//            addAnimation()
        }
        mFragmentTransaction?.replace(container, fragment)
        mFragmentTransaction?.commitAllowingStateLoss()
        mFragmentTransaction = null
        showOnly(fragment)
    }

//    open fun remove(fragment: Fragment?) {
//        mHandler.sendMessageDelayed(mHandler.obtainMessage(ACTION_REMOVE, fragment), 350)
//    }

    open fun remove(fragment: Fragment, withAnim: Boolean) {
        try {
            beingTransaction()
            if (withAnim) {
//                removeAnimation()
            }
            mFragmentTransaction?.remove(fragment)
            mFragmentTransaction?.commitAllowingStateLoss()
            mFragmentTransaction = null
            mFragmentManager.executePendingTransactions()
            showLatest()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    open fun removeUpDown(fragment: Fragment) {
        try {
            beingTransaction()
//            removeTopToBottomAnimation()
            mFragmentTransaction?.remove(fragment)
            mFragmentTransaction?.commitAllowingStateLoss()
            mFragmentTransaction = null
            mFragmentManager.executePendingTransactions()
            showLatest()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    private fun beingTransaction() {
        if (mFragmentTransaction == null) {
            mFragmentTransaction = mFragmentManager.beginTransaction()
        }
    }

//    private open fun addBottomToTopAnimation() {
//        mFragmentTransaction.setCustomAnimations(
//            R.anim.slide_from_top,
//            R.anim.slide_to_bottom,
//            R.anim.slide_from_bottom,
//            R.anim.slide_to_top
//        )
//    }
//
//    private open fun removeTopToBottomAnimation() {
//        mFragmentTransaction.setCustomAnimations(
//            R.anim.slide_from_bottom,
//            R.anim.slide_to_top,
//            R.anim.slide_from_top,
//            R.anim.slide_to_bottom
//        )
//    }
//
//    private open fun addAnimation() {
//        mFragmentTransaction.setCustomAnimations(
//            R.anim.slide_from_right,
//            R.anim.slide_to_left,
//            R.anim.slide_from_left,
//            R.anim.slide_to_right
//        )
//    }
//
//    private open fun removeAnimation() {
//        mFragmentTransaction.setCustomAnimations(
//            R.anim.slide_from_left,
//            R.anim.slide_to_right,
//            R.anim.slide_from_right,
//            R.anim.slide_to_left
//        )
//    }

    private fun showOnly(fragment: Fragment) {
        beingTransaction()
        for (f in mFragmentManager.getFragments()) {
            if (f === fragment) {
//                LogUtils.i("[show]fragment=>$f")
//                mFragmentTransaction?.show(f)
            } else if (f !== fragment && !f.isHidden()) {
//                LogUtils.i("[hidden]fragment=>$f")
//                addAnimation()
                mFragmentTransaction?.hide(f)
            }
        }
        mFragmentTransaction?.commitAllowingStateLoss()
        mFragmentTransaction = null
    }

    private fun showLatest() {
        if (mFragmentManager.fragments.size > 0) {
            beingTransaction()
//            removeAnimation()
            val fragment: Fragment =
                mFragmentManager.fragments.get(mFragmentManager.fragments.size - 1)
            mFragmentTransaction?.show(fragment)?.commitAllowingStateLoss()
            mFragmentTransaction = null
        }
    }
}