<template>
  <div class="youtubes">
        
        <div v-if="!loginUser" class="desc">
          로딩중입니다. 잠시만 기다려주세요...
        </div>

      </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.youtubes .desc {
  /* padding-left: 30px;
  margin-top: 5px;
  margin-bottom: 30px; */
  margin-top: 200px;
  text-align: center;
  margin: auto;
  font-size: 30px;
  color: #ffffffcb;
}
</style>