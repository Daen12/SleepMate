<template>
<div class="board-container">

  <div class="parent">
  <div class="child1">I am the first child
  <side-bar></side-bar>
  </div>


  <div class="child2">
      <h2 class="board_intro">커뮤니티</h2>
      <p>sample text comes here lorem ipsum</p>
      <br>
      <br>
    <board-table></board-table>
  </div>
  
  </div>
  <!-- parent div end -->
</div>
</template>

<script>
import SideBar from '@/components/common/SideBar.vue'
import BoardTable from '@/components/BoardTable.vue'

export default {
  components: { SideBar, BoardTable },
  
}
</script>

<style >

.board_intro {
}


.board-container {
  margin-bottom: -280px;
  position: inherit;
  width: 1500px;
  height: 800px;
  background-color: #7f8c8d50;
}
.parent {
  padding-top: 100px;
  margin: auto;
  margin-top: -100px;
  justify-content: center;
  width: 1300px;
  display: flex;
}
.child1 {
  border: 2px solid red;
  flex: 1;
}
.child2 {
  border: 2px solid orange;
  flex: 4;
}



</style>