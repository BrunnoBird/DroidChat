package com.bgabird.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.bgabird.droidchat.ui.feature.signIn.SignInRoute
import com.bgabird.droidchat.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object SignInRoute

@Serializable
object SignUpRoute

@Composable
fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute>() {
            SplashRoute {
                navController.navigate(
                    route = SignInRoute,
                    navOptions = navOptions {
                        popUpTo(SplashRoute) {
                            inclusive = true
                        }
                    }
                )
            }
        }

        composable<SignInRoute> {
            SignInRoute()
        }

        composable<SignUpRoute>() {

        }
    }
}