package ui.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import auth.domain.LogoutUseCase
import ui.onboarding.data.OnboardingDataState
import ui.onboarding.data.items.OnboardingStatus
import ui.onboarding.data.items.types.InstitutionType
import ui.onboarding.data.items.types.ServiceType
import ui.onboarding.data.local.OnboardingDataRepositoryImpl
import ui.onboarding.events.UIEvent
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingDataRepository: OnboardingDataRepositoryImpl
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardingDataState> =
        MutableStateFlow(OnboardingDataState())

    val stateFlow = _stateFlow.asStateFlow()

    private val steps = OnboardingStatus.values()


/*    fun getCurrentStep(): Int {
        return stateFlow.value.stepIndicator.step
    }*/

    fun onUIEvent(event: UIEvent){
        when (event){
            is UIEvent.WelcomeAndSignUpEvent -> updateWelcomeAndSignUp(
                name = event.name, emailOrPhone = event.emailOrPhone
            )
            is UIEvent.NewInstitutionEvent -> updateNewInstitution(
                institutionName = event.institutionName,
                institutionCountryAndCity = event.institutionCountryAndCity,
                institutionAddress = event.institutionAddress,
                isNewPlace = event.isNewPlace,
                automationSystemName = event.automationSystemName,
            )
            is UIEvent.InstitutionTypeEvent -> updateInstitutionType(
                item = event.institutionType
            )
            is UIEvent.InstitutionSizeEvent -> updateInstitutionSize(
                totalArea = event.totalArea,
                seating = event.seating,
                hallsArea = event.hallsArea,
                kitchenArea = event.kitchenArea,
            )
            is UIEvent.ServiceTypes -> updateServiceType(
                item = event.serviceType
            )
            is UIEvent.Submit -> submitApplication(
            )
        }
    }


    private fun updateWelcomeAndSignUp(name: String, emailOrPhone: String){
        _stateFlow.value = _stateFlow.value.copy(
            name = name,
            emailOrPhone = emailOrPhone,
        )
    }

    private fun updateNewInstitution(
        institutionName: String ,
        institutionCountryAndCity: String ,
        institutionAddress: String,
        isNewPlace: Boolean,
        automationSystemName: String,
    ){
        _stateFlow.value = _stateFlow.value.copy(
            institutionName = institutionName,
            institutionCountryAndCity = institutionCountryAndCity,
            institutionAddress = institutionAddress,
            isNewPlace = isNewPlace,
            automationSystemName = automationSystemName

        )
    }

    private fun updateInstitutionType(item: InstitutionType){
        _stateFlow.value = _stateFlow.value.copy(institutionTypes =
        when(_stateFlow.value.institutionTypes.contains(item)){
            true -> _stateFlow.value.institutionTypes.minus(item)
            else -> _stateFlow.value.institutionTypes.plus(item)
        } )
    }

    private fun updateInstitutionSize(
        totalArea: String,
        seating: String,
        hallsArea: String,
        kitchenArea: String
    ){
        _stateFlow.value = _stateFlow.value.copy(
            totalArea = totalArea,
            seating = seating,
            hallsArea = hallsArea,
            kitchenArea = kitchenArea
        )
    }

    private fun updateServiceType(item: ServiceType){
        _stateFlow.value = _stateFlow.value.copy(serviceTypes =
        when(_stateFlow.value.serviceTypes.contains(item)){
            true -> _stateFlow.value.serviceTypes.minus(item)
            else -> _stateFlow.value.serviceTypes.plus(item)
        } )
    }

    private fun submitApplication (){
        viewModelScope.launch (Dispatchers.IO) {
            onboardingDataRepository.saveData(
                state =
                _stateFlow.value
            )
        }
    }

    internal fun slideNext(){
        val step = _stateFlow.value.stepIndicator
        if (step != steps.last())
            _stateFlow.value = _stateFlow.value.copy(stepIndicator = steps[step.ordinal + 1])
    }
    internal fun slidePrevious(){
        val step = _stateFlow.value.stepIndicator
        if (step != steps.first())
            _stateFlow.value = _stateFlow.value.copy(stepIndicator = steps[step.ordinal - 1])
    }

}
