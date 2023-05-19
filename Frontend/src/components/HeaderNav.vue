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
                  <a class="nav-link" href="#">Our Community </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="contact.html">Contact Us</a>
                </li>
                <li class="nav-item">
                  <router-link to="/login"><a class="nav-link" href="#">Login</a></router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/signup"> <a class="nav-link" href="#">SignUp</a></router-link>
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
export default {
    

}
</script>

<style scoped >
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