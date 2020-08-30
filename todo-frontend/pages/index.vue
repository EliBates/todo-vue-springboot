<template>
  <div class="container mx-auto">
    <div>
      <h1 class="todo text-center mt-4">Todo List</h1>
      <Form @newTask="addItem($event)" @filter-selection="filter = $event"/>
      <div class="p-6">
        <TodoItem v-for="item of getItemsToDisplay()" :key="item.id" :item="item" @toggle-item="updateItem(item)" @delete-item="deleteItem(item.id)"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  mounted () {
    this.loadItems()
  },
  data () {
    return {
      url: 'http://localhost:8080/api/items',
      items: {},
      filter: 'All',
      default: {
        title: ''
      }
    }
  },
  methods: {
    loadItems () {
      axios
        .get(this.url, { crossdomains: true })
        .then(response => (this.items = response.data))
    },
    addItem (title) {
      this.default.title = title
      axios
        .post(this.url, this.default, { crossdomains: true })
        .then(response => this.loadItems())
    },
    updateItem (item) {
      axios
        .put(`${this.url}/${item.id}`, item, { crossdomains: true })
        .then(response => this.loadItems())
    },
    deleteItem (id) {
      console.log(`deleting ${id}`)
      axios
        .delete(`${this.url}/delete/${id}`, { crossdomains: true })
        .then(response => this.loadItems())
    },
    getItemsToDisplay () {
      if (this.filter === 'All') {
        return this.items
      }
      return Object.keys(this.items)
        .map(key => this.items[key])
        .filter(item => item.complete === (this.filter === 'Completed'))
    }
  }
}
</script>

<style>
  .todo {
    font-family: 'Courier New', Courier, monospace;
    font-size: 2em;
  }
</style>
