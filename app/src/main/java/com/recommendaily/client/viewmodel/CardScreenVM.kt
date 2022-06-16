package com.recommendaily.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.recommendaily.client.model.CardData

class CardScreenVM : ViewModel() {

    /**
     * Firebase Storage reference
     */
    private val storageRef = FirebaseStorage.getInstance().reference

    /**
     * [MutableLiveData] of [MutableList] with data for cards (id, title, price, image url, etc.)
     */
    private val _cardData = MutableLiveData<MutableList<CardData>>()
    val cardData: LiveData<MutableList<CardData>> = _cardData

    /**
     * [MutableList] of data with swiped cards
     */
    private val _swipedCardData = mutableListOf<CardData>()

    init {
        _cardData.value = provideCardInfo()
    }

    /**
     * This function provides cards data.
     *
     * Currently, provides prepared in advance sample data.
     */
    private fun provideCardInfo(): MutableList<CardData> {
        return mutableListOf(
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/945360/header.jpg?t=1598556351"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/730/header.jpg?t=1592263625"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/1097150/header_alt_assets_0.jpg?t=1602172542"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/1158310/header.jpg?t=1602596444"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/1145360/header.jpg?t=1601510167"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/1085660/header.jpg?t=1598982557"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/1172620/header.jpg?t=1596057038"
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
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/359550/header.jpg?t=1602605478"
            ),
            CardData(
                id = 8,
                title = "Warframe",
                subtitle = "Single-player Controller SupportRemote PhoneRemote",
                release_date = "Mar 25, 2013",
                popularity = "85%",
                developer = "Digital Extremes",
                user_count = "6,342",
                price = "Free",
                description = "Destiny 2 — компьютерная игра в жанре шутера от первого лица с элементами MMORPG, разработанная американской компанией Bungie.",
                imageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/230410/header.jpg?t=1601568920"
            )
        )
    }

    /**
     * Adds [dataToMemorize] inside [_swipedCardData]. Data will not be added if [_swipedCardData]
     * have copy of it already.
     *
     * It's assumed that the function will be used to transfer card data after a swipe.
     * @param dataToMemorize [CardData] to memorize.
     */
    fun memorizeSwipeResult(
        dataToMemorize: CardData
    ) {
        if (!_swipedCardData.contains(dataToMemorize)) {
            _swipedCardData.add(dataToMemorize)
        }
    }

//    fun downloadImageUrls(data: List<CardData>, callBack: DownloadImagesUrlsCallback) {
//        val ids = getCardIds(data)
//
//        for (id in ids) {
//            val imgRef = storageRef.child("$id.jpg")
//            imgRef.downloadUrl.addOnSuccessListener {
//                _imagesUrl.value?.add(it.toString())
//                Log.d("Upload_From_Storage", "Success: id=$id, URL=$it, Ids=${ids.size}")
//                if (id == ids.size) {
//                    Log.d("Upload_From_Storage", "Start Callback")
//                    callBack.onCallback(imagesUrl.value!!)
//                }
//            }.addOnFailureListener {
//                Log.e("Upload_From_Storage", "Failure")
//            }
//        }
//    }
}































