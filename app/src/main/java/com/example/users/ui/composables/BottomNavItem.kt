package com.example.users.ui.composables

import com.example.users.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Contacts : BottomNavItem("Contacts", R.drawable.ic_baseline_contacts_24, "contacts")
    object CallLogs : BottomNavItem("Call Logs", R.drawable.ic_baseline_phone_call_logs, "call_logs")
    object Messages : BottomNavItem("Sms Inbox", R.drawable.ic_baseline_message_24, "sms_inbox")
}