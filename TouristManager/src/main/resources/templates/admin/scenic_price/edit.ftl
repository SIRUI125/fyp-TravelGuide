<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Attraction Ticket Management-Edit attraction tickets</title>
<#include "../common/header.ftl"/>

  <link href="/admin/css/style.min.css" rel="stylesheet">

</head>

<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">

      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="/system/index"><img src="/admin/images/logo-sidebar.png" title="${sitename!""}" alt="${sitename!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll">
        <#include "../common/left-menu.ftl"/>
      </div>

    </aside>
    <!--end 左侧导航-->

    <#include "../common/header-menu.ftl"/>

     <!--页面主要内容-->
    <main class="lyear-layout-content">

      <div class="container-fluid">

        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>Edit attraction tickets</h4></div>
              <div class="card-body">
                <form id="scenic-price-edit-form"  class="row">
                  <input type="hidden" name="id" value="${scenicPrice.id}">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Attractions</span>
                    <input type="text" class="form-control required" value="${scenicPrice.scenic.title}"
                           readonly="readonly"/>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">price</span>
                    <input type="number" onkeyup="number(this)" class="form-control required" id="price" name="price"
                           min="0" oninput="if(value>10000)value=10000;if (value<0)value=0"

                           value="${scenicPrice.price}" placeholder="Please enter price" tips="Please enter price" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Single purchase limit</span>
                    <input type="number"
                           min="0" oninput="if(value>10)value=10;if (value<1)value=1"
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           class="form-control required" id="limitation" name="limitation" value="${scenicPrice.limitation}" placeholder="Please enter the purchase limit per person" tips="Please enter the purchase limit per person" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Ticket sale date</span>
                    <input type="text" class="form-control required" value="${scenicPrice.productDate?string('yyyy-MM-dd')}" readonly="readonly"/>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">increase tickets</span>
                    <input type="number" maxlength="3"
                           oninput="if(value>100)value=100;if (value<0)value=0"
                           class="form-control required" id="ticket" name="ticket" value="0" placeholder="Please enter the number of tickets to increase" tips="Please enter the number of tickets to increase" />
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
    <!--end 页面主要内容-->
  </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">

  function number(obj){
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个, 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
  }


$(document).ready(function(){
	//提交按钮监听事件
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("scenic-price-edit-form")){
			return;
		}
		var data = $("#scenic-price-edit-form").serialize();
		$.ajax({
			url:'edit_price',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Edited successfully!',function(){
						window.location.href = 'list_price?scenic.id=${scenicPrice.scenic.id}';
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
});

</script>
</body>
</html>
