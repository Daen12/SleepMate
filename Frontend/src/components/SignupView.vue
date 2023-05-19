<template >
<div class="signin-background">
  <section class="signin-form">
    <h1>SIGN UP</h1>
    <!-- form을 만들어야 서버에 전송 가능 -->
    <form class="signup-form" method = "post" action="/process/adduser">
        <!-- post로 전송해야 보안성이 좋다. action은 process부서의 adduser에서 데이터 처리해달라는 뜻 -->
        <div class="int-area">
            <!-- name은 키값이 된다. -->
            <input v-model="name" type="text" name="name" id="name" autocomplete="off" required> 
            <label for="name">YOUR NAME</label>
        </div>
        <div class="int-area">
            <input v-model="nickname" type="text" name="id" id="id" autocomplete="off" required>
            <label for="id">NICKNAME</label>
        </div>
        <div class="int-area">
            <input v-model="email" type="text" name="age" id="age" autocomplete="off" required>
            <label for="pw">EMAIL</label>
        <div class="int-area">
            <input v-model="id" type="text" name="id" id="id" autocomplete="off" required>
            <label for="id">USER NAME</label>
        </div>
        <div class="int-area">
            <input v-model="password" type="password" name="pw" id="pw" autocomplete="off" required>
            <label for="pw">PASSWORD</label>
        </div>
        </div>
        <div class="button-area">
            <button type="submit">SIGN UP</button>
        </div>
        <!-- <div class="caption">
            <a href="">Forgot Password?</a>
        </div> -->
    </form>
</section>
</div>
</template>

<script>
export default {
    data() {
    return {
      name: "",
      nickname: "",
      email : "",
      id: "",
      password: "",
    };
  },
  methods: {
    regist() {
      if (this.id === "" || this.password === "" || this.name === "" || this.email==="") {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        name: this.name,
        nickname: this.nickname,
        id: this.id,
        email:this.email,
        password: this.password,
      };

      this.$store.dispatch("createUser", user);
    },
  },
}

</script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
*{
    margin: 0;
    padding: 0;
    text-decoration: none;
    list-style:none;
    box-sizing: border-box;
}
/* body{
    font-family: 'Noto Sans KR', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background : url("./signup.jpg") no-repeat center;
    background-size: cover;
    backdrop-filter: blur(2px);
    background-color: rgba(0,0,0,0.3); 
} */
   .signin-background {
    justify-content: center;
    align-items: center;
    height: 100vh;
    background : url("./signup.jpg") no-repeat center;
    background-size: cover;
    backdrop-filter: blur(2px);
    height: 1080px;
    width: 1910px;
    }
.signup-form {
    width: 400px;
    text-align: center;
    margin: auto;
    justify-content: center;
    align-items: center;
}
h1{
    padding-top: 10%;
    text-align: center;
    margin-bottom: 60px;
    font-size: 32px;
    color: #fff;
}
body::before{
    position:absolute; z-index: 1;
    top: 0; right: 0; left: 0; bottom: 0;
    background-color: rgba(0,0,0,0.2);
}
.signin-form{
    position: relative; z-index: 2;
}
.int-area{
    width: 400px;
    position: relative;
    margin-top: 20px;
    border-bottom: #fff;

}
.int-area:first-child {
    margin-top: 0;
}
.int-area input {
    width: 100%;
    padding: 20px 10px 10px;
    background-color: transparent;
    font-size: 18px; color : #fff;
    border: none;
    border-bottom: 1px solid rgb(195, 195, 195);

}
.int-area label{
    position: absolute; left: 10px; top:15px;
    font-size: 16px;
    color: rgb(195, 195, 195);
    transition: all .5s ease;
}
.int-area input:focus + label,
.int-area input:valid + label{
    top: -2px;
    font-size: 13px; color: #b9ea16;
}
.button-area{
    margin-top: 30px;
}
.button-area button{
    width: 100%;
    height: 50px;
    background : #bcd806e0;
    color: #fff;
    border: none;
    font-size: 20px;
    border-radius: 20px;
    cursor: pointer;

}

.caption{
    margin-top : 20px;
    text-align: center;
    cursor: pointer;
}
.caption a{
    font-size: 15px;
    color: rgb(195, 195, 195);
}
</style>