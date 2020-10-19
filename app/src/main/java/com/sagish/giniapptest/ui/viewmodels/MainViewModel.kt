package com.sagish.giniapptest.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sagish.giniapptest.api.RetrofitClient
import com.sagish.giniapptest.api.services.GetIntArrayService
import com.sagish.giniapptest.models.Number
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class MainViewModel : ViewModel() {

    val intArray by lazy {
        MutableLiveData<ArrayList<Number>>()
    }

    var sumSet = HashSet<Int>()

    var sumHashMap = HashMap<Int, Int>()

    fun getIntArrayFromServer() {

        // Create the service
        val intArrayService = RetrofitClient.buildService(GetIntArrayService::class.java)

        // Run a coroutine in a IO thread
        CoroutineScope(Dispatchers.IO).launch {
            val array = intArrayService?.getIntArray()

            var numbersArray = arrayListOf<Number>()

            array?.numbers?.forEach { num ->

                sumSet.add(num.number)

                var value = sumHashMap.get(num)
                if (value == null) {
                    sumHashMap[num.number] = 1
                } else {
                    value++
                    sumHashMap[num.number] = value
                }
            }

            sumHashMap.toSortedMap().forEach {
                val numberItem = Number(it.key)
                if (it.key == 0 && it.value < 2) {
                    numberItem.hasCouple = false
                } else {
                    numberItem.hasCouple = sumSet.contains(0 - it.key)
                }

                numbersArray.add(numberItem)
            }

            withContext(Dispatchers.Main) {
                intArray.value = numbersArray
            }
        }
    }
}