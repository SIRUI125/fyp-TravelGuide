<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Operation log management-${title!""}</title>
<#include "../common/header.ftl"/>
<style>
td{
	vertical-align:middle;
}
</style>
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
              <div class="card-toolbar clearfix">
                <form class="pull-right search-bar" method="get" action="operator_log_list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      Operator <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">Operator</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${operator!""}" name="operator" placeholder="Please enter name">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">search</button>
                    </span>
                  </div>
                </form>
                <#include "../common/third-menu.ftl"/>
              </div>
              <div class="card-body">
                
                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" id="check-all"><span></span>
                          </label>
                        </th>
                        <th>Operator</th>
                        <th style="width:400px;">Operation Content</th>
                        <th>Operation Time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as operaterLog>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${operaterLog.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">${operaterLog.operator}</td>
                        <td align="center">${operaterLog.content}</td>
                        <td style="vertical-align:middle;"><font class="text-success">${operaterLog.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="4">Nothing here！</td></tr>
					</#if>                      
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="operator_log_list?operator=${operator!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="operator_log_list?operator=${operator!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="operator_log_list?operator=${operator!""}&currentPage=${pageBean.totalPage}">»</a></li>
                  </#if>
                  <li><span>共${pageBean.totalPage}page,${pageBean.total}Article data</span></li>
                </ul>
                </#if>
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
	
});
function del(url){
	if($("input[type='checkbox']:checked").length < 1){
		showWarningMsg('Please select at least one piece of data to delete！');
		return;
	}
	var ids = '';
	$("input[type='checkbox']:checked").each(function(i,e){
		if($(e).attr('id') != 'check-all'){
			ids += $(e).val() + ',';
		}
	});
	if(ids != ''){
		ids = ids.substring(0,ids.length-1);
	}
	$.confirm({
        title: 'Confirm delete？',
        content: 'Data cannot be recovered after deletion, please be careful！',
        buttons: {
            confirm: {
                text: 'Confirmation',
                action: function(){
                    deleteReq(ids,url);
                }
            },
            cancel: {
                text: 'Close',
                action: function(){
                    
                }
            }
        }
    });
}
//打开编辑页面
function delAll(url){
	$.confirm({
        title: 'Make sure to clear the entire log table？',
        content: 'Data cannot be recovered after clearing, please be careful！',
        buttons: {
            confirm: {
                text: 'Confirmation',
                action: function(){
                    ajaxRequest(url,'post',{},function(rst){
							window.location.reload();
					});
                }
            },
            cancel: {
                text: 'Close',
                action: function(){
                    
                }
            }
        }
    });
	
}
//调用删除方法
function deleteReq(ids,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{ids:ids},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('Operation log deleted successfully!',function(){
					window.location.reload();
					//$("input[type='checkbox']:checked").parents("tr").remove();
				})
			}else{
				showErrorMsg(data.msg);
			}
		},
		error:function(data){
			alert('Network Error!');
		}
	});
}
</script>
</body>
</html>