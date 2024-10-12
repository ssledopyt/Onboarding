package ui.onboarding.screens.steps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import ui.composable.OutlinedTextField
import ui.theme.Typography
import ui.theme.model.Paddings
import R
import ui.onboarding.OnboardingViewModel
import ui.onboarding.events.UIEvent


@Composable
fun InstitutionSize(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    ) {

        val state by viewModel.stateFlow.collectAsState()

        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.title_size),
            style = Typography.headlineSmall
        )


        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.totalArea,
            placeholder = { Text(text = stringResource(R.string.input_area)) },
            onValueChange = {
                viewModel.onUIEvent(UIEvent.InstitutionSizeEvent(
                    totalArea = it,
                    seating = state.seating,
                    hallsArea = state.hallsArea,
                    kitchenArea = state.kitchenArea
                ))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.seating,
            placeholder = { Text(text = stringResource(R.string.input_seating)) },
            onValueChange = {
                viewModel.onUIEvent(UIEvent.InstitutionSizeEvent(
                    totalArea = state.totalArea,
                    seating = it,
                    hallsArea = state.hallsArea,
                    kitchenArea = state.kitchenArea
                ))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.hallsArea,
            placeholder = { Text(text = stringResource(R.string.input_halls_area)) },
            onValueChange = {
                viewModel.onUIEvent(UIEvent.InstitutionSizeEvent(
                    totalArea = state.totalArea,
                    seating = state.seating,
                    hallsArea = it,
                    kitchenArea = state.kitchenArea
                ))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.kitchenArea,
            placeholder = { Text(text = stringResource(R.string.input_kitchen_area)) },
            onValueChange = {
                viewModel.onUIEvent(UIEvent.InstitutionSizeEvent(
                    totalArea = state.totalArea,
                    seating = state.seating,
                    hallsArea = state.hallsArea,
                    kitchenArea = it
                ))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}