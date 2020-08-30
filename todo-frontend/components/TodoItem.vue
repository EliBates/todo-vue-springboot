<template>
  <div
    class="p-4 my-2 rounded flex justify-between transition ease-in duration-200 transform hover:-translate-y-1 active:translate-y-0"
    v-bind:class="completed">
    <p
      v-if="!editing"
      class="title text-white my-auto">{{ model.title }}</p>
    <input
      v-else
      v-model="model.title"
      v-on:keyup.enter="edit()"
      class="appearance-none bg-transparent border-none text-white mr-3 px-2 leading-tight focus:outline-none"
      type="text"
      placeholder="Feed the dog..."
      aria-label="Add Item"
      />
    <div>
      <button
        v-if="model.complete"
        @click="toggle()"
        class="mr-3 focus:outline-none my-auto">
        <svg
          fill="none"
          viewBox="0 0 24 24"
          class="x w-6 h-6 stroke-current text-white hover:text-orange-400"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M6 18L18 6M6 6l12 12"
          ></path>
        </svg>
      </button>
      <button
        v-else
        class="mr-3 focus:outline-none my-auto"
        @click="toggle()">
        <svg
          fill="none"
          viewBox="0 0 24 24"
          class="check w-6 h-6 stroke-current text-white hover:text-green-400"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M5 13l4 4L19 7"
          ></path>
        </svg>
      </button>
      <button
        v-if="!editing"
        @click="editing = !editing"
        class="mr-3 focus:outline-none my-auto">
        <svg
          fill="none"
          viewBox="0 0 24 24"
          class="pencil w-6 h-6 stroke-current text-white hover:text-yellow-400"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z"
          ></path>
        </svg>
      </button>
      <button class="mr-3 focus:outline-none my-auto" @click="$emit('delete-item')">
        <svg
          fill="none"
          viewBox="0 0 24 24"
          class="trash w-6 h-6 stroke-current text-white hover:text-red-400"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
          ></path>
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    completed () {
      return this.model.complete ? 'bg-green-700 hover:bg-green-600' : 'bg-gray-700 hover:bg-gray-600'
    }
  },
  props: {
    item: Object
  },
  data () {
    return {
      model: this.item,
      editing: false
    }
  },
  methods: {
    toggle () {
      this.model.complete = !this.model.complete
      this.update()
    },
    edit () {
      this.editing = false
      this.update()
    },
    update () {
      this.$emit('toggle-item', this.model)
    }
  }
}
</script>

<style>
  .title {
    font-family: 'Courier New', Courier, monospace;
    font-size: 1em;
  }
</style>
