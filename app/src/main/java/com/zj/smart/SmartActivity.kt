package com.zj.smart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zj.smart.ui.theme.SmartHomeTheme

class SmartActivity : ComponentActivity() {

    companion object {
        private const val NAVIGATION_MAIN = "main"
        private const val NAVIGATION_SCAN = "scan"
        private const val NAVIGATION_DETAILS = "details"
        private const val NAVIGATION_ROUTE_URL = "details_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        StatusBar(this).hide()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            // Update the system bars to be translucent
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }
            SmartHomeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = NAVIGATION_MAIN) {
                        composable(NAVIGATION_MAIN) {
                            MainPage({ navController.navigate(NAVIGATION_SCAN) }) { staggeredGridData ->
                                val result =
                                    "${staggeredGridData.nameId}-${staggeredGridData.resId}"
                                navController.navigate("$NAVIGATION_DETAILS/$result")
                            }
                        }
                        composable(NAVIGATION_SCAN) { ScanPage { navController.popBackStack() } }
                        composable(
                            "${NAVIGATION_DETAILS}/{$NAVIGATION_ROUTE_URL}",
                            arguments = listOf(navArgument(NAVIGATION_ROUTE_URL) {
                                type = NavType.StringType
                            }),
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            val parcelable = arguments.getString(NAVIGATION_ROUTE_URL)
                                ?: "${R.string.air}-${R.drawable.air}"
                            val split = parcelable.split("-")
                            DetailsPage(StaggeredGridData(split[0].toInt(), split[1].toInt())) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }

}
