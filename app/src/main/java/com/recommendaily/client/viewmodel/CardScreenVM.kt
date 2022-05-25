package com.recommendaily.client.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.recommendaily.client.model.CardData

class CardScreenVM: ViewModel(){

    /**
     * List of items to show on CardScreen
     */
    private val itemsList: MutableState<List<CardData>> = mutableStateOf(listOf())

    private val storageRef = FirebaseStorage.getInstance().reference

    private val imageUrl = mutableStateOf("")

    /**
     * Gets data from server
     */
    fun getData(): List<CardData>{
        itemsList.value = listOf(
            CardData(
                id = 2,
                title = "Test A",
                subtitle = "Test A",
                release_date = "Test A",
                popularity = "100%",
                user_count = "Test A",
                price = "Test A",
                description = "Test A Test A Test A Test A"
            )
        )
        return itemsList.value
    }

    private fun getImageUrlFromStorage(id: Int){
        val imgRef = storageRef.child("$id.jpg")
        imgRef.downloadUrl.addOnSuccessListener {
            imageUrl.value = it.toString()
            Log.d("Upload_From_Storage", "Success with ${imageUrl.value}")
        }.addOnFailureListener {
            Log.e("Upload_From_Storage", "Failure")
        }
    }

    fun getImageUrl(id:Int): String{
        getImageUrlFromStorage(id)
        return imageUrl.value
    }

}