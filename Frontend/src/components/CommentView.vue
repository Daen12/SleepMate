<template>
  <div class="comment_container">
    <div class="comment_header">
      <div
        class="pointer writeBtn"
        v-if="loginUser && !writemode"
        @click="writemode = true"
      >
        &nbsp;+&nbsp;
      </div>
    </div>
    <!-- 댓글 쓰는 창 -->
    <div class="content" v-if="writemode">
      <dl class="writer">{{ nickName }}</dl>
      <dl class="real">
        <input
          type="text"
          @keyup.enter="commentWrite"
          v-model="c_content"
          placeholder="댓글을 입력해주세요."
        />
      </dl>
      <dl class="date"></dl>
      <dl class="pointer" @click="commentWrite">등록</dl>
      <dl class="pointer" @click="writemode = false">취소</dl>
    </div>
    <!-- 댓글 수정 창 -->
    <!-- <div class="content">
        <dl class="writer">{{nickName}}</dl>
        <dl class="real"><input type="text" v-model="e_content"> </dl>
        <dl class="date"></dl>
        <dl class="pointer" @click="commentWrite">수정</dl> 
        <dl class="pointer" @click="cancelWrite">취소</dl> 

    </div> -->

    <!-- 기존 댓글 창 -->
    <div class="content" v-for="(comment, i) in comments" :key="i">
      <!-- <div v-if="updatemode">
        <dl class="writer">{{comment.writer}}</dl>
        <dl class="real">{{comment.content}}</dl>
        <dl class="date">{{comment.regdate.slice(0,11)}}</dl>
        <dl class="pointer" @click="updateComment(comment.content, comment.idx)">수정</dl> 
        <dl class="pointer" @click="commentDelete(comment.idx)">삭제</dl>
      </div> -->

      <dl class="writer">{{ comment.writer }}</dl>
      <dl class="real" v-if="!updateContentmode[i]">{{ comment.content }} {{updateContentmode[i]}}</dl>
      <dl class="real" v-if="updateContentmode[i]"><input type="text" v-model="e_content[i]" /></dl>
      <dl class="date">{{ comment.regdate.slice(0, 11) }}</dl>
      <dl class="pointer" v-if="!updateContentmode[i]" @click="updateComment(i)">수정</dl>
      <dl class="pointer" v-if="!updateContentmode[i]" @click="commentDelete(comment.idx)">삭제</dl>
      <dl class="pointer" v-if="updateContentmode[i]">수정완료</dl>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "CommentView",
  props: {
    idx: Number,
  },
  data() {
    return {
      writemode: false,
      nickName: "",
      c_content: "",
      e_content: [],
      updateContentmode: [],
    }
  },
  computed: {
    ...mapState(["loginUser", "comments"]),
  },
  methods: {
    commentUpdate() {},
    updateComment(idx) {
      this.updateContentmode[idx] = true;
      console.log(this.updateContentmode);
    },
    commentDelete(idx) {
      const API_URL = `http://localhost:9999/api/comment/delete/${idx}`;
      axios({
        url: API_URL,
        method: "DELETE",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          alert("삭제되었습니다.");
          this.$store.commit("DELETE_COMMENT", idx);
        })
        .catch((err) => {
          console.log(err);
          alert("로그인 후 이용해주세요");
        });
    },
    commentWrite() {
      let comment = {
        articleIdx: this.idx,
        content: this.c_content,
        writer: this.nickName,
      };
      const API_URL = `http://localhost:9999/api/comment/write`;
      axios({
        url: API_URL,
        method: "POST",
        data: comment,
      })
        .then((res) => {
          this.$store.commit("CREATE_COMMENT", res.data);
          this.writemode = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    if (sessionStorage.getItem("loginUser")) {
      this.nickName = JSON.parse(sessionStorage.getItem("loginUser")).userNickname;
    }
    this.e_content = this.comments.content;
    for (let i = 0; i < this.comments.length; i++) {
      this.updateContentmode[i] = false;
    }
  },
  beforeUpdate() {
    if (sessionStorage.getItem("loginUser")) {
      this.nickName = JSON.parse(sessionStorage.getItem("loginUser")).userNickname;
    }
    for (let i = 0; i < this.comments.length; i++) {
      this.updateContentmode[i] = false;
    }
    this.e_content = this.comments.content;
  }
}
</script>

<style>
.writeBtn {
  display: inline-block;
  margin: auto;
  padding: 0px 2px;
  text-align: right;
  margin-left: 15px;
  border-radius: 45px;
  border: 1px solid rgb(126, 187, 35);
  color: rgb(126, 187, 35);
}
.pointer {
  cursor: pointer;
  margin-bottom: 20px;
}
.content input {
  width: 90%;
  border: none;
  outline: none;
  background-color: #ffffffd9;
}
.comment_container {
  margin-top: -95px;
  margin-left: 20px;
  margin-right: 20px;
  padding-top: 8px;
  font-size: 14px;
}
.comment_container .content {
  height: 30px;
  margin: 15px 0px;
  border-bottom: 1px dotted #ddd;
  display: flex;
}
.content .real {
  flex: 17;
  text-align: left;
  color: #222;
}
.content .date {
  flex: 3;
  text-align: middle;
  color: #666;
  font-size: 13px;
}
.content .writer {
  flex: 2.1;
  text-align: left;
  color: #666;
  font-size: 13px;
}

.content dl {
  margin-bottom: 0px;
  color: #666;
  flex: 1;
  text-align: center;
}
.content dl :nth-child(0) {
  border-right: 1px dotted #9a9999;
}
</style>
