package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    Box(
            modifier = Modifier
                    .background(
                            brush = Brush.verticalGradient(
                                    0.15f to Grey500,
                                    0.4f to Orange400,
                                    0.6f to Orange600,
                                    0.85f to Grey500,
                            )
                    )
                    .fillMaxWidth()
                    .fillMaxHeight()
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                    modifier = Modifier
                            .padding(top = 16.dp)
                            .width(64.dp),
                    color = Color.DarkGray,
                    thickness = 2.dp
            )
            val alpha = getBottomSheetSlide(bottomSheetScaffoldState.bottomSheetState)
            if (alpha >= 0.7f) {
                BottomSheetCollapsedContent(alpha = alpha)
            } else {
                BottomSheetExpandedContent(alpha = 1 - alpha)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun getBottomSheetSlide(state: BottomSheetState): Float {
    val fraction = state.progress.fraction
    return when {
        state.currentValue == state.targetValue -> {
            if (state.currentValue == BottomSheetValue.Collapsed) 1f else 0f
        }
        state.isCollapsed -> 1 - fraction
        else -> fraction
    }
}
