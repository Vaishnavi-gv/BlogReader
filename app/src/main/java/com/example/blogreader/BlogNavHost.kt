package com.example.blogreader

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.blogreader.presentation.Screens.BlogListScreen
import com.example.blogreader.presentation.Screens.BlogDetailScreen
import com.example.blogreader.presentation.Screens.SplashScreen

@Composable
fun BlogNavHost(navController: NavHostController) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("blogList") { BlogListScreen(navController) }
        composable(
            "blogDetail/{blogUrl}",
            arguments = listOf(navArgument("blogUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val blogUrl = backStackEntry.arguments?.getString("blogUrl") ?: return@composable
                BlogDetailScreen(blogUrl)
        }
    }
}