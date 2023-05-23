<template>
  <router-link :to="{name: 'Home'}">Home</router-link>
  <NoteList></NoteList>
  <div class="error" v-if="this.error">{{this.error}}</div>
</template>

<script>
import NoteList from "../components/NoteList.vue";
import {mapActions, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  name: "NoteListView",
  components: {NoteList},
  computed: {
    ...mapWritableState(useNoteStore, {
      page: "page",
      size: "size",
      error: state => state.requestData.error
    })
  },
  methods: {
    ...mapActions(useNoteStore, ["fetchNotes"])
  },
  async mounted() {
    await this.fetchNotes();
  }
}
</script>

<style scoped>

</style>
