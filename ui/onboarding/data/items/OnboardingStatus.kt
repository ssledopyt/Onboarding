package ui.onboarding.data.items

import androidx.compose.runtime.Immutable

@Immutable
enum class OnboardingStatus(val step: Int){
    WELCOME(0),
    NEW_INSTITUTION(1),
    INSTITUTION_TYPE(2),
    INSTITUTION_SIZE(3),
    SERVICE_TYPE(4),
    APPLICATION_SUBMITTED(5),
}