<template>
  <div id="app">
    <div class="black-bg" v-if="showAlertModal == true">
      <div class="white-bg">
        <div>
          <colgroup>
            <col style="width: 80%" />
            <col style="width: 5%" />
          </colgroup>
          <tbody>
            <tr v-for="(alert, i) in alerts" :key="i">
              <th class="tg-8lxw">
                {{ alert.writer }}님이  "{{ sliceSentence(alert.title) }}"  글에 "{{
                  sliceSentence(alert.content)
                }}"  댓글을 남겼습니다.
              </th>
              <th class="tg-8lxw">{{ sliceRegdate(alert.regdate) }}</th>
            </tr>
          </tbody>
              <hr />
          <button class="btn alertRemove" @click="removeAlerts">
            알림 모두 삭제
          </button>
          <button class="btn alertclose" @click="showAlertModal = false">
            닫기
          </button>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import { mapState } from "vuex";
export default {
  name: "App",
  data() {
    return {
      showAlertModal: false,
      alerts: [],
    };
  },
  created() {
    if (sessionStorage.getItem("loginUser")) {
      let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
      this.$store.commit("SET_LOGIN_USER", loginUser);
    }
  },
  computed: {
    ...mapState(["showAlert", "loginUser"]),
  },
  watch: {
    showAlert(res) {
      if (res) {
        this.showAlertModal = true;
        axios({
          url: `http://localhost:9999/api/alert/${this.loginUser.userNickname}`,
          method: "GET",
        }).then((res) => {
          this.alerts = res.data.Alerts;
        });
      } else {
        this.showAlertModal = false;
      }
    },
  },
  methods: {
    disableShowAlert() {
      this.$store.commit("DISABLE_ALERT");
    },
    async removeAlerts() {
      await axios({
        url: `http://localhost:9999/api/alert/delete/all/${this.loginUser.userNickname}`,
        method: "DELETE",
      }).then((res) => {
        console.log(res);
      });

      const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });

      Toast.fire({
        icon: "info",
        title: "알림 모두 삭제되었습니다.",
      });

      this.showAlertModal = false;
    },
    sliceRegdate(data) {
      var today = new Date();

      var year = today.getFullYear();
      var month = ("0" + (today.getMonth() + 1)).slice(-2);
      var day = ("0" + today.getDate()).slice(-2);
      let dateString = year + "-" + month + "-" + day;

      let regdate = "" + data;
      let result = "";
      if (regdate.substring(0, 10) === dateString) {
        result = regdate.substring(11);
      } else {
        result = regdate.substring(0, 10);
      }
      return result;
    },
    sliceSentence(data) {
      let regdate = "" + data;
      return regdate.substring(0, 5) + '....';
    }
  },
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

.nav-link {
  cursor: pointer;
}

body {
  margin: 0;
}

div {
  box-sizing: border-box;
}

.black-bg {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
  z-index: 900;
}

.white-bg {
  width: 70%;
  background: white;
  border-radius: 8px;
  margin: auto;
  padding: 20px;
}

.alertclose {
  float: right;
}
</style>
