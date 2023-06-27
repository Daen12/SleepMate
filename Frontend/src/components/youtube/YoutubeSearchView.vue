<template>
  <div class="youtubes">
        <div class="desc">
          무엇을 알고 싶으신가요? 키워드를 입력해주세요.
          <br>
          <br>
          <input  @keyup.enter="sendKeyword" v-model="keyword" type="text" placeholder="">
          <button class="searchYbtn" @click="sendKeyword">Go</button>
        </div>
      </div>
</template>

<script>
export default {
  name: 'YoutubeSearchView',
  data() {
    return {
      keyword: '',
    };
  },
  methods: {
    sendKeyword() {
      this.$emit('keywordSent', this.keyword);
      console.log(this.keyword);
    },
  },


};
</script>

<style scoped>
.desc input {
    outline: none;
    border: none;
    padding-left: 20px;
    border-bottom: 1px solid #0d4734;
    width: 30rem;
    background-color: transparent;
}
.desc button {
    width : 5rem;
    height: 40px;
    background-color: #ffffff65;
    border-radius: 7px;
    color: #0e3a2b9c;
    font-size: 20px;
}
.youtubes .desc {
  /* padding-left: 30px;
  margin-top: 5px;
  margin-bottom: 30px; */
  padding-top: 200px;
  text-align: center;
  margin: auto;
  font-size: 25px;
  color: #ffffffeb;
  height: 900px;
}

.loading {
    /* margin-top: -10px; */
    height: 70px;
    width: 150px;
    margin-left: -30px;
}

.searchYbtn {
    margin-left: 20px;
}
</style>
