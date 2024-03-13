let originBtn = document.querySelector('#originBtn>a')
let showSearch = document.querySelector('.show_search')
let searchInput = document.querySelector('#search_input')
originBtn.onclick = function (e) {
    e.preventDefault()
    showSearch.style.opacity = '1'
    this.style.opacity = '0'
    searchInput.focus()
}

searchInput.onblur = function () {
    showSearch.style.opacity = '0'
    originBtn.style.opacity = 1
}


let haveData = document.querySelector('.haveData')

let cartData = [
    {
        img: '../img/cart1.png',
        name: '【实战课】一天时间迅速准备前端面试 快速构建初级前端知识体系',
        tag: '4个组合套餐可选择',
        price: '￥299.00'
    },
    {
        img: '../img/cart2.png',
        name: '【实战课】Vue3.0(正式版) + TS 仿知乎专栏企业级项目 深度剖析Vue3新特性',
        tag: '2个组合套餐可选择',
        price: '￥348.00'
    }
]

for (let i = 0; i < cartData.length; i++) {
    haveData.innerHTML += `  <div class="cartItem">
                <ul class='itemUl'>
                    <li>
                        <i class='iconfont icon-quanxuan-weixuanzhong select' index="${i}"></i>
                    </li>
                    <li>
                        <a href="" class='itemA'>
                            <img src="${cartData[i].img}" alt="" class='itemImg'>
                            <div class="itemInfo">
                                <div class="itemName">
                                    ${cartData[i].name}
                                </div>
                                <div class="itemTag">
                                    ${cartData[i].tag}
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <span>${cartData[i].price}</span>
                    </li>
                    <li>
                        <i class='glyphicon glyphicon-remove removeBtn'></i>
                    </li>
                </ul>
            </div>`
}

let selectAllBtn = document.querySelector('.allSelect>i') // 全选
let selectBtnList = document.querySelectorAll('.select')  //课程复选框集合
let courseNum = document.querySelector('#courseNum') //总课程数
let selectNum = document.querySelector('#selectNum') //选择课程数
courseNum.innerHTML = cartData.length
selectNum.innerHTML = 0
// 全选
selectAllBtn.onclick = function () {
    if (this.classList.contains('icon-quanxuan-weixuanzhong')) {
        this.classList.replace('icon-quanxuan-weixuanzhong', 'icon-ziyuan')
        for (let i = 0; i < selectBtnList.length; i++) {
            selectBtnList[i].classList.replace('icon-quanxuan-weixuanzhong', 'icon-ziyuan')
        }
        selectNum.innerHTML = courseNum.innerHTML
    } else {
        this.classList.replace('icon-ziyuan', 'icon-quanxuan-weixuanzhong')
        for (let i = 0; i < selectBtnList.length; i++) {
            selectBtnList[i].classList.replace('icon-ziyuan', 'icon-quanxuan-weixuanzhong')
        }
        selectNum.innerHTML = 0
    }
    getPrice()
}

// 单个复选框
for (let i = 0; i < selectBtnList.length; i++) {
    selectBtnList[i].onclick = function () {
        if (this.classList.contains('icon-quanxuan-weixuanzhong')) {
            this.classList.replace('icon-quanxuan-weixuanzhong', 'icon-ziyuan')
            selectNum.innerHTML = parseInt(selectNum.innerHTML) + 1
            // 这里不能写成parseInt(selectNum.innerHTML)+=1,掉坑.
        } else {
            this.classList.replace('icon-ziyuan', 'icon-quanxuan-weixuanzhong')
            selectNum.innerHTML -= 1
        }
        getPrice()
    }
}

// 计算金额

let price = document.querySelector('.price')
function getPrice() {
    let selectBtnList = document.querySelectorAll('.select')  //课程
    let sum = 0
    let flag = true
    for (let i = 0; i < selectBtnList.length; i++) {
        if (selectBtnList[i].classList.contains('icon-ziyuan')) {
            flag = false
            let pri = selectBtnList[i].parentElement.nextElementSibling.nextElementSibling.firstElementChild
            sum = parseFloat(sum) + parseFloat(pri.innerHTML.slice(1))
            price.innerHTML = '￥' + sum
            if (price.innerHTML.indexOf('.') == -1) {
                price.innerHTML += '.00'
            }
        }
        if (flag) {
            price.innerHTML = '￥0.00'
        }
    }
}

let removeBtn = document.querySelectorAll('.removeBtn')
let cartItem = document.querySelectorAll('.cartItem')

let count = cartData.length
for (let i = 0; i < removeBtn.length; i++) {
    removeBtn[i].index = i
    removeBtn[i].onclick = function () {
        cartItem[this.index].style.display = 'none'
        count--
        if (count == 0) {
            document.querySelector('.trueCart').style.display = 'none'
            document.querySelector('.noData').style.display = 'block'
        }
        getPrice()
    }
}

let pay = document.querySelector('.pay')
let layer = document.querySelector('#popLayer')
pay.onclick = function (e) {
    e.preventDefault()
    let xuanzhong = document.querySelectorAll('.haveData .icon-ziyuan')
    if (xuanzhong.length === 0) {
        layer.style.display = 'block';
        popBox.style.display = 'block';
        return;
    }
    let boxArr = []
    for (let i = 0; i < xuanzhong.length; i++) {
        console.log(xuanzhong[i].getAttribute('index'))
        boxArr.push(xuanzhong[i].getAttribute('index'))
    }

    let obj = {
        tempArr: boxArr
    }
    sessionStorage.setItem('boxArr', JSON.stringify(obj))
    window.location.href = '../order.html'
}

let ok = document.querySelector('.ok')
ok.onclick = function (e) {
    e.preventDefault()
    layer.style.display = 'none'
    popBox.style.display = 'none'
}
