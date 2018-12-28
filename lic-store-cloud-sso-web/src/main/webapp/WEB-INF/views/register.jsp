<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>LIC商城用户注册</title>
	<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]>
	<link href="${ctx}/css/main.ie6.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<!--[if IE 7]>
	<link href="${ctx}/css/main.ie7.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<link type="text/css" href="${ctx}/css/lr.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-imgslideshow.js"></script>
	<script type="text/javascript" src="${ctx}/js/ks-switch.js"></script>
	<script type="text/javascript" src="${ctx}/js/lib.js"></script>
	<script type="text/javascript" src="${ctx}/js/portal.js"></script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
		<%@ include file="head.jsp" %>
	</div>

	<div id="s_bdw">
		<div id="s_bd">

			<div class="dl_zc">
				<div class="dl_zc_title">
					<h2 class="f_l">注册用户</h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="dl-con" id="regist">
					<form id="formpersonal" method="post" onsubmit="return false;">
						<div class="form">
							<div class="tipinfo">填写账户信息，以下信息均为必填</div>
							<div class="item">
								<span class="label">邮箱：</span>

								<div class="fl">
									<input type="text" id="mail" name="email" class="text" tabindex="4"/>
									<label id="mail_succeed" class="blank"></label>
									<span class="clear"></span>

									<div id="mail_error"></div>
								</div>
							</div>
							<!--item end-->
							<div class="item">
								<span class="label">用户姓名：</span>

								<div class="fl">
									<input type="text" id="username" name="userName" class="text" tabindex="1"/>

									<label id="username_succeed" class="blank"></label>
									<span class="clear"></span>

									<div id="username_error"></div>
								</div>
							</div>
							<!--item end-->
							<div id="o-password">
								<div class="item">

									<span class="label">设置密码：</span>

									<div class="fl">
										<input type="password" id="pwd" name="password" class="text" tabindex="2"/>
										<label id="pwd_succeed" class="blank"></label>
										<input type="checkbox" class="checkbox" id="viewpwd"/>
										<label class="ftx23" for="viewpwd">显示密码字符</label>
										<span class="clear"></span>

										<label class="hide" id="pwdstrength"><span class="fl">安全程度：</span><b></b></label>
										<label id="pwd_error"></label>
									</div>
								</div>
								<!--item end-->
								<div class="item">
									<span class="label">确认密码：</span>

									<div class="fl">
										<input type="password" id="pwd2" name="password2" class="text" tabindex="3"/>
										<label id="pwd2_succeed" class="blank"></label>
										<span class="clear"></span>
										<label id="pwd2_error"></label>
									</div>
								</div>
								<!--item end-->
							</div>

							<div class="item" style="display: none;">
								<span class="label">验证码：</span>

								<div class="fl">
									<input type="text" id="authcode" name="authcode" class="text text-1" tabindex="6"
										   autocomplete="off" MaxLength="6"/>
									<label class="img">
										<img id="JD_Verification1" Ver_ColorOfNoisePoint="#888888"
											 src="/Inc/Code/adminCode.php?sesstr=regCode" onClick="chanageCode('regCode')"
											 alt="" style="cursor:pointer;width:100px;height:26px;"/>
									</label>
									<label>&nbsp;看不清？点击验证码图片更换</label>
									<label id="authcode_succeed" class="blank invisible"></label>
									<span class="clear"></span>

									<label id="authcode_error"></label>
								</div>
							</div>
							<!--item end-->

							<div class="item">
								<span class="label">&nbsp;</span>

								<div class="fl">
									<input type="checkbox" name="" id="xieyi" value="" /><label class="blue" for="xieyi">自愿遵守LIC服务协议</label>
								</div>
							</div>
							<!--item end-->

							<div class="item">
								<span class="label">&nbsp;</span>
								<input type="button" class="btnimg" id="registsubmit" value=""
									   tabindex="8"/>
							</div>
							<!--item end-->

						</div>
					</form>
				</div><!--regist end-->
			</div><!--dl_zc end-->

			<script type="text/javascript" src="${ctx}/js/Validate.js"></script>
			<script type="text/javascript" src="${ctx}/js/Validate.personal.js"></script>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->
	<%@ include file="bottom.jsp" %>
</div>
<script type="text/javascript">
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
</script>
</body>
</html>
