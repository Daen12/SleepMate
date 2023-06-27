<template>
<div class="board-container">

  <div class="parent">
  <div class="child1">
  <side-bar @finishDetail="finishDetail" @finishCreate="finishCreate"></side-bar>
  </div>


  <div class="child2">
      <h2 class="board_intro">커뮤니티 공간</h2>
      <p>YogaMate 회원들의 정보공유를 위한 커뮤니티 입니다.</p>
      <hr>
      <br>
      <br>
    <board-table v-if="!detailopen && !createopen" @createOpen ="createOpen" @openDetail="openDetail"></board-table>
    <board-create v-if="createopen" @finishCreate="finishCreate"></board-create>
    <board-detail :idx="this.idx" :num="this.num" v-if="detailopen" @finishDetail="finishDetail" @finishDelete="finishDelete"></board-detail>
  </div>

  </div>
  <!-- parent div end -->
</div>
</template>

<script>
import SideBar from '@/components/common/SideBar.vue';
import BoardTable from '@/components/BoardTable.vue';
import BoardDetail from '@/components/BoardDetail.vue';
import BoardCreate from '@/components/BoardCreate.vue';

export default {
  components: {
    SideBar,
    BoardTable,
    BoardDetail,
    BoardCreate,
  },
  data() {
    return {
      detailopen: false,
      createopen: false,
      idx: -1,
      num: 0,
      pageNum: 0,
    };
  },
  methods: {
    openDetail(idx, num) {
      this.detailopen = true;
      this.idx = idx;
      this.num = num;
    },
    createOpen() {
      this.createopen = true;
    },
    finishDetail() {
      this.detailopen = false;
    },
    finishDelete() {
      this.detailopen = false;
    },
    finishCreate() {
      this.createopen = false;
    },
  },
  created() {
    // if (this.$state.verifyAlarm !== 0) {
    //   this.detailopen
    // }
  },
};
</script>

<style>

.board_intro {
  margin-top: 35px;
  margin-left: 25px;
  font-size: 27px;
}
.child2 p {
  margin-left: 20px;
}

.board-container {
  margin-bottom: -280px;
  position: inherit;
  height: 1050px;
  background-color: #bdd0d218;
}
.parent {
  padding-top: 100px;
  margin: auto;
  margin-top: -100px;
  justify-content: center;
  width: 1300px;
  display: flex;
}
.child1 {
  /* border: 2px solid red; */
  flex: 1;
}
.child2 {
  /* border: 2px solid orange; */
  flex: 4;
}


</style>
