<template>
  <section class="custom_footer info_section layout_padding">
    <div class="footer_container">
      <div class="info_logo">
        <h3>
          YogaMate
        </h3>
      </div>
      <div class="info_contact">
        <div class="row">
          <div class="col-md-4">
            <a href="">
              <img src="images/location.png" alt="">
              <span>
                대전광역시 유성구 동서대로 179번길 62-7 스위치
              </span>
            </a>
          </div>
          <div class="col-md-4">
            <a href="">
              <img src="images/call.png" alt="">
              <span>
                Call : +822334567890
              </span>
            </a>
          </div>
          <div class="col-md-4">
            <a href="">
              <img src="images/mail.png" alt="">
              <span>
                sdy971213@gmail.com
              </span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
    name : "FooterView",

}
</script>

<style>
.info_logo {
    flex: 1;
    margin-top: 55px;
}
.info_contact {
    flex: 3;
}
.footer_container {
    display: flex;
    width: 80%;
    margin: auto;
}
.custom_footer {
    padding-top: 0px;
    padding-bottom: 10px;
    position: fixed;
    width: 1920px;
    bottom: 0px;
    left: 0px;
    right: 0px;
    height:110px;
    background-color: rgba(32, 32, 32, 0.657);
}
</style>