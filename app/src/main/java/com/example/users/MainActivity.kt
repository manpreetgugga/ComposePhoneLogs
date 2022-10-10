package com.example.users

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.users.base.viewmodels.ContactsViewModel
import com.example.users.ui.composables.Composeable.BottomNavigation
import com.example.users.ui.composables.Composeable.NavigationGraph
import com.example.users.ui.theme.UsersTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this@MainActivity)[ContactsViewModel::class.java]
        viewModel.fetchUserContacts()
        viewModel.fetchCallLogs()
        viewModel.fetchMessages()
        Handler(Looper.getMainLooper()).postDelayed({
            setContent {
                UsersTheme {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Navigation()
                    }
                }
            }
        },2000)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Navigation() {
        val viewModel = ViewModelProvider(this@MainActivity)[ContactsViewModel::class.java]
        val navController = rememberNavController()
        Scaffold(bottomBar = { BottomNavigation(navController) }) {
            NavigationGraph(navController = navController,viewModel.contactsList.value!!,viewModel.callLogsList.value!!,viewModel.messages.value!!)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        UsersTheme {
            Navigation()
        }
    }
}

