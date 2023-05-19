import {useUserStore} from "../stores/userStore.js";
import {mapState, storeToRefs} from "pinia";

export default {
  data() {
    return {
      errorMessage: null,
      errorMessagesMap: {}
    }
  },
  computed: {
    ...mapState(useUserStore, ['error'])
  },
  watch: {
    error(newValue, oldValue) {
      console.log(newValue);
      if (newValue == null) {
        this.errorMessage = null;
        return;
      }
      if (!newValue.response || !newValue.response.status) {
        this.errorMessage = "Unknown error";
        return;
      }
      this.errorMessage = this.errorMessagesMap[newValue.response.status] || "Unknown error";
    }
  },
}
