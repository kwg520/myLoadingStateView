package wu.com.myloadingstateview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import wu.com.mylibrary.ToastUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToastUtils.show(this,"123")
    }
}

