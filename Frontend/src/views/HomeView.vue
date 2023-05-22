<template>
  <div>
    <div class="hero_area">
    <!-- header section strats -->
    <div class="brand_box">
      <a class="navbar-brand">
        <span>
          YogaMate
        </span>
      </a>
    </div>
    <!-- end header section -->
    <!-- slider section -->
  <section class=" slider_section position-relative">
      <div id="carouselExampleControls" class="carousel slide " data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="img-box">
              <img src="images/slider3.jpg" alt="">
            </div>
          </div>
          <div class="carousel-item">
            <div class="img-box">
              <img src="images/slider2.jpg" alt="">
            </div>
          </div>
          <div class="carousel-item">
            <div class="img-box">
              <img src="images/slider5.jpg" alt="">
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
          <span class="sr-only">Next</span>
        </a>
      </div>
    </section> 
    <!-- end slider section -->
  </div>

  <!-- nav section -->

  <section class="nav_section">
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
                <button class="btn  my-2 my-sm-0 nav_human" type="submit">signup</button>
              </form> -->
            </div>
          </div>
        </nav>
      </div>
    </div>
  </section>

  <!-- end nav section -->

  <!-- shop section -->

  <section class="shop_section layout_padding">
    <div class="container">
      <div class="box">
        <div v-if="!loginUser" class="detail-box">
          <h2>
            YogaMate - 
            내 손 안의 요가 도우미
          </h2>
          <p>
            맞춤형 요가 영상부터 전문가와의 빠른 상담과 더 나은 자세를 위한 커뮤니티까지. 현대인을 위한 요가 플랫폼, YogaMate.
          </p>
        </div>
        <div v-if="loginUser" class="detail-box">
          <h2>
            좋은 {{time}}, {{loginUser.userNickname}}님
          </h2>
          <p class="info">
            오늘은 무엇이 알고싶으신가요? 아래 설문 버튼을 눌러 맞춤형 키워드를 설정해주세요.
          </p>
        </div>

        <look-around @click="checkLoginStatus"></look-around>



        <div class="img-box">
          <!-- <img src="images/shop-img.jpg" alt=""> -->
        </div>
        <!-- <div class="btn-box">
          <a href="">
            Buy Now
          </a>
        </div> -->
      </div>
    </div>
  </section>

  <!-- end shop section -->

  <!-- about section -->

  <section class="about_section">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6 px-0">
          <div class="img-box">
            <!-- <img src="images/about-img.jpg" alt=""> -->
          </div>
        </div>
        <div class="col-md-5">
          <div class="detail-box">
            <div class="heading_container">
              <hr>
              <h2>
                About Us
              </h2>
            </div>
            <p>
               설명설명
            </p>
            <!-- <a href="">
              Read More
            </a> -->
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- end about section -->

  <!-- fruit section -->

  <section class="fruit_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <hr>
        <h2>
          Our Service
        </h2>
      </div>
    </div>
    <div class="container-fluid">

      <div class="fruit_container">
        <div class="box">
          <img src="images/main3.jpg" alt="">
          <div class="link_box">
            <h5>
              Community
            </h5>
            <h5>
              커뮤니티
            </h5>
            <router-link to="/base">
              Click
           </router-link>
          </div>
        </div>
        <div class="box">
          <img src="images/main2.jpg" alt="">
          <div class="link_box">
            <h5>
              Youtube
            </h5>
            <h5>
              맞춤형 유튜브
            </h5>
            <router-link class="" to="/youtube">
              Click
            </router-link>
            
          </div>
        </div>
        <div class="box">
          <img src="images/main1.jpg" alt="">
          <div class="link_box">
            <h5>
              Consultation
            </h5>
            <h5>
              전문가 상담
            </h5>
            <a href="">
              Click
            </a>
          </div>
        </div>
        <!-- <div class="box">
          <img src="images/f-4.jpg" alt="">
          <div class="link_box">
            <h5>
              Apple
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div>
        <div class="box">
          <img src="images/f-5.jpg" alt="">
          <div class="link_box">
            <h5>
              Mango
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div>
        <div class="box">
          <img src="images/f-6.jpg" alt="">
          <div class="link_box">
            <h5>
              Strawberry
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div> -->
      </div>
    </div>
  </section>

  <!-- end fruit section -->


  <!-- client section -->

  <section class="client_section layout_padding-bottom">
    <div class="container ">
      <div class="heading_container">
        <h2>
          유저들의 후기
        </h2>
        <hr>
      </div>
      <div id="carouselExample2Controls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="client_container layout_padding-top">
              <div class="img-box">
                <img src="images/client-img.png" alt="">
              </div>
              <div class="detail-box">
                <h5>
                  Jone Mark
                </h5>
                <p>
                  <img src="images/left-quote.png" alt="">
                  <span>
                    Lorem ipsum dolor sit amet,
                  </span>
                  <img src="images/right-quote.png" alt=""> <br>
                  consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris ut aliquip ex ea commodo
                  consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                </p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="client_container layout_padding-top">
              <div class="img-box">
                <img src="images/client-img.png" alt="">
              </div>
              <div class="detail-box">
                <h5>
                  Jone Mark
                </h5>
                <p>
                  <img src="images/left-quote.png" alt="">
                  <span>
                    Lorem ipsum dolor sit amet,
                  </span>
                  <img src="images/right-quote.png" alt=""> <br>
                  consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris ut aliquip ex ea commodo
                  consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                </p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="client_container layout_padding-top">
              <div class="img-box">
                <img src="images/client-img.png" alt="">
              </div>
              <div class="detail-box">
                <h5>
                  Jone Mark
                </h5>
                <p>
                  <img src="images/left-quote.png" alt="">
                  <span>
                    Lorem ipsum dolor sit amet,
                  </span>
                  <img src="images/right-quote.png" alt=""> <br>
                  consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris ut aliquip ex ea commodo
                  consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                </p>
              </div>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExample2Controls" role="button" data-slide="prev">
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExample2Controls" role="button" data-slide="next">
          <span class="sr-only">Next</span>
        </a>
      </div>

    </div>
  </section>

  <!-- end client section -->


  <!-- contact section -->
  <section id="contact-us" class="contact_section layout_padding-bottom">
    <div class="container-fluid">
      <div class="row">
        <div class="offset-lg-2 col-md-10 offset-md-1">
          <div class="heading_container">
            <hr>
            <h2>
              Contact us
            </h2>
          </div>
        </div>
      </div>

      <div class="layout_padding2-top">
        <div class="row">
          <div class="col-lg-4 offset-lg-2 col-md-5 offset-md-1">
            <form action="">
              <div class="contact_form-container">
                <div>
                  <div>
                    <input type="text" placeholder="이름" />
                  </div>
                  <div>
                    <input type="email" placeholder="이메일" />
                  </div>
                  <div>
                    <input type="text" placeholder="연락처" />
                  </div>
                  <div>
                    <input type="text" class="message_input" placeholder="문의하실 내용을 적어주세요" />
                  </div>
                  <div >
                    <button class="contact-button" type="submit">
                      Send
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-6 px-0">
            <div class="map_container">
              <div class="map-responsive">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3213.5258606356565!2d127.29234808944422!3d36.348039521851!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35654cb4cad2461b%3A0x9f3a7424c0a0a981!2z64yA7KCE6rSR7Jet7IucIOycoOyEseq1rCDrj5nshJzrjIDroZwxNznrsojquLggNjItNw!5e0!3m2!1sko!2skr!4v1684413703198!5m2!1sko!2skr" width="600" height="300" frameborder="0" style="border:0; width: 100%; height:100%" allowfullscreen></iframe>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- end contact section -->


  <!-- info section -->

  <section class="info_section layout_padding">
    <div class="container">
      <div class="info_logo">
        <h2>
          YogaMate
        </h2>
      </div>
      <div class="info_contact">
        <div class="row">
          <div class="col-md-4">
            <a href="">
              <img src="images/location.png" alt="">
              <span>
                대전광역시 유성구 동서대로 179번길 62-7
              </span>
            </a>
          </div>
          <div class="col-md-4">
            <a href="">
              <img src="images/call.png" alt="">
              <span>
                Call : +822334567890
              </span>
            </a>
          </div>
          <div class="col-md-4">
            <a href="">
              <img src="images/mail.png" alt="">
              <span>
                sdy971213@gmail.com
              </span>
            </a>
          </div>
        </div>
      </div>
      <!-- <div class="row"> -->
        <!-- <div class="col-md-8 col-lg-9"> -->
          <!-- <div class="info_form">
            <form action="">
              <input type="text" placeholder="Enter your email">
              <button>
                subscribe
              </button>
            </form>
          </div> -->
        <!-- </div> -->
        <!-- <div class="col-md-4 col-lg-3"> -->
          <!-- <div class="info_social">
            <div>
              <a href="">
                <img src="images/facebook-logo-button.png" alt="">
              </a>
            </div>
            <div>
              <a href="">
                <img src="images/twitter-logo-button.png" alt="">
              </a>
            </div>
            <div>
              <a href="">
                <img src="images/linkedin.png" alt="">
              </a>
            </div>
            <div>
              <a href="">
                <img src="images/instagram.png" alt="">
              </a>
            </div>
          </div> -->
        <!-- </div> -->
      <!-- </div> -->

    </div>
  </section>

  <!-- end info section -->


  <!-- footer section -->
  <section class="container-fluid footer_section ">
    <p>
      &copy; <span id="displayYear"></span> All Rights Reserved. SSAFY 대전4반 김민식 & 석다영
    </p>
  </section>
  </div>
</template>

<script>
import LookAround from "@/components/LookAround.vue"
import { mapState } from 'vuex';
export default {
  name: 'HomeView',
  data(){
    return {
      time : "",
    }
  },
  methods : {
    
    logout() {
          console.log("trying to log out");
            this.$store.dispatch("logout");
            this.$router.go(0);
        },
  },
  created(){
    let hours = new Date().getHours();
   if (hours < 12 && hours > 0) {
     this.time = "아침이에요";
  } else if (hours > 12 && hours < 18) {
     this.time = "오후에요";
  } else {
     this.time = "저녁이에요";
  }
      console.log("created");
      if(sessionStorage.getItem("loginUser")){
            let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
            console.log("user logged in");
            this.$store.commit("SET_LOGIN_USER", loginUser);
            return true;
          } else {
            console.log("user not detected")
            return false;
          }
  },
  components: {
    LookAround,
  },
  computed: {
    ...mapState(["loginUser"]), //loginUser를 state에서 가져와 매핑한다.
  } 
}
</script>

<style scoped>
.logoutButton {
  background-color: transparent;
  border : 0px;
}
.info_section {
  width: 100%;
}

.contact-button {
  background-color: rgb(209, 199, 56);
}
.main_router {
  border: none;

}
.detail-box .info {
  margin-top: 20px;
  font-size: 20px;
  border-bottom: none;
}

</style>
