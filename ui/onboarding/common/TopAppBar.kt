package ui.onboarding.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ui.theme.Typography
import ui.theme.appResources
import ui.theme.model.Paddings
import R
import core.text.Content
import ui.onboarding.OnboardingViewModel
import ui.onboarding.data.items.OnboardingStatus


@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    currentStep: Int,
    screenNumbersForPossibilityOfSkipping: Array<Int>,
    onClickSkip: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = Paddings.medium, bottom = Paddings.medium),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(bottom = Paddings.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            if (currentStep in screenNumbersForPossibilityOfSkipping)
                ClickableText(
                    modifier = Modifier,
                    text = AnnotatedString(stringResource(id = R.string.top_text_skip)),
                    style = Typography.labelMedium,
                ){
                    onClickSkip()
                }
        }
        ProgressBar(currentStep, OnboardingStatus.values().size)
    }
}
