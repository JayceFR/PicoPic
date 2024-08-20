package com.example.picopic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picopic.ui.theme.BlueGray
import com.example.picopic.ui.theme.PicopicTheme
import kotlin.math.sign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicopicTheme {
                Greeting(name = "jayce")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "background")
            val targetOffset = with(LocalDensity.current){
                1000.dp.toPx()
            }
            val offset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = targetOffset,
                animationSpec = infiniteRepeatable(tween(20000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse),
                label = "offset"
            )
            val brush = Brush.linearGradient(
                colors = listOf(Color(132, 255, 201), Color(170, 178, 255), Color(236, 160, 255)),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val signupmodel = viewModel<SignUpViewModel>()
            InputBox(label = "User name", trailing = "", modifier = Modifier.fillMaxWidth(), value = signupmodel.username, onValueChange = {text -> signupmodel.updateUsername(text) })
            Spacer(modifier = Modifier.height(15.dp))
            InputBox(label = "Password", trailing = "Forgot?", modifier = Modifier.fillMaxWidth(), value = signupmodel.password, onValueChange = {text -> signupmodel.updatePassword(text)})
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
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicopicTheme {
        Greeting("Android")
    }
}