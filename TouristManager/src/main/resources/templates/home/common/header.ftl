<!-- Header -->
<header>
    <nav id="navigation" class="navigation rlr-navigation default-nav fixed-top">
        <!-- Logo -->
        <div class="navigation-header">
            <div class="navigation-brand-text">
                <div class="rlr-logo rlr-logo__navbar-brand rlr-logo--default">
                    <a href="/home/index/index">
                        <img src="/home/assets//svg/emprise-logo-dark.svg" alt="#" class="" />
                    </a>
                </div>
            </div>
            <div class="navigation-button-toggler">
                <span class="rlr-sVGIcon"> <i class="rlr-icon-font rlr-icon-font--megamenu flaticon-menu"> </i> </span>
            </div>
        </div>
        <div class="navigation-body rlr-navigation__body container">
            <div class="navigation-body-header rlr-navigation__body-header">
                <div class="navigation-brand-text">
                    <div class="rlr-logo rlr-logo__navbar-brand rlr-logo--default">
                        <a href="./index.html">
                            <img src="/home/assets//svg/emprise-logo-dark.svg" alt="#" class="" />
                        </a>
                    </div>
                </div>
                <span class="rlr-sVGIcon navigation-body-close-button">
                    <i class="rlr-icon-font rlr-icon-font--megamenu flaticon-close"> </i>
                </span>
            </div>

            <!-- Main menu -->
            <ul class="navigation-menu rlr-navigation__menu rlr-navigation__menu--main-links">
                <!-- Dropdown menu 1 -->
                <li class="navigation-item is-active">
                    <a class="navigation-link" href="/home/index/index">Home</a>
                </li>
                <!-- Dropdown menu 1 -->
                <li class="navigation-item">
                    <a class="navigation-link" href="/home/tourist/list">Tours List</a>

                </li>

                <li class="navigation-item">
                    <a class="navigation-link" href="/home/hotel/list">Hotel List</a>
                </li>
                <li class="navigation-item">
                    <a class="navigation-link" href="/home/blog/list">Blog List</a>
                </li>

                <li class="navigation-item">
                    <a class="navigation-link" href="/home/gallery/list">Gallery</a>
                </li>
            </ul>
            <#if ylrc_home??>
            <ul class="navigation-menu rlr-navigation__menu align-to-right">

                <!-- User account dropdown -->
                <li class="navigation-item has-submenu user-right-menu">
                    <a class="navigation-link" href="/home/user_center/dashboard"> ${ylrc_home.username!""}
                        <#if ylrc_home.headPic??>
                            <img class="ui right spaced avatar image"
                                 src="/photo/view?filename=${ylrc_home.headPic!""}" alt="account avatar" />
                        <#else>
                            <img class="ui right spaced avatar image"
                                 src="/home/assets//images/blog-single-avatar.png" alt="account avatar" />
                        </#if>

                    </a>
                    <ul class="navigation-dropdown navigation-submenu" style="right: 0px;">
                        <li class="navigation-dropdown-item">
                            <a class="navigation-dropdown-link" href="/home/user_center/profile">Profile</a>
                        </li>
                        <li class="navigation-dropdown-item">
                            <a class="navigation-dropdown-link" href="/home/user_center/scenic_orders">Your Orders</a>
                        </li>
                        <li class="navigation-dropdown-item">
                            <a class="navigation-dropdown-link" href="/home/user_center/scenic_favorites">Favorites</a>
                        </li>
                        <li class="navigation-dropdown-item">
                            <hr class="dropdown-divider rlr-dropdown__divider">
                        </li>
                        <li class="navigation-dropdown-item">
                            <a class="navigation-dropdown-link" href="/home/index/logout">Sign out</a>
                        </li>
                    </ul>
                </li>
            </ul>
                <#else>
            <ul class="navigation-menu rlr-navigation__menu align-to-right">
                <li class="d-lg-none d-xxl-block navigation-item sign-login-li">
                    <a class="navigation-link " target="_blank"
                       href="/home/index/login">Login</a>
                </li>
                <li class="d-lg-none d-xxl-block navigation-item sign-login-li">
                    <a class="navigation-link " target="_blank"
                       href="/home/index/signup">SignUp</a>
                </li>
            </ul>
            </#if>
            <!-- User actions menu -->
           <#-- <ul class="navigation-menu rlr-navigation__menu align-to-right">

                <#if ylrc_home??>
                    <!-- User account dropdown &ndash;&gt;
                    <li class="navigation-item has-submenu is-active">
                        <a class="navigation-link" href="#"> ${ylrc_home.username}
                            <#if ylrc_home.headPic??>
                                <img class="ui right spaced avatar image"
                                     src="/photo/view?filename=${ylrc_home.headPic!""}" alt="account avatar" />
                                <#else>
                                    <img class="ui right spaced avatar image"
                                         src="/home/assets//images/blog-single-avatar.png" alt="account avatar" />
                            </#if>
                            </a>
                        <ul class="navigation-dropdown">
                            <li class="navigation-dropdown-item">
                                <a class="navigation-dropdown-link" href="/home/user_center/profile">Profile</a>
                            </li>
                            <li class="navigation-dropdown-item">
                                <a class="navigation-dropdown-link" href="./my-account-pages--order.html">Your Orders</a>
                            </li>
                            <li class="navigation-dropdown-item">
                                <a class="navigation-dropdown-link" href="./my-account-pages--subscription.html">Subscriptions</a>
                            </li>
                            <li class="navigation-dropdown-item">
                                <a class="navigation-dropdown-link" href="./search-results--no-sidebar.html">Favorites</a>
                            </li>
                            <li class="navigation-dropdown-item">
                                <hr class="dropdown-divider rlr-dropdown__divider" />
                            </li>
                            <li class="navigation-dropdown-item">
                                <a class="navigation-dropdown-link" href="/home/index/logout">Sign out</a>
                            </li>
                        </ul>
                    </li>
                    <#else>
                        <li class="d-lg-none d-xxl-block navigation-item sign-login-li">
                            <a class="navigation-link " target="_blank"
                               href="/home/index/login">Login</a>
                        </li>
                        <li class="d-lg-none d-xxl-block navigation-item sign-login-li">
                            <a class="navigation-link " target="_blank"
                               href="/home/index/signup">Singup</a>
                        </li>
                </#if>

            </ul>-->

        </div>
    </nav>
</header>


