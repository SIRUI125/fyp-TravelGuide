<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Blog Management-Add Blog</title>
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
              <div class="card-header"><h4>Add Blog</h4></div>
              <div class="card-body">
                <form id="blog-add-form" method="post" class="row">
                  <div class="form-group col-md-12">
                    <label>cover picture</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <img src="/admin/images/default-head.jpg" id="show-picture-img" alt="cover picture">
                          </figure>
                        </li>
                        <input type="hidden" name="coverPic" id="headPic">
                        <input type="file" id="select-file" style="display:none;" onchange="upload('show-picture-img','headPic')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-pic-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Title</span>
                    <input type="text" class="form-control required" id="add-title" name="title" value=""
                           placeholder="Please fill in the title" tips="Please fill in the title" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Author</span>
                    <input type="text" class="form-control required" id="add-author" name="author" value=""
                           placeholder="Please fill in the author" tips="Please fill in the author" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Type</span>
                    <input type="text" class="form-control required"
                           id="type" name="type"
                           value="" placeholder="Please enter the type" tips="Please enter the type" />
                  </div>

                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Label</span>
                    <input type="text" class="form-control required" id="add-tags" name="tags" value=""
                           placeholder="Please fill in the label" tips="Please fill in the label" />
                  </div>


                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Content</span>
                    <textarea style="width:auto;height:250px" id="add-content" name="content"></textarea>
                  </div>
                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    State：
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="0" checked="">
                    <span>show</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1">
                    <span>Do not show</span>
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
<script type="text/javascript" src="/admin/js/citydata.js"></script>
<script type="text/javascript" src="/admin/js/cityPicker-1.0.0.js"></script>
<script type="text/javascript" charset="utf-8" src="/admin/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/admin/kindeditor/kindeditor-all.js"></script>


<script type="text/javascript">
$(document).ready(function(){
  KindEditors("add-content")
	//提交按钮监听事件
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("blog-add-form")){
			return;
		}
		var data = $("#blog-add-form").serialize();
		$.ajax({
			url:'add',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Blog added successfully!',function(){
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
	$("#add-pic-btn").click(function(){
		$("#select-file").click();
	});


});

</script>
</body>
</html>
