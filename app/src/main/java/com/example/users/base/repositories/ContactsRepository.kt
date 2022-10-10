package com.example.users.base.repositories

import com.example.users.domain.entities.PhoneCallLog
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.example.users.domain.entities.ResultData
import kotlinx.coroutines.flow.Flow

abstract class ContactsRepository {

    abstract fun fetchContactsList() : Flow<ResultData<ArrayList<Contact>>>

    abstract fun fetchCallLogs(): Flow<ResultData<ArrayList<PhoneCallLog>>>

    abstract fun fetchSmsInboxConversions(): Flow<ResultData<ArrayList<Message>>>

}