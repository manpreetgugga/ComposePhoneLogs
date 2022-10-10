package com.example.users.ui.composables

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.example.users.domain.entities.PhoneCallLog

object ContactComposable {

    @Composable
    fun PhoneLogs(logs: ArrayList<PhoneCallLog>) {
        LazyColumn {
            items(logs.size) { index ->
                PhoneItem(logs[index])
            }
        }
    }

    @Composable
    fun Contacts(contacts: ArrayList<Contact>) {
        LazyColumn {
            items(contacts.size) { index ->
                ContactItem(contacts[index])
            }
        }
    }

    @Composable
    fun Messages(messages: ArrayList<Message>) {
        LazyColumn {
            items(messages.size) { index ->
                MessageItem(messages[index])
            }
        }
    }


    @Composable
    fun ContactItem(contact: Contact) {
        Card(
            shape = RoundedCornerShape(8.dp), modifier = Modifier
                .padding(top = 8.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    contact.name?.let {
                        Text(text = it.ifEmpty { "Unknown" }, color = Color.Black, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = if (contact.mobileNumber.isNotEmpty()) contact.mobileNumber[0].replace(" ", "").ifEmpty { "Unknown" } else "Unknown",
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp)
                    }
                }
            }
        }
    }

    @Composable
    fun MessageItem(message: Message) {
        Card(
            shape = RoundedCornerShape(8.dp), modifier = Modifier
                .padding(top = 8.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    message.address?.let {
                        Text(text = it.ifEmpty { "Unknown" }, color = Color.Black, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 0.dp, vertical = 4.dp))
                        Text(
                            text = message.messsage!!.ifEmpty { "Unknown" },
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun PhoneItem(call: PhoneCallLog) {
        Card(
            shape = RoundedCornerShape(8.dp), modifier = Modifier
                .padding(top = 8.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text(text = if (call.name.isNullOrEmpty()) "Unknown" else call.name, color = Color.Black, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = call.phNum ?: "", color = Color.Black, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }

}