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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onearmedbandit.ui.theme.OneArmedBanditTheme
import kotlinx.coroutines.*

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
object GlobalData{
    var currentChar by mutableStateOf('A')
    var charList by  mutableStateOf(mutableListOf<Char>())
}

@Composable
fun RandomCharGenerator() {


    var isRunning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val myBackground = Color(android.graphics.Color.parseColor("#219EBC"))
    val btn_background = Color(android.graphics.Color.parseColor("#FFB703"))
    val result_bg = Color(android.graphics.Color.parseColor("#8ECAE6"))
    val window_bg = Color(android.graphics.Color.parseColor("#FB8500"))

    // Okno s random čísly
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = window_bg)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
            Text(
                text = GlobalData.currentChar.toString(),
                //style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(16.dp)

            )



      //  Spacer(modifier = Modifier.height(16.dp))



    }

    // Sloupec s buttony
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = myBackground)
            .padding(top = 20.dp, bottom = 20.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = {
                    isRunning = !isRunning
                    if (isRunning){
                        scope.launch {
                            startRandomCharacterChange()
                        }
                    } else {
                        isRunning = false
                    }
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = btn_background)

            ) {
                Text(text = if (isRunning) "Stop" else "Generate", color = myBackground)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    GlobalData.charList.clear()

                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = btn_background)
            ) {
                Text("Clear", color = myBackground)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    GlobalData.currentChar = ('A'..'Z').random()
                    GlobalData.charList.add(GlobalData.currentChar)
                    // Aktualizace stavu seznamu
                    charList = GlobalData.charList
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = btn_background)
            ) {
                Text("Sort it", color = myBackground)
            }
        }
    }

    // Sloupec s hodnotami
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(color = result_bg)
    ) {
        GlobalData.charList.forEachIndexed { index, char ->
            Text(text = "$index: $char")
        }
    }
}
suspend fun startRandomCharacterChange() {
    while (true) {
        delay(500) // Změna znaku každých 500 ms
        val newChar = ('A'..'Z').random()
        // Aktualizace stavu
        GlobalData.currentChar = newChar

    }
}

@Preview
@Composable
fun PreviewMyApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RandomCharGenerator()
    }
}