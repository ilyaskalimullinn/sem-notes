<template>
  <MainLayout>
    <form @submit.prevent="getDefinitionsOfWord" class="col-4">
      <h5>Find a word definition</h5>
      <div class="input-group mb-5">
        <input type="text" name="word" id="word" class="form-control" placeholder="Word" v-model="this.word">
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="submit">Find</button>
        </div>
      </div>
      <div class="text-danger" v-if="this.error">{{this.error}}</div>
    </form>
    <Loader v-if="this.loading" />
    <ul class="col-4" v-if="this.definitions">
      <li v-for="definition in definitions">
        <p>{{definition.definition}}</p>
      </li>
    </ul>
  </MainLayout>
</template>

<script>
import MainLayout from "../components/blocks/MainLayout.vue";
import {apiDictionaryGetDefinitions} from "../services/api.js";
import Loader from "../components/Loader.vue";
//
export default {
  name: "DictionaryView",
  components: {Loader, MainLayout},
  data() {
    return {
      word: "",
      definitions: [],
      error: null,
      loading: false
    }
  },
  methods: {
    async getDefinitionsOfWord() {
      try {
        this.loading = true;
        const response = await apiDictionaryGetDefinitions(this.word);
        this.definitions = response.definitions;
      } catch (err) {
        this.error = err.message;
        this.definitions = null;
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>

</style>
