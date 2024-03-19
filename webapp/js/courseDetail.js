// 页面js开始
// 等待页面加载完成
$(document).ready(function () {
    // 点击展开按钮的处理函数
    $('.js-control-list').click(function () {
        console.log('点击了展开按钮');
        // 找到下一个兄弟元素，并切换显示状态
        $(this).next('.media-box').toggle();
    });

    // 点击展开所有按钮的处理函数
    $('.js-control-all-list').click(function () {
        console.log('点击了展开所有按钮');
        // 找到所有媒体框元素，并显示它们
        $('.media-box').show();
    });
});
// 页面js结束

// 事件js开始

// 事件js结束