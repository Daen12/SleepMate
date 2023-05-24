<template>
  <div class="youtube_body">
    <!-- 여기서부터 네비게이션 바 입니다 -->
    <div>
      <section class="nav_section">
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
        
        <youtube-wait-view v-if="loading"></youtube-wait-view>
        <youtube-not-found-view v-if="burst"></youtube-not-found-view>

<div> 
      <br />
      <br />
      <br />
      <div class="youtubes">
        <div class="welcome">환영합니다!</div>
          <hr>
        <div class="desc">
          요가 관련 최근 영상을 한 눈에 확인해보세요. 멤버십 가입으로 더욱
          차별화된 맞춤 영상을 확인하실 수 있습니다.
          <br>
        </div>
    </div>

        <!-- 유저 직접 검색 창 -->
        <youtube-search-view v-if="showSearch" @keywordSent="keywordSent"></youtube-search-view>
        <!-- 여기 부분 컴포넌트화 시켜야 하지 않을까 생각 중 -->
        <!-- 각각 prior(검색키워드)에 대해서 두번씩 돌려서 자식에 정보전달  -->
        <div class="subTitle">{{ this.keyword }} 관련 유튜브 영상</div>
       
        <youtube-video-item
          v-for="(keyword, i) in prior"
          :key="i"
          :keyword="keyword"
          :preidx=1
          :preidx2="i"
        ></youtube-video-item>
        <br>
        <br>
        <br>

</div>

    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { Configuration, OpenAIApi } from "openai";
import YoutubeVideoItem from "@/components/youtube/YoutubeVideoItem.vue";
import YoutubeWaitView from '@/components/youtube/YoutubeWaitView.vue';
import YoutubeSearchView from '@/components/youtube/YoutubeSearchView.vue';
import YoutubeNotFoundView from '@/components/youtube/YoutubeNotFoundView.vue';
export default {
  data() {
    return {
    keyword : "",
      loading : false,
      showSearch : true,
      prior: "",
      loaded : true,
      burst : false,
    //   search : false,
    };
  },
  components: {
    YoutubeVideoItem,
    YoutubeWaitView,
    YoutubeNotFoundView,
    YoutubeSearchView,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    if (sessionStorage.getItem("loginUser")) {
      let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
      this.$store.commit("SET_LOGIN_USER", loginUser);
    }
  },
  methods: {
    keywordSent(keyword){
        this.keyword = keyword;
      this.showSearch = false;
      this.openai(keyword).then((res) => {
        if (res.substring(0, 1) !== '[') { //배열형태가 아니면 새로고침 후 다시
            this.$router.go(0);
        } else { //맞는 형태라면
            this.prior = JSON.parse(res);
            // this.search = true;
            console.log(this.prior);
            this.loading = true;
            //7초뒤에 loaded 실행
            setTimeout(() => {
                this.loaded = true;
                this.loading = false;
            }, 7000);
        }
        });
    },

    async openai(keyword) {
      const configuration = new Configuration({
        organization: "org-YsN9LivjSkpgHXxpiJFZNpjS",
        apiKey: process.env.VUE_APP_OPEN_AI_API_KEY,
      });
      const openai = new OpenAIApi(configuration);

        try{
            const completion = await openai.createChatCompletion({
              model: "gpt-3.5-turbo",
              messages: [
                {
                  role: "system",
                  content:
                    "결과 값은 항상 배열 형식으로 반환해줘, 쌍따옴표를 쓰고, 다른 말은 하지 말고 배열만 반환해",
                },
                {
                  role: "user",
                  content: `${keyword}에 대해 사람들이 많이 검색한 유튜브 검색 키워드 2개를 배열 형식으로 알려줘`,
                },
              ],
              temperature: 0.8,
              max_tokens: 50,
            });
            console.log(completion.data.choices[0].message.content);
            return completion.data.choices[0].message.content;
        } catch(e){
            console.log(e);
            if(e.response.status == 429){
                this.burst = true;
            }
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
  font-size: 40px;
  padding-left: 30px;
}
.youtubes .desc {
  padding-left: 30px;
  margin-top: 5px;
  margin-bottom: 30px;
  font-size: 25px;
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
  /* border: 1px solid red; */
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
