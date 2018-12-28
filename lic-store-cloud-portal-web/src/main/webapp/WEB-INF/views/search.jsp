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
	<script type="text/javascript" src="${ctx}/js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
	<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
	<script type="text/javascript" src="${ctx}/js/lib.js"></script>
	<script type="text/javascript" src="${ctx}/js/portal.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqpaginator.min.js"></script>
	<style type="text/css">
		.cat_bd{
			display: none;
		}
	</style>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>
	</div>
	<link type="text/css" href="${ctx}/css/list.css" rel="stylesheet" />
	<div id="s_bdw">
		<div id="s_bd">
			<div class="zadv"><a href="#"><img src="${ctx}/images/3215wa.jpg" width="980" height="62" alt="" /></a></div>

			<div class="breadcrumbs">
				<div class="f-l"><a href="#">全部结果</a><span>»</span><a href="#">食品、饮料、酒水</a><span>»</span><a href="#">进口食品</a><span>»</span>进口米</div>
				<div class="f-r">搜索结果(<b class="red">19</b>)</div>
			</div>

			<div class="f-l leftlist">
				<div class="sort">
					<h2>筛选分类</h2>
					<h3><a href="#">大家电、生活电器(3890)</a></h3>
					<dl>
						<dt><a href="#">厨房电器(1001)</a></dt>
						<dd>
							<span>豆浆机(85)</span>
							<a href="#">微波炉(35)</a>
							<a href="#">电压力锅(181)</a>
							<a href="#">电水壶/热水瓶(144)</a>
							<a href="#">电磁炉(53)</a>
							<a href="#">多用途锅(78)</a>
							<a href="#">电饼铛/煎拷机(3)</a>
							<a href="#">煮蛋器(34)</a>
						</dd>
					</dl>
					<h3><a href="#">大家电、生活电器(3890)</a></h3>
					<dl>
						<dt><span>厨房电器(1001)</span></dt>
						<dd>
							<a href="#">豆浆机(85)</a>
							<a href="#">微波炉(35)</a>
							<a href="#">电压力锅(181)</a>
							<a href="#">电水壶/热水瓶(144)</a>
							<a href="#">电磁炉(53)</a>
							<a href="#">多用途锅(78)</a>
							<a href="#">电饼铛/煎拷机(3)</a>
							<a href="#">煮蛋器(34)</a>
						</dd>
					</dl>
				</div><!--sort end-->

				<div class="ladv"><a href="#"><img src="${ctx}/images/2asd.jpg" width="205" height="72" alt="" /></a></div>

				<div class="ladv"><a href="#"><img src="${ctx}/images/12ad.jpg" width="205" height="72" alt="" /></a></div>

				<div class="ladv"><a href="#"><img src="${ctx}/images/21af.jpg" width="205" height="72" alt="" /></a></div>


			</div><!--leftlist end-->

			<div class="f-r rightlist">

				<div class="hotbox cf">
					<div class="f-l hotcon">
						<h2>热卖推荐</h2>
						<ul class="cf">
							<li>
								<a href="#"><img src="${ctx}/images/xiaomiphone1.jpg" width="115" height="115" alt="" /></a>
								<dl>
									<dt><a href="#">小米5X 4GB+64GB</a></dt>
									<dd>特价：<strong class="red">￥3999.0</strong></dd>
									<dd><span class="startotal"></span></dd>
									<dd><a class="addcat" href="#">加入购物车</a></dd>
								</dl>
							</li>
							<li>
								<a href="#"><img src="${ctx}/images/xiaomiphone2.jpg" width="115" height="115" alt="" /></a>
								<dl>
									<dt><a href="#">红米Note 5A 2GB+16GB </a></dt>
									<dd>特价：<strong class="red">￥4999.0</strong></dd>
									<dd><span class="startotal"></span></dd>
									<dd><a class="addcat" href="#">加入购物车</a></dd>
								</dl>
							</li>
						</ul>
					</div>
					<div class="f-l promotion">
						<h2>促销活动</h2>
						<p>指定冰洗买就送插座!液晶电视清仓大放价，小家电惠战十月，最低三大合资空调疯狂抢购</p>
					</div>
				</div><!--hotbox end-->

				<div class="retrieve">
					<dl class="cf">
						<dt>品牌：</dt>
						<dd><span><a href="#" class="current">全部</a></span><span><a href="#">手机(1)</a></span><span><a href="#">电脑(3)</a></span><span><a href="#">耳机(6)</a></span><span><a href="#">充电宝(8)</a></span></dd>
					</dl>
					<dl class="cf">
						<dt>包装：</dt>
						<dd><span><a href="#" class="current">全部</a></span><span><a href="#">精包装(19)</a></span></dd>
					</dl>
					<div class="clear"></div>
				</div><!--retrieve end-->

				<div class="product">
					<div class="productsreach">
						<dl>
							<dt>显示：</dt><dd><a class="current" id="imgicon" href="#">图片</a><a id="listicon" href="#">列表</a></dd>
						</dl>
						<dl style="margin:0;">
							<dt>排列：</dt>
							<dd>
								<div id="rankmenu">
									<a href="#">默认排序</a>
									<ul class="cf">
										<li><a href="#">价格高低</a></li>
										<li><a href="#">上架时间</a></li>
									</ul>
								</div>
								<div class="iconsreach"><a class="current" id="price" href="#">价格</a><a id="sales" href="#">销量</a><a id="discuss" href="#">评论</a></div>
							</dd>
						</dl>
						<dl class="last">
							<dt>筛选：</dt>
							<dd>
								<input type="checkbox" name="" id="cx" /><label for="cx">促销</label>
								<input type="checkbox" name="" id="zp" /><label for="zp">有赠品</label>
								<input type="checkbox" name="" id="xp" /><label for="xp">新品</label>
							</dd>
						</dl>
					</div>
				</div><!--product end-->

				<script type="text/javascript">
                    $(document).ready(function(){
                        $("#rankmenu").hoverClass("current");
                    });
				</script>

				<div class="productlist">
					<ul id="productlist">
						<!-- 由ajax动态拼接字符串 -->
					</ul>
				</div>

				<div class="clear"></div>

				<div class="pagecon" id="ego_pagenator">
				</div>

			</div><!--rightlist end-->

			<div class="clear"></div>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->

	<input type="hidden" name="keywords_hide" id="keywords_hide" value="${keywords}"/>
</div>
<style type="text/css">
	.ego_page_span {
		border: 1px solid #D3D3D3;
		width: 66px;
		height: 26px;
		line-height: 26px;
		display: inline-block;
		text-align: center;
		margin-left: 5px;
	}
	#ego_pagenator li{
		display: inline-block;
	}
	#ego_pagenator li a{
		display: inline-block;
		width:26px;
		height: 26px;
		line-height: 26px;
		text-align: center;
		border: 1px solid #D3D3D3;
		margin-left: 5px;
		color:#3A5CBD;
	}
	#ego_pagenator .active a{
		color:red;
	}
</style>
<script type="text/javascript" src="${ctx}/js/cart.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
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

        //获取得到的关键字
        var keywords_hide = $("#keywords_hide").val();
        $("#keywords").val(keywords_hide);
        //执行搜索
        doSearch(1);
    });

    //执行搜索
    function doSearch(currentPage){
        var keywords = $("#keywords_hide").val();
        $.post("/dosearch",{"currentPage":currentPage,"pageSize":20,"keywords":keywords},function(res){
            //展示列表
            var htmlStr = '';
            for(i=0;i<res.rows.length;i++){
                var row = res.rows[i];
                htmlStr += '<li title="'+row.goodsName+'('+row.id+')">';
                htmlStr += '<a href="#"><img src="'+row.originalImg+'" width="170" height="160" alt="" /></a>';
                htmlStr += '<dl>';
                // htmlStr += '<dt><a href="#">'+row.goodsNameHl+'</a></dt>';
                htmlStr += '<dd>特价：<strong class="red">￥'+row.shopPrice+'</strong></dd>';
                htmlStr += '<dd><span class="startotal"></span></dd>';
                htmlStr += '<dd><a class="addcat" href="javascript:addCart('+row.id+',1)">加入购物车</a></dd>';
                htmlStr += '</dl>';
                htmlStr += '</li>';
            }
            $("#productlist").html(htmlStr);
            //分页
            goPage(res.totalPage,res.pageSize,res.currentPage);
        });
    }

    //分页函数
    function goPage(totalPages,visiblePages,currentPage){
        $('#ego_pagenator').jqPaginator({
            totalPages: totalPages,
            visiblePages: visiblePages,
            currentPage: currentPage,
            first:'<span class="ego_page_span"><a href="javascript:void(0);">首页</a></span>',
            prev:'<span class="ego_page_span"><a href="javascript:void(0);">上一页</a></span>',
            next:'<span class="ego_page_span"><a href="javascript:void(0);">下一页</a></span>',
            last:'<span class="ego_page_span"><a href="javascript:void(0);">尾页</a></span>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
            onPageChange: function (num, type) {
                if('change'==type){
                    doSearch(num);
                }
            }
        });
    }
</script>
</body>
<%@ include file="bottom.jsp" %>
</html>
