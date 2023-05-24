<template>
  <div class="slide_wrapper">
    <ul class="slides">
      <!-- 비디오 3개씩 반환하므로 idx는 3까지 -->
      <li v-for="(video, idx) in videos" :key="idx">
        <button
          type="button"
          data-toggle="modal"
          :data-target="`#youtubeModal${preidx}${preidx2}${idx}`"
        >
          <img
            class="youtubeThumbnail"
            :src="`https://img.youtube.com/vi/${video.id.videoId}/sddefault.jpg`"
          />
        </button>

        <!-- 유튜브 사진을 클릭하면 나오는 Modal 창 -->
        <div
          class="modal fade"
          :id="`youtubeModal${preidx}${preidx2}${idx}`"
          tabindex="-1"
          role="dialog"
          aria-labelledby="exampleModalCenterTitle"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-header">

              <iframe
                width="1000"
                height="563"
                :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
              ></iframe>


            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "YoutubeVideoItem",
  props: {
    keyword: String,
    preidx: Number,
    preidx2: Number,
  },
  data() {
    return {
      videos: [],
    };
  },
  created() {
    setTimeout(() => {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const API_KEY = "AIzaSyB_00m2oGsSGpPohlyp_BC0Isr5EZR7hj8";
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          q: this.keyword,
          type: "video",
          maxResults: 3,
        },
      })
        .then((res) => {
          this.videos = res.data.items;
          console.log(this.videos[0]);
        })
        .catch((err) => console.log(err));
     
    },5000)


  }
};
</script>

<style>
button {
  border: none;
}
.slide_wrapper {
  position: relative;
  width: 100%;
  margin: 10px auto;
  height: 300px;
  /* overflow: hidden; */
  /* border: 1px solid #222; */
}
.slides {
  position: absolute;
  /* left: 0; */
  top: 0;
  left: 0;
  margin-bottom: 10px;
}
.slides.animated {
  transition: 0.4s ease-out;
}
.slides li {
  margin-bottom: 10px;
  margin-top: 0px;
  /* border: 2px solid peru; */
  width: 500px;
  height: 300px;
  /* background: rgb(65, 146, 146); */
  background: #0000008c;
  float: left;
}
.slides li img {
  background-color: transparent;
  border-radius: 10px;
  width: 500px;
  height: 300px;
  /* margin: 10px; */
}
.slides li:not(:last-child) {
  margin-right: 30px;
}
.slides li:first-child {
  margin-left: 30px;
}

/* .youtubeThumbnail {
  width: 560px;
  height: 315px;
} */
.modal-header {
  color: white;
}
</style>
