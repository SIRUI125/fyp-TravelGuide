<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise."/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Tourist Attractions Platform| ${title!""}</title>

    <#include "../common/header_css.ftl"/>
    <link rel="stylesheet" href="/home/styles/dashboard.css"/>
</head>

<body class="rlr-body">

<#include "../common/header.ftl"/>
<!-- Main Content -->
<main id="rlr-main" class="rlr-main--fixed-top">
    <div class="rlr-section__content--md-top">
        <div class="rlr-section rlr-section__my">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 rlr-sidebar-menu__mobile">
                        <#-- <select class="rlr-sidebar-menu__sub-menu rlr-form-select" id="rlr-js-sub-menu" name="rlr-sub-menu">
                           <option>Navigate to...</option>
                           <option value="my-account-pages--dashboard">Dashboard</option>
                           <option value="my-account-pages--order">Orders</option>
                           <option value="my-account-pages--subscription">Subscriptions</option>
                           <option value="my-account-pages--dashboard">Addresses</option>
                           <option value="my-account-pages--dashboard">Payment Methods</option>
                           <option value="my-account-pages--dashboard">Account Details</option>
                           <option value="my-account-pages--dashboard">Logout</option>
                         </select>-->
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <aside class="rlr-sidebar-menu col-lg-3 col-xs-12 mb-5">
                        <div class="rlr-sidebar-menu__wrapper">
                            <#include "left_menu.ftl"/>
                        </div>
                    </aside>
                    <div class="content col-lg-9 col-xs-12">

                        <div class="dashboard-content">
                            <div class="dashboard-form mb-4">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-xs-12 padding-right-30">
                                        <div class="dashboard-list"><h4 class="gray">Profile Details</h4>
                                            <div class="dashboard-list-static">
                                                <form id="edit-profile-form">

                                                <div class="edit-profile-photo" style="cursor: pointer" >
                                                    <img src="/home/assets/images/subscribe-02.png"
                                                                                     alt="" id="prew-headpic">
                                                        <input class="form-control" style="display: none;"
                                                               type="file" id="select-file" onchange="upload('prew-headpic','edit-headPic')">
                                                </div>
                                                <div class="my-profile">
                                                    <input type="hidden" name="headPic" id="edit-headPic"/>
                                                    <div class="form-group"><label>Your Name *</label>
                                                        <input class="form-control required" tips="Please fill in Name"
                                                                value="${account.username!""}" placeholder="Please fill in Name" name="username" type="text">
                                                    </div>
                                                    <div class="form-group"><label>Phone Number *</label>
                                                        <input class="form-control"
                                                                value="${account.mobile!""}" name="mobile" readonly type="text">
                                                    </div>
                                                    <div class="form-group"><label>Email Address *</label>
                                                        <input class="form-control"
                                                                value="${account.email!""}" readonly name="email" type="text">
                                                    </div>

                                                    <div class="form-group"><label>Your GENDER *</label>
                                                        <select class="form-control" name="sex">
                                                            <option value="0" <#if account.sex==0>selected</#if>>男</option>
                                                            <option value="1"  <#if account.sex==1>selected</#if>>女</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group"><label>Your AGE *</label>
                                                        <input class="form-control required" tips="Please fill in Age"
                                                               value="${account.age!"0"}" placeholder="Please fill in Age" name="age" type="text">
                                                    </div>
                                                    <div class="form-group"><label>Your CARDNO. *</label>
                                                        <input class="form-control required" tips="Please fill in CardNo"
                                                               value="${account.cardNo!""}" name="cardNo" placeholder="Please fill in CardNo" type="text">
                                                    </div>
                                                    <div class="form-group"><label>Your BALANCE *</label>
                                                        <input class="form-control"
                                                               value="${account.balance!"0"}" readonly type="text">
                                                    </div>
                                                </form>
                                                </div>
                                                <div class="form-btn">
                                                   <button type="button" class="btn btn-primary" id="edit-user-btn">修改</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="dashboard-form mb-4">
                                <div class="dashboard-password"><h4>Change Password</h4>
                                    <form id="edit-password-form">
                                        <div class="row">
                                            <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-group"><label>Current Password</label>
                                                    <input class="form-control required" tips="Please fill in Old Password"
                                                            type="password" name="oldPassword" placeholder="*********"></div>
                                            </div>
                                            <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-group"><label>New Password</label>
                                                    <input  class="form-control required" tips="Please fill in New Password"
                                                            type="password" name="newPassword" id="new-password"></div>
                                            </div>
                                            <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
                                                <div class="form-group"><label>Re-enter Password</label>
                                                    <input class="form-control required" tips="Please fill in Re-enter Password"
                                                            type="password" id="rep-password">
                                                </div>
                                            </div>
                                            <div class="col-lg-12" style="margin-top: 15px;">
                                                <div class="form-btn mar-top-15">
                                                   <button type="button" id="edit-password-btn" class="btn btn-primary">
                                                       change password
                                                   </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                        <div class="dashboard-form mb-4">
                            <div class="dashboard-password"><h4>Recharge Balance</h4>
                                <form id="recharge-form">
                                    <div class="row">
                                        <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
                                            <div class="form-group"><label>Recharge amount</label>
                                                <input class="form-control required"
                                                       tips="Please fill in the recharge amount"
                                                       value="100"
                                                       type="number" name="price"
                                                       placeholder="Please fill in the recharge amount"></div>
                                        </div>
                                        <div class="col-lg-12" style="margin-top: 15px;">
                                            <div class="form-btn mar-top-15">
                                                <button type="button" id="recharge-balance-btn"
                                                        class="btn btn-primary">
                                                    recharge
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<#include "../common/footer.ftl"/>

<script type="text/javascript">
    $("#prew-headpic").click(function () {
        $("#select-file").click();
    });

    //修改个人信息
    $("#edit-user-btn").click(function () {
        if(!checkForm('edit-profile-form')){
            return;
        }
        var data=$("#edit-profile-form").serialize();
        ajaxRequest('edit_profile','POST',data,function (result) {
            showSuccessMsg("Personal information modified successfully",function () {
                window.location.reload();
            })
        })
    });

    //修改个人密码
    $("#edit-password-btn").click(function () {
        if(!checkForm('edit-password-form')){
            return;
        }
        var newPassword=$("#new-password").val();
        var repPassword=$("#rep-password").val();
        if(newPassword!=repPassword){
            showWarningMsg("Two passwords are inconsistent");
            return;
        }
        if(newPassword.length<8){
            showWarningMsg("Password must be greater than 8 digits");
            return;
        }
        var data=$("#edit-password-form").serialize();
        ajaxRequest('edit_password','POST',data,function () {
            showSuccessMsg("Password reset complete",function () {
                window.location.reload();
            })
        })
    });

    //充值操作
    $("#recharge-balance-btn").click(function () {
        if(!checkForm('recharge-form')){
            return;
        }
        var data=$("#recharge-form").serialize();
        ajaxRequest('/home/user_center/recharge_add','POST',data,function (result) {
            showSuccessMsg("Recharge successful",function () {
                window.location.href="/home/user_center/recharge_list";
            })
        })
    })
</script>
</body>
</html>
