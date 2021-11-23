package com.example.reciplease_ca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciplease_ca.models.Meal

class EditorViewModel : ViewModel() {
    private val _post: MutableLiveData<Meal?> = MutableLiveData()
    val post: LiveData<Meal?>
        get() = _post

    private val _currentStatus = MutableLiveData<ResultStatus>(ResultStatus.IDLE)
    val currentStatus: LiveData<ResultStatus>
        get() = _currentStatus

    fun updatePost(postId: Int, newPostData: Meal) {
        // TODO: send PUT request
    }

    fun patchPost(postId: Int, title: String, body: String) {
        // TODO: send PATCH request
    }

    fun deletePost(postId: Int) {
        // TODO: send DELETE request
    }
}