<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
  #custom-target {
    position: fixed;
    width: 600px;
    height: 300px;
    border-style: solid;
  }

  .position-absolute {
    position: fixed !important;
  }
</style>
