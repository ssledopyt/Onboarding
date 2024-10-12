package ui.onboarding.events

import ui.onboarding.data.items.types.InstitutionType
import ui.onboarding.data.items.types.ServiceType

interface Event

sealed class UIEvent :Event{
    data class WelcomeAndSignUpEvent(val name: String, val emailOrPhone: String, ) : UIEvent()
    data class NewInstitutionEvent(
        val institutionName: String,
        val institutionCountryAndCity: String,
        val institutionAddress: String,
        val isNewPlace: Boolean,
        val automationSystemName: String,
    ) : UIEvent()
    data class InstitutionTypeEvent(val institutionType: InstitutionType, ) : UIEvent()
    data class InstitutionSizeEvent(
        val totalArea: String,
        val seating: String,
        val hallsArea: String,
        val kitchenArea: String,
    ) : UIEvent()
    data class ServiceTypes(val serviceType: ServiceType, ) : UIEvent()
    object Submit: UIEvent()
}


class SaveEvent() : Event

class ReadEvent() : Event

class NextStepEvent() : Event

class BackStepEvent() : Event

class SkipEvent() : Event

data class ErrorEvent(val throwable: Throwable) : Event