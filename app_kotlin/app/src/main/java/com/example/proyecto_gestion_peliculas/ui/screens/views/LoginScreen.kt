package com.example.proyecto_gestion_peliculas.ui.screens.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.data.readEmail
import com.example.proyecto_gestion_peliculas.ui.components.MyTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import com.example.proyecto_gestion_peliculas.ui.screens.viewmodels.LoginScreenViewModel


@Composable
fun LoginScreen(navigator: Navigator, loginScreenViewModel: LoginScreenViewModel) {
    val context = LocalContext.current
    val alertDialogLogIn = remember { mutableStateOf(false) }
    val header = "Zinema"

    LaunchedEffect(Unit) {
        val savedEmail = readEmail(context)
        loginScreenViewModel.enterEmail(savedEmail)
    }

    Scaffold(
        topBar = { MyTopBar(header) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "¡Bienvenido a tu App!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Box() {
                    Image(
                        contentDescription = "Logo",
                        painter = painterResource(R.drawable.logo_app),
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Introduce tus datos:",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = loginScreenViewModel.email,
                    onValueChange = { enteredEmail -> loginScreenViewModel.enterEmail(enteredEmail = enteredEmail) },
                    label = {
                        Text(
                            text = "Email"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "tucorreo@gmail.com"

                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = MaterialTheme.colorScheme.outline,
                        unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                        focusedBorderColor = MaterialTheme.colorScheme.outline,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                    )

                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = loginScreenViewModel.password,
                    onValueChange = { enteredPassword ->
                        loginScreenViewModel.enterPassword(
                            enteredPassword
                        )
                    },
                    label = {
                        Text(
                            text = "Contraseña"
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = MaterialTheme.colorScheme.outline,
                        unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                        focusedBorderColor = MaterialTheme.colorScheme.outline,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (loginScreenViewModel.email.isBlank() || loginScreenViewModel.password.isBlank()) {
                            alertDialogLogIn.value = true
                        } else {
                            navigator.toFilmList()
                        }
                    },

                    modifier = Modifier.width(100.dp)
                ) {
                    Text(
                        text = "Log in"
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = { navigator.toEjemplo() },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(
                        text = "Sign Up"
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Has olvidado tu contraseña?",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 10.sp
                )
            }
        }
    }

    if (alertDialogLogIn.value) {
        AlertDialog(
            onDismissRequest = { alertDialogLogIn.value = false },
            title = {
                Text(
                    text = "ERROR"
                )
            },
            text = {
                Text(
                    text = "El email o la contraseña no puede estar vacío"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    alertDialogLogIn.value = false
                }) {
                    Text(
                        text = "OK"
                    )
                }
            }
        )
    }
}