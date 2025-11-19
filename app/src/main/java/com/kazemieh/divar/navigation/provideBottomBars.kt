package com.kazemieh.divar.navigation

import com.kazemieh.create_ads.navigation.createAdsRoute
import com.kazemieh.category.navigation.categoryRoute
import com.kazemieh.chat.navigation.chatRoute
import com.kazemieh.home.navigation.homeRoute
import com.kazemieh.main.model.BottomBarItem
import com.kazemieh.profile.navigation.profileRoute
import com.kazemieh.ui.R

fun provideBottomBars(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(R.string.my_divar, R.drawable.ic_user, profileRoute),
        BottomBarItem(R.string.chat, R.drawable.ic_chat, chatRoute),
        BottomBarItem(R.string.create_ads, R.drawable.ic_plus, createAdsRoute),
        BottomBarItem(R.string.category, R.drawable.ic_category, categoryRoute),
        BottomBarItem(R.string.ads, R.drawable.ic_home, homeRoute)
    )
}