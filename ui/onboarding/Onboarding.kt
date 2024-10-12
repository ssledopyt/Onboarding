package ui.onboarding

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ui.composable.NomiaScrollableScaffold
import ui.theme.model.Paddings
import R
import core.text.Content
import ui.onboarding.common.Footer
import ui.onboarding.common.TopAppBar
import ui.onboarding.data.items.OnboardingStatus


@SuppressLint("UnrememberedMutableState")
@Composable
fun Onboarding(
    widthSizeClass: WindowWidthSizeClass,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsState()
    val onboardingNavController = rememberNavController()

    val currentStep = mutableStateOf(state.stepIndicator).value

    val percentArea : Float = if (widthSizeClass == WindowWidthSizeClass.Expanded)  0.5f else 1f

    NomiaScrollableScaffold(
        modifier = Modifier.fillMaxWidth(),
        title = {
            TopAppBar(
                currentStep = currentStep.step,
                screenNumbersForPossibilityOfSkipping = screenNumbersForPossibilityOfSkipping,
                onClickSkip = {
                    onboardingNavController.navigateNext()
                    viewModel.slideNext()
                })
                },
        footer = {
            if (currentStep.step != 5) {
                Footer(
                    onClick = {
                        onboardingNavController.navigateNext()
                        viewModel.slideNext()
                    },
                    onBackClick = {
                        onboardingNavController.navigateBack()
                        viewModel.slidePrevious()
                    },
                    currentStep = currentStep.step,
                    widthSizeClass = widthSizeClass,
                    percentArea = percentArea,
                )
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(percentArea).align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ){
            OnboardingNavHost(onboardingNavController)
        }
    }
}


val screenNumbersForPossibilityOfSkipping = arrayOf(2, 3, 4)