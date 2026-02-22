package com.example.week6native.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week6native.data.repository.TaskRepository
import com.example.week6native.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: StateFlow<List<Task>> =
        repository.tasks
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)

    /* init { // Hyvin palvelivat mock taskit
        _tasks.value = mockTasks

    } */

    fun openTask(id: Int) {
        val task = tasks.value.find { it.id == id }
        _selectedTask.value = task
    }
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.add(task)
            addTaskDialogVisible.value = false
        }
    }

    fun removeTask(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    fun toggleDone(id: Int) {
        val task = tasks.value.find { it.id == id } ?: return
        viewModelScope.launch {
            repository.update(task.copy(done = !task.done))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
            _selectedTask.value = null
        }
    }

    fun selectTask(task: Task?) {
        _selectedTask.value = task
    }

    fun closeDialog() {
        _selectedTask.value = null
    }


}