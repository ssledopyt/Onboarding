package ui.onboarding.data

import androidx.compose.runtime.Immutable

@Immutable
sealed class OnboardingUiState {
    object Loading : OnboardingUiState()

    data class Success (val onboardingDataState: OnboardingDataState) : OnboardingUiState()

    data class Error(val message: String) : OnboardingUiState()

}