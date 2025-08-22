package ru.oneme.app.ui.screens.fake_max_login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.oneme.app.R
import ru.oneme.app.ui.theme.AppTheme

@Composable
fun loginScreen() {
    AppTheme {
        var phoneNumber by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(R.drawable.bg_gradient),
                contentDescription = "Фоновый градиент",
                contentScale = ContentScale.Crop
            )

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
                containerColor = Color.Transparent
            ) { innerPadding ->
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val scrollState = rememberScrollState()
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .verticalScroll(scrollState)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(10.dp),
                                    painter = painterResource(id = R.drawable.cute_logo),
                                    contentDescription = "Няшный логотип приложения \"MAX\"",
                                )

                                IconButton(
                                    onClick = { navController.navigate("help") },
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .size(52.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.size(26.dp),
                                        imageVector = Icons.AutoMirrored.Rounded.HelpOutline,
                                        contentDescription = "Кнопка \"Помощь\"",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }

                            Text(
                                text = "С каким номером\nтелефона хотите войти?",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 28.dp, top = 24.dp, end = 28.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Text(
                                text = "На него придёт СМС с кодом",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 28.dp, top = 8.dp, end = 28.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            PhoneInputField(
                                phoneNumber = phoneNumber,
                                onPhoneNumberChange = { phoneNumber = it }
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            val context = LocalContext.current
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Сервер перегружен", Toast.LENGTH_SHORT).show()
                                    keyboardController?.hide()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .padding(horizontal = 16.dp),
                                colors = ButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                                ),
                                shape = RoundedCornerShape(16.dp),
                                enabled = phoneNumber.length == 10
                            ) {
                                Text(
                                    text = "Продолжить",
                                    fontSize = 16.sp
                                )
                            }

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                                        append("Нажимая «Продолжить», вы принимаете ")
                                    }
                                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                                        append("политику конфиденциальности")
                                    }
                                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                                        append(" и ")
                                    }
                                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                                        append("пользовательское соглашение")
                                    }
                                },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    composable("help") {
                        HelpScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Назад",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun PhoneInputField(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_flag_russia),
            contentDescription = "Russian flag",
            modifier = Modifier
                .size(26.dp)
                .padding(end = 4.dp)
        )

        Text(
            text = "+7",
            modifier = Modifier.padding(end = 4.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        TextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }
                if (digits.length <= 10) onPhoneNumberChange(digits)
            },
            placeholder = { Text("123 456-78-90") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            visualTransformation = VisualTransformation { text ->
                val formatted = buildString {
                    text.forEachIndexed { i, char ->
                        when (i) {
                            0 -> append(char)
                            3 -> append(" $char")
                            6 -> append("-$char")
                            8 -> append("-$char")
                            else -> append(char)
                        }
                    }
                }
                TransformedText(
                    AnnotatedString(formatted),
                    object : OffsetMapping {
                        override fun originalToTransformed(offset: Int): Int {
                            return when {
                                offset <= 3 -> offset
                                offset <= 6 -> offset + 1
                                offset <= 8 -> offset + 2
                                offset <= 10 -> offset + 3
                                else -> formatted.length
                            }
                        }

                        override fun transformedToOriginal(offset: Int): Int {
                            return when {
                                offset <= 3 -> offset
                                offset <= 7 -> offset - 1
                                offset <= 10 -> offset - 2
                                offset <= 12 -> offset - 3
                                else -> text.length
                            }
                        }
                    }
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground
            ),
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
    }
}