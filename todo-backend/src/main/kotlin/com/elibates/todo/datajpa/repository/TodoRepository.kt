package com.elibates.todo.datajpa.repository

import com.elibates.todo.datajpa.model.TodoItem
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<TodoItem, Long>