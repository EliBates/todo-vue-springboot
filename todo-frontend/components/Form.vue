<template>
  <form
    v-on:submit.prevent=""
    class="w-full max-w-sm mt-5 mx-auto">
    <div class="flex items-center border-b border-gray-500 py-2">
      <input
        v-model="text"
        v-on:keyup.enter="add()"
        class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
        type="text"
        placeholder="Feed the dog..."
        aria-label="Add Item"
      />
      <button
        @click="add()"
        class="flex-shrink-0 bg-gray-700 hover:bg-gray-600 border-gray-700 hover:border-gray-600 text-sm border-4 text-white py-1 px-2 rounded"
        type="button"
      >
        Add Item
      </button>
    </div>
    <div class="relative inline-flex my-5 mx-auto">
      <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
      <select v-model="filterOption" @change="$emit('filter-selection', filterOption)" class="border border-gray-300 rounded-full text-gray-600 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none">
        <option>All</option>
        <option>Completed</option>
        <option>Incomplete</option>
      </select>
    </div>
  </form>

</template>

<script>
export default {
  data () {
    return {
      text: '',
      filterOption: 'All'
    }
  },
  methods: {
    add () {
      this.$emit('newTask', this.text)
      this.text = ''
    }
  }
}
</script>

<style></style>
