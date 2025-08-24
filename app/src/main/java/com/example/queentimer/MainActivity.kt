package com.example.queentimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.queentimer.ui.theme.QueenTimerTheme
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

import network.ApiRepository
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QueenTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(paddingValues = innerPadding)) {

                    LoginForm()


                    }
                }
            }
        }
    }
}

@Composable
fun LoginForm() {
    val scope = rememberCoroutineScope()
    val apiRepo = ApiRepository()
    var httpRes: HttpResponse
    val emailState: TextFieldState = rememberTextFieldState()
    val passwordState: TextFieldState = rememberTextFieldState()
    var displayString: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
    ) {
        TextField(
            state = emailState,
            label = { Text("Email") }

        )
        SecureTextField(
            state = passwordState,
            label = { Text("Password")}
        )
        Button(
            onClick = {
                scope.launch {
                    httpRes = apiRepo.authLogin(email = emailState.text.toString(), password = passwordState.text.toString())
                    displayString = httpRes.toString()
                }
            }
        ) {
            Text("Login")
        }

        CodeCard(jsonStr = displayString)
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    LoginForm()
}

@Composable
fun CodeCard(jsonStr: String) {
    val scrollState = rememberScrollState()

    Card(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(
            state = scrollState,
            enabled = true
        )
        .padding(all = 8.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp, top=12.dp),
            text = "response: ",
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace
        )
        Text(
            modifier = Modifier.padding(all = 12.dp),
            text = jsonStr,
            fontFamily = FontFamily.Monospace
        )
    }

}
