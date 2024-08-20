package com.example.picopic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.picopic.ui.theme.PicopicTheme
import com.example.picopic.user_interface.Login


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicopicTheme {
                Login()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicopicTheme {
        Login()
    }
}