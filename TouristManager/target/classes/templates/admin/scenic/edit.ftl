<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Attraction Management-Edit attraction</title>
<#include "../common/header.ftl"/>
  <link href="/admin/css/city-picker.css" rel="stylesheet">

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
              <div class="card-header"><h4>Edit attraction</h4></div>
              <div class="card-body">
                <form id="scenic-edit-form" method="post" class="row">
                  <input type="hidden" name="id" value="${scenic.id}"/>
                  <div class="form-group col-md-12">
                    <label>cover picture</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <#if scenic.coverPic?? && scenic.coverPic?length gt 0>
                              <img src="/photo/view?filename=${scenic.coverPic}" id="show-picture-img">
                            <#else>
                              <img src="/admin/images/default-head.jpg" id="show-picture-img" alt="Default avatar">
                            </#if>
                          </figure>
                        </li>
                        <input type="hidden" name="coverPic" value="${scenic.coverPic!""}" id="headPic">
                        <input type="file" id="select-file" style="display:none;" onchange="upload('show-picture-img','headPic')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="edit-pic-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="form-group col-md-12">
                    <label>Attraction map upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic" id="show-uploaded-pic">
                        <#if scenic.images?? && scenic.images?length gt 0>
                          <#list scenic.images?split(";") as photo>
                            <li class="col-xs-4 col-sm-3 col-md-2">
                              <figure>
                                <img src="/photo/view?filename=${photo}" width="130px" height="150px">
                                <figcaption>
                                  <a class="btn btn-round btn-square btn-danger del-img-btn" href="javascript:void(0)" data-val="${photo}"><i class="mdi mdi-delete"></i></a>
                                </figcaption>
                              </figure>
                            </li>
                          </#list>
                        <#else>
                          <img src="/admin/images/default-head.jpg" width="130px" height="150px">
                        </#if>
                        <input type="hidden" name="images" value="${scenic.images!""}" id="picture">
                        <input type="file" id="select-picture-file" style="display:none;" onchange="uploadPicture()">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="edit-images-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Title</span>
                    <input type="text" class="form-control required" id="edit-title" name="title" value="${scenic.title!""}"
                           placeholder="Please fill in the title" tips="Please fill in the title" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Address</span>
                    <input type="text" class="form-control required" id="edit-address" name="address" value="${scenic.address!""}"
                           placeholder="Please fill in the detailed address" tips="Please fill in the detailed address" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Price</span>
                    <input type="number" class="form-control required"
                           min="0"  id="price" name="price"
                           value="${scenic.price!"0.00"}" placeholder="Please enter price" tips="Please enter price" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Opening hours</span>
                    <input type="text" class="form-control required" id="edit-openTime" name="openTime" value="${scenic.openTime!""}"
                           placeholder="Please fill in the opening hours" tips="Please fill in the opening hours" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Official phone number</span>
                    <input type="text" class="form-control required" id="edit-officialPhone" name="officialPhone" value="${scenic.officialPhone!""}"
                           placeholder="Please fill in the official phone number" tips="Please fill in the official phone number" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Detailed introduction</span>
                    <textarea style="width:auto;height:250px" id="edit-content" name="content">${scenic.content!""}</textarea>
                  </div>
                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    State：
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="0" <#if scenic.status==0>checked</#if>>
                    <span>On the shelves</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1" <#if scenic.status==1>checked</#if>>
                    <span>Removed from shelves</span>
                  </label>
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
<script type="text/javascript" charset="utf-8" src="/admin/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/admin/kindeditor/kindeditor-all.js"></script>


<script type="text/javascript">
$(document).ready(function(){
  KindEditors("edit-content")
	//提交按钮监听事件
	$("#edit-form-submit-btn").click(function(){
		if(!checkForm("scenic-edit-form")){
			return;
		}
		var data = $("#scenic-edit-form").serialize();
		$.ajax({
			url:'edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Attraction edited successfully!',function(){
						window.location.href = 'list';
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
	//监听上传图片按钮
	$("#edit-pic-btn").click(function(){
		$("#select-file").click();
	});

	$("#edit-images-btn").click(function () {
      $("#select-picture-file").click();
    })

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
    html += '<img src="/photo/view?filename='+data.data+'" alt="picture"></figure></li>';
    if($("#show-uploaded-pic>li.show-img").length > 0){
      $("#show-uploaded-pic>li.show-img:last").after(html);
    }else{
      $("#show-uploaded-pic").prepend(html);
    }
    var pictures = $("#picture").val() == '' ? '' : $("#picture").val() + ';';
    $("#picture").val( pictures + data.data);
  });
}
</script>
</body>
</html>
