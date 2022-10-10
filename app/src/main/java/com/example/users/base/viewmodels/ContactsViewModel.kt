package com.example.users.base.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.users.domain.entities.PhoneCallLog
import com.example.users.domain.entities.Contact
import com.example.users.domain.entities.Message
import com.mobile.phonelogs.domain.implementations.ContactRepositoryImpl
import com.example.users.domain.implementations.ContactsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context get() = getApplication<Application>().applicationContext

    val contactsList: MutableLiveData<ArrayList<Contact>> by lazy {
        MutableLiveData<ArrayList<Contact>>()
    }


    val callLogsList = MutableLiveData<ArrayList<PhoneCallLog>>().apply {
        MutableLiveData<ArrayList<PhoneCallLog>>()
    }

    val messages = MutableLiveData<ArrayList<Message>>().apply {
    }


    fun fetchUserContacts() {
        viewModelScope.launch(Dispatchers.Default) {
            ContactsUseCaseImpl(ContactRepositoryImpl(context.contentResolver)).fetchContactsList().collect {
                it.data?.apply {
                    contactsList.postValue(this)
                }
            }
        }
    }

    fun fetchMessages() {
        viewModelScope.launch(Dispatchers.Default) {
            ContactsUseCaseImpl(ContactRepositoryImpl(context.contentResolver)).fetchMessages().collect {
                it.data?.apply {
                    messages.postValue(this)
                }
            }
        }
    }

    fun fetchCallLogs() {
        viewModelScope.launch(Dispatchers.Default) {
            ContactsUseCaseImpl(ContactRepositoryImpl(context.contentResolver)).fetchCallLogs().collect {
                it.data?.apply {
                    callLogsList.postValue(this)
                }
            }
        }
    }
}
