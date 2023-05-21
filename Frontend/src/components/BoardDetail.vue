<template>
  <div>
    <div class="board_container">
      <!-- 여기서부터 상세페이지가 시작돼요 -->
      <!-- 상세페이지 입체적인 css 부여 -->
      <div class="board_view_wrap">
        <div class="board_view">
          <div v-if="!updatemode" class="title">
            {{ board.title }}
          </div>
          <input
            v-if="updatemode"
            type="text"
            v-model="board.title"
            class="title update"
          />
        </div>
        <div class="info">
          <dl>
            <dt>번호</dt>
            <dd>{{ this.num }}</dd>
          </dl>
          <dl>
            <dt>글쓴이</dt>
            <dd>{{ board.writer }}</dd>
          </dl>
          <dl>
            <dt>작성일</dt>
            <dd>{{ board.regdate.slice(0, 11) }}</dd>
          </dl>
          <dl>
            <dt>조회</dt>
            <dd>{{ board.viewcnt }}</dd>
          </dl>
        </div>
        <div v-if="!updatemode" class="cont">
          {{ board.content }}
        </div>

        <comment-view
          v-if="!updatemode"
          :idx="board.idx"
        ></comment-view>

        <textarea v-if="updatemode" v-model="board.content" class="update cont">
        </textarea>
      </div>
    </div>

    <div class="bt_wrap">
      <button class="btn goBackBtn" @click="goback">목록으로</button>
      <div v-if="checkUser" class="modifyDeleteBtn">
        <button
          v-if="!this.updatemode"
          class="btn goBackBtn"
          @click="updateBoard"
        >
          수정
        </button>
        <button
          v-if="this.updatemode"
          class="btn goBackBtn updateFinish"
          @click="updateFinish"
        >
          수정완료
        </button>
        <button v-if="!updatemode" class="btn goBackBtn" @click="deleteBoard">
          삭제
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import CommentView from "@/components/CommentView.vue";
export default {
  methods: {
    showComment() {
      this.showingComment = true;
    },
    goback() {
      this.$emit("finishDetail");
    },
    deleteBoard() {
      if (!sessionStorage.getItem("loginUser")) {
        alert("로그인 후 이용 가능합니다.");
        return;
      } else {
        if (confirm("정말로 삭제하시겠습니까?")) {
          const API_URL = `http://localhost:9999/api/board/delete/${this.board.idx}`;
          axios({
            url: API_URL,
            method: "DELETE",
            headers: {
              "access-token": sessionStorage.getItem("access-token"),
            },
          }).then(() => {
            console.log("deleted");
            alert("삭제되었습니다.");
            this.$emit("finishDelete");
          });
        }
      }
    },
    updateBoard() {
      if (!sessionStorage.getItem("loginUser")) {
        alert("로그인 후 이용 가능합니다");
      } else if (
        this.board.writer !==
        JSON.parse(sessionStorage.getItem("loginUser")).userNickname
      ) {
        alert("본인이 작성한 글만 수정 가능합니다");
      } else {
        this.updatemode = true;
      }
    },
    updateFinish() {
      const API_URL = `http://localhost:9999/api/board/update`;
      axios({
        url: API_URL,
        method: "PUT",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        data: this.board,
      }).then(() => {
        this.updatemode = false;
      });
    },
  },
  data() {
    return {
      showingComment: false,
      titleUpdate: "",
      contentUpdate: "",
      updatemode: false,
      checkUser: false,
    };
  },
  props: {
    idx: Number,
    num: Number,
  },
  computed: {
    ...mapState(["board", "loginUser"]),
  },
  created() {
    axios({
      url: `http://localhost:9999/api/board/detail/${this.idx}`,
      method: "GET",
    })
      .then((res) => {
        this.board = res.data;
        this.$store.commit("SET_BOARD", res.data);
      })
      .catch((err) => {
        console.log(err);
      });

    axios({
      url: `http://localhost:9999/api/comment/${this.idx}`,
      method: "GET",
    })
      .then((res) => {
        this.$store.commit("SET_COMMENTS", res.data.comments);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  updated() {
    if (this.loginUser && this.board.writer === this.loginUser.userNickname) {
      this.checkUser = true;
    }
  },
  components: {
    CommentView,
  },
};
</script>

<style>
/* .goBackBtn {
    /* float: 300px; */
/* }
.board_container {
    margin-top: -50px;
    margin-left: 20px;
}
.board_view_wrap {
    border: 1px solid white;
    border-radius: 15px;
    background-color: rgba(255, 255, 255, 0.482);
    height: 400px;
    width: 90%;
}
.board_view {
    height : 30px;
    padding: 5px, 10px;
} */

/*** */
.modifyDeleteBtn {
  display: inline-block;
  margin-left: 10px;
}

.update {
  background-color: #ffffffcb;
}
.board_container {
  margin-top: -50px;
  margin-left: 20px;
}
.board_view_wrap {
  /* border: 1px solid white; */
  border-radius: 7px;
  background-color: #ffffff75;
  border-bottom: 1px solid #000;
  width: 90%;
}
.board_view {
  height: 63px;
  border-bottom: 1px solid gray;
  border-top: 1.5px solid black;
  padding-left: 30px;
  padding-top: 20px;
  font-weight: bold;
  font-size: larger;
}
.info {
  padding-left: 30px;
  height: 35px;
  border-bottom: 1px solid #999;
  margin-top: 15px;
}
.info dl {
  /* 부모를 relative로 설정해주면 그에 맞게 자식이 dl:before이 position absolute 설정 가능하다 */
  position: relative;
  display: inline-block;
  padding: 0 20px;
}
.info dl:first-child {
  padding-left: 0px;
}

.info dl::before {
  /* display inline이라 자신만의 영역을 갖고잇지 않으므로 block으로 바꿔야함 */
  display: block;
  top: 1px;
  left: 0px;
  content: "";
  position: absolute;
  width: 1px;
  height: 17px;
  background: gray;
}
.info dl:first-child::before {
  display: none;
  /* 첫번째 가상선택자는 보이지 않게 */
}

.info dl dt,
.info dl dd {
  display: inline-block;
  font-size: 13px;
}

.info dl dt {
}
.info dl dd {
  display: inline-block;
  color: #777;
  margin-left: 10px;
}
.cont {
  padding: 15px;
  height: 230px;
  width: 100%;
  line-height: 160%;
  font-size: 15px;
}
.bt_wrap {
  margin-top: 10px;
  /* text-align: center; */
  font-size: 0;
  height: 40px;
}

.bt_wrap button {
  height: 42px;
  background-color: #000;
  color: #fff;
  display: inline-block;
  min-width: 80px;
  margin-left: 10px;
  /* padding: 10px; */
  border: 1px solid black;
  border-radius: 2px;
}
.bt_wrap button:first-child {
  margin-left: 0;
  background-color: #fff;
  color: #000;
}
.bt_wrap .updateFinish {
  background-color: rosybrown;
}
.bt_wrap button.on {
  background: #000;
  color: #fff;
}
</style>
