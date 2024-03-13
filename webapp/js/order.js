let cartData = [
    {
        img: '../images/cart1.png',
        name: '【实战课】一天时间迅速准备前端面试 快速构建初级前端知识体系',
        tag: '4个组合套餐可选择',
        price: '￥299.00'
    },
    {
        img: '../images/cart2.png',
        name: '【实战课】Vue3.0(正式版) + TS 仿知乎专栏企业级项目 深度剖析Vue3新特性',
        tag: '2个组合套餐可选择',
        price: '￥348.00'
    }
]

let dataArr = JSON.parse(sessionStorage.getItem('boxArr')).tempArr
console.log(dataArr)
let htmlStr = ''
let money = 0
for (let i of dataArr) {
    htmlStr += `
    <ul>
        <li>
            <a href=""><img src="${cartData[i].img}" alt=""></a>
        </li>
        <li>${cartData[i].name}</li>
        <li>${cartData[i].price}</li>
    </ul>
`
    money = money + parseInt(cartData[i].price.slice(1))
}
$(".haveData").html(htmlStr)

let arrow = $("#arrow")
arrow.bind('click', function () {
    if ($(".hidContent").css('display') == 'none') {
        $(this).css({ "transform": "rotate(180deg)" })
        $(".hidContent").css('display', 'block')
    } else {
        $(".hidContent").css('display', 'none')
        $(this).css({ "transform": "rotate(0deg)" })
    }
})

$(".quanA").eq(0).bind('click', function (e) {
    e.preventDefault()
    $('.quanA').eq(1).toggleClass('activeQuan')
    $(this).toggleClass('activeQuan')
    $('.yhq').toggleClass('hidYH')
    $('.yhm').toggleClass('hidYH')
})

$(".quanA").eq(1).bind('click', function (e) {
    e.preventDefault()
    $('.quanA').eq(0).toggleClass('activeQuan')
    $(this).toggleClass('activeQuan')
    $('.yhq').toggleClass('hidYH')
    $('.yhm').toggleClass('hidYH')
})


$('.sum-pay,.should-pay').html('￥' + money.toFixed(2))

console.log(money)

