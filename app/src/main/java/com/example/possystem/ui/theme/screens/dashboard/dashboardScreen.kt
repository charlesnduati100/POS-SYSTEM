package com.example.possystem.ui.theme.screens.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import data.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "POS Dashboard",
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { authViewModel.logout(navController, context) }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null, tint = if(selectedItem == 0) Color.Cyan else White) },
                    label = { Text("Home", color = White) }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = if(selectedItem == 1) Color.Cyan else White) },
                    label = { Text("Settings", color = White) }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = { Icon(Icons.Default.Person, contentDescription = null, tint = if(selectedItem == 2) Color.Cyan else White) },
                    label = { Text("Profile", color = White) }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF0F0F2E))
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(
                text = "Business Overview",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Revenue Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.7f)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Today's Revenue", color = Color.Gray, fontSize = 14.sp)
                    Text(
                        "KES 12,500",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Transactions: 45", color = Color.Cyan, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // KPIs Section
            Text(
                text = "Key Metrics",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetricItem(
                    modifier = Modifier.weight(1f),
                    title = "Products",
                    value = "128",
                    icon = Icons.Default.ShoppingCart,
                    color = Color(0xFF44DDFF)
                )
                MetricItem(
                    modifier = Modifier.weight(1f),
                    title = "Orders",
                    value = "342",
                    icon = Icons.AutoMirrored.Filled.List,
                    color = Color(0xFFFF66BB)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sales Performance with Area Chart
            Text(
                text = "Sales Performance",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.5f))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    SalesAreaChart(
                        data = listOf(10f, 40f, 25f, 60f, 45f, 85f, 70f),
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF44DDFF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pie Chart Section - Sales by Category
            Text(
                text = "Sales by Category",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PieChart(
                        data = mapOf(
                            "Electronics" to 40f,
                            "Fashion" to 30f,
                            "Groceries" to 20f,
                            "Others" to 10f
                        ),
                        colors = listOf(
                            Color(0xFF44DDFF),
                            Color(0xFFFF66BB),
                            Color(0xFF7722CC),
                            Color(0xFFCCAAFF)
                        ),
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        LegendItem("Electronics", Color(0xFF44DDFF))
                        LegendItem("Fashion", Color(0xFFFF66BB))
                        LegendItem("Groceries", Color(0xFF7722CC))
                        LegendItem("Others", Color(0xFFCCAAFF))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tables Section
            Text(
                text = "Inventory Overview",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // New Products Table
            ProductTable(
                title = "New Products",
                products = listOf(
                    ProductSummary("Gaming Mouse", "KES 2,500", "12"),
                    ProductSummary("LED Strip", "KES 1,200", "45"),
                    ProductSummary("Smart Watch", "KES 5,800", "8")
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // All Products Table (Top Sellers)
            ProductTable(
                title = "All Products (Top Sellers)",
                products = listOf(
                    ProductSummary("Mechanical Keyboard", "KES 8,500", "24"),
                    ProductSummary("Monitor Stand", "KES 3,200", "15"),
                    ProductSummary("USB-C Hub", "KES 2,800", "60"),
                    ProductSummary("Desk Mat", "KES 1,500", "40")
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1.0f).height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A0A2E))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Cyan)
                        Text("Add New", color = White, fontWeight = FontWeight.Medium)
                    }
                }
                Card(
                    modifier = Modifier.weight(1.0f).height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A0A2E))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Magenta)
                        Text("View All", color = White, fontWeight = FontWeight.Medium)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun MetricItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, color = Color.Gray, fontSize = 12.sp)
            Text(text = value, color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PieChart(
    data: Map<String, Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val total = data.values.sum()
    Canvas(modifier = modifier) {
        var startAngle = -90f
        data.values.forEachIndexed { index, value ->
            val sweepAngle = (value / total) * 360f
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true
            )
            startAngle += sweepAngle
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        Box(modifier = Modifier.size(10.dp).background(color, RoundedCornerShape(2.dp)))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = White, fontSize = 12.sp)
    }
}

@Composable
fun ProductTable(title: String, products: List<ProductSummary>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, color = White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Product", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(2f))
                Text("Price", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                Text("Stock", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            HorizontalDivider(color = Color.DarkGray, thickness = 0.5.dp, modifier = Modifier.padding(vertical = 8.dp))
            products.forEach { product ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(product.name, color = White, fontSize = 14.sp, modifier = Modifier.weight(2f))
                    Text(product.price, color = Color.Cyan, fontSize = 13.sp, modifier = Modifier.weight(1.5f))
                    Text(product.stock, color = White, fontSize = 14.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                }
            }
        }
    }
}

data class ProductSummary(val name: String, val price: String, val stock: String)

@Composable
fun SalesAreaChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    color: Color = Color.Cyan
) {
    Canvas(modifier = modifier) {
        if (data.isEmpty()) return@Canvas

        val spacing = size.width / (data.size - 1)
        val maxData = data.maxOrNull() ?: 1f
        val minData = 0f // Baseline

        val path = Path().apply {
            data.forEachIndexed { index, value ->
                val x = index * spacing
                val y = size.height - ((value - minData) / (maxData - minData)) * size.height
                if (index == 0) {
                    moveTo(x, y)
                } else {
                    lineTo(x, y)
                }
            }
        }

        val fillPath = Path().apply {
            addPath(path)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        // Draw the filled area
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(color.copy(alpha = 0.4f), Color.Transparent)
            )
        )

        // Draw the line
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 4.dp.toPx())
        )
        
        // Draw points
        data.forEachIndexed { index, value ->
            val x = index * spacing
            val y = size.height - ((value - minData) / (maxData - minData)) * size.height
            drawCircle(
                color = color,
                radius = 4.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    Dashboard(rememberNavController())
}
