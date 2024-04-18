<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise." />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tourist Platform | ${title!""}</title>

    <#include  "../common/header_css.ftl"/>
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
                <form class="rlr-authforms" id="login-form">
                  <div class="rlr-authforms__header">
                    <img src="/home/assets/images/logos/Logomark.svg" alt="Logo" />
                    <h2>Log in to your emprise</h2>
                    <p>Welcome back! Please enter login details.</p>
                  </div>
                  <div class="rlr-authforms__form">
                    <div class="rlr-authforms__inputgroup">
                      <label class="rlr-form-label rlr-form-label--light">
                        Phone </label>
                      <input type="text" autocomplete="off"
                             class="form-control form-control--light required"
                             tips="Please fill in your Phone" id="login-phone"  name="mobile"
                             placeholder="Please fill in your Phone" />
                    </div>
                    <div class="rlr-authforms__inputgroup">
                      <label class="rlr-form-label rlr-form-label--light"> Password </label>
                      <input type="password" autocomplete="off" class="form-control form-control--light required"
                             tips="Please fill in your Password" name="password"
                             placeholder="Please fill in your Password" />
                    </div>
                    <div class="rlr-authforms__forgotpassword">
                      <div class="form-check-inline">
                        <input class="form-check-input rlr-form-check-input" id="rlr-checkbox-1"
                               checked type="checkbox" value="defaultValue" />
                        <label class="rlr-form-label rlr-form-label--checkbox rlr-form-label--font-inherit rlr-form-label--bold" for="rlr-checkbox-1">Remember me on this device</label>
                      </div>
                      <a href="#">Forgot password</a>
                  </div>
                    <button type="button" class="btn mb-3 rlr-button rlr-button--fullwidth rlr-button--primary"
                            id="sign-in-btn">Sign in</button>
                  </div>
                  <div class="rlr-authforms__notes">
                    <p>Don’t have an account? <a href="/home/index/signup">Sign up</a></p>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

  <#include "../common/footer.ftl"/>

  <script type="text/javascript">
    //登录
    $("#sign-in-btn").click(function () {
        if(!checkForm('login-form')) {
          return;
        }
      var phone=$("#login-phone").val();
      var phonePattern = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
      if(!phonePattern.test(phone)){
        showWarningMsg("Phone number format is incorrect");
        return;
      }
      var data=$("#login-form").serialize();
      ajaxRequest('/home/index/login','POST',data,function (result) {
        showSuccessMsg("Login successful",function () {
          var url=result.data;
          if(url==null){
            window.location.href="/home/index/index";
          }else{
            window.location.href=result.data;
          }
        })
      })
    })
  </script>
  </body>
</html>
