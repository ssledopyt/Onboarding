package ui.onboarding.screens.steps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.composable.OutlinedTextField
import ui.theme.Typography
import ui.theme.model.Paddings
import R
import ui.onboarding.OnboardingViewModel
import ui.onboarding.events.UIEvent


@Composable
fun NewInstitution(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    ){

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    ) {

        val state by viewModel.stateFlow.collectAsState()

        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.title_create),
            style = Typography.headlineSmall
        )

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.small),
            text = stringResource(R.string.subtitle_create),
            style = Typography.bodyLarge,
            color = Color.Gray
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.medium),
            value = state.institutionName,
            placeholder = { Text(text = stringResource(R.string.input_name_institution)) },
            supportingText = { Text(text = stringResource(id = R.string.support_name_institution)) },
            onValueChange = {
                viewModel.onUIEvent(
                    UIEvent.NewInstitutionEvent(
                        institutionName = it,
                        institutionCountryAndCity = state.institutionCountryAndCity,
                        institutionAddress = state.institutionAddress,
                        isNewPlace = state.isNewPlace,
                        automationSystemName = state.automationSystemName
                    )
                )
            }
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.medium),
            value = state.institutionCountryAndCity,
            placeholder = { Text(text = stringResource(R.string.input_country_city)) },
            supportingText = { Text(text = stringResource(id = R.string.support_country_city)) },
            onValueChange = {
                viewModel.onUIEvent(
                    UIEvent.NewInstitutionEvent(
                        institutionName = state.institutionName,
                        institutionCountryAndCity = it,
                        institutionAddress = state.institutionAddress,
                        isNewPlace = state.isNewPlace,
                        automationSystemName = state.automationSystemName
                    )
                )
            }
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.medium),
            value = state.institutionAddress,
            placeholder = { Text(text = stringResource(R.string.input_address)) },
            onValueChange = {
                viewModel.onUIEvent(
                    UIEvent.NewInstitutionEvent(
                        institutionName = state.institutionName,
                        institutionCountryAndCity =  state.institutionCountryAndCity,
                        institutionAddress = it,
                        isNewPlace = state.isNewPlace,
                        automationSystemName = state.automationSystemName
                    )
                )
            }
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = Paddings.large),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isNewPlace,
                onCheckedChange = {
                    viewModel.onUIEvent(
                        UIEvent.NewInstitutionEvent(
                            institutionName = state.institutionName,
                            institutionCountryAndCity =  state.institutionCountryAndCity,
                            institutionAddress = state.institutionAddress,
                            isNewPlace = it,
                            automationSystemName = state.automationSystemName
                        )
                    )
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = stringResource(id = R.string.checkbox_new_institution),
                    style = Typography.titleMedium
                )
                Text(
                    text = stringResource(id = R.string.support_new_institution),
                    style = Typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        if (!state.isNewPlace) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Paddings.large),
                    value = state.automationSystemName,
                    placeholder = { Text(text = stringResource(R.string.input_automation)) },
                    supportingText = { Text(text = stringResource(id = R.string.support_automation)) },
                    onValueChange = {
                        viewModel.onUIEvent(
                            UIEvent.NewInstitutionEvent(
                                institutionName = state.institutionName,
                                institutionCountryAndCity =  state.institutionCountryAndCity,
                                institutionAddress = state.institutionAddress,
                                isNewPlace = state.isNewPlace,
                                automationSystemName = it
                            )
                        )
                    }
                )
            }
        }
    }
}