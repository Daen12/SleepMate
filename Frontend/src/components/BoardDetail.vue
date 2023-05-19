<template>
<div class="board_container">
  <!-- 여기서부터 상세페이지가 시작돼요 -->
  <div class="detailView">
    <div>{{board.title}}</div>
    <div>{{board.writer}}</div>
    <div>{{board.viewCnt}}</div>
    <div>{{board.date}}</div>




  </div>
<button class="goBackBtn" @click="goback">목록으로</button>

  </div>
</template>

<script>
import router from '@/router'
import axios from 'axios';
export default {
    methods : {
        goback(){
            router.go(0);
        }
    },
    data (){
        return {
            board : {},
            comment : [],
        }
    },
    props : {
        idx : Number,
    },
    created(){
        axios({
                url: `http://localhost:9999/api/board/detail/${this.idx}`,
                method: "GET",
            })
                .then((res) => {
                    console.log(res.data);
                    this.board = res.data;
                })
                .catch((err) => {
                    console.log(err);
                });
    }
}
</script>

<style>
.goBackBtn {
    /* float: 300px; */
}
.board_container {
    margin-top: -50px;
    margin-left: 20px;
}
.detailView {
    border: 1px solid white;
    border-radius: 15px;
    background-color: rgba(255, 255, 255, 0.482);
    height: 400px;
    width: 90%;
}

</style>