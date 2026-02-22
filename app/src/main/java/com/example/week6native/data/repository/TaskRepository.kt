package com.example.week6native.data.repository

import com.example.week6native.data.local.TaskDao
import com.example.week6native.model.Task
import com.example.week6native.model.TaskEntity
import com.example.week6native.model.toDomain
import com.example.week6native.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val dao: TaskDao) {

    val tasks: Flow<List<Task>> =
        dao.getAll().map { list ->
            list.map { it.toDomain() }
        }

    suspend fun add(task: Task) {
        dao.insert(task.toEntity())
    }

    suspend fun update(task: Task) {
        dao.update(task.toEntity())
    }

    suspend fun delete(id: Int) {
        dao.deleteById(id)
    }
}