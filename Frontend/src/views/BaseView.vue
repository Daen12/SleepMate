<template>
  <div>
    <section class="nav_section">
      <div class="brand_box">
        <a class="navbar-brand">
          <router-link to="/"> <span> YogaMate </span> </router-link>
        </a>
      </div>
      <img class="header-img" src="images/slider3-h.jpg" alt="" />

      <div class="container">
        <div class="custom_nav2">
          <nav class="navbar navbar-expand custom_nav-container">
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>

            <div
              class="collapse navbar-collapse navigation"
              id="navbarSupportedContent"
            >
              <div class="d-flex flex-column flex-lg-row align-items-center">
                <ul class="navbar-nav">
                  <li class="nav-item active">
                    <router-link to="/">
                      <a class="nav-link" href="index.html"
                        >Home <span class="sr-only">(current)</span></a
                      ></router-link
                    >
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" @click="loginCheck">Youtube-AI</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link pointer" @click="goToCommun"
                      >Our Community
                    </a>
                    <!-- <router-link to="/base"></router-link> -->
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#contact-us">Contact Us</a>
                  </li>
                  <li v-if="!loginUser" class="nav-item">
                    <router-link to="/login"
                      ><a class="nav-link" href="#">Login</a></router-link
                    >
                  </li>
                  <li v-if="loginUser" class="nav-item">
                    <button class="nav-link logoutButton" @click="logout">
                      Logout
                    </button>
                  </li>
                  <li v-if="!loginUser" class="nav-item">
                    <router-link to="/signup"
                      ><a class="nav-link" href="#">signup</a></router-link
                    >
                  </li>
                </ul>
                <!-- <form class="form-inline my-2 my-lg-0 ml-0 ml-lg-4 mb-3 mb-lg-0">
                <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit"></button>
              </form> -->
              </div>
            </div>
          </nav>
        </div>
      </div>
    </section>

    <router-view></router-view>

    <!-- <footer-view></footer-view> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
import Swal from 'sweetalert2';
// import FooterView from "@/components/common/FooterView.vue";

export default {
  components: {
    // FooterView,
  },
  created() {
    if (sessionStorage.getItem("loginUser")) {
      let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
      this.$store.commit("SET_LOGIN_USER", loginUser);
      return true;
    } else {
      return false;
    }
  },
  methods: {
    logout() {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,   
      });
      console.log("trying to log out");
      this.$store.dispatch("logout");
      setTimeout(() => {
        this.$router.go(0);
      }, 2000);
      Toast.fire({
          icon: 'info',
          title: '로그아웃 되었습니다.'
      });
    },
    goToCommun() {
      this.$router.go(0);
    },
    loginCheck() {
      if (this.loginUser) {
        //로그인유저가 있다면
        console.log("clicked");
        this.$router.push("/youtube");
      } else {
        Swal.fire({
          title: "",
          text: '멤버십 가입 시 맞춤형 유튜브 서비스를 이용하실 수 있습니다. 베타 서비스 페이지로 이동하시겠습니까?',
          width: 570,
          icon: 'info',
          showCancelButton: true,
          confirmButtonText: '이동',
          cancelButtonText: '취소',
        }).then((result) => {
          if (result.isConfirmed) {
            this.$router.push("/youtubebeta");
          }
        })
      }
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style scoped>
.pointer {
  cursor: pointer;
}

.container {
  position: inherit;
  top: 150px;
  margin-top: -30px;
  margin-bottom: 30px;
}

.header-img {
  position: inherit;
  height: 180px;
  width: 100%;
}

.logoutButton {
  background-color: transparent;
  border : 0px;
}

.nav-link {
  cursor: pointer;
}
</style>
