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
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
<script type="text/javascript" src="${ctx}/js/lib.js"></script>
<script type="text/javascript">
var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

$(document).ready(function(){
	$('#slideshow').imgSlideShow({itemclass: 'i'})
	$("#slide-qg").switchTab({titCell: "dt a", trigger: "mouseover", delayTime: 0});
	$("#s_cart_nums1").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cart_nums2").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cartbox").hover(function(){
		mcancelclosetime();
	},function(){
		mclosetime();
	});
	 var $cur = 1;
    var $i = 4;
    var $len = $('.hot_list>ul>li').length;
    var $pages = Math.ceil($len / $i);
    var $w = $('.hotp').width()-66;

    var $showbox = $('.hot_list');

    var $pre = $('div.left_icon');
    var $next = $('div.rgt_icon');

    $pre.click(function(){
        if (!$showbox.is(':animated')) {
            if ($cur == 1) {
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 500);
                $cur = $pages;
            }
            else {
                $showbox.animate({
                    left: '+=' + $w
                }, 500);
                $cur--;
            }

        }
    });

    $next.click(function(){
        if (!$showbox.is(':animated')) {
            if ($cur == $pages) {
                $showbox.animate({
                    left: 0
                }, 500);
                $cur = 1;
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 500);
                $cur++;
            }

        }
    });

});
function mclose()
{
	if(ddmenuitem) ddmenuitem.hide();
}
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}
function mcancelclosetime()
{

	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}


</script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>
	</div><!--s_hdw end-->
	<div id="s_bdw">
		<div id="s_bd">
			<div class="cf">
				<div id="i_col_lft" class="i_col_lft">
					<div class="categories"></div>
				</div>
				<div id="i_col_rgt" class="i_col_rgt">
					<div class="i_col_rgt_box">

						<div class="i_slides" id="slideshow">

						</div>
						<script type="text/javascript">
                            $(function(){
                                $.ajax({
                                    url:"http://localhost:8083/content/findIndexSlideImg",
                                    type:"post",
                                    dataType:"jsonp",
									jsonp:"callBack",
                                    success:function(res){
                                        var htmlStr='';
                                        for(i = 0;i<res.length;i++){
                                            htmlStr+='<div class = "i"><a href="#"><img src="'+res[i]+'" /></a></div>';
                                        }
                                        $("#slideshow").html(htmlStr);
                                        $('#slideshow').imgSlideShow({itemclass: 'i'})
                                    }
                                })
                            });
						</script>

						<div class="pbt10"></div>

						<div class="lft">
							<div class="hotp">
								<div class="lft_icon"><a href="#"><span>pre</span></a></div>
								<div class="hot_list">
									<ul>
										<li><a href="#"><img src="${ctx}/images/f1.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f2.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f3.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f4.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f5.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f6.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f7.jpg" /></a></li>
										<li><a href="#"><img src="${ctx}/images/f8.jpg" /></a></li>
									</ul>
								</div>
								<div class="rgt_icon"><a href="#"><span>Nexr</span></a></div>
							</div>
						</div>

						<div class="rgt">
							<div class="rgt-box">
								<div class="loginbox">
									<div class="login_icon cf">
										<ul>
											<li><a href="#">免费注册</a></li>
											<li><a href="#">用户登录</a></li>
										</ul>
									</div>

									<div class="announce_top cf"><h3>关注LIC商城<span><a href="#">更多</a></span></h3></div>

									<div class="announce_cont">
										<ul>
											<li><a href="#">LIC商城促销活动开始</a></li>
											<li><a href="#">耐克专卖店开业</a></li>
											<li><a href="#">贺LIC商城盛大开业</a></li>
											<li><a href="#">凡注册为LIC商城网上商城的会员</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>

			</div>


		</div>
	</div><!--s_bdw end-->
	<%@ include file="xiaomi.jsp" %>
	<%@ include file="xiaomi2.jsp" %>
	<%@ include file="xiaomi3.jsp" %>

</div>
</body>
</html>
<%@ include file="bottom.jsp" %>
