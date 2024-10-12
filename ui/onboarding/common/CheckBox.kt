package ui.onboarding.common

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import ui.composable.list_item.ListItem
import ui.composable.list_item.ListItemColors
import ui.composable.list_item.ListItemDefault
import ui.theme.spacers

@Composable
fun OnboardingCheckBox(
    modifier: Modifier = Modifier,
    headlineText: @Composable () -> Unit,
    enabled: Boolean = true,
    colors: ListItemColors = ListItemDefault.colors(enabled = enabled),
    checked: Boolean = false,
    icon: Painter,
    onCheckedChange: (Boolean) -> Unit,
){
    ListItem(
        headlineText = headlineText,
        colors = colors,
        leadingContent = {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.requiredSize(MaterialTheme.spacers.large)
            )
        },
        trailingContent = {
                Checkbox(
                    modifier = modifier.requiredSize(MaterialTheme.spacers.large),
                    checked = checked,
                    onCheckedChange = { onCheckedChange(it) },
                    enabled = enabled,
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                )
            }
    )
    Divider()
}