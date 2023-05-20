<template>
  
  <section class="nav_section">
    <div class="brand_box">
      <a class="navbar-brand">
        <router-link to="/"> <span>
          YogaMate
        </span> </router-link>
      </a>
    </div>
    <img src="images/header-img.jpg" alt="">

    <div class="container">
      <div class="custom_nav2">
        <nav class="navbar navbar-expand custom_nav-container ">
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse navigation" id="navbarSupportedContent">
            <div class="d-flex  flex-column flex-lg-row align-items-center">
              <ul class="navbar-nav  ">
               <li class="nav-item active">
                  <router-link to="/"> <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a></router-link>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="about.html">About </a>
                </li>
                <li class="nav-item">
                  <router-link to="/base"><a class="nav-link" href="#">Our Community </a></router-link>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#contact-us">Contact Us</a>
                </li>
                <li v-if="!loginUser" class="nav-item">
                  <router-link to="/login"><a class="nav-link" href="#">Login</a></router-link>
                </li>
                <li v-if="loginUser" class="nav-item">
                  <button class="nav-link logoutButton" @click="logout">Logout</button>
                </li>
                <li v-if="!loginUser" class="nav-item">
                  <router-link to="/signup"><a class="nav-link" href="#">signup</a></router-link>
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
</template>

<script>
import { mapState } from 'vuex';
export default {
    created(){
       if(sessionStorage.getItem("loginUser")){
            let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
            this.$store.commit("SET_LOGIN_USER", loginUser);
            return true;
          } else {
            return false;
          }
    },
    methods : {
    logout() {
          console.log("trying to log out");
            this.$store.dispatch("logout");
            this.$router.go(0);
        },
  },
    computed : {
      ...mapState(["loginUser"])
    }

}
</script>

<style scoped >
.logoutButton {
  background-color: transparent;
  border : 0px;
}
.container {
    position: fixed;
    top: 150px;
    left : 20%;

}
img {
    position: fixed;
    top: 0px;
    height: 180px;
    width: 100%;
    /* height: ; */
    left: 0px;
}
.brand_box {
    margin-top: 60px;
}
</style>