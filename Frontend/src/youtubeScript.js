// window.onload = function () {
// document.addEventListener("DOMContentLoaded", () => {
// let name = 5;

let slides = document.querySelector(".youtubes .slides"),
    slide = document.querySelectorAll(".slide_wrapper .slides li"),
    currentIdx = 0,
    slideCount = slide.length,
    slideWidth = 400,
    slideMargin = 30,
    prevBtn = document.querySelector(".prev"),
    nextBtn = document.querySelector(".next");
makeClone();

function makeClone() {
    //뒤에 복사본 추가
    for (let i = 0; i < slideCount; i++) {
        let cloneSlide = slide[i].cloneNode(true);
        cloneSlide.classList.add("clone");
        slides.appendChild(cloneSlide);
    }
    //앞에 복사본 추가
    for (let i = slideCount - 1; i >= 0; i--) {
        let cloneSlide = slide[i].cloneNode(true);
        cloneSlide.classList.add("clone");
        slides.prepend(cloneSlide);
    }
    updateWidth();
    setInitialPos();
    setTimeout(function () {
        slides.classList.add("animated");
    }, 200);
}
function updateWidth() {
    let currentSlides = document.querySelectorAll(".slides li");
    let newSlideCount = currentSlides.length;

    let newWidth =
        (slideWidth + slideMargin) * newSlideCount - slideMargin + "px";
    slides.style.width = newWidth;
}
function setInitialPos() {
    let initialTranslateValue = -(slideWidth + slideMargin) * slideCount;
    slides.style.transform = "translateX(" + initialTranslateValue + "px)";
    //-2150px
}

nextBtn.addEventListener("click", function () {
    console.log("clicked");
    moveSlide(currentIdx + 1);
});
prevBtn.addEventListener("click", function () {
    moveSlide(currentIdx - 1);
});
function moveSlide(num) {
    slides.style.left = -num * (slideWidth + slideMargin) + "px";
    currentIdx = num;
    if (currentIdx == slideCount || currentIdx == -slideCount) {
        setTimeout(function () {
            slides.classList.remove("animated");
            slides.style.left = "0px";
            currentIdx = 0;
        }, 500);
        setTimeout(function () {
            slides.classList.add("animated");
        }, 600);
    }
}

let timer = undefined;
function autoSlide() {
    if (timer == undefined) {
        timer = setInterval(function () {
            moveSlide(currentIdx + 1);
        }, 3000);
    }
}
autoSlide();
function stopSlide() {
    clearInterval(timer);
    timer = undefined;
}
slides.addEventListener("mouseenter", function () {
    stopSlide();
});
slides.addEventListener("mouseleave", function () {
    autoSlide();
});
// });
// };
