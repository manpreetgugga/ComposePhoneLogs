package com.example.users.domain.implementations

import com.example.users.base.repositories.ContactsRepository
import com.example.users.base.usecases.ContactsUseCase
import com.example.users.domain.entities.PhoneCallLog
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.example.users.domain.entities.ResultData
import kotlinx.coroutines.flow.Flow

// Contacts UseCases Implementation Version 1
class ContactsUseCaseImpl(private var contactsRepository: ContactsRepository) : ContactsUseCase() {

    override fun fetchContactsList(): Flow<ResultData<ArrayList<Contact>>> = contactsRepository.fetchContactsList()

    override fun fetchCallLogs(): Flow<ResultData<ArrayList<PhoneCallLog>>> = contactsRepository.fetchCallLogs()

    override fun fetchMessages(): Flow<ResultData<ArrayList<Message>>> = contactsRepository.fetchSmsInboxConversions()
}