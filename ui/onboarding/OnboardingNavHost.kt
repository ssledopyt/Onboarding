package ui.onboarding

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.onboarding.data.items.OnboardingStatus
import ui.onboarding.screens.steps.ApplicationSubmitted
import ui.onboarding.screens.steps.InstitutionSize
import ui.onboarding.screens.steps.InstitutionType
import ui.onboarding.screens.steps.NewInstitution
import ui.onboarding.screens.steps.ServiceType
import ui.onboarding.screens.steps.WelcomeAndSignUp
import timber.log.Timber

@Composable
fun OnboardingNavHost(
    navHostController: NavHostController,
    ){
    NavHost(
        navController = navHostController,
        startDestination = OnboardingStatus.WELCOME.name){
            composable(OnboardingStatus.WELCOME.name){
                WelcomeAndSignUp()
            }
            composable(OnboardingStatus.NEW_INSTITUTION.name){
                NewInstitution()
            }
            composable(OnboardingStatus.INSTITUTION_TYPE.name){
                InstitutionType()
            }
            composable(OnboardingStatus.INSTITUTION_SIZE.name){
                InstitutionSize()
            }
            composable(OnboardingStatus.SERVICE_TYPE.name){
                ServiceType()
            }
            composable(OnboardingStatus.APPLICATION_SUBMITTED.name){
                ApplicationSubmitted()
            }
    }
}

internal fun NavHostController.navigateBack(){
    this.navigateUp()
}

internal fun NavHostController.navigateNext(){
    try {
        this.currentDestination?.route.let{ route ->
            val nextRoute = OnboardingStatus.values().find { it.name==route }?.ordinal?.plus(1) ?: 0
            this.navigate(OnboardingStatus.values()[nextRoute].name)
        }
    } catch ( _: Throwable){
        //TODO
    }
}



