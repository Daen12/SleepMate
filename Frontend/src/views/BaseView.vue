<template>
<div>
  <header-nav></header-nav>

  <router-view></router-view>

  <footer-view></footer-view>

</div>
</template>

<script>
import HeaderNav from "@/components/HeaderNav.vue";
import FooterView from "@/components/common/FooterView.vue"

export default {
    components : {
        HeaderNav,
        FooterView,
    }

}
</script>

<style>

</style>