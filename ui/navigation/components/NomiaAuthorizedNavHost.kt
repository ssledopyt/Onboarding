package net.nomia.pos.ui.navigation.components

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import net.nomia.pos.ui.OnboardingDestination
import net.nomia.pos.ui.argumentList
import net.nomia.pos.ui.onboarding.Onboarding
import net.nomia.pos.ui.onboarding.data.items.OnboardingStatus
import net.nomia.pos.ui.onboarding.screens.steps.InstitutionSize
import net.nomia.pos.ui.onboarding.screens.steps.InstitutionType
import net.nomia.pos.ui.onboarding.screens.steps.NewInstitution
import net.nomia.pos.ui.onboarding.screens.steps.ServiceType
import net.nomia.pos.ui.onboarding.screens.steps.WelcomeAndSignUp
import net.nomia.pos.ui.route

@Composable
internal fun NomiaAuthorizedNavHost(
    widthSizeClass: WindowWidthSizeClass,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingDestination.route
    ) {
        composable(
            route = OnboardingDestination.route,
            arguments = OnboardingDestination.argumentList
        ) {
            Onboarding(widthSizeClass = widthSizeClass)
        }
    }
}
