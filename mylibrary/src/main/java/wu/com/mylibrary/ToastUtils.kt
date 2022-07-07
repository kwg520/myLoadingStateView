package wu.com.mylibrary

import android.content.Context
import android.widget.Toast

/**
 *@author:kwg
 *@date:2022/7/1,10:54
 */
object ToastUtils {
    fun show(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showL(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}