package com.example.week6native.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week6native.viewmodel.TaskViewModel
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {},
    onNavigateSettings: () -> Unit = {}

) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()

    // Scaffold ja paddingValues auttaa tumman tilan kanssa, muuten näkyy silti valkoista
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
        ) {
            // Yläpalkki jossa otsikko ja navigaatio kalenteriin
            TopAppBar(
                title = { Text("Todo List") },
                actions = {
                    IconButton(onClick = onNavigateCalendar ) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = "Go to calendar")
                    }

                    IconButton(onClick = onNavigateSettings ) {
                        Icon(Icons.Default.Settings, contentDescription = "Go to settings")
                    }
                }
            )

            Row {
                Button(
                    onClick = onAddClick,
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Add Task")
                }
            }


            // Lista LazyColumnilla
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
                    // taskin ulkonäölle
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { viewModel.selectTask(task) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        // Tyylittelyä listalle
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),

                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Checkboxille
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(task.title, style = MaterialTheme.typography.titleMedium)
                                Text(task.description, style = MaterialTheme.typography.bodyMedium)
                                Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        // Delete buttonin tyylittelyä
                        Button(
                            onClick = { viewModel.removeTask(task.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Delete", color = MaterialTheme.colorScheme.onError)
                        }
                    }
                }
            }
        }


// Dialog näkyy jos selectedTask != null, eli taskia on painettu
        if (selectedTask != null) {
            DetailDialog(task = selectedTask!!,
                onClose = { viewModel.closeDialog() },
                onUpdate = { viewModel.updateTask(it) }
            )
        }

// Näytä AddDialog, jos addTaskFlag = true
        if (addTaskFlag) {
            AddDialog(
                onClose = { viewModel.addTaskDialogVisible.value = false },
                onUpdate = { viewModel.addTask(it) }
            )
        }
    }
}