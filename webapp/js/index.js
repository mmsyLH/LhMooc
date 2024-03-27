console.clear();
console.log("%c 有朋自远方来, 不亦说乎.", "background:#24272A; color:#ffffff", "");
console.log("%c Github %c", "background:#24272A; color:#ffffff", "", "https://github.com/1072344372");
console.log("%c CSDN %c", "background:#24272A; color:#ffffff", "", "https://blog.csdn.net/luohanisme");
var doms = {
  carousel: document.querySelector('.banner-center .carousel'),
  indicators: document.querySelectorAll('.banner-center .indicator span'),
  leftButton: document.querySelector('.banner-center .indicatorButton.left'),
  rightButton: document.querySelector('.banner-center .indicatorButton.right')
};
console.log("banner-center", doms)
var currentIndex = 0; //轮播图索引
var intervalId; // 定义intervalId变量
//移动轮播图到第几个板块
function moveTo(index) {
  doms.carousel.style.transform = `translateX(-${index}00%)`;
  //去除当前选中的指示器 
  var active = document.querySelector('.banner-center .indicator span.active');
  active.classList.remove('active');
  //重新设置选择的指示器
  doms.indicators[index].classList.add('active');
  currentIndex = index; // 更新当前索引
}

// 注册点击事件
doms.indicators.forEach(function (item, i) {
  item.onclick = function () {
    moveTo(i)
  }
})
// 左右按钮点击事件
doms.leftButton.addEventListener('click', function () {
  currentIndex = (currentIndex - 1 + doms.indicators.length) % doms.indicators.length;
  moveTo(currentIndex);
});

doms.rightButton.addEventListener('click', function () {
  currentIndex = (currentIndex + 1) % doms.indicators.length;
  moveTo(currentIndex);
});

// 自动切换轮播图
intervalId = window.setInterval(function () {
  currentIndex = (currentIndex + 1) % doms.indicators.length; // 更新索引
  moveTo(currentIndex);
}, 2000);


// 鼠标悬停时停止自动切换
doms.carousel.addEventListener('mouseenter', function () {
  clearInterval(intervalId);
});

// 鼠标移出时恢复自动切换
doms.carousel.addEventListener('mouseleave', function () {
  intervalId = window.setInterval(function () {
    currentIndex = (currentIndex + 1) % doms.indicators.length; // 更新索引
    moveTo(currentIndex);
  }, 2000);
});
// 好课区域结束





// 轮播图区域结束

// 好课区域开始
