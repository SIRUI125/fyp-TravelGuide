<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise." />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tourist Attractions Platform | ${title!""}</title>
  <#include "../common/header_css.ftl"/>

  </head>

  <body class="rlr-body">

  <#include "../common/header.ftl"/>
    <!-- Main Content -->
    <main id="rlr-main" class="rlr-main--fixed-top">
      <div class="rlr-section__content--md-top">
        <div class="rlr-section rlr-section__my">
          <div class="container">
          </div>
          <div class="container">
            <div class="row">
              <aside class="rlr-sidebar-menu col-lg-3 col-xs-12 mb-5">
                <div class="rlr-sidebar-menu__wrapper">
               <#include "left_menu.ftl"/>
                </div>
              </aside>
              <div class="content col-lg-9 col-xs-12">
                <div class="rlr-data-container woocommerce-MyAccount-content">
                  <h1 class="type-h3 rlr-data-table__title">Your Recharge Record</h1>
                  <div class="table">
                    <table class="rlr-data-table woocommerce-orders-table woocommerce-MyAccount-orders shop_table shop_table_responsive my_account_orders account-orders-table">
                      <thead class="rlr-data-table__header">
                        <tr class="rlr-data-table__header-row">
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-number">
                            <span class="nobr"> recharge amount </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-number">
                            <span class="nobr"> original amount </span>
                          </th>

                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                            <span class="nobr"> cash amount </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                            <span class="nobr"> recharge time </span>
                          </th>

                        </tr>
                      </thead>
                      <tbody>
                      <#if pageBean.content?size gt 0>
                        <#list pageBean.content as recharge>

                        <tr class="rlr-data-table__row woocommerce-orders-table__row woocommerce-orders-table__row--status-on-hold order">
                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                            ${recharge.price!""}
                          </td>

                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                            ${recharge.oldBalance!""}

                          </td>

                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                           ${recharge.balance!""}
                          </td>

                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total"
                              data-title="Total" style="width: 200px;">
                            <span class="rlr-data-table__amount">
                             ${recharge.createTime!""}
                            </span>
                          </td>

                        </tr>
                        </#list>
                        <#else>
                          <tr>
                            <td colspan="8" style="text-align: center">No recharge information yet</td>
                          </tr>

                      </#if>

                      </tbody>

                    </table>

                  </div>
                  <#if pageBean.total gt 0>
                    <ul class="pagination rlr-pagination__list">
                      <#if pageBean.currentPage == 1>
                        <li class="page-item rlr-pagination__page-item disabled">
                          <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--prev" href="#" aria-label="Previous">
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                              <path d="M15.833 10H4.167m0 0L10 15.833M4.167 10 10 4.167"
                                    stroke="var(--body-color)" stroke-width="1.67" stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                            <span aria-hidden="true">Previous</span>
                          </a>
                        </li>
                      <#else>
                        <li class="page-item rlr-pagination__page-item disabled">
                          <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--prev"
                             href="recharge_list?&currentPage=1" aria-label="Previous">
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                              <path d="M15.833 10H4.167m0 0L10 15.833M4.167 10 10 4.167" stroke="var(--body-color)"
                                    stroke-width="1.67" stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                            <span aria-hidden="true">Previous</span>
                          </a>
                        </li>
                      </#if>
                      <li class="page-item rlr-pagination__page-item">
                        <ul class="pagination rlr-pagination__child-list">
                          <#list pageBean.currentShowPage as showPage>
                            <#if pageBean.currentPage == showPage>
                              <li class="page-item rlr-pagination__page-item">
                                <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--counter active"
                                   href="#">${showPage}</a>
                              </li>
                            <#else>
                              <li class="page-item rlr-pagination__page-item">
                                <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--counter"
                                   href="recharge_list?currentPage=${showPage}">${showPage}</a>
                              </li>
                            </#if>
                          </#list>
                        </ul>
                      </li>
                      <#if pageBean.currentPage == pageBean.totalPage>
                        <li class="page-item rlr-pagination__page-item disabled">
                          <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--next " href="#" aria-label="Next">
                            <span aria-hidden="true">Next</span>
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                              <path d="M4.167 10h11.666m0 0L10 4.167M15.833 10 10 15.833"
                                    stroke="var(--body-color)" stroke-width="1.67"
                                    stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                          </a>
                        </li>
                      <#else>
                        <li class="page-item rlr-pagination__page-item">
                          <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--next"
                             href="recharge_list?currentPage=${pageBean.totalPage}" aria-label="Next">
                            <span aria-hidden="true">Next</span>
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                              <path d="M4.167 10h11.666m0 0L10 4.167M15.833 10 10 15.833" stroke="var(--body-color)" stroke-width="1.67" stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                          </a>
                        </li>
                      </#if>
                    </ul>
                  </#if>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>


  <link rel="stylesheet" href="//res.ilayuis.com/layui/dist/css/layui.css" tppabs="//res.ilayuis.com/layui/dist/css/layui.css"  media="all">
  <script src="//res.ilayuis.com/layui/dist/layui.js" charset="utf-8"></script>

  <#include "../common/footer.ftl"/>
  <script src="/home/js/layer.js" ></script>

  <script type="text/javascript">


  </script>
  </body>
</html>
