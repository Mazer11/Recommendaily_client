package com.recommendaily.client.ui.settings

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.RecommendailyApp
import com.recommendaily.client.repository.DataStoreRepository
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    application: RecommendailyApp
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val datastore = DataStoreRepository(context)

    var locale = datastore.readLocaleFromDataStore.collectAsState(initial = "").value
    var localeName by remember { mutableStateOf("") }
    var expandDropDownMenu by remember { mutableStateOf(false) }

    val isDarkTheme = application.isDarkTheme

    localeName = when (locale) {
        "en" -> {
            ChangeLocale(lang = "en")
            "English"
        }
        "ru" -> {
            ChangeLocale(lang = "ru")
            "Русский"
        }
        else -> "English"
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        maxLines = if (locale == "en") 1
                        else 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.dark_mode),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Switch(
                    checked = isDarkTheme.value,
                    onCheckedChange = {
                        application.switchAppTheme()
                        scope.launch {
                            datastore.editThemePreference()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Language",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = localeName,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { expandDropDownMenu = true }
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp)
                )
                DropdownMenu(
                    expanded = expandDropDownMenu,
                    onDismissRequest = { expandDropDownMenu = false },
                    offset = DpOffset(x = 8.dp, y = (-8).dp),
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "English") },
                        onClick = {
                            scope.launch {
                                datastore.editLocalePreference("en")
                            }
                            locale = "en"
                            localeName = "English"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Русский") },
                        onClick = {
                            scope.launch {
                                datastore.editLocalePreference("ru")
                            }
                            locale = "ru"
                            localeName = "Русский"
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChangeLocale(
    lang: String
) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val configuration = LocalConfiguration.current
    configuration.setLocale(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        configuration.setLocale(locale)
    else
        configuration.locale = locale
    val resources = LocalContext.current.resources
    resources.updateConfiguration(configuration, resources.displayMetrics)
}