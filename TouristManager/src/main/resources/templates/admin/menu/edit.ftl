<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Menu Management-Edit Menu</title>
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
              <div class="card-header"><h4>Edit Menu</h4></div>
              <div class="card-body">
                <form action="add" id="menu-add-form" method="post" class="row">
                  <input type="hidden" name="id" value="${menu.id}">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Sub-headings</span>
                    <select name="parent.id" class="form-control" id="type">
                        <option value="">Please select the upper level category</option>
                        <#if topMenus??>
                        	<#list topMenus as topMenu>
                        		<option value="${topMenu.id}" style="font-weight:bold;" <#if menu.parent??><#if topMenu.id == menu.parent.id>selected</#if></#if>>${topMenu.name}</option>
                        		<#if secondMenus??>
                        			<#list secondMenus as secondMenu>
                        				<#if secondMenu.parent.id == topMenu.id>
                        				<option value="${secondMenu.id}" <#if menu.parent??><#if secondMenu.id == menu.parent.id>selected</#if></#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${secondMenu.name}</option>
                        				</#if>
                        			</#list>
                        		</#if>
                        	</#list>
                        </#if>
                      </select>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu Name</span>
                    <input type="text" class="form-control required" id="name" name="name" value="${menu.name}" placeholder="Please enter a menu name" tips="Please fill in the menu name" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu URL</span>
                    <input type="text" class="form-control" id="url" name="url" value="${menu.url}" placeholder="Please fill in the menu url" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu icon</span>
                    <input type="text" readonly="readonly" class="form-control required" id="icon" name="icon" value="${menu.icon}" placeholder="Please enter menu icon" tips="Please select a menu icon" />
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" id="show-icons-btn" data-toggle="modal" data-target="#icons-panel" type="button">Click to select</button>
                    </span>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">sort</span>
                    <input type="number" class="form-control" id="sort" name="sort" value="${menu.sort}" />
                  </div>
                  <div class="input-group m-b-10">
                    Whether button：
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="true" <#if menu.button == true>checked</#if> >
                    <span>Yes</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="false" <#if menu.button == false>checked</#if>>
                    <span>No</span>
                  </label>
                  </div>
                  <div class="input-group m-b-10">
                    Whether to display：
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="true" <#if menu.show == true>checked</#if> >
                    <span>Yes</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="false" <#if menu.show == false>checked</#if>>
                    <span>No</span>
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
<#include "../common/icons.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//图标icon选择后的确认按钮事件
	$("#confirm-icon-btn").click(function(){
		getSelectedIcon();
	});
	//提交按钮监听事件
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("menu-add-form")){
			return;
		}
		var data = $("#menu-add-form").serialize();
		$.ajax({
			url:'edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Menu edited successfully!',function(){
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
});
function getSelectedIcon(){
	$("#icon").val($(".selected-icon").attr('val'));
	$("#icons-panel").modal('hide');
}
</script>
</body>
</html>