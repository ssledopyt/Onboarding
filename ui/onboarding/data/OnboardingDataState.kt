package ui.onboarding.data

import ui.onboarding.data.items.OnboardingStatus
import ui.onboarding.data.items.types.InstitutionType
import ui.onboarding.data.items.types.ServiceType

data class OnboardingDataState(
    var stepIndicator: OnboardingStatus = OnboardingStatus.WELCOME,

    var name: String = "",
    var emailOrPhone: String = "",
    var institutionName: String = "",
    var institutionCountryAndCity: String = "",
    var institutionAddress: String = "",
    var isNewPlace: Boolean = true,
    var automationSystemName: String = "",
    var institutionTypes: List<InstitutionType> = emptyList(),
    var totalArea: String = "",
    var seating: String = "",
    var hallsArea: String = "",
    var kitchenArea: String = "",
    var serviceTypes: List<ServiceType> = emptyList()
)
