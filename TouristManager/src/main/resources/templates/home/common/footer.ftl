<!-- Footer -->
<footer class="rlr-footer rlr-section rlr-section__mt">
    <div class="container">
        <div class="rlr-footer__getintouch">
            <div class="rlr-footer__getintouch_col rlr-footer__getintouch__col--title">
                <h4>Get in travel</h4>
                <p>Adventures Calling You Guys!</p>
            </div>
            <div class="rlr-footer__getintouch_col rlr-footer__getintouch__col--address">
                <h4>Our Offices</h4>
                <a href="#">Nepal, USA, India</a>
            </div>
        </div>
        <!-- Footer menu -->
        <div class="rlr-footer__menu">
            <li><a href="/system/login">前往后台</a></li>

        </div>

    </div>
</footer>
<!-- Scripts -->
<script src="/home/js/jquery.min.js"></script>
<script src="/home/vendors/navx/js/navigation.js" ></script>
<script src="/home/vendors/navx/js/navigation.min.js" ></script>
<script src="/home/js/main.js" ></script>
<script src="/home/js/jconfirm/jquery-confirm.min.js" ></script>
<script src="/home/js/common.js" ></script>

<script type="text/javascript">
    var pathname=window.location.pathname;
    $(".sign-login-li").each(function (i,e) {
        var href=$(e).find("a").attr("href");
        if(pathname.includes(href)){
            $(e).find("a").siblings().removeClass("rlr-navigation__link--so");
            $(e).find("a").addClass("rlr-navigation__link--so");
        }
    });
    $(".user-right-menu").mouseenter(function (e) {
        $(this).addClass("is-active");
        $(this).find("ul").addClass("is-visible");

    });
    $(".user-right-menu").mouseleave(function (e) {
        $(this).removeClass("is-active");
        $(this).find("ul").removeClass("is-visible");

    });

    var  pathname=window.location.pathname;
    $(".rlr-sidebar-menu__link").each(function (i,e) {
        var href=$(e).attr("href");
        $(e).removeClass("active");
        if(pathname.includes(href)){
            $(e).addClass("active");
        }
    });
    //导航栏
    $(".navigation-link").each(function (i,e) {
       var href=$(e).attr("href");
       $(e).parent().removeClass("is-active");
        var split=href.split("/")[2];
        if(pathname.includes(split)){
           $(e).parent().addClass("is-active");
           return false;
       }
    })
</script>
