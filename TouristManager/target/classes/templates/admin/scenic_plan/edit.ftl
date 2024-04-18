<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Attraction Guide Management-Edit attraction guide</title>
<#include "../common/header.ftl"/>

</head>

<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">

      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="/system/index"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll">
        <#include "../common/left-menu.ftl"/>
      </div>

    </aside>
    <!--End 左侧导航-->

    <#include "../common/header-menu.ftl"/>

     <!--页面主要内容-->
    <main class="lyear-layout-content">

      <div class="container-fluid">

        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>Edit attraction guide</h4></div>
              <div class="card-body">
                <form id="scenic-plan-edit-form" class="row">
              <input type="hidden" name="scenic.id" value="${scenicPlan.scenic.id!""}"/>
                  <input type="hidden" name="id" value="${scenicPlan.id}"/>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">name</span>
                    <input type="text" class="form-control required" id="edit-name" name="name" value="${scenicPlan.name!""}"
                           placeholder="Please fill in the name" tips="Please fill in the name" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">brief introduction</span>
                    <input type="text" class="form-control required" id="edit-intro" name="intro" value="${scenicPlan.intro!""}"
                           placeholder="Please fill in a brief introduction" tips="Please fill in a brief introduction" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">content</span>
                    <textarea  class="form-control required" id="edit-content" style="height: 231px;" name="content"
                               placeholder="Please fill in the content" tips="Please fill in the content" >${scenicPlan.content!""}</textarea>
                  </div>
                    <div class="form-group col-md-12">
                        <label>picture</label>
                        <div class="form-controls">
                            <ul class="list-inline clearfix lyear-uploads-pic" id="show-uploaded-pic">
                                <#if scenicPlan.images?? && scenicPlan.images?length gt 0>
                                    <#list scenicPlan.images?split(";") as photo>
                                        <li class="col-xs-4 col-sm-3 col-md-2">
                                            <figure>
                                                <img src="/photo/view?filename=${photo}" width="176px" height="300px">
                                                <figcaption>
                                                    <a class="btn btn-round btn-square btn-danger del-img-btn" href="javascript:void(0)" data-val="${photo}"><i class="mdi mdi-delete"></i></a>
                                                </figcaption>
                                            </figure>
                                        </li>
                                    </#list>
                                <#else>
                                    <img src="/admin/images/default-head.jpg" width="130px" height="300px">
                                </#if>
                                <input type="hidden" name="images" value="${scenicPlan.images!""}" id="picture">
                                <input type="file" id="select-picture-file" style="display:none;" onchange="uploadPicture()">
                                <li class="col-xs-4 col-sm-3 col-md-2">
                                    <a class="pic-add" id="edit-images-btn" href="javascript:void(0)" title="click to upload"></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="edit-form-submit-btn">Sure</button>
                    <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">Return</button>
                  </div>
                </form>

              </div>
            </div>
          </div>

        </div>

      </div>

    </main>
    <!--End 页面主要内容-->
  </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//提交按钮监听事件
	$("#edit-form-submit-btn").click(function(){
		if(!checkForm("scenic-plan-edit-form")){
			return;
		}
		var data = $("#scenic-plan-edit-form").serialize();
		$.ajax({
			url:'edit_plan',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Attraction guide edited successfully!',function(){
                        window.location.href = 'list_plan?scenic.id='+data.data;
					})
				}else{
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('Network Error!');
			}
		});
	});

});

//监听删除图片
$(".del-img-btn").click(function(){
    var pic = $(this).attr('data-val');
    var picture = $("#picture").val();
    picture = picture.replace(pic+';','');
    picture = picture.replace(';' + pic,'');
    picture = picture.replace(pic,'');
    $("#picture").val(picture);
    $(this).closest("li").remove();
});
function uploadPicture(){
    if($("#select-picture-file").val() == '')return;
    var picture = document.getElementById('select-picture-file').files[0];
    uploadPhoto(picture,function(data){
        var html = '<li class="col-xs-4 col-sm-3 col-md-2 show-img"><figure>';
        html += '<img src="/photo/view?filename='+data.data+'" alt="图片"></figure></li>';
        if($("#show-uploaded-pic>li.show-img").length > 0){
            $("#show-uploaded-pic>li.show-img:last").after(html);
        }else{
            $("#show-uploaded-pic").prepend(html);
        }
        var pictures = $("#picture").val() == '' ? '' : $("#picture").val() + ';';
        $("#picture").val( pictures + data.data);
    });
}
$("#edit-images-btn").click(function () {
    $("#select-picture-file").click();
})
</script>
</body>
</html>
