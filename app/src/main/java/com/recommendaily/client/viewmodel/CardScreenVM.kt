package com.recommendaily.client.viewmodel

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.recommendaily.client.DownloadImagesUrlsCallback
import com.recommendaily.client.model.CardData
import kotlinx.coroutines.launch

class CardScreenVM : ViewModel() {

    /**
     * List of items to show on CardScreen
     */
    private val storageRef = FirebaseStorage.getInstance().reference

    private val imagesUrl = mutableListOf<String>()

    private val cardData = mutableListOf<CardData>()

    init {
        cardData.addAll(provideCardInfo())
    }

    fun getCardsData(): List<CardData> {
        return cardData
    }

    fun getImages(): List<String> {
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/945360/header.jpg?t=1598556351")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/730/header.jpg?t=1592263625")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/1097150/header_alt_assets_0.jpg?t=1602172542")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/1158310/header.jpg?t=1602596444")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/1085660/header.jpg?t=1598982557")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/1145360/header.jpg?t=1601510167")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/1172620/header.jpg?t=1596057038")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/359550/header.jpg?t=1602605478")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/359550/header.jpg?t=1602605478")
        imagesUrl.add("https://steamcdn-a.akamaihd.net/steam/apps/230410/header.jpg?t=1601568920")

        return imagesUrl
    }

    /**
     * Gets data from server
     */
    private fun provideCardInfo(): List<CardData> {
        val mList = mutableListOf(
            CardData(
                id = 0,
                title = "Among Us",
                subtitle = "Online PvP Coop",
                release_date = "Nov 16, 2018",
                popularity = "96%",
                developer = "Innersloth",
                user_count = "151,281",
                price = "4.99\$",
                description = "Crusader Kings 3 — компьютерная игра в жанре глобальной стратегии в реальном времени, разрабатываемая студией Paradox Interactive.",
            ),
            CardData(
                id = 1,
                title = "Counter-Strike: Global Offensive",
                subtitle = "Steam Controller",
                release_date = "Aug 21, 2012",
                popularity = "88%",
                developer = "Valve, Hidden Path Entertainment",
                user_count = "90,780",
                price = "Free",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 2,
                title = "Fall Guys: Ultimate Knockout",
                subtitle = "MMO PvP Co-op",
                release_date = "Aug 3, 2020",
                popularity = "84%",
                developer = "Mediatonic",
                user_count = "32,436",
                price = "19.99\$",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 3,
                title = "Crusader Kings III",
                subtitle = "Single player Online PvP",
                release_date = "Sep 1, 2020",
                popularity = "91%",
                developer = "Paradox Development Studio",
                user_count = "5,359",
                price = "49.99\$",
                description = "Crusader Kings 3 — компьютерная игра в жанре глобальной стратегии в реальном времени, разрабатываемая студией Paradox Interactive.",
            ),
            CardData(
                id = 4,
                title = "Hades",
                subtitle = "Single player Controller",
                release_date = "Sep 17, 2020",
                popularity = "98%",
                developer = "Supergiant Games",
                user_count = "17,940",
                price = "24.99\$",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",

                ),
            CardData(
                id = 5,
                title = "Destiny 2",
                subtitle = "MMO TPS Shooter",
                release_date = "Oct 1, 2019",
                popularity = "86%",
                developer = "Bungie",
                user_count = "284,689",
                price = "Free",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 6,
                title = "Sea of Thieves",
                subtitle = "PvP Coop Cross-Platform Multiplayer",
                release_date = "Jun 3, 2020",
                popularity = "88%",
                developer = "Rare Ltd",
                user_count = "4,703",
                price = "39.99\$",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 7,
                title = "Tom Clancy's Rainbow Six® Siege",
                subtitle = "Single-player Online PvP Co-op PurchasesPartial",
                release_date = "Dec 1, 2015",
                popularity = "89%",
                developer = "Ubisoft Montreal",
                user_count = "14,261",
                price = "19.99\$",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 8,
                title = "Dota 2",
                subtitle = "Steam Trading CardsSteam WorkshopSteamVR",
                release_date = "Jul 9, 2013",
                popularity = "79%",
                developer = "Valve",
                user_count = "14,202",
                price = "Free",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            ),
            CardData(
                id = 9,
                title = "Warframe",
                subtitle = "Single-player Controller SupportRemote PhoneRemote",
                release_date = "Mar 25, 2013",
                popularity = "85%",
                developer = "Digital Extremes",
                user_count = "6,342",
                price = "Free",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
            )
        )
        return mList.toList()
    }

    fun downloadImageUrls(data: List<CardData>, callBack: DownloadImagesUrlsCallback) {
        val ids = getCardIds(data)

        for (id in ids) {
            val imgRef = storageRef.child("$id.jpg")
            imgRef.downloadUrl.addOnSuccessListener {
                imagesUrl.add(it.toString())
                Log.d("Upload_From_Storage", "Success: id=$id, URL=$it, Ids=${ids.size}")
                if (id == ids.size) {
                    Log.d("Upload_From_Storage", "Start Callback")
                    callBack.onCallback(imagesUrl)
                }
            }.addOnFailureListener {
                Log.e("Upload_From_Storage", "Failure")
            }
        }
    }

    private fun getCardIds(data: List<CardData>): List<Int> {
        val ids = mutableListOf<Int>()
        for (item in data) {
            ids.add(item.id)
        }
        return ids
    }

}































