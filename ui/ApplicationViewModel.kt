package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import Constants.SharingStartedWithDefaultTimeout
import PrincipalRepository
import model.Principal
import model.AppStartDestination
import SettingsRepository
import javax.inject.Inject

@Suppress("LongParameterList")
@HiltViewModel
class ApplicationViewModel @Inject constructor(
    principalRepository: PrincipalRepository,
) : ViewModel() {


    val appStartDestination = principalRepository.currentPrincipal
        .map { principal ->
            when (principal) {
                is Principal -> AppStartDestination.Authorized
                else -> AppStartDestination.Unauthorized
            }
        }
        .stateIn(viewModelScope, SharingStartedWithDefaultTimeout, AppStartDestination.Unauthorized)
}
