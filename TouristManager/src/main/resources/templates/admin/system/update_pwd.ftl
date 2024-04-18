<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}Backend management system home page</title>
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
              <div class="card-body">
                
                <form method="post" action="update_pwd" class="site-form" id="edit-pwd-form">
                  <div class="form-group">
                    <label for="old-password">Old Password</label>
                    <input type="password" class="form-control required" name="oldpwd" id="old-password" placeholder="Enter the original login password of the account" tips="Please fill in the old password">
                  </div>
                  <div class="form-group">
                    <label for="new-password">New Password</label>
                    <input type="password" class="form-control required" name="newpwd" id="new-password" placeholder="Enter new password" tips="Please fill in new password">
                  </div>
                  <div class="form-group">
                    <label for="confirm-password">Confirm the new password</label>
                    <input type="password" class="form-control required" name="confirmpwd" id="confirm-password" placeholder="Please confirm new password" tips="Please fill in the new password again">
                  </div>
                  <button type="button" class="btn btn-primary" id="submit-btn">Edit password</button>
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
	//监听上传图片按钮
	$("#submit-btn").click(function(){
		if(!checkForm("edit-pwd-form")){
			return;
		}
		var oldPwd = $("#old-password").val();
		var newPwd = $("#new-password").val();
		var reNewPwd = $("#confirm-password").val();
		if(newPwd != reNewPwd){
			showErrorMsg('The two password inputs are inconsistent！');
			return;
		}
		//向后台发送请求
		var data = {oldPwd:oldPwd,newPwd:newPwd};
		ajaxRequest('/system/update_pwd','post',data,function(rst){
			showSuccessMsg('Password reset complete！',function(){});
		});
	});
});

</script>
</body>
</html>