package com.example.calculater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    AppNavHost(navController, innerPadding)
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val history = remember { mutableStateOf(mutableListOf<String>()) }
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") { Screen(history =  history, context = LocalContext.current, navController = navController, modifier = Modifier.padding(innerPadding)) }
        composable("History") { HistoryScreen(history = history, navController = navController, modifier = Modifier.padding(innerPadding)) }
        composable("Options") { OptionsScreen(navController = navController, modifier = Modifier.padding(innerPadding)) }
        composable("About Us") { inDevScreen(innerPadding = innerPadding, navController = navController) }
        
        composable("Length") { OptionTemplate(navController = navController, option = "Length", modifier = Modifier.padding(innerPadding), context = LocalContext.current) }
        composable("Capacity") { OptionTemplate(navController = navController, option = "Capacity", modifier = Modifier.padding(innerPadding), context = LocalContext.current) }
        composable("Weight") { OptionTemplate(navController = navController, option = "Weight", modifier = Modifier.padding(innerPadding), context = LocalContext.current) }
        composable("Temp") { OptionTemplate(navController = navController, option = "Temp", modifier = Modifier.padding(innerPadding), context = LocalContext.current) }
        composable("Volume") { inDevScreen(innerPadding = innerPadding, navController = navController) }
        composable("Area") {  inDevScreen(innerPadding = innerPadding, navController = navController) }
        
    }
}

fun NavController.goTo(destination: String) {
    navigate(destination)
}

fun eval(expression: String): Double {
    return ExpressionBuilder(expression).build().evaluate()
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    CalculaterTheme {
//        MainScreen()
//    }
//}
