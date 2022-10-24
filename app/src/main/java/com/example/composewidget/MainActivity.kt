package com.example.composewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composewidget.ui.theme.ComposeWidgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWidgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val toolbarHeight = 200.dp
                    // ToolBar 最大向上位移量
                    // 56.dp 参考自 androidx.compose.material AppBar.kt 里面定义的 private val AppBarHeight = 56.dp
                    val maxUpPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() - 56.dp.roundToPx().toFloat() }
                    // ToolBar 最小向上位移量
                    val minUpPx = 0f
                    // 偏移折叠工具栏上移高度
                    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
                    // 现在，让我们创建与嵌套滚动系统的连接并聆听子 LazyColumn 中发生的滚动
                    val nestedScrollConnection = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                                val delta = available.y
                                val newOffset = toolbarOffsetHeightPx.value + delta
                                toolbarOffsetHeightPx.value = newOffset.coerceIn(-maxUpPx, minUpPx)
                                // here's the catch: let's pretend we consumed 0 in any case, since we want
                                // LazyColumn to scroll anyway for good UX
                                // We're basically watching scroll without taking it
                                return Offset.Zero
                            }
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxSize()
                            // attach as a parent to the nested scroll system
                            .nestedScroll(nestedScrollConnection)
                    ) {
                        // our list with build in nested scroll support that will notify us about its scroll
                        LazyColumn(contentPadding = PaddingValues(top = toolbarHeight)) {
                            items(100) { index ->
                                Text("I'm item $index", modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp))
                            }
                        }
                        ScrollableAppBar(
                            title = "toolbar offset is ${toolbarOffsetHeightPx.value}",
                            backgroundImageId = R.drawable.top_bar_bk,
                            scrollableAppBarHeight = toolbarHeight,
                            toolbarOffsetHeightPx = toolbarOffsetHeightPx
                        )
                    }
                    
                    Button(onClick = {  }) {
                        
                    }
                    
                    TextButton(onClick = { /*TODO*/ }) {
                        
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWidgetTheme {
        Greeting("Android")
    }
}