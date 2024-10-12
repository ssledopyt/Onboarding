package ui.onboarding.screens.steps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography
import ui.theme.model.Paddings
import R
import ui.onboarding.OnboardingViewModel
import ui.onboarding.common.OnboardingCheckBox
import ui.onboarding.data.items.types.ServiceType
import ui.onboarding.events.UIEvent


@Composable
fun ServiceType(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsState()

    val serviceTypes = listOf(
        ServiceType.TakeAway,
        ServiceType.InInstitution,
        ServiceType.Delivery
    )

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    )
    {
        Text(
            text = stringResource(id = R.string.title_type_services),
            style = Typography.headlineSmall,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = Paddings.large)
        )


        serviceTypes.forEach() { item ->
            OnboardingCheckBox(
                headlineText = { Text(text = stringResource(id = item.title)) },
                icon = painterResource(id = item.icon),
                checked = item in state.serviceTypes,
                onCheckedChange = {
                    viewModel.onUIEvent(UIEvent.ServiceTypes(serviceType = item))
                }
            )
        }
    }
}
