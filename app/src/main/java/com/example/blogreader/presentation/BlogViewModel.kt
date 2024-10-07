package com.example.blogreader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreader.data.model.BlogPost
import com.example.blogreader.domain.repositories.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val repository: BlogRepository
) : ViewModel() {


    private val _blogPosts = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogPosts: StateFlow<List<BlogPost>> = _blogPosts

    init {
        fetchBlogPosts()
    }

    private fun fetchBlogPosts() {
        viewModelScope.launch {
            val response = repository.getPosts(10, 1)
            if (response.isSuccessful) {
                _blogPosts.value = response.body() ?: emptyList()
            }
        }
    }
}
