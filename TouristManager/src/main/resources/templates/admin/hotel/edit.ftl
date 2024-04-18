<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Hotel Management-Edit Hotel</title>
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
              <div class="card-header"><h4>Edit hotel</h4></div>
              <div class="card-body">
                <form id="hotel-edit-form" method="post" class="row">
                  <input type="hidden" name="id" value="${hotel.id}"/>
                  <div class="form-group col-md-12">
                    <label>cover picture</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <#if hotel.coverPic?? && hotel.coverPic?length gt 0>
                              <img src="/photo/view?filename=${hotel.coverPic!""}" id="show-picture-img" alt="cover picture">
                          <#else>
                            <img src="/admin/images/default-head.jpg" id="show-picture-img" alt="cover picture">
                            </#if>
                          </figure>
                        </li>
                        <input type="hidden" name="coverPic" value="${hotel.coverPic!""}" id="headPic">
                        <input type="file" id="select-file" style="display:none;"
                               onchange="upload('show-picture-img','headPic')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="edit-pic-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="form-group col-md-12">
                    <label>Hotel map upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic" id="show-uploaded-pic">
                        <#if hotel.images?? && hotel.images?length gt 0>
                          <#list hotel.images?split(";") as photo>
                            <li class="col-xs-4 col-sm-3 col-md-2">
                              <figure>
                                <img src="/photo/view?filename=${photo}" width="130px" height="150px">
                                <figcaption>
                                  <a class="btn btn-round btn-square btn-danger del-img-btn"
                                     href="javascript:void(0)" data-val="${photo}"><i class="mdi mdi-delete"></i></a>
                                </figcaption>
                              </figure>
                            </li>
                          </#list>
                        <#else>
                          <img src="/admin/images/default-head.jpg" width="130px" height="150px">
                        </#if>
                        <input type="hidden" name="images" value="${hotel.images!""}" id="picture">
                        <input type="file" id="select-picture-file" style="display:none;" onchange="uploadPicture()">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="edit-images-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">name</span>
                    <input type="text" class="form-control required" id="edit-name" name="name" value="${hotel.name!""}"
                           placeholder="Please fill in the name" tips="Please fill in the name" />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">average price</span>
                    <input type="number" class="form-control required" id="edit-avgPrice"
                           name="avgPrice"
                           value="${hotel.avgPrice!""}"
                           placeholder="Please fill in the average price" tips="Please fill in the average price" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Contact information</span>
                    <input type="tel" class="form-control required" id="edit-phone"
                           name="phone"
                           value="${hotel.phone!""}"
                           placeholder="Please fill in the contact information" tips="Please fill in the contact information" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Address</span>
                    <input type="text" class="form-control required" id="edit-address" name="address" value="${hotel.address!""}"
                           placeholder="Please fill in the detailed address" tips="Please fill in the detailed address"  />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Service Information</span>
                    <textarea style="height:250px" id="edit-service"
                              placeholder="Use | to separate multiple services"
                              class="form-control required" tips="Please fill in service information"
                              name="service">${hotel.service!""}</textarea>
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Detailed introduction</span>
                    <textarea style="width:auto;height:250px" id="edit-content" name="content">${hotel.content!""}</textarea>
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Precautions</span>
                    <textarea style="height:250px" class="form-control" id="edit-remark" name="remark">${hotel.remark!""}</textarea>
                  </div>

                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    State：
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="0" <#if hotel.status==0>checked</#if>>
                    <span>On the shelves</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1" <#if hotel.status==1>checked</#if>>
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
  KindEditors("edit-content");
	//提交按钮监听事件
	$("#edit-form-submit-btn").click(function(){
		if(!checkForm("hotel-edit-form")){
			return;
		}
		var data = $("#hotel-edit-form").serialize();
		$.ajax({
			url:'edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Hotel edited successfully!',function(){
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
