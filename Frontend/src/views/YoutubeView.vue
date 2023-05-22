<template>
<div class="youtube_body">
<!-- 여기서부터 네비게이션 바 입니다 -->
  <div>
    <section class="nav_section">
      <!-- <div class="brand_box">
        <a class="navbar-brand">
          <router-link to="/"> <span> YogaMate </span> </router-link>
        </a>
      </div>
      <img class="header-img" src="images/header-img.jpg" alt="" /> -->

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
                    <a class="nav-link" href="about.html">About </a>
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
  여기까지 네비게이션바입니다.
    <!-- <router-view></router-view> -->
    <br>
    <br>
    <br>
    <div class="youtubes">
        <div class="slide_wrapper">
            <ul class="slides">
                <li>slide 01</li>
                <li>slide 02</li>
                <li>slide 03</li>
                <li>slide 04</li>
                <li>slide 05</li>
            </ul>
        </div>
    </div>
  </div>
</div>
</template>

<script>
import { mapState } from "vuex";
// import slides from "public/js/youtubeScript.js";
export default {
    mounted() {
        // console.log("mounted" + slides);
    },
  components: {
  },
  created() {
    // console.log(slides);
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
      console.log("trying to log out");
      this.$store.dispatch("logout");
      this.$router.go(0);
    },
    goToCommun() {
      this.$router.push("/base")
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
}
.youtube_body {
    width: 100%;
    height: 100%;
    background: #159957;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #3e71a4, #629c7f);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #3e71a4, #629c7f); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
.custom_nav2 {
    margin-top: 0px;
    /* align-items: center; */
}
.pointer {
  cursor: pointer;
}

.container {
  position: inherit;
  top: 150px;
  margin-top: -10px;
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

/* videos */

li{
    list-style: none;
}
.slide_wrapper {
    position: relative;
    width: 1260px;
    margin: 0 auto;
    height: 200px;
    overflow: hidden;
    border: 1px solid #222;
}
.slides {
    position: absolute;
    left: 0;
    top: 0;
}
.slides.animated {
    transition: 0.4s ease-out;
}
.slides li{
    width: 400px;
    height: 200px;
    background: rgb(65, 146, 146);
    float: left;
}
.slides li:not(:last-child){
    margin-right: 30px;
}
.controls {
    text-align: center;
    margin-top: 50px;
}
.controls span {
    border: 1px solid #000;
    padding: 5px 10px;
    cursor: pointer;

}
</style>
