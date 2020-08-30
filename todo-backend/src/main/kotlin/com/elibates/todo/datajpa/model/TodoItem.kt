package com.elibates.todo.datajpa.model

import javax.persistence.*

@Entity
data class TodoItem(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        var title: String,
        var isComplete: Boolean
) {
}