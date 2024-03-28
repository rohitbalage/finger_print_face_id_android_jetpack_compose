package com.rrbofficial.faeidandfigureprint

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.rrbofficial.faeidandfigureprint.ui.theme.FaeIdAndFigurePrintTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biometricAuthenticator = BiometricAuthenticator(this)
        setContent {
            FaeIdAndFigurePrintTheme {

             Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 val activity = LocalContext.current as FragmentActivity
                 var message by remember {
                     mutableStateOf("")
                 }
                TextButton(onClick = {
                    biometricAuthenticator.promptBiometricAuth(
                        title = "Login ",
                        subTitle = "Use your figure print or face id",
                        negativeArraySizeException = "cancle",
                        fragmentActivity = activity,
                        onSuccess = {
                                message = "Success"
                        },
                        onFailed = {
                            message = "wrong fingure print or face id"
                        },
                        onError = { _, error ->
                            {
                                message = error
                            }

                        }
                    )


                }) {
                    Text(text = "Login with figureprint or face id")
                }
                 Spacer(modifier = Modifier.height(10.dp))
                 Text(text = message)
             }
            }
        }
    }
}