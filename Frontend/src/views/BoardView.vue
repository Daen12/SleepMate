<template>
  <ul class="menu-hover-fill flex flex-col items-start leading-none text-2xl uppercase space-y-4 board-sidebar">
  <li><a href="#" data-text="수면 정보 공유">수면 정보 공유</a></li>
  <li><a href="#" data-text="archives">archives</a></li>
  <li><a href="#" data-text="tags">tags</a></li>
  <li><a href="#" data-text="categories">categories</a></li>
  <li><a href="#" data-text="about">about</a></li>
</ul>
</template>

<script>
export default {

}
</script>

<style>
li {
    margin-top: 10px;
}
body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

:root {
  --grey-color: #7f8c8d;
  color:rgb(213, 215, 60)
}

ul {
  padding: 0;
  margin: 0;
  list-style-type: none;
}

a {
  text-decoration: none;
}

.menu-hover-fill li {
  position: relative;
}
.menu-hover-fill li::before {
  position: absolute;
  content: "";
  top: 0;
  left: -1rem;
  width: 0.25rem;
  height: 100%;
  background: var(--menu-link-active-color);
  transition: 0.6s;
}
.menu-hover-fill li a {
  --menu-link-color: var(--grey-color);
  position: relative;
  background: linear-gradient(var(--menu-link-active-color) 0 100%) left/0 no-repeat;
  color: transparent;
  background-clip: text;
  -webkit-background-clip: text;
  transition: background-size 0.45s 0.04s;
}
.menu-hover-fill li a::before {
  position: absolute;
  content: attr(data-text);
  z-index: -1;
  color: var(--menu-link-color);
}
.menu-hover-fill li:nth-child(1) {
  --menu-link-active-color: rgb(213, 215, 60);
}
.menu-hover-fill li:nth-child(2) {
  --menu-link-active-color: rgb(143, 173, 96);
}
.menu-hover-fill li:nth-child(3) {
  --menu-link-active-color: rgb(213, 215, 60);
}
.menu-hover-fill li:nth-child(4) {
  --menu-link-active-color: rgb(143, 173, 96);
}
.menu-hover-fill li:nth-child(5) {
  --menu-link-active-color: rgb(213, 215, 60);
}
.menu-hover-fill li:hover::before {
  left: calc(100% + 1rem);
}
.menu-hover-fill li:hover a {
  background-size: 100%;
}

</style>