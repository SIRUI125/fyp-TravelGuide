<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>${siteName!""}|Attraction Order Management-${title!""}</title>
    <#include "../common/header.ftl"/>
    <style>
        td {
            vertical-align: middle;
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
                <a href="/system/index"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}"
                                             alt="${siteName!""}"/></a>
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
                                <form class="pull-right search-bar" method="get" action="list" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                User mobile phone number <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)"
                                                       data-field="title">User mobile phone number</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" value="${mobile!""}" name="account.mobile"
                                               placeholder="Please enter phone number">
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
                                            <th>cover picture</th>
                                            <th>title</th>
                                            <th>date</th>
                                            <th>number</th>
                                            <th>total price</th>
                                            <th>user name</th>
                                            <th>phone number</th>
                                            <th>state</th>
                                            <th>creation time</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as order>
                                                <tr>
                                                    <td style="vertical-align:middle;">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]"
                                                                   value="${order.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td style="vertical-align:middle;width: 300px;">
                                                        <#if order.scenicPrice.scenic.coverPic?? && order.scenicPrice.scenic.coverPic?length gt 0>
                                                                <img src="/photo/view?filename=${order.scenicPrice.scenic.coverPic}"
                                                                     width="300px" height="150px">
                                                            <#else>
                                                                <img src="/admin/images/default-head.jpg" width="200px"
                                                                     height="150px;">
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align:middle;">${order.scenicPrice.scenic.title!""}</td>

                                                    <td style="vertical-align:middle;">
                                                        ${order.startDate?string("yyyy-MM-dd")!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${order.count!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${order.price!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${order.account.username!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${order.account.mobile!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        <#if order.status==0>
                                                            Unused
                                                        <#elseif order.status==2>
                                                            Using
                                                        <#elseif order.status==3>
                                                            Cancelled
                                                        <#elseif order.status==4>
                                                            Commented
                                                        <#else>
                                                            Used
                                                        </#if>
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        <font class="text-success">${order.createTime}</font>
                                                    </td>
                                                </tr>
                                            </#list>
                                        <#else>
                                            <tr align="center">
                                                <td colspan="13">Nothing here！</td>
                                            </tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>
                                <#if pageBean.total gt 0>
                                    <ul class="pagination ">
                                        <#if pageBean.currentPage == 1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="list?account.mobile=${mobile!""}&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage == showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li>
                                                    <a href="list?account.mobile=${mobile!""}&currentPage=${showPage}">${showPage}</a>
                                                </li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage == pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="list?account.mobile=${mobile!""}&currentPage=${pageBean.totalPage}">»</a>
                                            </li>
                                        </#if>
                                        <li><span>共${pageBean.totalPage}页,${pageBean.total}条数据</span></li>
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
    $(document).ready(function () {

    });

    function del(url) {
        if ($("input[type='checkbox']:checked").length != 1) {
            showWarningMsg('Please select a piece of data to delete！');
            return;
        }
        var id = $("input[type='checkbox']:checked").val();
        $.confirm({
            title: 'Confirm delete？',
            content: 'Data cannot be recovered after deletion, please be careful！',
            buttons: {
                confirm: {
                    text: 'confirmation',
                    action: function () {
                        deleteReq(id, url);
                    }
                },
                cancel: {
                    text: 'close',
                    action: function () {

                    }
                }
            }
        });
    }

    //打开编辑页面
    function edit(url) {
        if ($("input[type='checkbox']:checked").length != 1) {
            showWarningMsg('Please select a piece of data to edit！');
            return;
        }
        window.location.href = url + '?id=' + $("input[type='checkbox']:checked").val();
    }

    //调用删除方法
    function deleteReq(id, url) {
        $.ajax({
            url: url,
            type: 'POST',
            data: {id: id},
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    showSuccessMsg('Attraction order deleted successfully!', function () {
                        $("input[type='checkbox']:checked").parents("tr").remove();
                    })
                } else {
                    showErrorMsg(data.msg);
                }
            },
            error: function (data) {
                alert('Network Error!');
            }
        });
    }


</script>
</body>
</html>
