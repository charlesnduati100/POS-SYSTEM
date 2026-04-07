package com.example.possystem.ui.theme.screens.login

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.possystem.R
import com.example.possystem.navigation.ROUTE_LOGIN
import com.example.possystem.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black),
//        contentAlignment = Alignment.Center
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "login",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape).border(2.dp, color = White, CircleShape))
    Text(
        text = "Login Here",
        fontSize = 33.sp,

        fontWeight = FontWeight.Bold,
        color = White
    )


        OutlinedTextField(
            value = email,
            label = {Text(text = "Enter Email",color = White)},
            onValueChange = { email = it },
            leadingIcon = {Icon(Icons.Default.Email, contentDescription = null, tint = White) })
        OutlinedTextField(
            value = password,
            label = {Text(text = "Enter Password",color = White)},
            onValueChange = {password = it },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = White) })
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {}) { Text(text = "Login") }
        Text(text = "Don't Have an Account?", color = Green)
        Text(text = "Register Here", color = Yellow, modifier = Modifier.clickable{navController.navigate(
            ROUTE_REGISTER
        )})

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    LoginScreen(rememberNavController())
}
