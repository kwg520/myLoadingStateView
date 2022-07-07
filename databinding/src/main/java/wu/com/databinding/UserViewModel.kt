package wu.com.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *@author:kwg
 *@date:2022/7/5,10:41
 */
class UserViewModel : ViewModel(){
    private  var data:MutableLiveData<User>? = null
    fun getMyAge():Int{
        if(data==null){
            data = MutableLiveData<User>()
            data?.value = User(11,"jack")
        }
        return data?.value!!.age
    }

    fun addAge(){
       data?.value?.age =  data?.value?.age!!.plus(1)
    }

}