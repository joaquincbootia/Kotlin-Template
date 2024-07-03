package com.uruguayemergencia.ui.components

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@Composable
fun SpinnerTaboo(
    size: Dp = 48.dp, // spinner size
    sweepAngle: Float = 160f, // angle (lenght) of spinner arc
    strokeWidth: Dp = 6.dp,
    strokeVariationMultiplier: Float = 1.5f
) {
    val gradient = Brush.sweepGradient(
        colors = listOf(
            Color(0xFF64B5F6),
            Color(0xFF2196F3),
            Color(0xFF64B5F6),
            Color(0xFFBBDEFB),
            Color(0xFFE3F2FD),
            Color(0xFF64B5F6)
        ),
    )
    val transition = rememberInfiniteTransition(label = "SpinnerRotation")

    val customEasing = Easing { fraction ->
        val easedFraction = (fraction * 2 - 1).pow(3) * 0.5f + 0.5f
        easedFraction
    }

    val currentArcStartAngle by transition.animateValue(
        initialValue = 0f,
        targetValue = 360f,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1100, // Total duration of the animation
                easing = customEasing,
            ),
        ),
        label = "ArcStartAngleAnimation",
    )

    val strokeWidthAnimationUp by transition.animateValue(
        initialValue = with(LocalDensity.current) { strokeWidth.toPx() },
        targetValue = with(LocalDensity.current) { (strokeWidth * strokeVariationMultiplier).toPx() },
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = customEasing,
            ),
        ),
        label = "StrokeWidthAnimationUp",
    )

    val strokeWidthAnimationDown by transition.animateValue(
        initialValue = with(LocalDensity.current) { (strokeWidth * strokeVariationMultiplier).toPx() },
        targetValue = with(LocalDensity.current) { strokeWidth.toPx() },
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                delayMillis = 1200,
                easing = customEasing,
            ),
        ),
        label = "StrokeWidthAnimationDown",
    )

    val stroke = Stroke(
        width = strokeWidthAnimationUp.coerceAtLeast(strokeWidthAnimationDown),
        cap = StrokeCap.Round,
    )

    Canvas(
        Modifier
            .progressSemantics()
            .size(size)
            .padding(strokeWidth / 2),
    ) {

        drawArc(
            gradient,
            startAngle = currentArcStartAngle - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke,
        )
    }
}

@Composable
@Preview
fun LoadingViewPreview() {
    SpinnerTaboo()
}
