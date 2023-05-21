<template>
  <div class="lookAround">
   <!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
  둘러보기
</button> -->

<!-- Modal -->
<div class="modal fade " id="firstModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content modal_design">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body body_design">
        내용1입니다.
        내용1입니다.
        내용1입니다.
        내용1입니다.
        내용1입니다.
        <br>
        <br>
        <br>
        <br>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#secondModal" data-dismiss = "modal" >next</button>
      </div>
    </div>
  </div>
</div>
<!-- 2번째 모달 등장 -->
<div class="modal fade" id="secondModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        내용2입니다.
        내용2입니다.
        내용2입니다.
        내용2입니다.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#thirdModal" >next</button>
      </div>
    </div>
  </div>
</div>
   
<!-- 처음 버튼이 나와요 -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#firstModal">
  둘러보기
</button>




  </div>
</template>

<script>
export default {


}
</script>

<style>
    .lookAround {
        margin-top : 90px;
        background-color: bisque;
    }

    .modal_design {
      background-color: rgba(90, 194, 159, 0.627);
      border: none;
      border-radius: 10px;
    }
    .body_design {
      border-bottom: transparent;
    }
</style>