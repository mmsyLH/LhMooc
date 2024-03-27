// 页面js开始
// 等待页面加载完成
$(document).ready(function () {
    var isExpanded = false; // 初始状态为未展开

    // 点击展开按钮的处理函数
    $('.catalogue-box').on('click', '.js-control-list', function () {
        console.log('点击了展开按钮');
        // 找到下一个兄弟元素，并切换显示状态
        $(this).next('.media-box').toggle();
    });


    // 点击展开所有按钮的处理函数
    $('.js-control-all-list').click(function () {
        console.log('点击了展开所有按钮');
        if (!isExpanded) {
            // 如果当前未展开，则展开所有媒体框元素，并更新状态为已展开
            $('.media-box').show();
            isExpanded = true;
        } else {
            // 如果当前已展开，则收起所有媒体框元素，并更新状态为未展开
            $('.media-box').hide();
            isExpanded = false;
        }
    });
});
var userObj;
// 判断是否登录并且拿到用户信息
$(document).ready(function () {
    // 判断用户登录状态
    var user = sessionStorage.getItem("LoginMoocUser");
    if (!user) {
        // 如果用户未登录，则提示并跳转到首页
        alert("您未登录，请先登录！");
        setTimeout(function () {
            window.location.href = "/"; // 跳转到首页
        }, 1000); // 1秒后跳转
    } else {
        userObj = JSON.parse(user);
        console.log("userObj", userObj)
    }
});


// 页面js结束

// 事件js开始
$(document).ready(function () {
    // 点赞按钮点击事件
    $(".like div:nth-child(1) button").click(function () {
        console.log("点赞：", courseId);
        console.log("用户id ", userObj.id);
        // 发起点赞的ajax请求
        $.ajax({
            url: "LhMooc/likeServlet",
            type: "POST",
            dataType: "json",
            data: {
                userId: userObj.id,
                courseId: courseId, // 使用已有的courseId
                action: "likeAdd"
            },
            success: function (response) {
                if (response.code === 200) {
                    alert(response.msg);
                    setTimeout(function () {
                        location.reload();
                    });
                } else {
                    alert(response.msg);
                }
            },
            error: function (error) {
                alert(error.msg);
            }
        });
    });

    // 收藏按钮点击事件
    $(".like div:nth-child(2) button").click(function () {
        console.log("收藏：", courseId);
        console.log("用户id ", userObj.id);
        // 发起收藏的ajax请求
        $.ajax({
            url: "LhMooc/followServlet",
            type: "POST",
            dataType: "json",
            data: {
                userId: userObj.id,
                courseId: courseId, // 使用已有的courseId
                action: "followAdd"
            },
            success: function (response) {
                if (response.code === 200) {
                    alert(response.msg);
                    setTimeout(function () {
                        location.reload();
                    }, 0);
                } else {
                    alert(response.msg);
                }
            },
            error: function (error) {
                alert(error.msg);
            }
        });
    });

    // 发布评论按钮点击事件
    $(".comment-input button").click(function () {
        var commentContent = $(".comment-input input").val();
        console.log("评论：", commentContent);
        console.log("用户id ", userObj.id);
        // 发起发布评论的ajax请求
        $.ajax({
            url: "LhMooc/commentServlet",
            type: "POST",
            dataType: "json",
            data: {
                action: "commentAdd",
                userId: userObj.id,
                courseId: courseId, // 使用已有的courseId
                content: commentContent
            },
            success: function (response) {
                if (response.code === 200) {
                    alert(response.msg);
                    setTimeout(function () {
                        location.reload();
                    }, 0);
                } else {
                    alert(response.msg);
                }
            },
            error: function (error) {
                alert(error.msg);
            }
        });
    });

    // 购买课程按钮点击事件
    $(".buyButton").click(function () {
        console.log("购买课程：", courseId);
        console.log("用户id ", userObj.id);
        // 发起购买课程的ajax请求
        $.ajax({
            url: "LhMooc/courseServlet",
            type: "POST",
            dataType: "json",
            data: {
                userId: userObj.id,
                courseId: courseId,
                action: "buyCourse"
            },
            success: function (response) {
                if (response.code === 200) {
                    alert(response.msg);
                    setTimeout(function () {
                        location.reload();
                    }, 0);
                } else {
                    alert(response.msg);
                }
            },
            error: function (error) {
                alert(error.msg);
            }
        });
    });

});

// 事件js结束