// 管理后台通用的js
// 更新导航条内容
function updatePagination(pageNo1, totalPages, totalRecords) {
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = ""; // 清空之前的内容
    var ul = document.createElement("ul");

    // 计算起始页码和结束页码
    var startPage = Math.max(1, pageNo1 - 2); // 最多显示5个页码链接
    var endPage = Math.min(totalPages, startPage + 5); // 最多显示10个页码链接

    // 添加首页链接
    if (startPage > 1) {
        var firstLi = document.createElement("li");
        var firstA = document.createElement("a");
        firstA.href = "#";
        firstA.innerText = "首页";
        firstA.onclick = function () {
            search(1);
        };
        firstLi.appendChild(firstA);
        ul.appendChild(firstLi);
    }

    // 添加页码链接
    for (var i = startPage; i <= endPage; i++) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        a.href = "#";
        a.innerText = i;
        a.onclick = function () {
            search(this.innerText);
        };
        // 是当前页码
        if (i == pageNo1) {
            a.classList.add("active");
        }
        li.appendChild(a);
        ul.appendChild(li);
    }

    // 添加末页链接
    if (endPage < totalPages) {
        var lastLi = document.createElement("li");
        var lastA = document.createElement("a");
        lastA.href = "#";
        lastA.innerText = "末页";
        lastA.onclick = function () {
            search(totalPages);
        };
        lastLi.appendChild(lastA);
        ul.appendChild(lastLi);
    }

    // 添加总页数和总记录数
    var totalPageLi = document.createElement("li");
    totalPageLi.innerHTML = "<a>共" + totalPages + "页</a>";
    ul.appendChild(totalPageLi);
    var totalRecordLi = document.createElement("li");
    totalRecordLi.innerHTML = "<a>共" + totalRecords + "记录</a>";
    ul.appendChild(totalRecordLi);

    pagination.appendChild(ul);
}
// 将日期字符串转换为指定格式（YYYY/MM/DD HH:MM:SS）
function formatDate(dateString) {
    var date = new Date(dateString);
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2); // 月份从0开始，需要加1，并确保两位数
    var day = ('0' + date.getDate()).slice(-2); // 确保两位数的日期
    var hours = ('0' + date.getHours()).slice(-2); // 确保两位数的小时
    var minutes = ('0' + date.getMinutes()).slice(-2); // 确保两位数的分钟
    var seconds = ('0' + date.getSeconds()).slice(-2); // 确保两位数的秒钟
    return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
