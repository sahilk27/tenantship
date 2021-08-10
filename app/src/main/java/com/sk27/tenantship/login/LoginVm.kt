package com.sk27.tenantship.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sk27.tenantship.base.BaseVm
import com.sk27.tenantship.model.api.User

class LoginVm : BaseVm() {

    lateinit var mUser: User

    private val loginAuth: MutableLiveData<User> by lazy { MutableLiveData<User>().also { authenticateUser() } }

    private fun authenticateUser() {
        mUser = User("sahilk", "123456")
    }

    fun getUser(): LiveData<User> {
        return loginAuth
    }

    override fun onCleared() {
        super.onCleared()
    }

}
