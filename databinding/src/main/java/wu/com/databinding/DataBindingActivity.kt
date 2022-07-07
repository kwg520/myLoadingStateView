package wu.com.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import wu.com.databinding.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDataBindingBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(this.application)).get(UserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }
}