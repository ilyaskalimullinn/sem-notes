import {defineStore} from "pinia";

export const useUserStore = defineStore('userStore', {
  state: () => ({
    token: undefined,
    user: undefined,
    loading: false,
    errors: []
  })
})
