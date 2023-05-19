<template>
<div>
  <board-detail v-if="step == 1"></board-detail>
<div>
  <table class="tg" style="undefined;table-layout: fixed; width: 546px">
<colgroup>
<col style="width: 50px">
<col style="width: 150px">
<col style="width: 300px">
<col style="width: 100px">
<col style="width: 200px">
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
    <td class="tg-0lax">{{board.classNum}}</td>
    <td class="tg-0lax"><button @click="goToDetail(board.idx)">{{board.title}}</button></td>
    <td class="tg-0lax">{{board.writer}}</td>
    <td class="tg-0lax">{{board.date}}</td>
    <td class="tg-baqh">{{board.viewCnt}}</td>
  </tr>
<!-- <router-link :to="{name : 'BoardDetail', params : {idx : 2}}"></router-link>  -->

<!-- -->
</tbody>
</table>
<!-- 여기서부터 pagination -->
 <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
</div>
<!-- 여기까지 pagination -->
</div>
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
    this.$store.dispatch("setBoardList")
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    goToDetail(idx){
      this.$emit("openDetail", idx);
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
  margin: auto;
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
</style>