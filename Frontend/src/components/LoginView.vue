<template >
<div class="signin-background">
  <section class="signin-form">
    <h1>LOGIN</h1>
    <!-- form을 만들어야 서버에 전송 가능 -->
    <!-- <form class="signup-form" method = "post" action="/process/adduser"> -->
    <div class="signup-form">
        <div class="int-area">
            <input v-model="id" type="text" name="id" id="id" autocomplete="off" required>
            <label for="id">USER NAME</label>
        </div>
        <div class="int-area">
            <input  @keyup.enter="login" v-model="password" type="password" name="pw" id="pw" autocomplete="off" required>
            <label for="pw">PASSWORD</label>
        </div>
   
        <div class="button-area">
            <button @click="login" type="submit">LOGIN</button>
        </div>
        <!-- <div class="caption">
            <a href="">Forgot Password?</a>
        </div> -->
         </div>
    <!-- </form> -->
</section>
</div>
</template>

<script>
export default {
    data() {
        return {
            id: "",
            password: "",
        }
    },
  methods: {
    login() {
      if (this.id === "" || this.password === "") {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        id: this.id,
        password: this.password,
      };

      this.$store.dispatch("setLoginUser", user);
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