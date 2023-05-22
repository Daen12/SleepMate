<template>
  <div class="slide_wrapper">
    <ul class="slides">
      <li>slide 01</li>
      <li>slide 02</li>
      <li>slide 03</li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style>
.slide_wrapper {
  position: relative;
  width: 1260px;
  margin: 0 auto;
  height: 200px;
  overflow: hidden;
  border: 1px solid #222;
}
.slides {
  position: absolute;
  /* left: 0; */
  top: 0;
}
.slides.animated {
  transition: 0.4s ease-out;
}
.slides li {
  width: 400px;
  height: 200px;
  /* background: rgb(65, 146, 146); */
  background: #0000008c;
  float: left;
}
.slides li:not(:last-child) {
  margin-right: 30px;
}
</style>
