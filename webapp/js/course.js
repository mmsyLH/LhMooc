var categoryList = {};

window.onload = function () {
    loadCategories(); // 在页面加载时加载全部课程分类
    searchCourseByCategory(); // 在页面加载时查询全部课程
};

// 加载全部课程分类
function loadCategories() {
    $.ajax({
        url: 'LhMooc/categoryServlet',
        type: 'get',
        data: {
            action: "getAll"
        },
        dataType: "JSON",
        success: function (res) {
            if (res.code === 200) {
                categoryList = res.data;
                fillCategorySelects();
            } else {
                alert(res.msg);
            }
        },
        error: function (err) {
            console.log('error', err);
        },
    });
}