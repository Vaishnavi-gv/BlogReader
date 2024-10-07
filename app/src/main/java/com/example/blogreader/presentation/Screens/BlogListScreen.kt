package com.example.blogreader.presentation.Screens

import android.text.Html
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.blogreader.data.model.BlogPost
import com.example.blogreader.presentation.BlogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogListScreen(navController: NavController){
    val viewModel: BlogViewModel = hiltViewModel()
    val blogPosts = viewModel.blogPosts.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(blogPosts.value) { post ->
            BlogListItem(post = post, onItemClick = {
                val encodedUrl = URLEncoder.encode(post.link, StandardCharsets.UTF_8.toString())
                navController.navigate("blogDetail/${encodedUrl}")
            })
        }
    }
}

@Composable
fun BlogListItem(post: BlogPost, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
            .background(Color(0xFFEFEFEF)) // Set a light background color
            .clickable { onItemClick() }
            .padding(16.dp) // Inner padding for text
    ) {
        Column {
            Text(
                text = Html.fromHtml(post.title.rendered, Html.FROM_HTML_MODE_COMPACT).toString(),
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium // Use MaterialTheme for consistent typography
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = Html.fromHtml(post.excerpt.rendered, Html.FROM_HTML_MODE_COMPACT).toString(),
                fontSize = 14.sp,
                color = Color.Gray // Color for the excerpt
            )
        }
    }
}
