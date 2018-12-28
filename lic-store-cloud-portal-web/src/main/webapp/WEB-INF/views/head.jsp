<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="s_tbar">
	<div class="s_hd">
		<div class="tbar_lft">
			<a href="#">请登录</a> | <a href="#" style="color:red;font-size:12px">免费注册</a>
		</div>
		<div class="tbar_rgt">
			<ul>
				<li class="first"><a href="#">我的订单</a></li>
				<li><a href="#">我的LIC</a></li>
				<li><a href="#">LIC会员</a></li>
				<li><a href="#">联系客服</a></li>
				<li><a href="#">网站导航</a></li>
				<li class="s_tel_str">服务热线：</li>
				<li class="s_tel">123-456-7890</li>
			</ul>
		</div>
	</div>
</div>
<!--s_tbar end-->

<div class="s_hd nav">
	<div id="s_logo">
		<a href="#"><img src="${ctx }/images/logo.png" border="0" /></a>
	</div>
	<div id="s_nav">
		<ul>
			<li class="first cur"><a href="#">秒杀</a><span></span></li>
			<li><a href="#">优惠券</a><span></span></li>
			<li><a href="#">服装城</a><span></span></li>
			<li class="last"><a href="#">全球购</a><span></span></li>
		</ul>
	</div>
</div>
<!--s_hd end-->

<div class="mmenu">
	<div class="s_hd">
		<div id="s_search">
			<form action="${ctx }/search" method="post">
				<input name="keywords" id="keywords" value="${keywords }" type="text" class="search-input" />
				<input name="" type="image" src="${ctx }/images/btn_search.jpg" />
			</form>
		</div>

		<div id="s_keyword">
			<ul>
				<li><strong>热门搜索：</strong></li>
				<li><a href="#">iphoneX</a></li>
				<li><a href="#">三星</a></li>
				<li><a href="#">华为</a></li>
			</ul>
		</div>

		<div id="s_cart" style="position: fixed;right: 10px;top:123px;">
			<ul>
				<li class="nums">
					<a href="${ctx }/cart/list" id="s_cart_nums1">
						购物车： <span id="s_cart_num1_span">0</span>件
					</a>
					<a href="${ctx }/cart/list" class="btn" id="s_cart_nums2"></a></li>
				<li class="checkout"><a href="#">去结算>></a></li>
			</ul>
		</div>
		<div id="s_cartbox" class="s_cartbox">您的购物车中暂无商品，赶快选择心爱的商品吧！</div>
		<%@ include file="menu.jsp" %>
	</div>
</div>
<script type="text/javascript">
	function getCartTotal(){
		$.post("${ctx}/cart/getTotal",function(res){
			$("#cart_total").html(res);
			if(res>0){
				$("#s_cartbox").remove();
			}
			
		});
	}
	//获取购物车总数量
	$(function(){
		getCartTotal();
	});
</script>