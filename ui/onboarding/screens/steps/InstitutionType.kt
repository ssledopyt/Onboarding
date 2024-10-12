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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography
import ui.theme.model.Paddings
import R
import ui.onboarding.OnboardingViewModel
import ui.onboarding.common.OnboardingCheckBox
import ui.onboarding.data.items.types.InstitutionType
import ui.onboarding.events.UIEvent


@Composable
fun InstitutionType(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsState()

    val institutionTypes = listOf(
        InstitutionType.Restaurant,
        InstitutionType.Bar,
        InstitutionType.Cafe,
        InstitutionType.Canteen,
        InstitutionType.CoffeeHouse,
        InstitutionType.Cookery,
        InstitutionType.Other
    )

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    )
    {
        Text(
            text = stringResource(id = R.string.title_type_institution),
            style = Typography.headlineSmall,
            modifier = modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.subtitle_type_institution),
            style = Typography.bodyLarge,
            modifier = modifier.padding(bottom = 16.dp),
            color = Color.Gray
        )

        institutionTypes.forEach() { item ->
            OnboardingCheckBox(
                headlineText = { Text(text = stringResource(id = item.title)) },
                icon = painterResource(id = item.icon),
                checked = item in state.institutionTypes,
                onCheckedChange = {
                    viewModel.onUIEvent(UIEvent.InstitutionTypeEvent(institutionType = item))
                }
            )
        }
    }
}


