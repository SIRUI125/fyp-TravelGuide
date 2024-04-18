<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Attraction Ticket Management-Add attraction tickets</title>
<#include "../common/header.ftl"/>
  <link href="/admin/css/style.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/home/layui/css/layui.css"  media="all">

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
              <div class="card-header"><h4>Add attraction tickets</h4></div>
              <div class="card-body">
                <form id="scenic-price-add-form"  class="row">
                  <input type="hidden" name="scenic.id" value="${scenic.id}">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">title</span>
                    <input type="text" class="form-control required" value="${scenic.title}" readonly="readonly"/>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">price</span>
                    <input type="number" onkeyup="number(this)" class="form-control required" id="price" name="price"
                           min="0" oninput="if(value>1000000)value=1000000;if (value<0)value=0"
                           value="${scenic.price}" placeholder="Please enter price" tips="Please fill in the price" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Single day ticket release</span>
                    <input type="number" maxlength="3"
                           min="0" oninput="if(value>10000)value=10000;if (value<1)value=1"
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           class="form-control required" id="ticket" name="ticket" value="" placeholder="Please enter the number of tickets" tips="Please enter the number of tickets" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Single purchase limit</span>
                    <input type="number"
                           min="0" oninput="if(value>10)value=10;if (value<1)value=1"
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           class="form-control required" id="limitation" name="limitation" value="" placeholder="Please enter the purchase limit per person" tips="Please enter the purchase limit per person" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Ticket sale date</span>
                    <#--<label class="layui-form-label">choose date</label>-->
                    <input type="text" class="layui-input" id="date-range" autocomplete="off" placeholder=" - " value="">
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


<script src="/home/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">

  function number(obj){
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个, 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
  }

  var startDate = '';
  var endDate = '';

  var minTime = '${minTime}';

  layui.use('laydate', function() {

    var laydate = layui.laydate;

    laydate.render({
      elem: '#date-range'
      ,range: true
      ,min: minTime
      ,max: 30
      ,btns: ['confirm']
      ,trigger: 'click'
      ,done:function (value, date1, date2) {
        startDate = date1.year + "-" + date1.month + "-" + date1.date;
        endDate = date2.year + "-" +date2.month + "-" + date2.date;
      }
    });
    return startDate,endDate
  })


$(document).ready(function(){
	//提交按钮监听事件
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("scenic-price-add-form")){
			return;
		}
		var data = $("#scenic-price-add-form").serialize();
		if(startDate==null || startDate==""){
		  showWarningMsg("Please fill in the start date");
		  return;
        }
      if(endDate==null || endDate==""){
        showWarningMsg("Please fill in the end date");
        return;
      }
		$.ajax({
			url:'add_price',
			type:'POST',
			data:data+"&startDate="+startDate+"&endDate="+endDate,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Added successfully!',function(){
                  window.location.href="list_price?scenic.id=${scenic.id}";
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
