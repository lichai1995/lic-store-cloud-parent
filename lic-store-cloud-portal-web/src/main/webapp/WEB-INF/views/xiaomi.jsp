<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>布局作业</title>
	<style>
		*{
			margin: 0;
			padding: 0;
		}
		body{
			background-color:rgb(245,245,245);
		}

		.content{
			width: 1210px;
			height: 615px;
			margin: 20px auto;
		}

		.left{
			width: 230px;
			height: 615px;
			background: yellow;
			float: left;
		}
		.right>div{
			width: 230px;
			height: 300px;
			background: #FFFFFF;
			float: left;
			margin-bottom: 15px;
			position: relative;

		}
		.right>div:nth-child(1),.right>div:nth-child(3),.right>div:nth-child(5),.right>div:nth-child(7){
			margin: 0 15px;
		}

		.content .right img{
			width: 150px;
			height: 150px;
			position: absolute;
			left: 115px;
			top: 150px;
			margin-top:-120px;
			margin-left:-75px;
		}
		.title{
			font-size: 14px;
			position: absolute;
			top: 180px;
			left: 25px;
		}
		.introduce{
			font-size: 11px;
			color: #b0b0b0;
			position: absolute;
			top: 205px;
			left: 15px;
		}
		p .price{
			font-size: 15px;
			color:#ff6700;
			position: absolute;
			top: 230px;
			left: 90px;
		}
		.foot{

		}
		.foot_top,.foot_bottom{
			width: 230px;
			height: 143px;
			position: relative;
			float: left;
			margin-bottom: 15px;
			background: #FFFFFF;
		}
		.foot_top img{
			width: 80px;
			height: 80px;
			margin-bottom: 15px;
			position: absolute;
			top: 30px;
			left: 150px;

		}

		.foot_bottom img{
			width: 80px;
			height: 80px;
			position: absolute;
			top: 30px;
			left: 150px;
		}
		.foot_introduce{
			font-size: 14px;
			position: absolute;
			top: 50px;
			left: 30px;
		}
		.foot_price{
			font-size: 15px;
			color:#ff6700;
			position: absolute;
			top: 75px;
			left: 30px;
		}
		.productlist{width:90%;height:80%;
			margin: 0 auto;}
		.productlist li{float:left;padding:10px 20px;width:200px;}
		.productlist li img{border:solid 1px #ccc;
			margin: 10px 40px;}
		.productlist li dl dt{height:32px;line-height:16px;overflow:hidden;}
		.productlist li dl dd{padding:8px 0 0 55px;}


		.productlist2{width:90%;height:80%;
			margin: 0 auto;}
		.productlist2 li{float:left;padding:10px 20px;width:200px;}
		.productlist2 li img{border:solid 1px #ccc;
			margin: 10px 40px;}
		.productlist2 li dl dt{height:32px;line-height:16px;overflow:hidden;}
		.productlist2 li dl dd{padding:8px 0 0 55px;}

		.addcat{border:solid 1px #ccc;
			background:#ef581c;color:#fff!important;
			display:block;width:80px;height:24px;line-height:24px;overflow:hidden;text-align:center;}

	</style>
</head>
<body>
<%--手机--%>
<div class="productlist">
	<ul id="productlist">
		<!-- 由ajax动态拼接字符串 -->
	</ul>
</div>
<%--电脑--%>

<%--日用品--%>
<script type="text/javascript" src="${ctx}/js/cart.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>

<script type="text/javascript">
    $(function(){
        $.post("/dosearch",{"currentPage":0,"pageSize":12,"keywords":"小米"},function(res){
            //展示列表
            var htmlStr = '';
            for(i=0;i<res.rows.length;i++){
                var row = res.rows[i];
                htmlStr += '<li title="'+row.goodsName+'('+row.id+')">';
                htmlStr += '<a href="#"><img src="'+row.originalImg+'" width="200" height="180" alt="" /></a>';
                htmlStr += '<dl>';
                htmlStr += '<dd>特价：<strong class="red">￥'+row.shopPrice+'</strong></dd>';
                htmlStr += '<dd><span class="startotal"></span></dd>';
                htmlStr += '<dd><a class="addcat" href="javascript:addCart('+row.id+',1)">加入购物车</a></dd>';
                htmlStr += '</dl>';
                htmlStr += '</li>';
            }
            $("#productlist").html(htmlStr);
        });
    });
</script>


</body>
</html>