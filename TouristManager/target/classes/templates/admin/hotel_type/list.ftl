<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>${siteName!""}|Housekeeping-${title!""}</title>
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
                                <form class="pull-right search-bar" method="get" action="hotel_type_list" role="form">
                                    <input type="hidden" name="hotel.id" value="${hotelId}"/>
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                name <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)" data-field="title">title</a>
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
                                            <th>Name</th>
                                            <th>Number of single beds</th>
                                            <th>Number of guests</th>
                                            <th>The number of rooms</th>
                                            <th>Number of rooms remaining</th>
                                            <th>Introduction</th>
                                            <th>Price</th>
                                            <th>Area</th>
                                            <th>Landscape</th>
                                            <th>State</th>
                                            <th>Added time</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as hotelType>
                                                <tr>
                                                    <td style="vertical-align:middle;">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]"
                                                                   value="${hotelType.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td style="vertical-align:middle;">${hotelType.name!""}</td>
                                                    <td style="vertical-align:middle;">
                                                      ${hotelType.number!"0"}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.guestNumber!"0"}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.roomNumber!"0"}
                                                    </td>
                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.defaultNumber!"0"}
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.introduce!""}
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.price!""}
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.area!""}
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        ${hotelType.landscape!""}
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        <#if hotelType.status == 0>
                                                            <font class="text-success">On the shelves</font>
                                                        <#else>
                                                            <font class="text-warning">Removed from shelves</font>
                                                        </#if>
                                                    </td>

                                                    <td style="vertical-align:middle;">
                                                        <font class="text-success">${hotelType.createTime}</font>
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
                                            <li><a href="hotel_type_list?name=${name!""}&hotel.id=${hotelId}&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage == showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li>
                                                    <a href="hotel_type_list?name=${name!""}&hotel.id=${hotelId}&currentPage=${showPage}">${showPage}</a>
                                                </li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage == pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="hotel_type_list?name=${name!""}&hotel.id=${hotelId}&currentPage=${pageBean.totalPage}">»</a>
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
                    text: 'Confiramation',
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


    function  add(url){
        window.location.href="hotel_type_add?hotelId=${hotelId}";
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
                    showSuccessMsg('Room deleted successfully!', function () {
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
