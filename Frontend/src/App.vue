<template>
  <div id="app">
    <router-view />
    <button hidden id="Alertbtn" data-bs-toggle="modal" data-bs-target="#AlertModal"></button>
    
    <div
      class="modal"
      id="AlertModal"
      tabindex="-1"
    >
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">댓글 알림</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              @click="disableShowAlert"
            ></button>
          </div>
          <div class="modal-body">
            
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "App",
  data() {
    return {
      showAlertModal: false,
    }
  },
  computed: {
    ...mapState(["showAlert"]),
  },
  watch: {
    showAlert(res) {
      if (res) {
        console.log(res);
        document.getElementById("Alertbtn").click();


        
      }
    }
  },
  methods: {
    disableShowAlert() {
      this.$store.commit('DISABLE_ALERT');
    }
  }
};
</script>

<style>
#custom-target {
  position: fixed;
  width: 600px;
  height: 300px;
  border-style: solid;
}

.position-absolute {
  position: fixed !important;
}
</style>
