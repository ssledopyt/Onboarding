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
import androidx.compose.ui.graphics.Color
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
fun WelcomeAndSignUp(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel()
){
    val state by viewModel.stateFlow.collectAsState()

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.title_welcome),
            style = Typography.headlineSmall
        )

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.small),
            text = stringResource(R.string.subtitle_welcome),
            style = Typography.bodyLarge,
            color = Color.Gray
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.name,
            placeholder = { Text(text = stringResource(R.string.input_name)) },
            onValueChange = {
                viewModel.onUIEvent(
                    UIEvent.WelcomeAndSignUpEvent(
                        name = it,
                        emailOrPhone = state.emailOrPhone
                    )
                )
            },
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.large),
            value = state.emailOrPhone,
            placeholder = { Text(text = stringResource(R.string.input_email)) },
            onValueChange = {
                viewModel.onUIEvent(
                    UIEvent.WelcomeAndSignUpEvent(
                        name = state.name,
                        emailOrPhone = it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
    }

}
