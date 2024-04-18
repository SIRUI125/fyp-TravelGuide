<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Housekeeping-Edit room</title>
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
              <div class="card-header"><h4>Edit room</h4></div>
              <div class="card-body">
                <form id="hotel-type-edit-form" method="post" class="row">
                  <input type="hidden" name="id" value="${hotelType.id}"/>
                  <div class="form-group col-md-12">
                    <label>Room picture upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic" id="show-uploaded-pic">
                        <#if hotelType.images?? && hotelType.images?length gt 0>
                          <#list hotelType.images?split(";") as photo>
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
                        <input type="hidden" name="images" value="${hotelType.images!""}" id="picture">
                        <input type="file" id="select-picture-file" style="display:none;" onchange="uploadPicture()">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="edit-images-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">name</span>
                    <input type="text" class="form-control required" id="edit-name" name="name" value="${hotelType.name!""}"
                           placeholder="Please fill in the name" tips="Please fill in the name" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Number of single beds</span>
                    <input type="number" class="form-control required" id="edit-number" name="number" value="${hotelType.number!"0"}"
                           placeholder="Please fill in the number of single beds" tips="Please fill in the number of single beds" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Number of guests</span>
                    <input type="number" class="form-control required" id="edit-guestNumber" name="guestNumber" value="${hotelType.guestNumber!"0"}"
                           placeholder="Please fill in the number of guests" tips="Please fill in the number of guests" />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">The number of rooms</span>
                    <input type="number" class="form-control required" id="edit-roomNumber" name="roomNumber"
                           value="${hotelType.roomNumber!"0"}" readonly
                           placeholder="Please fill in the number of rooms" tips="Please fill in the number of rooms" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">area</span>
                    <input type="number" class="form-control required" id="edit-area" name="area" value="${hotelType.area!""}"
                           placeholder="Please fill in the area" tips="Please fill in the area" />
                  </div>



                  <div class="input-group m-b-10">
                    <span class="input-group-addon">landscape</span>
                    <input type="text" class="form-control required" id="edit-landscape" name="landscape" value="${hotelType.landscape!""}"
                           placeholder="Please fill in the landscape" tips="Please fill in the landscape" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">price</span>
                    <input type="number" class="form-control required" id="edit-price" name="price" value="${hotelType.price!""}"
                           placeholder="Please fill in the price" tips="Please fill in the price" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Room introduction</span>
                    <input type="text" class="form-control required" id="edit-introduce" name="introduce" value="${hotelType.introduce!""}"
                           placeholder="For multiple introductions | separate" tips="Please fill in the introduction" />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Room facilities</span>
                    <textarea style="height:250px" id="edit-details"
                              placeholder="For facilities | separated"
                              class="form-control required" tips="Please fill in room facilities"
                              name="details">${hotelType.details!""}</textarea>
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Detailed introduction</span>
                    <textarea style="height:250px" class="form-control required" tips="Please fill in the detailed introduction"
                              id="edit-remark" name="remark">${hotelType.remark!""}</textarea>
                  </div>



                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    state：
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="0" <#if hotelType.status==0>checked</#if>>
                    <span>On the shelves</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1" <#if hotelType.status==1>checked</#if>>
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


<script type="text/javascript">
$(document).ready(function(){
	//提交按钮监听事件
	$("#edit-form-submit-btn").click(function(){
		if(!checkForm("hotel-type-edit-form")){
			return;
		}
		var data = $("#hotel-type-edit-form").serialize();
		$.ajax({
			url:'hotel_type_edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Room edited successfully!',function(){
						window.location.href = 'hotel_type_list?hotel.id=${hotelType.hotel.id}';
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
