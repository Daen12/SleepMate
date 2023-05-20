<template>
<div class="board_container">
  <!-- 여기서부터 상세페이지가 시작돼요 -->
  <!-- 상세페이지 입체적인 css 부여 -->
    <div class="board_view_wrap">
        <div class="board_view">
            <div class="title">
                {{board.title}}
            </div>
        </div>
        <div class="info">
            <dl>
                <dt>번호</dt>
                <dd>{{this.num}}</dd>
            </dl>
            <dl>
                <dt>글쓴이</dt>
                <dd>{{board.writer}}</dd>
            </dl>
            <dl>
                <dt>작성일</dt>
                <dd>{{board.date}}</dd>
            </dl>
            <dl>
                <dt>조회</dt>
                <dd>{{board.viewCnt}}</dd>
            </dl>
        </div>
        <div class="cont">
            {{board.content}}
        </div>
    </div>

    <div class="bt_wrap">
        <button class="goBackBtn" @click="goback">목록으로</button> 
        <button class="goBackBtn" @click="updateBoard">수정</button> 
        <button class="goBackBtn" @click="deleteBoard">삭제</button>
    </div>

    </div>


  <!-- <div class="detailView">
    <div></div>
    <div>{{board.writer}}</div>
    <div>{{board.viewCnt}}</div>
    <div>{{board.date}}</div>
    <hr> 여기부터 COMMENT
    <div v-for="(comment, i) in comments" :key="i">
    <div>{{comment.content}}</div>
    <div>{{comment.writer}}</div>
    <hr>
    </div>
  </div> -->



</template>

<script>
import router from '@/router'
import axios from 'axios';
export default {
    methods : {
        goback(){
            router.go(0);
        },
        deleteBoard(){
            this.$store.dispatch("deleteBoard")
        }
    },
    data (){
        return {
            board : {},
            comments : [],
        }
    },
    props : {
        idx : Number,
        num : Number,
    },
    created(){
        console.log(this.num);
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
        
        axios({
            url: `http://localhost:9999/api/comment/${this.idx}`,
                method: "GET",
            })
            .then((res) => {
                console.log(res.data);
                this.comments = res.data.comments;
            })
            .catch((err) => {
                console.log(err);
            });
            
    }
}

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

.board_container {
    margin-top: -50px;
    margin-left: 20px;
}
.board_view_wrap {
    /* border: 1px solid white; */
    border-radius: 7px;
    background-color: #ffffff75;
    border-bottom: 1px solid #000;
    height: 400px;
    width: 90%;
}
.board_view {
    height : 63px;
    border-bottom: 1px solid gray;
    border-top : 1.5px solid black;
    padding-left: 30px;
    padding-top: 20px;
    font-weight: bold;
    font-size: larger;
    
}
.info {
    padding-left: 30px;
    height: 35px;
    border-bottom: 1px solid #999;
    margin-top : 15px;
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
    background: gray ;
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
    border : 1px solid black;
    border-radius : 2px;
}
.bt_wrap button:first-child {
    margin-left: 0;
    background-color: #fff;
    color: #000;
}

.bt_wrap button.on {
    background : #000;
    color: #fff;
}

</style>