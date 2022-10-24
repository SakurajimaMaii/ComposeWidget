package com.example.composewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composewidget.ui.theme.ComposeWidgetTheme

class ButtonActivity : ComponentActivity() {
    data class ButtonState(var text: String, var textColor: Color, var buttonColor: Color)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWidgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val interactionState = remember { MutableInteractionSource() }

                    val (text, textColor, buttonColor) = when {
                        interactionState.collectIsPressedAsState().value  -> ButtonState("Just Pressed", Color.Red, Color.Black)
                        else -> ButtonState( "Just Button", Color.White, Color.Red)
                    }

                    Button(
                        onClick = { },
                        interactionSource = interactionState,
                        elevation = null,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = buttonColor,
                        ),
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .height(IntrinsicSize.Min)
                    ) {
                        Text(
                            text = text, color = textColor
                        )
                    }
                    
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        
                    }
                    
                    IconButton(onClick = { /*TODO*/ }) {
                        
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeWidgetTheme {
        Greeting2("Android")
    }
}