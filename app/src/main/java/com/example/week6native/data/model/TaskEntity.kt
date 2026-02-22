package com.example.week6native.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)

fun TaskEntity.toDomain(): Task =
    Task(
        id = id,
        title = title,
        description = description,
        priority = priority,
        dueDate = dueDate,
        done = done
    )

fun Task.toEntity(): TaskEntity =
    TaskEntity(
        id = id,
        title = title,
        description = description,
        priority = priority,
        dueDate = dueDate,
        done = done
    )