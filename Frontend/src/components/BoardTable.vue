<template>
<div>
  <board-detail v-if="step == 1"></board-detail>
<div>
  <table class="tg" style="undefined;table-layout: fixed; width: 546px">
<colgroup>
<col style="width: 50px">
<col style="width: 100px">
<col style="width: 330px">
<col style="width: 150px">
<col style="width: 150px">
<col style="width: 90px">
</colgroup>
<thead>
  <tr>
    <th class="tg-8lxw">#</th>
    <th class="tg-8lxw">구분</th>
    <th class="tg-8lxw">제목</th>
    <th class="tg-8lxw">작성자</th>
    <th class="tg-8lxw">Date</th>
    <th class="tg-8lxw">조회수</th>
  </tr>
</thead>
<tbody>
  <!-- <tr>
    <td class="tg-0lax">1</td>
    <td class="tg-0lax">ed</td>
    <td class="tg-0lax">제목제목</td>
    <td class="tg-0lax">다영</td>
    <td class="tg-0lax">2023-05-18</td>
    <td class="tg-baqh">1</td>
  </tr> -->
  <tr v-for="(board, i) in boardList" :key="i">
    <td class="tg-0lax">{{i+1}}</td>
    <td class="tg-0lax">{{board.classnum}}</td>
    <td class="tg-0lax"><button @click="goToDetail(board.idx, i+1)">{{board.title}}</button></td>
    <td class="tg-0lax">{{board.writer}}</td>
    <td class="tg-0lax">{{board.regdate.slice(0,11)}}</td>
    <td class="tg-baqh">{{board.viewcnt}}</td>
  </tr>
<!-- <router-link :to="{name : 'BoardDetail', params : {idx : 2}}"></router-link>  -->

<!-- -->
</tbody>
</table>
<!-- 여기서부터 pagination -->
 <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        ← &ensp;
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        &ensp; →
      </button>
</div>
<!-- 여기까지 pagination -->
</div>
<button @click="writeBoard" class="writeboard_btn">글 작성</button>
</div>
</template>

<script>
import { mapState } from 'vuex';
import BoardDetail from './BoardDetail.vue';
export default {
  components: { BoardDetail },
    data () {
    return {
      pageNum: 0,
    }
  },
  props : {
    reviews: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 5,
    },
  },
  created(){
    this.$store.dispatch("setBoardList");
    console.log("create");
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    goToDetail(idx, num){
      console.log(num);
      this.$emit("openDetail", idx, num);
    },
    writeBoard(){
      if(!sessionStorage.getItem("loginUser")){
          alert("로그인 후 이용 가능한 서비스입니다.");
          return;
      } else {
          this.$emit("createOpen");
      }
    }
  },
  computed: {
    ...mapState(['boardList']),

    pageCount () {
        let listLeng = this.boardList.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng/listSize);
        if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.boardList.slice(start, end);
    },
  }
}
</script>

<style>
.tg-0lax button {
  border: none;
  font-weight: 500;
  background-color: transparent;
}
.writeboard_btn {
  height: 42px;
    background-color: #000;
    color: #fff;
    display: inline-block;
    min-width: 80px;
    margin-left: 10px;
    /* padding: 10px; */
    border : 1px solid black;
    border-radius : 2px;
    float: right;
    margin-right: 150px;
    margin-top: -40px;
}
/* board */
.board-intro {
  margin-top: 10px;
  margin-left: 20px;
}
tr {
  height: 50px;
  border-bottom: 0.5px solid rgba(85, 107, 47, 0.161);
}
.tg  {
  border-collapse:collapse;
  border-spacing:0;
  margin-left: 20px;
  margin-top: -40px;
  }
.tg td{
  border-bottom-width:1px;
  border-color:black;
  border-style:solid;
  border-top-width:1px;
  border-width:0px;
  /* font-family:Arial, sans-serif; */
  font-size:14px;
  overflow:hidden;
  padding:3px 20px;
  word-break:normal;
  }
.tg th{
  border-bottom-width:1px;
  border-color:black;
  border-style:solid;
  border-top-width:1px;
  border-width:0px;
  /* font-family:Arial, sans-serif; */
  font-size:14px;
  font-weight:normal;
  overflow:hidden;
  padding:3px 20px;
  word-break:normal;
  }
.tg .tg-baqh{text-align:center;vertical-align:center}
.tg .tg-8lxw{
  font-weight: bold;
  font-size: 15px;
  /* background-color:#efefefab; */
  border-bottom: 1px solid darkolivegreen;
  text-align:center;
  vertical-align:center;
  }
.tg .tg-0lax{
  text-align:center;
  vertical-align:center;
  
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