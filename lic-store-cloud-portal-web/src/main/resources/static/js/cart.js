$(function () {
    getTotal();
    //监听购物车div点击事件
    $("#s_cart").click(function () {
        location.href="/cart/list";
    })
    
});

function getTotal() {
    $.post("/cart/getTotal",function (res) {
        if(0!=parseInt(res)){
            $("#s_cart_num1_span").text(res)
            $("#s_cartbox").remove();
        }else{
            $("#s_cart_num1_span").text(0);
        }
    });
}

function addCart(goodsId,goodsNum,flag) {
    //flag为1表示直接替换 0累加
    $.post("/cart/addCart",{"goodsId":goodsId,"goodsNum":goodsNum},function (res) {
        if(200 == res.status){
            getTotal();
            $("#s_cartbox").remove();
            layer.msg("添加至购物车成功")
        }
        
    })
}