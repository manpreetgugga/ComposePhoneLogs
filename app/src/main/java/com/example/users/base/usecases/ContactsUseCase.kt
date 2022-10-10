package com.example.users.base.usecases

import com.example.users.domain.entities.PhoneCallLog
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.example.users.domain.entities.ResultData
import kotlinx.coroutines.flow.Flow

abstract class
ContactsUseCase {

    abstract fun fetchContactsList() : Flow<ResultData<ArrayList<Contact>>>

    abstract fun fetchCallLogs() : Flow<ResultData<ArrayList<PhoneCallLog>>>

    abstract fun fetchMessages(): Flow<ResultData<ArrayList<Message>>>

}