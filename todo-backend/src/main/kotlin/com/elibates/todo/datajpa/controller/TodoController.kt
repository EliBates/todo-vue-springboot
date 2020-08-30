package com.elibates.todo.datajpa.controller

import com.elibates.todo.datajpa.model.TodoItem
import com.elibates.todo.datajpa.repository.TodoRepository
import org.springframework.data.annotation.AccessType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/api")
class TodoController (private val todoRepository: TodoRepository) {

    @GetMapping("/items")
    fun getAllTodoItems(): List<TodoItem> =
            todoRepository.findAll()

    @PostMapping("/items")
    fun createTodoItem(@Valid @RequestBody item: TodoItem): TodoItem =
            todoRepository.save(item)

    @GetMapping("/items/{id}")
    fun getItemById(@PathVariable(value = "id") todoId: Long): ResponseEntity<TodoItem> {
        return todoRepository.findById(todoId).map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/items/{id}")
    fun updateItemById(@Valid @PathVariable(value = "id") todoId:Long, @RequestBody item: TodoItem): ResponseEntity<TodoItem> {
        return todoRepository.findById(todoId).map {
            val newItem = it.copy(title = item.title, isComplete = item.isComplete)
            ResponseEntity.ok().body(todoRepository.save(newItem))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/items/delete/{id}")
    fun deleteItemById(@PathVariable(value = "id") todoId: Long): ResponseEntity<Void> {
        return todoRepository.findById(todoId).map {
            todoRepository.delete(it)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}