<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>LIC商城</title>
	<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]>
	<link href="${ctx}/css/main.ie6.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<!--[if IE 7]>
	<link href="${ctx}/css/main.ie7.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<link href="${ctx}/css/page.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="${ctx}/css/info.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
	<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
	<script type="text/javascript" src="${ctx}/js/lib.js"></script>
	<script type="text/javascript" src="${ctx}/js/portal.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqpaginator.min.js"></script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>
	</div>
	<div id="s_bdw">
		<div id="s_bd">

			<div class="stepflow"><img src="${ctx}/images/step01.gif" width="980" height="32" alt="" /></div>

			<div class="cartlist">
				<table width="100%">
					<thead>
					<tr>
						<th>购物车中的商品</th><th>LIC价</th><th>购买数量</th><th>是否包邮</th><th>操作</th>
					</tr>
					</thead>
					<tbody id="cart_list">

					</tbody>
					<tr>
						<td colspan="5">
							<div class="pagecon" id="ego_pagenator">
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top"><a href="javascript:deleteAll()"><img src="${ctx}/images/deleteicon.gif" /> 清空购物车</a></td>
						<td align="right" colspan="5" id="allAmount">

						</td>
					</tr>
					<tr>
						<td style="border:none;padding-top:20px;" colspan="6"><input type="button" value="" id="" class="btnimg f-r" /><a class="f-r goonbtn" href="#"><img src="/images/gooncat.gif" width="86" height="24" alt="" /></a></td>
					</tr>
				</table>
			</div><!--cartlist end-->
		</div><!--s_bd end-->
	</div><!--s_bdw end-->
	<%@ include file="bottom.jsp" %>
</div>
<script type="text/javascript" src="${ctx}/js/cart.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript">
    /* reduce_add */
    var setAmount = {
        min:1,
        max:999,
        reg:function(x) {
            return new RegExp("^[1-9]\\d*$").test(x);
        },
        amount:function(obj, mode) {
            var x = $(obj).val();
            if (this.reg(x)) {
                if (mode) {
                    x++;
                } else {
                    x--;
                }
            } else {
                alert("请输入正确的数量！");
                $(obj).val(1);
                $(obj).focus();
            }
            return x;
        },
        reduce:function(obj) {
            var x = this.amount(obj, false);
            if (x >= this.min) {
                $(obj).val(x);
                var begin = obj.lastIndexOf("_");
                var goodsId = obj.substring(begin+1);
                addCart(goodsId,x,1);
            } else {
                alert("商品数量最少为" + this.min);
                $(obj).val(1);
                $(obj).focus();
            }
            setAllAmount();
        },
        add:function(obj) {
            var x = this.amount(obj, true);
            if (x <= this.max) {
                $(obj).val(x);
                var begin = obj.lastIndexOf("_");
                var goodsId = obj.substring(begin+1);
                addCart(goodsId,x,1);
            } else {
                alert("商品数量最多为" + this.max);
                $(obj).val(999);
                $(obj).focus();
            }
            setAllAmount();
        },
        modify:function(obj) {
            var x = $(obj).val();
            if (x < this.min || x > this.max || !this.reg(x)) {
                alert("请输入正确的数量！");
                $(obj).val(1);
                $(obj).focus();
            }
            var begin = obj.lastIndexOf("_");
            var goodsId = obj.substring(begin+1);
            addCart(goodsId,x,1);
        }
    }
</script>
<script type="text/javascript">
    $(function(){
        //隐藏菜单
        $(".cat_bd").hide();
        $('.cat_item').mousemove(function(){
            $(this).addClass('cat_item_on');
        });
        $('.cat_item').mouseleave(function(){
            $(this).removeClass('cat_item_on');
        });

        $(".cat_bd").mouseleave(function(){
            $(".cat_bd").hide();
        });
        $(".cat_hd").mousemove(function(){
            $(".cat_bd").show();
        });
    });
</script>
<script type="text/javascript">
    $(function(){
        findByPage(1);
    });
    //ajax获取购物车列表
    function findByPage(currentPage,pageSize){
        pageSize = pageSize?pageSize:5;
        $.post("/cart/findByPage",{"currentPage":currentPage,"pageSize":pageSize},function(res){
            var cartList = res.rows;
            var htmlstr='';
            for(i=0;i<cartList.length;i++){
                var cart = cartList[i];
                htmlstr +='<tr bgcolor="#fffaf1" id="cart_row_'+cart.id+'">';
                htmlstr +='<td>';
                htmlstr +='<a href="#"><img class="smallpic" src="'+cart.originalImg+'" width="80" height="80" /></a>';
                htmlstr +='<a href="#">'+cart.goodsName+'</a>';
                htmlstr +='</td>';
                htmlstr +='<td><strong class="red amount">￥'+cart.shopPrice+'</strong></td>';
                htmlstr +='<td>';

                htmlstr +='<div class="addinput">';
                htmlstr +='<input type="text" name="qty_item_'+cart.id+'" value="'+cart.cartNum+'" id="qty_item_'+cart.id+'" onKeyUp="setAmount.modify(\'#qty_item_'+cart.id+'\')" class="stext"/>';
                htmlstr +='<a class="add" onClick="setAmount.add(\'#qty_item_'+cart.id+'\')" href="javascript:void(0)"></a>';
                htmlstr +='<a class="reduce" onClick="setAmount.reduce(\'#qty_item_'+cart.id+'\')" href="javascript:void(0)"></a>';

                htmlstr +='</div>';
                htmlstr +='</td>';
                htmlstr +='<td>包邮</td>';
                htmlstr +='<td><a href="javascript:deleteGoods(\''+cart.id+'\')" class="blue">删除</a></td>';
                htmlstr +='</tr>';
            }
            $("#cart_list").html(htmlstr);
            //分页
            pagenator(res.totalPage,res.currentPage);
            setAllAmount();
        });
    }
    //删除购物车商品
    function deleteGoods(id) {
		$.post("/cart/delete",{"id":id},function (res) {
		    if(200 == res.status){
		        $("#cart_row_"+id).remove();
		        getTotal();
		        layer.msg("删除成功");
			}
        });
    }
    //清空购物车
	function  deleteAll() {
        layer.confirm('您确定清空购物车吗?', {
            btn: ['确定', '取消']
        	},function(){
            $.post("/cart/deleteAll",function (res) {
                if(200 == res.status){
                    getTotal();
                    $("#cart_list").remove();
                    $("#ego_pagenator").remove();
                    layer.msg("清空成功");
                }
            });
			},function(){

        });

		
    }

    //分页函数
    function pagenator(total,currentPage){
        $('#ego_pagenator').jqPaginator({
            totalPages: total,
            visiblePages: 10,
            currentPage: currentPage,
            first:'<span class="ego_page_span"><a href="javascript:void(0);">首页</a></span>',
            prev:'<span class="ego_page_span"><a href="javascript:void(0);">上一页</a></span>',
            next:'<span class="ego_page_span"><a href="javascript:void(0);">下一页</a></span>',
            last:'<span class="ego_page_span"><a href="javascript:void(0);">尾页</a></span>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
            onPageChange: function (num, type) {
                if("change" == type){
                    findByPage(num);
                }
            }
        });
    }

    //计算总金额
    function setAllAmount(){
        var amountList = $(".amount");
        var addinputList = $(".addinput");
        var allAmount = 0;
        var allNum = 0;
        for(i=0;i<amountList.length;i++){
            var amount = parseFloat($(amountList[i]).text().replace("￥",""));
            var num = parseInt($(addinputList[i]).find("input").val());
            allNum+=num;
            allAmount +=(amount*num);
        }
        var htmlStr='<p>共'+num+'件商品</p>';
        htmlStr+='<p style="margin-top:10px;font-size:14px;">';
        htmlStr+='总计金额(不含运费)：￥'+allAmount+'(商品金额) - ￥0.00(返现金额)= ';
        htmlStr+='<strong style="font-size:18px;color:#d80000;">￥'+allAmount+'</strong> </p>';
        $("#allAmount").html(htmlStr);
    }
</script>
</body>
</html>