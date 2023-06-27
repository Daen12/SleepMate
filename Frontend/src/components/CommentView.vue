<template>
  <div class="comment_container">
    <div class="comment_header">
      <div @click="checkLoginStatus" class="pointer writeBtn" v-if="!writemode">
        &nbsp;+&nbsp;댓글 등록&nbsp;
      </div>
    </div>
    <!-- 댓글 쓰는 창 -->
    <div v-if="writemode">
      <div class="content">
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
        <dl class="pointer" @click="commentWrite">➕</dl>
        <dl class="pointer" @click="writemode = 0">❌</dl>
      </div>
    </div>

    <!-- 기존 댓글 창 -->
    <div v-if="updatemode == 0">
      <div class="content" v-for="(comment, i) in comments" :key="i">
        <dl class="writer">{{ comment.writer }}</dl>
        <dl class="real">{{ comment.content }}</dl>
        <dl class="date">{{ sliceRegdate(comment.regdate) }}</dl>
        <!-- <div class="hideUpdate"> -->
        <dl
          class="pointer"
          v-if="nickName === comment.writer"
          @click="changeUpdateMode(i)"
        >
          ✏️
        </dl>
        <dl
          class="pointer"
          v-if="nickName === comment.writer"
          @click="commentDelete(comment.idx)"
        >
          🗑
        </dl>
        <!-- </div> -->
        <dl class="nullPointer" v-if="nickName !== comment.writer"></dl>
        <dl class="nullPointer" v-if="nickName !== comment.writer"></dl>
      </div>
    </div>

    <!-- 댓글 수정 창 -->
    <div v-if="updatemode == 1">
      <div class="content" v-for="(comment, i) in comments" :key="i">
        <dl class="writer">{{ comment.writer }}</dl>
        <dl class="real" v-if="!updateContentmode[i]">{{ comment.content }}</dl>
        <dl class="real" v-if="updateContentmode[i]">
          <input type="text" v-model="comment.content" />
        </dl>
        <dl class="date">{{ sliceRegdate(comment.regdate) }}</dl>
        <dl
          class="pointer"
          v-if="updateContentmode[i]"
          @click="
            commentUpdate({
              idx: i,
              commentIdx: comment.idx,
              updateContent: comment.content,
            })
          "
        >
          ✏️
        </dl>
        <dl
          class="pointer"
          v-if="updateContentmode[i]"
          @click="cancelUpdate(i)"
        >
          ❌
        </dl>
        <dl class="nullPointer" v-if="nickName !== comment.writer"></dl>
        <dl class="nullPointer" v-if="nickName !== comment.writer"></dl>
        <dl
          class="pointer"
          v-if="!updateContentmode[i] && nickName === comment.writer"
          @click="changeUpdateMode(i)"
        >
          ✏️
        </dl>
        <dl
          class="pointer"
          v-if="!updateContentmode[i] && nickName === comment.writer"
          @click="commentDelete(comment.idx)"
        >
          🗑
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import {mapState} from 'vuex';
import Swal from 'sweetalert2';
export default {
  name: 'CommentView',
  props: {
    idx: Number,
    bWriter: String,
  },
  data() {
    return {
      writemode: 0,
      updatemode: 0,
      nickName: '',
      c_content: '',
      updateContentmode: [],
    };
  },
  computed: {
    ...mapState(['loginUser', 'comments']),
  },
  watch: {
    writemode() {
      axios({
        url: `http://localhost:9999/api/comment/${this.idx}`,
        method: 'GET',
      })
          .then((res) => {
            this.$store.commit('SET_COMMENTS', res.data.comments);
          })
          .catch((err) => {
            console.log(err);
          });
    },
    updatemode() {
      axios({
        url: `http://localhost:9999/api/comment/${this.idx}`,
        method: 'GET',
      })
          .then((res) => {
            this.$store.commit('SET_COMMENTS', res.data.comments);
          })
          .catch((err) => {
            console.log(err);
          });
    },
  },
  methods: {
    checkLoginStatus() {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });
      if (sessionStorage.getItem('loginUser')) {
        this.writemode = 1;
      } else {
        Toast.fire({
          icon: 'warning',
          title: '로그인 후 이용 가능합니다.',
        });
      }
    },
    sliceRegdate(data) {
      const today = new Date();

      const year = today.getFullYear();
      const month = ('0' + (today.getMonth() + 1)).slice(-2);
      const day = ('0' + today.getDate()).slice(-2);
      const dateString = year + '-' + month + '-' + day;

      const regdate = '' + data;
      let result = '';
      if (regdate.substring(0, 10) === dateString) {
        result = regdate.substring(11);
      } else {
        result = regdate.substring(0, 10);
      }
      return result;
    },

    // CREATE
    async commentWrite() {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });
      if (this.c_content === '') {
        Toast.fire({
          icon: 'error',
          title: '댓글 내용을 입력해주세요',
        });
        return;
      }
      const alert = {
        nickname: this.bWriter,
        articleIdx: this.idx,
        content: this.c_content,
        writer: this.nickName,
      };
      const API_URL = `http://localhost:9999/api/alert/write`;
      await axios({
        url: API_URL,
        method: 'POST',
        data: alert,
      })
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });

      const comment = {
        articleIdx: this.idx,
        content: this.c_content,
        writer: this.nickName,
      };
      const API_URL2 = `http://localhost:9999/api/comment/write`;
      await axios({
        url: API_URL2,
        method: 'POST',
        data: comment,
      })
          .then((res) => {
            this.$store.commit('CREATE_COMMENT', res.data);
            this.c_content = '';
          })
          .catch((err) => {
            console.log(err);
          })
          .finally(() => {
            this.writemode = 0;
          });
    },

    // UPDATE
    cancelUpdate(idx) {
      this.updatemode = 0;
      this.updateContentmode[idx] = false;
    },
    changeUpdateMode(idx) {
      this.updatemode = 1;
      this.updateContentmode[idx] = true;

      for (let i = 0; i < this.updateContentmode.length; i++) {
        if (i !== idx && this.updateContentmode[i] == true) {
          this.updateContentmode[i] = false;
        }
      }
    },
    commentUpdate(obj) {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });

      if (obj.updateContent === '') {
        Toast.fire({
          icon: 'error',
          title: '댓글 내용을 입력해주세요',
        });
        return;
      }

      const comment = {
        articleIdx: this.idx,
        content: obj.updateContent,
        writer: this.nickName,
        idx: obj.commentIdx,
      };

      Swal.fire({
        title: '',
        text: '댓글을 수정하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '수정',
        cancelButtonText: '취소',
      }).then((result) => {
        if (result.isConfirmed) {
          const API_URL = `http://localhost:9999/api/comment/update`;
          axios({
            url: API_URL,
            method: 'PUT',
            data: comment,
            headers: {
              'access-token': sessionStorage.getItem('access-token'),
            },
          }).then(() => {
            Toast.fire({
              icon: 'success',
              title: '수정되었습니다.',
            });
            this.updatemode = 0;
            this.updateContentmode[obj.idx] = false;
          });
        }
      });
    },

    // DELETE
    commentDelete(idx) {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });
      const API_URL = `http://localhost:9999/api/comment/delete/${idx}`;

      Swal.fire({
        title: '',
        text: '댓글을 삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '삭제',
        cancelButtonText: '취소',
      }).then((result) => {
        if (result.isConfirmed) {
          axios({
            url: API_URL,
            method: 'DELETE',
            headers: {
              'access-token': sessionStorage.getItem('access-token'),
            },
          })
              .then(() => {
                Toast.fire({
                  icon: 'success',
                  title: '삭제되었습니다.',
                });
                this.$store.commit('DELETE_COMMENT', idx);
              })
              .catch((err) => {
                console.log(err);
                Toast.fire({
                  icon: 'warning',
                  title: '로그인 후 이용해주세요',
                });
              });
        }
      });
    },
  },
  created() {
    if (sessionStorage.getItem('loginUser')) {
      this.nickName = JSON.parse(
          sessionStorage.getItem('loginUser'),
      ).userNickname;
    }
    for (let i = 0; i < this.comments.length; i++) {
      this.updateContentmode[i] = false;
    }
  },
};
</script>

<style>
/* .comment_header{
  margin-bottom: 0px;
} */
.hideUpdate {
  display: inline-block;
  flex: 3;
}
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
  margin-left: 20px;
}

.nullPointer {
  margin-bottom: 20px;
  margin-left: 20px;
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
  flex: 3;
  text-align: left;
  color: #666;
  font-size: 13px;
}

.content dl {
  margin-bottom: 0px;
  color: #666;
  flex: 1;
  text-align: center;
  display: inline;
}
.content dl :nth-child(0) {
  border-right: 1px dotted #9a9999;
}
</style>
