// 登录注册区域Js
function showLoginBox() {
    let popBox = document.querySelector('#popBox');
    // 如果登录框是显示状态，则隐藏；否则显示
    if (popBox) {
        if (getComputedStyle(popBox).display === 'block') {
            popBox.style.display = 'none';
        } else {
            popBox.style.display = 'block';
        }
    }
}

function showResBox() {
    let registerBox = document.querySelector('#registerBox');
    // 如果注册框是显示状态，则隐藏；否则显示
    if (registerBox) {
        if (getComputedStyle(registerBox).display === 'block') {
            registerBox.style.display = 'none';
        } else {
            registerBox.style.display = 'block';
        }
    }
}
document.addEventListener('DOMContentLoaded', function () {
    // 点击登录按钮
    document.getElementById('loginBtn1').addEventListener('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为
        // 获取用户名和密码
        var username = document.getElementById('loginusername').value;
        var password = document.getElementById('loginpassword').value;

        // 调用ajaxLogin方法进行登录
        ajaxLogin(username, password);
    });

    // 点击验证码登录按钮
    document.getElementById('loginBtn2').addEventListener('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为

        // // 获取手机号和验证码
        // var phoneNum = document.getElementById('phoneNum').value;
        // var yzm = document.getElementById('yzm').value;

        // // 调用ajaxLogin方法进行登录
        // // 此处根据实际需求修改，例如传递phoneNum和yzm参数
        // ajaxLogin(phoneNum, yzm);
    });

    // 点击注册按钮
    document.getElementById('loginBtn3').addEventListener('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为

        // 获取注册用户名和密码
        var resUsername = document.getElementById('resusername').value;
        var resPassword = document.getElementById('respassword').value;

        // 调用ajaxRes方法进行注册
        ajaxRes(resUsername, resPassword);
    });
});


// ajax登录
function ajaxLogin(username, password) {
    console.log("username", username);
    console.log("password", password);
    $.ajax({
        url: 'LhMooc/userServlet',
        type: 'get',
        data: {
            action: "login",
            username: username,
            password: password
        },
        dataType: "JSON",
        success: function (res) {
            console.log("返回的res", res);
            //转对象
            // var jsonObject = $.parseJSON(res)
            if (res.code === 200) {
                //存session
                sessionStorage.setItem("LoginMoocUser", JSON.stringify(res.data));
                //存cookies
                //存储用户信息到cookies
                document.cookie = `LoginMoocUser=${JSON.stringify(res.data)}; expires=Fri, 31 Dec 9999 23:59:59 GMT; path=/`;
                alert(res.msg)
                location.href = "/";
            } else {
                alert(res.msg)
            }
        },
        error: function (err) {
            alert('error', err);
            // 只有请求不正常（状态码不为200）才会执行
            console.log('error', err);
        },
    });
}

// ajax注册
function ajaxRes(username, password) {
    console.log("username", username);
    console.log("password", password);
    $.ajax({
        url: 'LhMooc/userServlet',
        type: 'post',
        data: {
            action: "register",
            username: username,
            password: password
        },
        success: function (res) {
            console.log("返回的res", res);
            if (res.code === 200) {
                alert(res.msg)
                location.href = "/";
            }
        },
        error: function (err) {
            console.log('error', err);
        },
    });
}