<template>
<div class="board_container">
  <!-- 여기서부터 상세페이지가 시작돼요 -->
  <!-- 상세페이지 입체적인 css 부여 -->
    <div class="board_view_wrap">
        <div class="board_view">
            <!-- <div class="title">
                {{board.title}}
            </div> -->
            <input v-model="title" type="text" class="title" placeholder="제목을 입력하세요">
        </div>
        <div class="info">
            <dl>
                <dt>작성자</dt>
                <dd>{{nickName}}</dd>
            </dl>
            <dl>
                <dt>카테고리</dt>
                <dd>
                    <select name="" id="" v-model="category" class="category">
                        <option value="1">카테고리1</option>
                        <option value="2">카테고리2</option>
                        <option value="3">카테고리3</option>
                        <option value="4">카테고리4</option>
                    </select>
                </dd>
            </dl> 
            
        </div>
        <!-- <div class="cont">
            {{board.content}}
        </div> -->
        <textarea v-model="content" class="cont" placeholder="내용을 입력하세요">
           
        </textarea>
    </div>

    <div class="bt_wrap">
        <button class="" @click="goback">목록으로</button> 
        <button class="" @click="createBoard">등록</button>
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
        createBoard(){
            
            //axios 요청 보내기
            let board = {
                classnum : this.category,
                title : this.title,
                content : this.content,
                writer : this.nickName,
            };
            axios({
                url : `http://localhost:9999/api/board/write`,
                method : "POST",
                data : board,
            }).then(()=>{
                router.go(0);
                alert("등록되었습니다.");
            })

        }
    },
    data (){
        return {
            board : null,
            comments : [],
            updatemode : false,
            title : "",
            content : "",
            // updateText : "",
            nickName : "",
            category : "1",
        }
    },
    props : {
        idx : Number,
        num : Number,
    },
   created(){
    this.nickName = JSON.parse(sessionStorage.getItem("loginUser")).userNickname;
   },
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
dd .category {
    outline: none;
    border: transparent;
    background-color: #ffffff8e;
}
.title {
    color: #222;
    background-color: transparent;
    border : transparent;
    width: 600px;
    font-size: 17px;
    outline: none;
}

.update{
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

/* .info dl dt {
} */
.info dl dd {
    display: inline-block;
    color: #777;
    margin-left: 10px;
}
.cont {
    padding: 30px;
    height: 230px;
    width: 100%;
    line-height: 160%;
    font-size: 17px;
    color: #222;
    border: transparent;
    background-color: transparent;
    outline: none;
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
.bt_wrap .updateFinish {
    background-color: rosybrown;
}
.bt_wrap button.on {
    background : #000;
    color: #fff;
}

</style>