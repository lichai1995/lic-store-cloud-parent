<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="s_cats">
	<div class="cat_hd"><h3><a href="#">所有商品分类</a></h3></div>
	<div class="cat_bd" style="display:block;" id="menu_body">
		<ul id="goods_category_content">
			<!-- 通过ajax访问 -->
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"http://localhost:8083/goods/cat/getMenu",
			type:"POST",
			dataType:"jsonp",
			jsonp:"callBack",
			success:function(res){
				var htmlStr = '';
				for(i=0;i<res.length;i++){
					var level_1 = res[i];
					htmlStr += '<li class="cat_item">';
					htmlStr += '<h4 class="cat_tit">';
					htmlStr += '<a href="#" class="cat_tit_link">'+level_1.name+'</a>';
					htmlStr += '<span class="s_arrow">></span></h4>';
					htmlStr += '<div class="cat_cont">';
					htmlStr += '<div class="cat_cont_lft">';
					for(j=0;j<level_1.children.length;j++){
						var level_2 = level_1.children[j];
						htmlStr += '<dl class="cf">';
						htmlStr += '<dt><a href="#">'+level_2.name+'</a></dt>';
						htmlStr += '<dd>';
						htmlStr += '<ul>';
						for(k=0;k<level_2.children.length;k++){
							var level_3 = level_2.children[k];
							if(k==0){
								htmlStr += '<li class="first"><a href="#">'+level_3.name+'</a></li>';
							}else{
								htmlStr += '<li><a href="#">'+level_3.name+'</a></li>';
							}
						}
						htmlStr += '</ul>';
						htmlStr += '</dd>';
						htmlStr += '</dl>';
					}
					htmlStr += '</div>';
					htmlStr += '</div>';
					htmlStr += '</li>';
				}
				$("#goods_category_content").html(htmlStr);
				//鼠标移入移除事件
				$('.cat_item').mousemove(function(){
					$(this).addClass('cat_item_on');
				});
				$('.cat_item').mouseleave(function(){	
					$(this).removeClass('cat_item_on');
				});
			}
		});
	});
</script>