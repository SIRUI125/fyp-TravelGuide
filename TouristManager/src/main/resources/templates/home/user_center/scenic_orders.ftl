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
            <div class="row">
              <div class="col-sm-12 rlr-sidebar-menu__mobile">
                <select class="rlr-sidebar-menu__sub-menu rlr-form-select" id="rlr-js-sub-menu" name="rlr-sub-menu">
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
                <div class="rlr-data-container woocommerce-MyAccount-content">
                  <h1 class="type-h3 rlr-data-table__title">Your Scenic Orders</h1>
                  <div class="table">
                    <table class="rlr-data-table woocommerce-orders-table woocommerce-MyAccount-orders shop_table shop_table_responsive my_account_orders account-orders-table">
                      <thead class="rlr-data-table__header">
                        <tr class="rlr-data-table__header-row">
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-number">
                            <span class="nobr"> Scenic Photo </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-number">
                            <span class="nobr"> Scenic Title </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-date">
                            <span class="nobr"> Date </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-date">
                            <span class="nobr"> Number </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                            <span class="nobr"> Total </span>
                          </th>


                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                            <span class="nobr"> STATE </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                            <span class="nobr"> CREATE_TIME </span>
                          </th>
                          <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-actions">
                            <span class="nobr"> Actions </span>
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                      <#if pageBean.content?size gt 0>
                        <#list pageBean.content as scenicOrder>

                        <tr class="rlr-data-table__row woocommerce-orders-table__row woocommerce-orders-table__row--status-on-hold order">
                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                            <img src="/photo/view?filename=${scenicOrder.scenicPrice.scenic.coverPic}" style="width:200px;height: 200pd;">
                          </td>

                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                          <a href="/home/tourist/detail?id=${scenicOrder.scenicPrice.scenic.id}">${scenicOrder.scenicPrice.scenic.title!""}</a>
                          </td>
                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Date">
                            <time datetime="2022-04-20T08:01:42+00:00">
                              ${scenicOrder.startDate?string("yyyy-MM-dd")!""}
                            </time>
                          </td>
                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Total">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                              ${scenicOrder.count!"0"}
                            </span>
                          </td>
                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Total">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                              ${scenicOrder.price!""}
                            </span>
                          </td>

                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Total">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                              <#if scenicOrder.status==0>
                                unused
                                <#elseif scenicOrder.status==2>
                                using
                                <#elseif scenicOrder.status==3>
                                cancelled
                              <#elseif scenicOrder.status==4>
                                commented
                                <#else>
                                used
                              </#if>
                            </span>
                          </td>
                          <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total"
                              data-title="Total" style="width: 100px;">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                             ${scenicOrder.createTime!""}
                            </span>
                          </td>
                          <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-actions" data-title="Actions">
                            <a href="/home/tourist/detail?id=${scenicOrder.scenicPrice.scenic.id}"
                               class="rlr-data-table__link woocommerce-button button view"> View </a>

                            <#if scenicOrder.status==0>
                              <a href="javascript:void(0);" class="rlr-data-table__link woocommerce-button button cancel_order_btn view"
                                 data-id="${scenicOrder.id}"> Cancel </a>
                            </#if>

                            <#if scenicOrder.status==3  || scenicOrder.status==1 || scenicOrder.status==4>
                              <a href="javascript:void(0);"
                                 class="rlr-data-table__link woocommerce-button button delete_order_btn view"
                                 data-id="${scenicOrder.id}"> Delete</a>
                            </#if>

                            <#if scenicOrder.status==1>
                              <a href="javascript:void(0);"
                                 class="rlr-data-table__link woocommerce-button button comment_order_btn view"
                                 data-id="${scenicOrder.id}"> Comment</a>
                            </#if>
                          </td>
                        </tr>
                        </#list>
                        <#else>
                          <tr>
                            <td colspan="8" style="text-align: center">No order information yet</td>
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
                             href="scenic_orders?&currentPage=1" aria-label="Previous">
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
                                   href="scenic_orders?currentPage=${showPage}">${showPage}</a>
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
                             href="scenic_orders?currentPage=${pageBean.totalPage}" aria-label="Next">
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
    //取消订单
    $(".cancel_order_btn").click(function () {
      var id=$(this).attr("data-id");
      ajaxRequest('cancel_order','POST',{id:id},function (result) {
        showSuccessMsg("Order canceled successfully",function () {
          window.location.reload();
        })
      })
    })
    //删除订单操作
    $(".delete_order_btn").click(function () {
      var id=$(this).attr("data-id");
      ajaxRequest('delete_order','POST',{id:id},function (result) {
        showSuccessMsg("Order deleted successfully",function () {
          window.location.reload();
        })
      })
    });
    //评论
    $(".comment_order_btn").click(function () {
      var id=$(this).attr("data-id");
      $("#comment-id").val(id);
      layer.open({
        type: 1,
        shade: false,
        area: ['600px', '290px'],
        title: "comments", //是否显示标题
        content: $("#comment-content-btn"), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function(){

        }
      });
    })


    layui.use(['rate','form'], function() {
      var rate = layui.rate;
      var form = layui.form;
      //基础效果
      var initRate=rate.render({
        elem: '#test1',
      })
      //监听提交
      form.on('submit(comment)', function(data){
        var score=initRate.config.value;
        if(score==0){
          showWarningMsg("Please select a rating");
          return false;
        }
        var id=data.field.id;
        var content=data.field.content;
        ajaxRequest('/home/user_center/order_comment',"POST",{oid:id,score:score,content:content},function (result) {
          showSuccessMsg("Comment successful",function () {
            window.location.reload();
          })
        });
        return false;
      });

    });
  </script>
  </body>
  <div style="display: none;" id="comment-content-btn">
    <form class="layui-form" >
      <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">rating</label>
      <div id="test1"></div>
      </div>
      <div class="layui-form-item layui-form-text">
        <input type="hidden" name="id" id="comment-id"/>
        <label class="layui-form-label">content</label>
        <div class="layui-input-block" style="margin-left:80px;">
          <textarea placeholder="Please enter content" lay-verify="content" id="comment-content"
                    class="layui-textarea" name="content"></textarea>
        </div>
      </div>
      <div class="layui-form-item" style="float: right;">
        <button type="submit" class="layui-btn" lay-submit="" lay-filter="comment" >comment now</button>
      </div>
    </form>
  </div>
</html>
