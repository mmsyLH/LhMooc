// 管理后台通用的js
// 更新导航条内容
function updatePagination(pageNo1, totalPages, totalRecords) {
    console.log("pageNo1", pageNo1)
    console.log("totalPages", totalPages)
    console.log("totalRecords", totalRecords)
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = ""; // 清空之前的内容
    var ul = document.createElement("ul");

    for (var i = 1; i <= totalPages; i++) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        a.href = "#";
        a.innerText = i;
        a.onclick = function () {
            console.log("this.innerText", this.innerText)
            searchCategory(this.innerText);
        };
        // 是当前页码
        if (i == pageNo1) {
            a.classList.add("active");
        }
        li.appendChild(a);
        ul.appendChild(li);
    }

    //导航条
    var totalPageLi = document.createElement("li");
    totalPageLi.innerHTML = "<a>共" + totalPages + "页</a>";
    ul.appendChild(totalPageLi);
    var totalRecordLi = document.createElement("li");
    totalRecordLi.innerHTML = "<a>共" + totalRecords + "记录</a>";
    ul.appendChild(totalRecordLi);

    pagination.appendChild(ul);
}