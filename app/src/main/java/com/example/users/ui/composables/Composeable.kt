package com.example.users.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.users.R
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.example.users.domain.entities.PhoneCallLog
import com.google.accompanist.pager.ExperimentalPagerApi

object Composeable {

    @androidx.compose.runtime.Composable
    fun ContactsScreen(contacts: ArrayList<Contact>) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_gray))
                .wrapContentSize(align = Alignment.Center)
        ) {
            ContactComposable.Contacts(contacts = contacts)
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @androidx.compose.runtime.Composable
    fun CallLogsScreen(phoneCallLog: ArrayList<PhoneCallLog>) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_gray))
                .wrapContentSize(align = Alignment.Center)
        ) {
            LogsComposable.TabLayout(logs = phoneCallLog)
        }
    }

    @androidx.compose.runtime.Composable
    fun MessageScreen(messages: ArrayList<Message>) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_gray))
                .wrapContentSize(align = Alignment.Center)
        ) {
            ContactComposable.Messages(messages)
        }
    }

    @androidx.compose.runtime.Composable
    fun NavigationGraph(navController: NavHostController, contacts: ArrayList<Contact>, callLogs: ArrayList<PhoneCallLog>, messages: ArrayList<Message>) {
        NavHost(navController, startDestination = BottomNavItem.Contacts.screen_route) {
            composable(BottomNavItem.Contacts.screen_route) {
                ContactsScreen(contacts)
            }
            composable(BottomNavItem.CallLogs.screen_route) {
                CallLogsScreen(callLogs)
            }
            composable(BottomNavItem.Messages.screen_route) {
                MessageScreen(messages)
            }
        }
    }

    @androidx.compose.runtime.Composable
    fun BottomNavigation(navController: NavHostController) {
        val items = listOf(BottomNavItem.Contacts, BottomNavItem.CallLogs, BottomNavItem.Messages)
        BottomNavigation(backgroundColor = colorResource(id = R.color.purple_200)) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(text = item.title, fontSize = 9.sp, color = Color.White) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}