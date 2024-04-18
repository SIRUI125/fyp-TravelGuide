<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise."/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Tour Travel Website Template | Emprise</title>

    <#include "../common/header_css.ftl"/>
    <link rel="stylesheet" href="/home/styles/dashboard.css" />

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
                        <select class="rlr-sidebar-menu__sub-menu rlr-form-select" id="rlr-js-sub-menu"
                                name="rlr-sub-menu">
                            <option>Navigate to...</option>
                            <option value="my-account-pages--dashboard">Dashboard</option>
                            <option value="my-account-pages--order">Orders</option>
                            <option value="my-account-pages--subscription">Subscriptions</option>
                            <option value="my-account-pages--dashboard">Addresses</option>
                            <option value="my-account-pages--dashboard">Payment Methods</option>
                            <option value="my-account-pages--dashboard">Account Details</option>
                            <option value="my-account-pages--dashboard">Logout</option>
                        </select>
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
                            <div class="row mb-4">
                                <div class="col-lg-12">
                                    <div class="notification success"><p class="m-0">Your listing Nelson Center Restro
                                            has been approved!</p><a class="notification-close" href="#"><i
                                                    class="fa fa-times"></i></a></div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-md-12 col-xs-12">
                                    <div class="dashboard-stat mb-4">
                                        <div class="dashboard-stat-content"><h4>${scenicOrderCount!"0"}</h4><span>Scenic Order Count</span></div>
                                        <div class="dashboard-stat-icon"><i class="rlr-icon-font flaticon-history"></i></div>
                                        <div class="dashboard-stat-item"><p>Someone bookmarked your listing!</p></div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-xs-12">
                                    <div class="dashboard-stat mb-4">
                                        <div class="dashboard-stat-content"><h4>${hotelOrderCount!"0"}</h4><span>Hotel Order Count</span>
                                        </div>
                                        <div class="dashboard-stat-icon"><i class="rlr-icon-font flaticon-history"></i></div>
                                        <div class="dashboard-stat-item"><p>Someone bookmarked your listing!</p></div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-xs-12">
                                    <div class="dashboard-stat mb-4">
                                        <div class="dashboard-stat-content"><h4>${commentCount!"0"}</h4><span>Scenic Total Reviews</span></div>
                                        <div class="dashboard-stat-icon"><i class="rlr-icon-font flaticon-reviews
"></i></div>
                                        <div class="dashboard-stat-item"><p>Someone bookmarked your listing!</p></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<!-- Footer -->
<#include "../common/footer.ftl"/>

</body>
</html>
