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
      <!-- <router-view></router-view> -->
      <!-- <button @click="verify">실험</button> -->
        
        <youtube-wait-view v-if="!loaded"></youtube-wait-view>
        <youtube-not-found-view v-if="burst"></youtube-not-found-view>

<div v-if="loaded"> 
      <br />
      <br />
      <br />
      <div class="youtubes">
        <div v-if="loginUser" class="welcome">
          {{ loginUser.userNickname }}님을 위한 맞춤형 유튜브입니다.
        </div>
          <hr>
        <div v-if="loginUser" class="desc">
          요가도 더욱 스마트하게, 지금 필요한 영상을 만나보세요.
          <br>
        </div>
        <div class="refresh">
          결과가 마음에 들지 않으세요?
          <button @click="refresh" >Refresh</button>
        </div>
        <!-- <div v-if="!loginUser" class="welcome">환영합니다!</div>
        <div v-if="!loginUser" class="desc">
          요가 관련 최근 영상을 한 눈에 확인해보세요. 멤버십 가입으로 더욱
          차별화된 맞춤 영상을 확인하실 수 있습니다.
          <br>
          <hr>
        </div> -->

        <!-- 여기 부분 컴포넌트화 시켜야 하지 않을까 생각 중 -->
        <!-- 각각 prior(검색키워드)에 대해서 두번씩 돌려서 자식에 정보전달  -->
        <div class="subTitle">{{ loginUser.prefer1 }} 관련 유튜브 영상</div>
       
        <youtube-video-item
          v-for="(keyword, i) in prior1"
          :key="i"
          :keyword="keyword"
          :preidx=1
          :preidx2="i"
        ></youtube-video-item>
        <br>
        <br>
        <br>

        <div class="subTitle">{{ loginUser.prefer2 }} 관련 유튜브 영상</div>
        <youtube-video-item
          v-for="(keyword, i) in prior2"
          :key="i"
          :keyword="keyword"
          :preidx=2
          :preidx2="i"
        ></youtube-video-item>
        <br>
        <br>
        <br>

        <div class="subTitle">{{ loginUser.prefer3 }} 관련 유튜브 영상</div>
        <youtube-video-item
          v-for="(keyword, i) in prior3"
          :key="i"
          :keyword="keyword"
          :preidx=3
          :preidx2="i"
        ></youtube-video-item>
        <br>
        <br>
        <br>
      </div>
</div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { Configuration, OpenAIApi } from "openai";
import YoutubeVideoItem from "@/components/youtube/YoutubeVideoItem.vue";
import YoutubeWaitView from '@/components/youtube/YoutubeWaitView.vue';
import YoutubeNotFoundView from '@/components/youtube/YoutubeNotFoundView.vue';
export default {
  data() {
    return {
      prior1: "",
      prior2: "",
      prior3: "",
      loaded : false,
      burst : false,
    };
  },
  components: {
    YoutubeVideoItem,
    YoutubeWaitView,
    YoutubeNotFoundView,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    // setTimeout(() => {
    //   this.loaded = true;
    // }, 7000);

    if (sessionStorage.getItem("loginUser")) {
      let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
      this.$store.commit("SET_LOGIN_USER", loginUser);
    }
    //openai() : n순위 관심사에 대해 chat gpt가 검색 키워드를 두개씩 반환. ['첫번째', '두번째'] 형태로!
    this.openai(1).then((res) => {
      if (res.substring(0, 1) !== '[') { //배열형태가 아니면 새로고침 후 다시
        this.$router.go(0);
      } else {
        this.prior1 = JSON.parse(res);
        console.log(this.prior1);
      }
    });
    this.openai(2).then((res) => {
      if (res.substring(0, 1) !== '[') {
        this.$router.go(0);
      } else {
        this.prior2 = JSON.parse(res);
        console.log(this.prior2);

      }
    });
    this.openai(3).then((res) => {
      if (res.substring(0, 1) !== '[') {
        this.$router.go(0);
      } else {
        this.prior3 = JSON.parse(res);
        console.log(this.prior3);
        res;
        
        setTimeout(()=>{
          this.loaded = true;
        },5000);
      }
    });
  },
  methods: {
    refresh(){
      this.$router.go(0);
    },
    // async openai(num){
    //     const configuration = new Configuration({
    //     organization: "org-YsN9LivjSkpgHXxpiJFZNpjS",
    //     apiKey: process.env.VUE_APP_OPEN_AI_API_KEY,
    //   });
    //   const openai = new OpenAIApi(configuration);
    //   let preference = `this.login.prefer${num}`;
    // }
    ////////////////////////
    async openai(num) {
      const configuration = new Configuration({
        organization: "org-YsN9LivjSkpgHXxpiJFZNpjS",
        apiKey: process.env.VUE_APP_OPEN_AI_API_KEY,
      });
      const openai = new OpenAIApi(configuration);

      if (num === 1) {
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
                  content: `${this.loginUser.prefer1}에 대해 사람들이 많이 검색한 유튜브 검색 키워드 2개를 배열 형식으로 알려줘`,
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
                setTimeout(()=>{
                  this.burst = false;
                },5000);
            }
        }
      }

      if (num === 2) {
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
                  content: `${this.loginUser.prefer2}에 대해 사람들이 많이 검색한 유튜브 검색 키워드 2개를 배열 형식으로 알려줘`,
                },
              ],
              temperature: 0.8,
              max_tokens: 50,
            });
            console.log(completion.data.choices[0].message.content);
            return completion.data.choices[0].message.content;
        } catch(e){
            console.log(e.response.status);
            if(e.response.status == 429){
                this.burst = true;
            }
        }
      }

      if (num === 3) {
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
                  content: `${this.loginUser.prefer3}에 대해 사람들이 많이 검색한 유튜브 검색 키워드 2개를 배열 형식으로 알려줘`,
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
                setTimeout(()=>{
                  this.burst = false;
                  this.$router.go(0);
                })
            }
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
    verify() {
      console.log(this.prior1);
      console.log(this.prior2);
      console.log(this.prior3);
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
.youtubes .refresh {
  padding-left: 30px;
  margin-top: 5px;
  margin-bottom: 30px;
  color: #ffffff6d;
  text-align: right;
  font-size: 20px;
}

.youtubes .refresh button {
  margin-left: 10px;
  padding: 7px 10px;
  border-radius: 10px;
  background-color: rgba(255, 0, 0, 0.541);
  color: #333;
  font-size: 17px;
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
