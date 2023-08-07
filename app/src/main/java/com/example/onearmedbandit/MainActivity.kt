package com.example.onearmedbandit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onearmedbandit.ui.theme.OneArmedBanditTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneArmedBanditTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    PreviewMyApp()
                }
            }
        }
    }
}

@Composable
fun RandomCharGenerator() {
    var currentChar by remember { mutableStateOf('A') }
    var charList by remember { mutableStateOf(mutableListOf<Char>()) }
    val context = LocalContext.current



    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentChar.toString(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                currentChar = ('A'..'Z').random()
                charList.add(currentChar)
                // Aktualizace stavu seznamu
                charList = charList
            }
        ) {
            Text("Generate Random Char")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Red)
    ) {
        charList.forEachIndexed { index, char ->
            Text(text = "$index: $char")
        }
    }
}


@Composable
fun CharDisplay(currentChar: Char) {
    Text(
        text = "Current Char: $currentChar",
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun MyApp() {
    var currentChar by remember { mutableStateOf('A') }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RandomCharGenerator()
        CharDisplay(currentChar)
    }
}

@Preview
@Composable
fun PreviewMyApp() {
    MyApp()
}