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
                </div>
              </div>
            </nav>
          </div>
        </div>
      </section>
      <!-- 여기까지 네비게이션바입니다. -->
      <!-- <router-view></router-view> -->
      <button @click="verify">실험</button>

      <br />
      <br />
      <br />
      <div class="youtubes">
        <div v-if="loginUser" class="welcome">
          {{ loginUser.userNickname }}님을 위한 맞춤형 유튜브입니다.
        </div>
        <div v-if="loginUser" class="desc">
          요가도 더욱 스마트하게, 지금 필요한 영상을 만나보세요.
        </div>
        <div v-if="!loginUser" class="welcome">환영합니다!</div>
        <div v-if="!loginUser" class="desc">
          요가 관련 최근 영상을 한 눈에 확인해보세요. 멤버십 가입으로 더욱
          차별화된 맞춤 영상을 확인하실 수 있습니다.
        </div>

        <!-- 여기 부분 컴포넌트화 시켜야 하지 않을까 생각 중 -->
        <div class="subTitle">{{ Object.keys(this.prior1)[0] }}</div>
        <youtube-video-item
          v-for="(keyword, i) in Object.values(this.prior1)[0]"
          :key="i"
          :keyword="keyword"
        ></youtube-video-item>

        <div class="subTitle">{{ Object.keys(this.prior2)[0] }}</div>
        <youtube-video-item
          v-for="(keyword, i) in Object.values(this.prior2)[0]"
          :key="i"
          :keyword="keyword"
        ></youtube-video-item>

        <!-- <div class="subTitle">{{ Object.keys(this.prior3)[0] }}</div>
        <youtube-video-item v-for="(keyword, i) in Object.values(this.prior3)[0]" :key="i" :keyword="keyword"></youtube-video-item> -->
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { Configuration, OpenAIApi } from "openai";
import YoutubeVideoItem from "@/components/youtube/YoutubeVideoItem.vue";

export default {
  data() {
    return {
      prior1: "",
      prior2: "",
      prior3: "",
    };
  },
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
    YoutubeVideoItem,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    if (sessionStorage.getItem("loginUser")) {
      let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
      this.$store.commit("SET_LOGIN_USER", loginUser);
    }

    this.openai(1).then((res) => {
      this.prior1 = JSON.parse(res);
    });

    this.openai(2).then((res) => {
      this.prior2 = JSON.parse(res);
    });

    // setTimeout(() => {
    //   this.openai(3).then((res) => {
    //     this.prior3 = JSON.parse(res);
    //   });
    // }, 0);
  },
  methods: {
    async openai(num) {
      const configuration = new Configuration({
        organization: "org-YsN9LivjSkpgHXxpiJFZNpjS",
        apiKey: process.env.VUE_APP_OPEN_AI_API_KEY,
      });
      const openai = new OpenAIApi(configuration);

      if (num === 1) {
        const completion = await openai.createChatCompletion({
          model: "gpt-3.5-turbo",
          messages: [
            {
              role: "system",
              content:
                "결과 값은 항상 Javascript Object Notation 형식만 반환하고, 다른 것은 안나오게 해줘",
            },
            {
              role: "user",
              content: `${this.loginUser.prefer1}(key)에 대한 사람들이 많이 검색한 한국어 유튜브 검색 키워드 2개(value) Javascript Object Notation형식으로 반환해줘`,
            },
          ],
          temperature: 0.4,
          max_tokens: 300,
        });
        return await completion.data.choices[0].message.content;
      }

      if (num === 2) {
        const completion = await openai.createChatCompletion({
          model: "gpt-3.5-turbo",
          messages: [
            {
              role: "system",
              content:
                "결과 값은 항상 Javascript Object Notation 형식만 반환하고, 다른 것은 안나오게 해줘",
            },
            {
              role: "user",
              content: `${this.loginUser.prefer2}(key)에 대한 사람들이 많이 검색한 한국어 유튜브 검색 키워드 2개(value) Javascript Object Notation형식으로 반환해줘`,
            },
          ],
          temperature: 0.4,
          max_tokens: 300,
        });
        return await completion.data.choices[0].message.content;
      }

      if (num === 3) {
        const completion = await openai.createChatCompletion({
          model: "gpt-3.5-turbo",
          messages: [
            {
              role: "system",
              content:
                "결과 값은 항상 Javascript Object Notation 형식만 반환하고, 다른 것은 안나오게 해줘",
            },
            {
              role: "user",
              content: `${this.loginUser.prefer3}(key)에 대한 사람들이 많이 검색한 한국어 유튜브 검색 키워드 1개(value) Javascript Object Notation형식으로 반환해줘`,
            },
          ],
          temperature: 0.3,
          max_tokens: 300,
        });
        return await completion.data.choices[0].message.content;
      }
    },
    logout() {
      console.log("trying to log out");
      this.$store.dispatch("logout");
      this.$router.go(0);
    },
    goToCommun() {
      this.$router.push("/base");
    },
    verify() {
      console.log(Object.values(this.prior1)[0]);
      console.log(this.prior1);
      console.log(Object.values(this.prior2)[0]);
      console.log(this.prior2);
      // console.log(Object.values(this.prior3)[0]);
    },
  },
};
</script>

<style scoped>
* {
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
  background: #159957; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #3e71a4,
    #629c7f
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #3e71a4,
    #629c7f
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.subTitle {
  display: inline-block;
  border: 1px solid white;
  margin: 50px 100px;
  padding: 10px 20px;
  color: #fff;
  font-size: 23px;
  border-radius: 30px;
}
.custom_nav2 {
  margin-top: 0px;
  /* align-items: center; */
}

/* nav bar과 관련된 CSS */
.pointer {
  cursor: pointer;
}

.container {
  position: inherit;
  top: 150px;
  margin: auto;
}

.header-img {
  position: inherit;
  height: 180px;
  width: 100%;
}

.logoutButton {
  background-color: transparent;
  border: 0px;
}

.youtubes {
  border: 1px solid red;
  margin: auto;
  width: 85%;
  height: 100%;
}

/* videos */

li {
  list-style: none;
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
