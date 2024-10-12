package ui.onboarding.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ui.composable.FilledButton
import ui.composable.Footer
import ui.theme.Typography
import ui.theme.model.Paddings
import R

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onBackClick : () -> Unit,
    currentStep: Int,
    widthSizeClass: WindowWidthSizeClass,
    percentArea: Float = 1f
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Footer(modifier = Modifier.fillMaxWidth(percentArea).align(Alignment.CenterHorizontally),) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                        if (currentStep != 0) {
                        when (widthSizeClass) {
                            WindowWidthSizeClass.Expanded -> {
                                OnboardingFooterButtonTablet(onClick = { onBackClick() })
                            }

                            else -> {
                                OnboardingFooterButtonMobile(onClick = { onBackClick() })
                            }
                        }
                        Spacer(modifier = modifier.width(Paddings.small))
                    }

                    FilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onClick() }
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.footer_button_next),
                            style = Typography.labelLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }
}
