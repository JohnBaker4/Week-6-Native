package com.example.week6native.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateCalendar: () -> Unit
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateHome) {
                        Icon(Icons.Filled.List, contentDescription = "Go to list")
                    }
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tässä itse vipu tummalle teemalle
                Text("Dark theme")
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { onToggleTheme() }
                )
            }

            // "Back" on nyt tässä tapauksessa nappi HomeScreenille eli tehtävälistalle
            Button(onClick = onNavigateHome) {
                Text("Back")
            }

            // Nappi kalenterillekin niin pääsee heti ihailemaan
            Button(onClick = onNavigateCalendar) {
                Text("To the calendar")
            }
        }
    }
}