<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>${siteName!""}|Hotel Management-${title!""}</title>
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
                                                name <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)" data-field="title">Title</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" value="${name!""}" name="name"
                                               placeholder="Please enter name">
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
                                            <th>name</th>
                                            <th>address</th>
                                            <th>contact information</th>
                                            <th>state</th>
                                            <th>added time</th>
                                            <th>operation</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as hotel>
                                                <tr>
                                                    <td style="vertical-align:middle;">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]"
                                                                   value="${hotel.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        <#if hotel.coverPic?? && hotel.coverPic?length gt 0>
                                                                <img src="/photo/view?filename=${hotel.coverPic}"
                                                                     width="200px" height="150px">
                                                            <#else>
                                                                <img src="/admin/images/default-head.jpg" width="200px"
                                                                     height="150px;">
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align:middle;">${hotel.name!""}</td>

                                                    <td style="vertical-align:middle;">
                                                        ${hotel.address!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${hotel.phone!""}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        <#if hotel.status == 0>
                                                            <font class="text-success">On the shelves</font>
                                                        <#else>
                                                            <font class="text-warning">Removed from shelves</font>
                                                        </#if>
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        <font class="text-success">${hotel.createTime}</font>
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        <a class="btn btn-xs btn-default hotel-type-list" data-id="${hotel.id!""}" href="javascript:void(0);" title=""
                                                           data-toggle="tooltip" data-original-title="Room List">
                                                            <i class="mdi mdi-home"></i>
                                                        </a>


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
                                            <li><a href="list?name=${name!""}&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage == showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li>
                                                    <a href="list?name=${name!""}&currentPage=${showPage}">${showPage}</a>
                                                </li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage == pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="list?name=${name!""}&currentPage=${pageBean.totalPage}">»</a>
                                            </li>
                                        </#if>
                                        <li><span>total${pageBean.totalPage}page,${pageBean.total}data</span></li>
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
            title: 'Confirm Delete？',
            content: 'Data cannot be recovered after deletion, please be careful！',
            buttons: {
                confirm: {
                    text: 'Confirmation',
                    action: function () {
                        deleteReq(id, url);
                    }
                },
                cancel: {
                    text: 'Close',
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
                    showSuccessMsg('Hotel deleted successfully!', function () {
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

    //酒店客房列表
    $(".hotel-type-list").click(function () {
        var id=$(this).attr("data-id");
        window.location.href="hotel_type_list?hotel.id="+id
    })

</script>
</body>
</html>
