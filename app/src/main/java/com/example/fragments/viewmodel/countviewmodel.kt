package com.example.fragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class countViewModel: ViewModel() {
    private val count = MutableLiveData<Int>()

    fun getcount() : LiveData<Int>{
        return count
    }

    fun setcount(){

    }

    init {//constructor
        count.value = 0;
    }

    fun increment(){
        count.value = count.value!! +1;
    }
    fun decrement(){
        count.value=count.value!!-1;
    }
}