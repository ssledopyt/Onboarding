package ui.onboarding.screens.steps

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography
import ui.theme.model.Paddings
import R
import ui.onboarding.OnboardingViewModel
import ui.onboarding.events.UIEvent

@Composable
fun ApplicationSubmitted(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    SideEffect {
        viewModel.onUIEvent(UIEvent.Submit)
        Log.e("LastWindow","Really")
    }

    BackHandler(false){}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Paddings.medium, end = Paddings.medium, top = Paddings.medium)
    ) {

        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.title_bye),
            style = Typography.headlineSmall
        )

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Paddings.medium),
            text = stringResource(R.string.subtitle_bye),
            style = Typography.bodyLarge,
            color = Color.Gray
        )
    }
}