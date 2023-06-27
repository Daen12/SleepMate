<template>
  <div>
    <div class='boardtable'>
      <table class='tg' style='undefined;table-layout: fixed; width: 546px'>
        <colgroup>
          <col style='width: 50px' />
          <col style='width: 120px' />
          <col style='width: 330px' />
          <col style='width: 150px' />
          <col style='width: 150px' />
          <col style='width: 90px' />
        </colgroup>
        <thead>
          <tr>
            <th class='tg-8lxw'>#</th>
            <th class='tg-8lxw'>구분</th>
            <th class='tg-8lxw'>제목</th>
            <th class='tg-8lxw'>작성자</th>
            <th class='tg-8lxw'>Date</th>
            <th class='tg-8lxw'>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for='(board, i) in boardList' :key='i'>
            <td class='tg-0lax'
            v-if='board.classnum == category || category == 0'>{{ i + 1 }}</td>
            <td class='tg-0lax category'
            v-if='board.classnum == category || category == 0'>
            {{ categoryDecode[ board.classnum - 1 ]}}</td>
            <td class='tg-0lax'
            v-if='board.classnum == category || category == 0'>
              <button @click='goToDetail(board.idx, i + 1, board.writer)'>
                {{ board.title }}
              </button>
            </td>
            <td class='tg-0lax'
            v-if='board.classnum == category || category == 0'>
            {{ board.writer }}</td>
            <td class='tg-0lax'
            v-if='board.classnum == category || category == 0'>
            {{ sliceRegdate(board.regdate) }}</td>
            <td class='tg-baqh'
            v-if='board.classnum == category || category == 0'>
            {{ board.viewcnt }}</td>
          </tr>
        </tbody>
      </table>
      <!-- 여기서부터 pagination -->
      <!-- 여기까지 pagination -->
    </div>
    <div>
      <div class='btn-cover'>
        <button :disabled='pageNum === 0' @click='prevPage' class='page-btn'>
          ← &ensp;
        </button>
        <span class='page-count'
          >{{ pageNum + 1 }} / {{ pageSize }} 페이지</span
        >
        <button
          :disabled='pageNum + 1 === pageSize'
          @click='nextPage'
          class='page-btn'>
          &ensp; →
        </button>
      </div>
    <button @click='writeBoard' class='writeboard_btn'>글 작성</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import {mapState} from 'vuex';
import Swal from 'sweetalert2';
export default {
  data() {
    return {
      pageSize: 0,
      categoryDecode: ['요가 용품', '요가 자격증', '요가 센터', '요가 영상'],
      nickName: '',
    };
  },
  computed: {
    ...mapState(['boardList', 'category', 'pageNum']),
  },
  created() {
    this.$store.dispatch('setBoardList',
        {pagenum: this.pageNum + 1, category: this.category});
    if (sessionStorage.getItem('loginUser')) {
      this.nickName = JSON.parse(
          sessionStorage.getItem('loginUser')).userNickname;
    }
  },
  methods: {
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
    nextPage() {
      this.$store.commit('PLUS_PAGENUM');
      this.$store.dispatch('setBoardList',
          {pagenum: this.pageNum + 1, category: this.category});
    },
    prevPage() {
      this.$store.commit('MINUS_PAGENUM');
      this.$store.dispatch('setBoardList',
          {pagenum: this.pageNum + 1, category: this.category});
    },
    goToDetail(idx, num, writer) {
      this.$emit('openDetail', idx, num);
      if (this.nickName === writer) {
        axios({
          url: `http://localhost:9999/api/alert/delete/${writer}/${idx}`,
          method: 'DELETE',
        }).then((res) => {
          console.log(res);
        });
      }
    },
    writeBoard() {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });
      if (!sessionStorage.getItem('loginUser')) {
        Toast.fire({
          icon: 'warning',
          title: '로그인 후 이용 가능한 서비스입니다.',
        });
        return;
      } else {
        this.$emit('createOpen');
      }
    },
  },
  updated() {
    if (this.category === 0) {
      axios({
        url: `http://localhost:9999/api/board/`,
        method: 'GET',
      }).then((res) => {
        this.pageSize = Math.floor(res.data / 10 + 1);
      });
    } else {
      axios({
        url: `http://localhost:9999/api/board/class/${this.category}`,
        method: 'GET',
      }).then((res) => {
        this.pageSize = Math.floor(res.data / 10 + 1);
      });
    }
  },
};
</script>

<style>
.tg-0lax button {
  border: none;
  font-weight: 500;
  background-color: transparent;
}
.category {
  color: #333;
}
.writeboard_btn {
  height: 42px;
  background-color: #000;
  color: #fff;
  display: inline-block;
  min-width: 80px;
  margin-left: 10px;
  /* padding: 10px; */
  border: 1px solid black;
  border-radius: 2px;
  float: right;
  margin-right: 150px;
  margin-top: -40px;
}
/* board */
.boardtable {
  height: 560px;
}
.board-intro {
  margin-top: 10px;
  margin-left: 20px;
}
tr {
  height: 50px;
  border-bottom: 0.5px solid rgba(85, 107, 47, 0.161);
}
.tg {
  border-collapse: collapse;
  border-spacing: 0;
  margin-left: 20px;
  margin-top: -40px;
}
.tg td {
  border-bottom-width: 1px;
  border-color: black;
  border-style: solid;
  border-top-width: 1px;
  border-width: 0px;
  /* font-family:Arial, sans-serif; */
  font-size: 14px;
  overflow: hidden;
  padding: 3px 20px;
  word-break: normal;
}
.tg th {
  border-bottom-width: 1px;
  border-color: black;
  border-style: solid;
  border-top-width: 1px;
  border-width: 0px;
  /* font-family:Arial, sans-serif; */
  font-size: 14px;
  font-weight: normal;
  overflow: hidden;
  padding: 3px 20px;
  word-break: normal;
}
.tg .tg-baqh {
  text-align: center;
  vertical-align: center;
}
.tg .tg-8lxw {
  font-weight: bold;
  font-size: 15px;
  /* background-color:#efefefab; */
  border-bottom: 1px solid darkolivegreen;
  text-align: center;
  vertical-align: center;
}
.tg .tg-0lax {
  text-align: center;
  vertical-align: center;
}
.btn-cover {
  margin-top: 20px;
  margin-left: 40px;
  color: #682b2b;
  font-size: 17px;
}
.btn-cover .page-btn {
  border: none;
  background-color: transparent;
}
</style>
