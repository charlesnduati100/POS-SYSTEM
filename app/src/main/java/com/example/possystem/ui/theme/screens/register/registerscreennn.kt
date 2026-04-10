package com.example.possystem.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.possystem.R
import com.example.possystem.navigation.ROUTE_LOGIN
import data.AuthViewModel

// Anime color palette
private val AnimeDeepNavy = Color(0xFF0A0A1A)
private val AnimeDarkPurple = Color(0xFF0F0F2E)
private val AnimeDeepPurple = Color(0xFF1A0A2E)
private val AnimePurpleBorder = Color(0xFF5533AA)
private val AnimeMagenta = Color(0xFFCC22AA)
private val AnimePurple = Color(0xFF7722CC)
private val AnimeCyan = Color(0xFF44DDFF)
private val AnimePink = Color(0xFFFF66BB)
private val AnimeLightPurple = Color(0xFFCCAAFF)
private val AnimeMutedPurple = Color(0xFF7744AA)
private val AnimeFieldBg = Color(0x99200A50)
private val AnimeFieldBgBlue = Color(0x990A1432)

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(AnimeDarkPurple, AnimeDeepPurple, AnimeDeepNavy)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            // Avatar with glowing ring
            Box(contentAlignment = Alignment.Center) {
                // Outer glow ring
                Box(
                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape)
                        .border(1.dp, AnimeCyan.copy(alpha = 0.3f), CircleShape)
                )
                // Inner border ring
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, AnimePurple.copy(alpha = 0.6f), CircleShape)
                )
                // Avatar image
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .border(2.dp, AnimePurpleBorder, CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Japanese subtitle
            Text(
                text = "WELCOME",
                fontSize = 11.sp,
                letterSpacing = 3.sp,
                color = AnimeCyan,
            )

            Text(
                text = "Register Here",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFF44AA), Color(0xFFAA44FF), Color(0xFF44AAFF))
                    )
                )
            )

            Text(
                text = "Create an account to continue",
                fontSize = 11.sp,
                color = Color(0xFF44DDFF),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Username field
            AnimeTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                placeholder = "Enter Username",
                icon = Icons.Default.Person,
                iconTint = Color(0xFF44DDFF),
                fieldBackground = AnimeFieldBg,
                borderColor = Color(0xFF4422AA)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email field
            AnimeTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Enter Email",
                icon = Icons.Default.Email,
                iconTint = Color(0xFF44DDFF),
                fieldBackground = AnimeFieldBg,
                borderColor = Color(0xFF4422AA)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Phone field
            AnimeTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number",
                placeholder = "Enter Phone Number",
                icon = Icons.Default.Phone,
                iconTint = Color(0xFF44DDFF),
                fieldBackground = AnimeFieldBg,
                borderColor = Color(0xFF4422AA)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password field
            AnimeTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter Password",
                icon = Icons.Default.Lock,
                iconTint = AnimeCyan,
                fieldBackground = AnimeFieldBgBlue,
                borderColor = Color(0xFF224488),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password field
            AnimeTextField(
                value = confirmpassword,
                onValueChange = { confirmpassword = it },
                label = "Confirm Password",
                placeholder = "Confirm Password",
                icon = Icons.Default.Check,
                iconTint = Color(0xFF44FFCC),
                fieldBackground = AnimeFieldBgBlue,
                borderColor = Color(0xFF224488),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    authViewModel.signup(
                        username = username,
                        email = email,
                        password = password,
                        confirmpassword = confirmpassword,
                        phoneNumber = phoneNumber,
                        navController = navController,
                        context = context
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AnimePurple,
                )
            ) {
                Text(
                    text = "Register",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Already registered?",
                color = AnimeCyan,
                fontSize = 16.sp
            )
            Text(
                text = "→ Login here ←",
                color = AnimePink,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { navController.navigate(ROUTE_LOGIN) }
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun AnimeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    fieldBackground: Color,
    borderColor: Color,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(fieldBackground, RoundedCornerShape(10.dp)),
        label = {
            Text(text = label, color = Color.White, fontSize = 10.sp, letterSpacing = 0.5.sp)
        },
        placeholder = {
            Text(text = placeholder, color = Color(0xFFCCAAFF), fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = borderColor,
            focusedBorderColor = iconTint.copy(alpha = 0.8f),
            unfocusedTextColor = Color(0xFFCCAAFF),
            focusedTextColor = Color.White,
            cursorColor = iconTint,
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
