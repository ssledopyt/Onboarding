package ui.onboarding.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ui.theme.Typography
import R


@Composable
fun OnboardingFooterButtonMobile(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    OutlinedButton(onClick = { onClick() },
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        border= BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Black)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_24),
            contentDescription = null
        )
    }
}

@Composable
fun OnboardingFooterButtonTablet(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    OutlinedButton(onClick = { onClick() },
        modifier = Modifier.height(40.dp),
        border= BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Black)
    ) {
        Text(
            text = stringResource(id = R.string.footer_button_back),
            style = Typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
