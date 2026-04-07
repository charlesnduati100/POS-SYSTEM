package com.example.possystem.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    var selectedItem by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopAppBar(title = {Text(text = "POS Dashboard",color = White, fontSize = 40.sp,

            fontWeight = FontWeight.Bold)}, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)) },
        bottomBar = { NavigationBar(containerColor = Color.Black){
            NavigationBarItem(
                selected = selectedItem == 0,
                onClick = {selectedItem = 0 },
                icon = { Icon(Icons.Default.Home,contentDescription = null) },
                label = { Text("Home",color = White)}
            )
            NavigationBarItem(selected = selectedItem == 0,
                onClick = {selectedItem = 0 },
                icon = { Icon(Icons.Default.Settings,contentDescription = null) },
                label = { Text("Settings", color = White)}
            )
            NavigationBarItem(selected = selectedItem == 0,
                onClick = {selectedItem = 0 },
                icon = { Icon(Icons.Default.Person,contentDescription = null) },
                label = { Text("Person",color = White)}
            )
        } }
    )
    {padding -> Column(modifier = Modifier.padding(padding)) {
        Text(text = "Business Overview", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Today's Revenue",color = White)
                Text(
                    "KES 12,500",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                    )
                Spacer(modifier = Modifier.height(10.dp))
                Text("Transactions: 45", color = Color.White)
            }
        }
        Row() {
            Card(
                modifier = Modifier.padding(20.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {Text(text = "New Product",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White

            ) }
            Card(modifier = Modifier.padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                shape = RectangleShape
            ) {Text(text = "Products",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
                ) }
        }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview(){
    Dashboard()
}