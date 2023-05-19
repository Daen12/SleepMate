<template>
<div>
<button @click="goback">목록으로</button>
  <div>testtesttesttesttesttesttesttesttestvtesttest</div>
  </div>
</template>

<script>
import router from '@/router'
export default {
    methods : {
        goback(){
            router.go(0);
        }
    }
}
</script>

<style>

</style>