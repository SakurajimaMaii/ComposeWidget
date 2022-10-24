package com.example.composewidget

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composewidget.ui.theme.ComposeWidgetTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fruits = arrayOf("Apple", "Apricot", "Almond", "Banana", "Betelnut", "Cherry", "Damson")

        setContent {
            ComposeWidgetTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current

                    LazyColumn {
                        items(fruits) { fruit ->
                            FruitsRow(fruit) { fruitClick(fruit, context) }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun FruitsRow(item: String, clickEvent: () -> Unit) {
        Text(text = item,
            modifier = Modifier
                .clickable(onClick = clickEvent)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .height(50.dp)
        )
    }

    private val fruitClick = { fruit: String, context: Context ->
        Toast.makeText(context, fruit, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeWidgetTheme {
        Greeting3("Android")
    }
}