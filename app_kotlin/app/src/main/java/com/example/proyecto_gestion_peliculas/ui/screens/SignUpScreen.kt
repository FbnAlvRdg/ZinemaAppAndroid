package com.example.proyecto_gestion_peliculas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.data.saveEmail
import com.example.proyecto_gestion_peliculas.ui.theme.PureWhite
import com.example.proyecto_gestion_peliculas.core.checkEmail
import com.example.proyecto_gestion_peliculas.core.checkEqualPassword
import com.example.proyecto_gestion_peliculas.core.checkPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(back: () -> Unit) {
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmar = remember { mutableStateOf("") }
    val telefono = remember { mutableStateOf("") }
    val checkedRecuerdame = remember { mutableStateOf(false) }
    val checkedTerms = remember { mutableStateOf(false) }
    val alertDialogEmail = remember { mutableStateOf(false) }
    val alertDialogPassword = remember { mutableStateOf(false) }
    val alertDialogEqualPas = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "¡Registrate!",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { nuevoEmail -> email.value = nuevoEmail },
                label = {
                    Text(
                        text = "Email"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = userName.value,
                onValueChange = { nuevoUsername -> userName.value = nuevoUsername },
                label = {
                    Text(
                        text = "Username"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = password.value,
                onValueChange = { nuevoPassword -> password.value = nuevoPassword },
                label = {
                    Text(
                        text = "Contraseña"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = passwordConfirmar.value,
                onValueChange = { nuevoPasswordConfirmar ->
                    passwordConfirmar.value = nuevoPasswordConfirmar
                },
                label = {
                    Text(
                        text = "Repite la contraseña"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = telefono.value,
                onValueChange = { nuevoTelefono ->
                    telefono.value = nuevoTelefono
                },
                label = {
                    Text(
                        text = "Teléfono"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedRecuerdame.value,
                onCheckedChange = { checkedRecuerdame.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text(
                text = "Recuérdame",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedTerms.value,
                onCheckedChange = { checkedTerms.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Text(
                text = "Acepto los términos y condiciones",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .width(120.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(

                text = "o",
                color = MaterialTheme.colorScheme.onBackground
            )
            HorizontalDivider(
                Modifier
                    .padding(horizontal = 16.dp)
                    .width(120.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "Continuar con:",
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier.background(
                    color = PureWhite,
                    shape = CircleShape
                )
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    contentDescription = "Registrarse con Google",
                    painter = painterResource(R.drawable.icono_google)
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            IconButton(
                onClick = {},
                modifier = Modifier.background(
                    color = PureWhite,
                    shape = CircleShape
                )
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    contentDescription = "Registrarse con Apple",
                    painter = painterResource(R.drawable.icono_apple)
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            IconButton(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier.background(
                    color = PureWhite,
                    shape = CircleShape
                )
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    contentDescription = "Registrarse con X",
                    painter = painterResource(R.drawable.icono_x)
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (!checkEmail(email.value)) {
                        alertDialogEmail.value = true
                        return@Button
                    }

                    if (!checkPassword(password.value)) {
                        alertDialogPassword.value = true

                        return@Button
                    }

                    if (!checkEqualPassword(password.value, passwordConfirmar.value)) {
                        alertDialogEqualPas.value = true
                        return@Button
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        saveEmail(context, email.value)
                    }

                    back()
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Registrarse"
                )
            }
        }
    }

    if (alertDialogEmail.value) {
        AlertDialog(
            onDismissRequest = { alertDialogEmail.value = false },
            title = {
                Text(
                    text = "ERROR"
                )
            },
            text = {
                Text(
                    text = "El email introducido NO es válido"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    alertDialogEmail.value = false
                }) {
                    Text(
                        text = "OK"
                    )
                }
            }
        )
    }

    if (alertDialogPassword.value) {
        AlertDialog(
            onDismissRequest = { alertDialogPassword.value = false },
            title = {
                Text(
                    text = "ERROR"
                )
            },
            text = {
                Text(
                    text = "La contraseña introducida NO es válida"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    alertDialogPassword.value = false
                }) {
                    Text(
                        text = "OK"
                    )
                }
            }
        )
    }

    if (alertDialogEqualPas.value) {
        AlertDialog(
            onDismissRequest = { alertDialogEqualPas.value = false },
            title = {
                Text(
                    text = "ERROR"
                )
            },
            text = {
                Text(
                    text = "Las contraseñas NO coinciden"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    alertDialogEqualPas.value = false
                }) {
                    Text(
                        text = "OK"
                    )
                }
            }
        )
    }
}

