package com.example.tinpet.screens.mainMenu

import com.example.tinpet.screens.LoginViewModel


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinpet.R
import com.example.tinpet.screens.LUserField
import com.example.tinpet.ui.theme.TinPetTheme
import com.example.tinpet.ui.theme.abrilFatface

@Composable
fun AddPetScreen(
    viewModel: LoginViewModel,
    onClick:() -> Unit
) {
    val signupEnable: Boolean by viewModel.signupEnable.observeAsState(initial = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LOGO SUPERIOR
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
        ) {
            // Logo TinPet
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
                //horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = if (isSystemInDarkTheme()) {
                        painterResource(R.drawable.icon_pawprint_black)
                    } else {
                        painterResource(R.drawable.icon_pawprint_white)
                    },
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 32.sp,
                    fontFamily = abrilFatface,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        // SIGNUP COMPONENTS
        Signup(Modifier,viewModel)

        // BOTÓN CREAR CUENTA
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(signupEnable){
                Button(
                    onClick = { viewModel.autenticate() },
                    enabled = true,
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.ManageAccounts,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = stringResource(R.string.addpet_access_ES)
                    )
                }
            }else{
                Button(
                    onClick = {viewModel.autenticate()},
                    enabled = true,
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        disabledBackgroundColor = MaterialTheme.colors.onSurface,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }

        }
    }
}



@Composable
fun Signup(modifier: Modifier, viewModel: LoginViewModel) {
    val number: String by viewModel.number.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val password2: String by viewModel.password2.observeAsState(initial = "")
    val verifyNumber: String by viewModel.verifyNumber.observeAsState(initial = "")


    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(15.dp))
        STitleText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        SUserField(verifyNumber) { viewModel.onVerifyNumberChanged(it) }
        Spacer(modifier = Modifier.padding(5.dp))
        Spacer(modifier = Modifier.padding(5.dp))
        Spacer(modifier = Modifier.padding(15.dp))
    }
}


@Composable
fun STitleText(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,
            text = (stringResource(R.string.addpet_ES)),
            fontSize = 32.sp,
            fontFamily = abrilFatface,
            color = MaterialTheme.colors.onBackground
        )
    }
}


@Composable
fun SUserField(number: String, onTextFieldChanged: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        OutlinedTextField(
            value = number,
            onValueChange = {
                //if (it.length <= 9)
                    onTextFieldChanged(it)
            },
            label = {
                Text(
                    text = stringResource(R.string.username_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.phone_ES),
                    color = MaterialTheme.colors.onBackground
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.PhoneAndroid,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
            if (isSystemInDarkTheme()) {
                SPrefixVisualTransformationDark("+34 | ")
            } else {
                SPrefixVisualTransformationLight("+34 | ")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.onBackground)
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPetScreenPreviewLT() {
    TinPetTheme(darkTheme = false) {
        AddPetScreen(viewModel = LoginViewModel(LocalContext.current),onClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPetScreenPreviewDT() {
    TinPetTheme(darkTheme = true) {
        AddPetScreen(viewModel = LoginViewModel(LocalContext.current),onClick = {})
    }
}

class SPrefixVisualTransformationDark(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.White)
        ) + text

        return TransformedText(transformedText, SPrefixOffsetMapping(prefix))
    }

}
class SPrefixVisualTransformationLight(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = AnnotatedString(
            prefix,
            SpanStyle(Color.Black)
        ) + text

        return TransformedText(transformedText, SPrefixOffsetMapping(prefix))
    }

}
class SPrefixOffsetMapping(private val prefix: String) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + prefix.length

    override fun transformedToOriginal(offset: Int): Int {
        val delta = offset - prefix.length
        return if (delta < 0) 0 else delta
    }
}
