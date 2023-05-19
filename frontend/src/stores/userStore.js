import {defineStore} from "pinia";

export const useUserStore = defineStore('userStore', {
  state: () => ({
    token: undefined,
    user: undefined,
    loading: false,
    error: null
  }),
  actions: {
    storeUser(user) {
      this.user = user;
    },
    storeToken(token) {
      this.token = token;
    },
    setLoading(loading) {
      this.loading = loading;
    },
    storeError(error) {
      this.error = error
    },
    clearError() {
      this.error = null
    }
  }
})
