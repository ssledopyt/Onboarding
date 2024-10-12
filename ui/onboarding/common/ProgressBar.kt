package ui.onboarding.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    currentPage: Int,
    totalPages: Int
) {
    val indicatorHeight = 4.dp
    val lineColor = Color.White
    val filledColor = Color(0xFF005CBC)
    val unfilledColor = Color.Gray

    val miniSpace = 10f

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(indicatorHeight)
    ) {
        val widthIndicator = (size.width / totalPages)

        // Отрисовка фона
        drawLine(
            color = unfilledColor,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = indicatorHeight.toPx()
        )

        // Отрисовка прогресса
        drawLine(
            color = filledColor,
            start = Offset(0f, size.height / 2),
            end = Offset((currentPage+1) *widthIndicator, size.height / 2),
            strokeWidth = indicatorHeight.toPx()
        )

        // Отрисовка делений
        for (i in 1 until totalPages) {
            val positionX = widthIndicator * i
            drawLine(
                color = lineColor,
                start = Offset(positionX-6, size.height / 2),
                end = Offset(positionX+6, size.height / 2),
                strokeWidth = indicatorHeight.toPx()
            )

        }
    }
}

@Preview
@Composable
fun ProgressPreview(){
    ProgressBar(6,6)
}
