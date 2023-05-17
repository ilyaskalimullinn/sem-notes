<template>
    <h1>Notes project</h1>
    <h2>{{this.test}}</h2>
</template>

<style scoped>
</style>

<script>
import {testApi} from "./services/api.js";

export default {
    data() {
        return {
            test: "Api not working"
        }
    },
    async mounted() {
        this.test = await testApi()
    }
}
</script>
