package com.example.picopic.user_interface

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picopic.ui.theme.Black
import com.example.picopic.ui.theme.BlueGray
import com.example.picopic.user_interface.components.InputBox
import com.example.picopic.view_models.SignUpViewModel

@Composable
fun Login() {
    val uiColor:Color = if (isSystemInDarkTheme()) Black else Color.White
    var colors = listOf<Color>()
    if (!isSystemInDarkTheme()){
        colors = listOf(Color(132, 255, 201), Color(170, 178, 255), Color(236, 160, 255))
    }
    else{
        colors = listOf(Color(14, 7, 3), Color(42, 69, 75), Color(41, 72, 97))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = uiColor)
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "background")
            val targetOffset = with(LocalDensity.current){
                1000.dp.toPx()
            }
            val offset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = targetOffset,
                animationSpec = infiniteRepeatable(
                    tween(20000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse),
                label = "offset"
            )
            val brush = Brush.linearGradient(
                colors = colors,
                start = Offset(offset, offset),
                end = Offset(offset + 400f, offset + 400f),
                tileMode = TileMode.Mirror
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .blur(50.dp)
                    .drawWithCache {
                        onDrawBehind { drawRect(brush) }
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    modifier = Modifier
                        .offset(y = 120.dp)
                        .size(250.dp)
                        .clip(CircleShape)
                        .background(Color.Blue)
                )
            }
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val signupmodel = viewModel<SignUpViewModel>()
            InputBox(label = "User name", trailing = "", modifier = Modifier.fillMaxWidth(), value = signupmodel.username, onValueChange = { text -> signupmodel.updateUsername(text) })
            Spacer(modifier = Modifier.height(15.dp))
            InputBox(label = "Password", trailing = "Forgot?", modifier = Modifier.fillMaxWidth(), value = signupmodel.password, onValueChange = { text -> signupmodel.updatePassword(text)})
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) BlueGray else Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(size = 5.dp)
            ) {
                Text(text = "Log In", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium))
            }
        }
        val uiColor:Color = if (isSystemInDarkTheme()) Color.White else Color.Black
        Box(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,

            ){
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF94A3B8),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                ){
                    append("Don't have account?")
                }
                withStyle(
                    style = SpanStyle(
                        color = uiColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                ){
                    append(" ")
                    append("Create Now")
                }
            })
        }
    }

}