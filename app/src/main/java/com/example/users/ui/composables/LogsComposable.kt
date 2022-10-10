package com.example.users.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.users.R
import com.example.users.domain.entities.PhoneCallLog
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


object LogsComposable {

    @ExperimentalPagerApi
    @Composable
    fun TabLayout(logs: ArrayList<PhoneCallLog>) {
        val pagerState = rememberPagerState(pageCount = 3)
        Column(modifier = Modifier.background(Color.White)) {
            Tabs(pagerState = pagerState)
            TabsContent(pagerState = pagerState, logs)
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun Tabs(pagerState: PagerState) {
        val list = listOf(
            "Incoming" to Icons.Default.Home,
            "Outgoing" to Icons.Default.ShoppingCart,
            "Missed" to Icons.Default.Settings
        )
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            contentColor = colorResource(id = R.color.purple_200),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 2.dp,
                    color = colorResource(id = R.color.purple_200)
                )
            }
        ) {
            list.forEachIndexed { index, _ ->
                Tab(
                    text = {
                        Text(
                            list[index].first,
                            color = if (pagerState.currentPage == index) colorResource(id = R.color.purple_200) else Color.Black
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun TabsContent(pagerState: PagerState, logs: ArrayList<PhoneCallLog>) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> TabContentScreen(logs, page)
                1 -> TabContentScreen(logs, page)
                2 -> TabContentScreen(logs, page)
            }
        }
    }

    // on below line we are creating a Tab Content
// Screen for displaying a simple text message.
    @Composable
    fun TabContentScreen(logs: ArrayList<PhoneCallLog>, page: Int) {
        LazyColumn(Modifier.background(color = colorResource(id = R.color.light_gray))) {
            items(logs.size) { index ->
                if (page == logs[index].viewType) {
                    ContactComposable.PhoneItem(logs[index])
                }
            }
        }
    }
}