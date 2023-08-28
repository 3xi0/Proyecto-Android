package com.example.proyectoandroid.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val firstField: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val secondField: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val compareResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getFirstField(): LiveData<String> {
        return firstField
    }
    fun getSecondField(): LiveData<String> {
        return secondField
    }

    fun getCompareResult(): LiveData<Boolean> {
        return compareResult
    }

    private fun setCompareResult(newValue: Boolean) {
        compareResult.value = newValue
    }

    fun setFirstField(newText: String) {
        firstField.value = newText
    }
    fun setSecondField(newText: String) {
        secondField.value = newText
    }
    fun compare() {
        setCompareResult(firstField.value==secondField.value)
    }
}
