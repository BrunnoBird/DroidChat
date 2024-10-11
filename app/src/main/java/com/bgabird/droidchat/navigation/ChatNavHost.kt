package com.bgabird.droidchat.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.bgabird.droidchat.navigation.extension.slideInTo
import com.bgabird.droidchat.navigation.extension.slideOutTo
import com.bgabird.droidchat.ui.feature.signIn.SignInRoute
import com.bgabird.droidchat.ui.feature.signup.SignUpRoute
import com.bgabird.droidchat.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute
}


@Composable
fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.SplashRoute) {
        composable<Route.SplashRoute> {
            SplashRoute {
                navController.navigate(
                    route = Route.SignInRoute,
                    navOptions = navOptions {
                        popUpTo(Route.SplashRoute) {
                            inclusive = true
                        }
                    }
                )
            }
        }

        composable<Route.SignInRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                }
            )
        }

        composable<Route.SignUpRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            SignUpRoute()
        }
    }
}