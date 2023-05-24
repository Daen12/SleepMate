<template>
  <div class="lookAround">
    <!-- 처음 버튼이 나와요 -->
    <button
      @click="checkLoginStatus"
      type="button"
      class="btn btn-primary btn-lg survey_start"
      data-toggle="modal"
      data-target="#startModal"
    >
      Survey
    </button>

    <!-- Modal -->
    <div
      class="modal fade"
      id="startModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <div class="modal-header">
            <h5 class="modal-title survey_q" id="exampleModalLongTitle">
              오늘은 무엇에 관심이 있으신가요? <br />
              다음 중 3가지를 선호 순서대로 선택해주세요.
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="select" v-if="!toggle[0][0]" @click="zero(1)">1. 요가 동작</div>
              <div class="select_ed" v-if="toggle[0][0]" @click="zero(1)">1. 요가 동작</div>
              <div class="select" v-if="!toggle[0][1]" @click="zero(2)">2. 요가원</div>
              <div class="select_ed" v-if="toggle[0][1]" @click="zero(2)">2. 요가원</div>
              <div class="select" v-if="!toggle[0][2]" @click="zero(3)">3. 요가 자격증</div>
              <div class="select_ed" v-if="toggle[0][2]" @click="zero(3)">3. 요가 자격증</div>
              <div class="select" v-if="!toggle[0][3]" @click="zero(4)">4. 요가 용품</div>
              <div class="select_ed" v-if="toggle[0][3]" @click="zero(4)">4. 요가 용품</div>
              <!-- :class="{select_ed : toggle[0][0]}" -->
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turn"
            >
              next
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Keyword = 요가 동작 -->
    <div
      class="modal fade"
      id="first"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <div class="modal-header">
            <h5 class="modal-title survey_q" id="exampleModalLongTitle">
              {{ this.step }}번째 선택 문항 - 어떤 요가 동작을 찾고 계신가요?
             
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="select" v-if="!toggle[1][0]" @click="firstQ(0)">
                1. 가볍게 몸 풀기를 하고 싶어요
              </div>
              <div class="select_ed" v-if="toggle[1][0]" @click="firstQ(0)">
                1. 가볍게 몸 풀기를 하고 싶어요
              </div>
              <div class="select" v-if="!toggle[1][1]" @click="firstQ(1)">
                2. 목, 어깨가 뻐근해요
              </div>
              <div class="select_ed" v-if="toggle[1][1]" @click="firstQ(1)">
                2. 목, 어깨가 뻐근해요
              </div>
              <div class="select" v-if="!toggle[1][2]" @click="firstQ(2)">
                3. 상급자용 심화 동작을 배우고 싶어요
              </div>
              <div class="select_ed" v-if="toggle[1][2]" @click="firstQ(2)">
                3. 상급자용 심화 동작을 배우고 싶어요
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turn"
            >
              next
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Keyword = 요가원  -->
    <div
      class="modal fade"
      id="second"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <div class="modal-header">
            <h5 class="modal-title survey_q" id="exampleModalLongTitle">
              {{ this.step }}번째 선택 문항 - 요가원에 대해 어떤 것이
              궁금하신가요?
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="select" v-if="!toggle[2][0]" @click="secondQ(0)">1. 요가원 고르는 법</div>
              <div class="select_ed" v-if="toggle[2][0]" @click="secondQ(0)">1. 요가원 고르는 법</div>
              <div class="select" v-if="!toggle[2][1]" @click="secondQ(1)">
                2. 내가 사는 지역의 요가원 추천
              </div>
              <div class="select_ed" v-if="toggle[2][1]" @click="secondQ(1)">
                2. 내가 사는 지역의 요가원 추천
              </div>
              <span v-if="openregion"
                ><input
                  class="select"
                  type="text"
                  v-model="region"
                  placeholder="지역을 입력해주세요"
              /></span>
              <!-- <div class="select" @click="firstQ(2)">3. 상급자용 심화 동작을 배우고 싶어요</div> -->
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turnSecond"
            >
              next
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Keyword = 요가자격증  -->
    <div
      class="modal fade"
      id="third"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <div class="modal-header">
            <h5 class="modal-title survey_q" id="exampleModalLongTitle">
              {{ this.step }}번째 선택 문항 - 요가 자격증에 대해 어떤 것이
              궁금하신가요? 
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="select" v-if="!toggle[3][0]" @click="thirdQ(0)">
                1. 요가 뉴비에요. 자격증 A-Z가 궁금해요
              </div>
              <div class="select_ed" v-if="toggle[3][0]" @click="thirdQ(0)">
                1. 요가 뉴비에요. 자격증 A-Z가 궁금해요
              </div>
              <div class="select" v-if="!toggle[3][1]" @click="thirdQ(1)">
                2. 국제 요가 자격증에 대해 알고 싶어요
              </div>
              <div class="select_ed" v-if="toggle[3][1]" @click="thirdQ(1)">
                2. 국제 요가 자격증에 대해 알고 싶어요
              </div>
              <div class="select" v-if="!toggle[3][2]" @click="thirdQ(2)">
                3. 국내 요가 자격증에 대해 알고 싶어요
              </div>
              <div class="select_ed" v-if="toggle[3][2]" @click="thirdQ(2)">
                3. 국내 요가 자격증에 대해 알고 싶어요
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turn"
            >
              next
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Keyword = 요가 용품 -->
    <div
      class="modal fade"
      id="fourth"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <div class="modal-header">
            <h5 class="modal-title survey_q" id="exampleModalLongTitle">
              {{ this.step }}번째 선택 문항 - 어떤 요가 용품에 관심이
              있으신가요? (택1)
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="select" v-if="!toggle[4][0]"  @click="fourthQ(0)">1. 요가 매트</div>
              <div class="select_ed" v-if="toggle[4][0]" @click="fourthQ(0)">1. 요가 매트</div>
              <div class="select" v-if="!toggle[4][1]"  @click="fourthQ(1)">2. 요가 블럭</div>
              <div class="select_ed" v-if="toggle[4][1]"  @click="fourthQ(1)">2. 요가 블럭</div>
              <div class="select" v-if="!toggle[4][2]" @click="fourthQ(2)">3. 요가복</div>
              <div class="select_ed" v-if="toggle[4][2]" @click="fourthQ(2)">3. 요가복</div>
              <input
                type="text"
                class="select"
                @click="fourthQ(3)"
                v-model="material"
                placeholder="기타 용품을 입력해주세요"
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turnFourth"
            >
              next
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal Keyword = 마지막 페이지 -->
    <div
      class="modal fade"
      id="last"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content modal_design">
          <!-- <div class="modal-header">
        <h5 class="modal-title survey_q" id="exampleModalLongTitle">
          어떤 요가 용품에 관심이 있으신가요? (택1)
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div> -->
          <div class="modal-body body_design">
            <div class="survey_wrapper">
              <div class="last_page">
                <p>설문이 끝났어요!</p>
                <hr>
                <!-- type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal" -->
                <div v-if="!goYoutube">Chat GPT가 최적화 키워드를 분석 중입니다... <img src="images/loading.gif" alt=""></div>
                <div v-if="goYoutube">분석이 완료되었습니다. <br>지금 바로 유튜브 페이지로 이동해보세요!</div>
                <router-link to="/youtube"><div id="youtubeBtn" v-if="goYoutube" data-dismiss="modal">Youtube</div></router-link>
                <!-- <div>{{ gptAnswer }}</div> -->
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary survey_btn empty"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-warning survey_btn last"
              data-toggle="modal"
              :data-target="'#' + index"
              data-dismiss="modal"
              @click="turnFourth"
            >
              Finish
            </button>
          </div>
        </div>
      </div>
    </div>

    
  </div>
</template>

<script>
// import { Configuration, OpenAIApi } from "openai";
import axios from "axios";
import Swal from "sweetalert2";
export default {
  name: "SurveyView",
  data() {
    // this.$set(this.testArr, 4, 'Z')
    // this.$set(<변수 명>,<변수 타입>,<값>)
    return {
      toggle : [[0,0,0,0],[0,0,0],[0,0],[0,0,0],[0,0,0,0]],
      goYoutube : false,
      // gptAnswer: "",
      fourthQFreeType: false,
      secondQFreeType: false,
      region: "",
      openregion: false,
      seq: [], //1,4,2
      step: 0,
      seqWord: ["zero", "first", "second", "third", "fourth"],
      index: "",
      sendToGPT: [],
      first: [
        "가볍게 몸풀기용 요가",
        "목 어깨 풀어주는 요가",
        "요가 상급 동작",
      ],
      second: ["요가스튜디오 고르는 법"],
      third: [
        "요가 지도자 자격증 정보",
        "국제 요가 자격증",
        "국내 요가자격증 정보",
      ],
      fourth: ["요가 매트 추천", "요가 블럭 추천", "요가복 추천"],
      material: "",
    };
  },
  watch : {
    // gptAnswer(){
    //   this.goYoutube = true;
    //   this.sendToGPT = []; //sendToGPT 배열 초기화
    // }
  },
  methods: {
    checkLoginStatus() {
      const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
      });
      if (!sessionStorage.getItem("loginUser")) {
        this.$router.go(0);
        Toast.fire({
          icon: "warning",
          title: "로그인 후 이용가능한 서비스입니다.",
        });
      }
    },
    ////
    // async openai() {
    //   const configuration = new Configuration({
    //     organization: "org-YsN9LivjSkpgHXxpiJFZNpjS",
    //     apiKey: process.env.VUE_APP_OPEN_AI_API_KEY,
    //     // apiKey: "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",
    //   });
    //   const openai = new OpenAIApi(configuration);

    //   const completion = await openai.createChatCompletion({
    //     model: "gpt-3.5-turbo",
    //     messages: [
    //       {
    //         role: "system",
    //         content: "javascript에 대해 잘 아는 사람",
    //       },
    //       {
    //         role: "user",
    //         content: `${this.sendToGPT[0]}, ${this.sendToGPT[1]}, ${this.sendToGPT[2]} 주제(key)에 대한 한국어 유튜브 검색 키워드 2개(value)를 Javascript Object Notation형식으로 반환해줘`,
    //       },
    //     ],
    //     temperature: 0.7,
    //     max_tokens: 700,
    //   });

    //   this.gptAnswer = await completion.data.choices[0].message.content;
    //   console.log(this.answer);
    // },
    ////
    closeModal() {
      this.step = 0;
      this.sendToGPT = [];
      this.goYoutube = false;
      this.toggle = [[0,0,0,0],[0,0,0],[0,0],[0,0,0],[0,0,0,0]];
    },
    zero(keywordNum) {
      this.seq.push(keywordNum);
      this.toggle[0][keywordNum-1] = 1;
    },
    firstQ(answerNum) {
      this.toggle[1][answerNum] = 1;
      console.log(this.first[answerNum]);
      this.sendToGPT.push(this.first[answerNum]);
    },
    secondQ(answerNum) {
      this.toggle[2][answerNum] = 1;
      if (answerNum === 1) {
        this.openregion = true;
        this.secondQFreeType = true;
      } else {
        let send = this.second[0];
        console.log(send);
        this.sendToGPT.push(send);
      }
      // this.turnNext();
    },
    thirdQ(answerNum) {
      this.toggle[3][answerNum] = 1;
      let send = this.third[answerNum];
      console.log(send);
      this.sendToGPT.push(send);
    },
    fourthQ(answerNum) {
      this.toggle[4][answerNum] = 1;
      let send;
      if (answerNum === 3) {
        this.fourthQFreeType = true;
      } else {
        send = this.fourth[answerNum];
        console.log(send);
        this.sendToGPT.push(send);
      }
    },
    turn() {
      //마지막 모달이라면 
      if (this.step === 3) {
        console.log("finished!");
        console.log(this.sendToGPT);
        this.index = "last"; //마지막 페이지로 이동시킴
        this.step = 0; //step 초기화
        this.toggle = [[0,0,0,0],[0,0,0],[0,0],[0,0,0],[0,0,0,0]];//toggle 초기화
        // this.openai(); //API 가동
        setTimeout(()=>{
          this.goYoutube = true;
        },4000)
        //유저 정보를 db에 저장
        let id = JSON.parse(sessionStorage.getItem("loginUser")).userId;
        const API_URL = "http://localhost:9999/api/gpt";
        axios({
          url: API_URL,
          method: "PUT",
          data: {
            id: id,
            prefer1: this.sendToGPT[0],
            prefer2: this.sendToGPT[1],
            prefer3: this.sendToGPT[2],
          },
        }).then(() => {
          console.log("preference saved.");
          //직접 세션에 preference 넣어주기
          let loginUser = JSON.parse(sessionStorage.getItem('loginUser'));
          loginUser.prefer1 = this.sendToGPT[0];
          loginUser.prefer2 = this.sendToGPT[1];
          loginUser.prefer3 = this.sendToGPT[2];
          sessionStorage.setItem("loginUser", JSON.stringify(loginUser));
          // sessionStorage.setItem("gptPreference", this.sendToGPT);
        });
      } else {
        this.index = this.seqWord[this.seq[this.step]];
        this.step++;
        console.log("turned page");
        console.log(this.index);
      }
    },
    turnFourth() {
      if (this.fourthQFreeType) {
        let send = this.material + ""; //"추천 다시 넣기"
        this.sendToGPT.push(send);
        console.log(send);
      }
      this.turn();
      this.fourthQFreeType = false; //초기화
    },

    // 요가원 지역 정보
    turnSecond() {
      if (this.secondQFreeType) {
        let send = this.region + "요가 스튜디오 추천";
        this.sendToGPT.push(send);
        console.log(send);
      }
      this.turn();
      this.secondQFreeType = false; //초기화
    },
  },
};
</script>

<style>
.last {
  padding: 8px 10px;
}
.last_page img {
  height: 50px;
  width : 90px;
}
#youtubeBtn {
  margin-top: 35px;
  display: inline-block;
  width : 120px;
  height: 45px;
  background-color: rgba(194, 52, 52, 0.912);
  border: solid 1px rgba(185, 35, 35, 0.946);
  color: #fff;
  line-height: 45px;
  border-radius: 10px;
}
.last_page p {
  font-size: 20px;
  font-weight: bolder;
  margin-top: 10px;
  margin-bottom: 20px;
}
.last_page div {
  font-size: 18px;
  color: #333;
}
/* .last_page div .youtubeBtn{
  font-size: 20px;
  color: #fff;
  line-height: 20px;
} */
.survey_wrapper input {
  width: 100%;
  padding: 0px 25px;
  outline: none;
}
.survey_wrapper input ::placeholder {
  text-align: center;
}
.survey_q {
  width: 100%;
  margin-right: 0px;
  font-size: 20px;
  /* color: #222; */
  color: #222;
  text-align: center;
}
.survey_wrapper {
  margin: auto;
  width: 50%;
}
.select {
  display: block;
  cursor: pointer;
  font-size: 15px;
  font-weight: bolder;
  background-color: rgba(255, 255, 255, 0.648);
  border-radius: 10px;
  margin-top: 5px;
  color: #333;
  border: solid 1px #159957;
  height: 50px;
  line-height: 50px;
  transition: all 0.7s, color 0.3;
}
.select_ed {
  display: block;
  cursor: pointer;
  font-size: 15px;
  font-weight: bolder;
  background-color: rgba(88, 159, 100, 0.428);
  border-radius: 10px;
  margin-top: 5px;
  color: #FFF;
  border: solid 1px #159957;
  height: 50px;
  line-height: 50px;
  transition: all 0.7s, color 0.3;
}
/* .select_ed {
  background-color: rgba(88, 159, 100, 0.428);
} */
.select:hover {
  color: #32605b;
  box-shadow: 700px 0 0 0 rgba(88, 159, 100, 0.325) inset;
}

.survey_start {
  height: 70px;
  width: 120px;
  background: #159957; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #155799,
    #159957
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #155799,
    #159957
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

  border: none;
}
.survey_btn {
  color: white;
  background-color: rgba(56, 139, 70, 0.736);
  border: 1px solid #159957;
}
.empty {
  border: 1px solid #555;
  /* border: 1px solid #159957; */
  background-color: transparent;
  /* color: #159957; */
  color: #333;
}
.lookAround {
  margin-top: 90px;
  background-color: bisque;
}

.modal_design {
  background: #feffe4ce; /* fallback for old browsers */
  /* background: -webkit-linear-gradient(to right, #33ae87, #41bb57);  Chrome 10-25, Safari 5.1-6 */
  /* background: linear-gradient(to right, #33ae87, #41bb57); W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

  border: none;
  border-radius: 10px;
}
.body_design {
  border-bottom: transparent;
}
</style>
