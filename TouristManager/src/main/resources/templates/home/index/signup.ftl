<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise."/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Tourist Attractions Platform | ${title!""}</title>

    <#include "../common/header_css.ftl"/>
</head>

<body class="rlr-body">
<#include "../common/header.ftl"/>
<!-- Main Content -->
<main id="rlr-main" class="rlr-main--fixed-top">
    <div class="rlr-section__content--lg-top">
        <section class="rlr-section rlr-section__mt rlr-account">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 offset-lg-4">
                        <div class="rlr-authforms">
                            <div class="rlr-authforms__header">
                                <img class="mb-2" src="/home/assets/images/logos/Logomark.svg" alt="Logo"/>
                                <h2>Create an account</h2>
                                <p>Helps to access all features of the site.</p>
                            </div>
                            <div class="rlr-authforms__form">
                                <form id="sign-up-form">
                                <div class="rlr-authforms__inputgroup">
                                    <label class="rlr-form-label rlr-form-label--light"> Name </label>
                                    <input type="text" autocomplete="off" name="username" placeholder="Please fill in your name "
                                           class="form-control form-control--light required" tips="Please fill in your name "/>
                                </div>
                                <div class="rlr-authforms__inputgroup">
                                    <label class="rlr-form-label rlr-form-label--light"> PHONE </label>
                                    <input type="tel" autocomplete="off" name="mobile" placeholder=" Please fill in your Phone "
                                           class="form-control form-control--light required"
                                           tips="Please fill in your phone " id="reg-phone"/>
                                </div>
                                <div class="rlr-authforms__inputgroup"><label
                                            class="rlr-form-label rlr-form-label--light"> Email </label>
                                    <input type="email" autocomplete="off" id="reg-email" name="email"
                                           placeholder=" Please fill in your Email "
                                           class="form-control form-control--light required" tips="Please fill in your email "/>
                                </div>
                                <div class="rlr-authforms__inputgroup">
                                    <label class="rlr-form-label rlr-form-label--light"> Password </label>
                                    <input type="password" autocomplete="off" name="password"
                                           placeholder=" Please fill in your Password " id="reg-password"
                                           class="form-control form-control--light required" tips="Please fill in your password "/>
                                    <p class="help-text">Must be 8 characters or more.</p>
                                </div>
                                <div class="rlr-authforms__inputgroup">
                                    <label class="rlr-form-label rlr-form-label--light"> ConfirmPassword </label>
                                    <input type="password" autocomplete="off" id="reg-confirm-password"
                                           class="form-control form-control--light required" tips="Please fill in your ConfirmPassword " placeholder=" Please fill in your ConfirmPassword "
                                    />
                                    <p class="help-text">Must be 8 characters or more.</p>
                                </div>

                                    <div class="rlr-authforms__inputgroup">
                                        <label class="rlr-form-label rlr-form-label--light"> CPACHA </label>
                                        <input type="text" name="cpacha" id="cpacha" maxlength="4"
                                        class="form-control form-control--light required"
                                               tips="Please fill in your CPACHA "
                                               placeholder=" Please fill in your CPACHA "
                                        />
                                        <div class="col-xs-5" style="float:right;">
                                            <img src="/cpacha/generate_cpacha?vl=4&fs=25&w=128&h=40&method=home_sign" class="pull-right" id="captcha" style="cursor: pointer;" onclick="this.src=this.src+'&d='+Math.random();" title="点击刷新" alt="captcha">
                                        </div>
                                    </div>
                                    <div class="rlr-authforms__inputgroup">
                                        <label class="rlr-form-label rlr-form-label--light"> Email CPACHA </label>
                                        <div>
                                            <input type="text" name="emailCpa" id="email-cpacha" maxlength="4"
                                                   class="form-control form-control--light required"
                                                   tips="Please fill in your Email CPACHA " style="width: 300px;float: left;"
                                                   placeholder=" Please fill in yourEmail CPACHA "
                                            />

                                            <button id="verifyYz" class="btn rlr-button rlr-button--fullwidth rlr-button--primary"
                                                     style="width: 200px;" type="button">Send the verification code</button>
                                            <button class="btn rlr-button rlr-button--fullwidth rlr-button--primary"
                                                    style="width: 200px;display: none;" type="button"  id="time_box" ></button>
                                        </div>

                                    </div>
                                <button type="button" class="btn rlr-button rlr-button--fullwidth rlr-button--primary" id="sign-up-btn">
                                    Sign up
                                </button>
                                </form>
                            </div>
                            <div class="rlr-authforms__notes">
                                <p>Already have an account? <a href="/home/index/login">Log in</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>

<#include "../common/footer.ftl"/>

<script type="text/javascript">

    $(function () {
        var  LocalDelay = getLocalDelay();
        var  timeLine = parseInt(( new  Date().getTime() - LocalDelay.time) / 1000);
        $("#reg-email").val(LocalDelay.email);
        if  (timeLine > LocalDelay.delay) {
            $("#verifyYz").show();
            $("#time_box").hide();
        }else{
            timerC = LocalDelay.delay - timeLine;
            sendVerify();
        }
    });
    //注册
    $("#sign-up-btn").click(function () {
        if(!checkForm("sign-up-form")){
            return ;
        }
        var phone=$("#reg-phone").val();
        var password=$("#reg-password").val();
        var repassword=$("#reg-confirm-password").val();
        if(password.length<8){
            showWarningMsg("Password must be at least 8 characters");
            return ;
        }
        if(password!=repassword){
            showWarningMsg("Two passwords are inconsistent");
            return;
        }
        var email=$("#reg-email").val();
        var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if(!pattern.test(email)){
            showWarningMsg("E-mail format is incorrect");
            return;
        }

        var phonePattern = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
       if(!phonePattern.test(phone)){
           showWarningMsg("Phone number format is incorrect");
           return;
        }
       var data=$("#sign-up-form").serialize();
       ajaxRequest('/home/index/sign_up','POST',data,function (result) {
           showSuccessMsg("Registration Success",function () {
               window.location.href="login";
           })
       })
    })


    //验证码发送按钮
    $("#verifyYz").click(function () {
        var email=$('#reg-email').val();
        var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if(!pattern.test(email)){
            showWarningMsg("E-mail format is incorrect");
            return;
        }
        $("#time_box").text("Can be resent after 60s");
        sendVerify();
       ajaxRequest('/home/index/send_email','POST',{email:email},function (result) {

       })


    })

    var h, timerC = 5;
    function sendVerify() {
        $("#verifyYz").text("Send the verification code").hide();
        $("#time_box").text(timerC+"can be resent").show();
        if (timerC === 0) {
            clearTimeout(h);
            timerC = 5;
            $("#verifyYz").show();
            $("#time_box").hide();
            return
        }
        $("#time_box").text(timerC + " s can be resent");
        setLocalDelay(timerC);
        timerC--;
        h = setTimeout(function () {
                sendVerify();
            },
            1000)
    }


    //设置setLocalDelay
    function  setLocalDelay(delay) {
        //location.href作为页面的唯一标识，可能一个项目中会有很多页面需要获取验证码。
        sessionStorage.setItem( "delay_"  + location.href, delay);
        sessionStorage.setItem( "time_"  + location.href,  new  Date().getTime());
        sessionStorage.setItem("email_"+location.href,$("#reg-email").val());
    }


    //getLocalDelay()
    function  getLocalDelay() {
        var  LocalDelay = {};
        LocalDelay.delay = sessionStorage.getItem( "delay_"  + location.href);
        LocalDelay.time = sessionStorage.getItem( "time_"  + location.href);
        LocalDelay.email=sessionStorage.getItem("email_"+location.href);
        return  LocalDelay;
    }
</script>
</body>
</html>
