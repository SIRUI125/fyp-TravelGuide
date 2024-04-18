<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Housekeeping-Add room</title>
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
              <div class="card-header"><h4>Add room</h4></div>
              <div class="card-body">
                <form id="hotel-type-add-form" method="post" class="row">
                  <input type="hidden" name="hotel.id" value="${hotelId}"/>
                  <div class="form-group col-md-12">
                    <label>Room picture upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic" id="show-uploaded-pic">
                        <input type="hidden" name="images" id="picture">
                        <input type="file" id="select-picture-file" style="display:none;" onchange="uploadPicture()">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-images-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">name</span>
                    <input type="text" class="form-control required" id="add-name" name="name" value=""
                           placeholder="Please fill in the name" tips="Please fill in the name" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Number of single beds</span>
                    <input type="number" class="form-control required" id="add-number" name="number" value=""
                           placeholder="Please fill in the number of single beds" tips="Please fill in the number of single beds" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Number of guests</span>
                    <input type="number" class="form-control required" id="add-guestNumber" name="guestNumber" value=""
                           placeholder="Please fill in the number of guests" tips="Please fill in the number of guests" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">The number of rooms</span>
                    <input type="number" class="form-control required" id="add-roomNumber" name="roomNumber"
                           value="0"
                           placeholder="Please fill in the number of rooms" tips="Please fill in the number of rooms" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">area</span>
                    <input type="number" class="form-control required" id="add-area" name="area" value=""
                           placeholder="Please fill in the area" tips="Please fill in the area" />
                  </div>



                  <div class="input-group m-b-10">
                    <span class="input-group-addon">landscape</span>
                    <input type="text" class="form-control required" id="add-landscape" name="landscape" value=""
                           placeholder="Please fill in the landscape" tips="Please fill in the landscape" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">price</span>
                    <input type="number" class="form-control required" id="add-price" name="price" value=""
                           placeholder="Please fill in the price" tips="Please fill in the price"" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Room introduction</span>
                    <input type="text" class="form-control required" id="add-introduce" name="introduce" value="28m²|City view|Air conditioning|Flat screen TV"
                           placeholder="For multiple introductions | separate" tips="Please fill in the introduction" />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Room facilities</span>
                    <textarea style="height:250px" id="add-details"
                              placeholder="For facilities | separated"
                              class="form-control required" tips="Please fill in room facilities"
                              name="details">Desk | Safe | Upper floors directly accessible by elevator | Flat-screen TV | Wake-up service | Alarm clock | Sofa | Alarm clock | Towels | Air conditioning | Clothes racks</textarea>
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Detailed introduction</span>
                    <textarea style="height:250px" class="form-control required" tips="Please fill in the detailed introduction"
                              id="add-remark" name="remark"></textarea>
                  </div>



                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    state：
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="0" checked="">
                    <span>On the shelves</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1">
                    <span>Removed from shelves</span>
                  </label>
                  </div>
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">Sure</button>
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
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("hotel-type-add-form")){
			return;
		}
		var data = $("#hotel-type-add-form").serialize();
		$.ajax({
			url:'hotel_type_add',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Room added successfully!',function(){
						window.location.href = 'hotel_type_list?hotel.id=${hotelId}';
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
	$("#add-pic-btn").click(function(){
		$("#select-file").click();
	});

	$("#add-images-btn").click(function () {
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
