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
  <!-- 여기까지 네비게이션바입니다. -->
    <!-- <router-view></router-view> -->
    <br>
    <br>
    <br>
    <div class="youtubes">
        <div v-if="loginUser" class="welcome">
          {{loginUser.userNickname}}님을 위한 맞춤형 유튜브입니다.
        </div>
        <div v-if="loginUser" class="desc">
            요가도 더욱 스마트하게, 지금 필요한 영상을 만나보세요. 
        </div>
        <div v-if="!loginUser" class="welcome">
          환영합니다!
        </div>
        <div v-if="!loginUser" class="desc">
          요가 관련 최근 영상을 한 눈에 확인해보세요. 멤버십 가입으로 더욱 차별화된 맞춤 영상을 확인하실 수 있습니다.
        </div>

        <div class="subTitle">키워드1</div>
        <div class="slide_wrapper">
            <ul class="slides">
                <li>slide 01</li>
                <li>slide 02</li>
                <li>slide 03</li>
                <!-- <li>slide 04</li>
                <li>slide 05</li> -->
            </ul>
        </div>
        <div class="subTitle">키워드2</div>
        <div class="slide_wrapper">
            <ul class="slides">
                <li>slide 01</li>
                <li>slide 02</li>
                <li>slide 03</li>
                <!-- <li>slide 04</li>
                <li>slide 05</li> -->
            </ul>
        </div>
        <div class="subTitle">키워드3</div>
        <div class="slide_wrapper">
            <ul class="slides">
                <li>slide 01</li>
                <li>slide 02</li>
                <li>slide 03</li>
                <!-- <li>slide 04</li>
                <li>slide 05</li> -->
            </ul>
        </div>
    </div>
  </div>
</div>
</template>
<script>
import { mapState } from "vuex";
export default {
    // mounted() {
    //     const recaptchaScript = document.createElement('script');
    //     recaptchaScript.setAttribute(
    //         "src",
    //         "./js/youtubeScript.js"
    //     );
    //     recaptchaScript.setAttribute(
    //         "defer", "defer"
    //     );
    //     document.body.appendChild(recaptchaScript);
    //     console.log("updated")
    // },
  components: {
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
.youtubes .welcome {
    color: white;
    font-size: 35px;
}
.youtubes .desc {
    padding-left: 30px;
    margin-top: 15px;
    margin-bottom: 30px;
    font-size: 20px;
    color: #ffffffcb;
}
.youtube_body {
    width: 100%;
    height: 100%;
    background: #159957;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #3e71a4, #629c7f);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #3e71a4, #629c7f); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
.subTitle {
    display: inline-block;
    border: 1px solid white;
    margin: 50px 100px ;
    padding: 10px 20px;
    color: #fff;
    font-size: 23px;
    border-radius: 30px;
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
.youtubes {
    border: 1px solid red;
    margin: auto;
    width: 85%;
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
.slide_wrapper {
    
    position: relative;
    /* width: 1260px; */
    margin: 0 auto;
    height: 200px;
    /* overflow: hidden; */
    border: 1px solid #222;
}
.slides {
    position: absolute;
    /* left: 0; */
    top: 0;
}
.slides.animated {
    transition: 0.4s ease-out;
}
.slides li{
    width: 400px;
    height: 200px;
    /* background: rgb(65, 146, 146); */
    background: #0000008c;
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
