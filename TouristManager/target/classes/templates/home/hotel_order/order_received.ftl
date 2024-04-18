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
  <!-- Main Content -->
  <main id="rlr-main" class="rlr-main--fixed-top">
    <div class="rlr-section__content--md-top">
      <div class="rlr-section rlr-section__my">
        <div class="rlr-checkout">
          <article id="post-7" class="post-7 page type-page status-publish hentry">
            <div class="container-xxl">
              <div class="entry-content">
                <div class="woocommerce">
                  <div class="woocommerce-info">
                    <div class="rlr-page-title">
                      <span class="rlr-page-title__icon"> <i class="rlr-icon-font flaticon-carbon-wallet"> </i> </span>
                      <div>
                        <h2 class="type-h3 rlr-page-title__title">Order Payment</h2>
                      </div>
                    </div>
                  </div>

                  <div class="woocommerce-notices-wrapper"></div>
                  <form name="checkout" method="post" class="checkout woocommerce-checkout" action="https://ui.emprise.tours/order-received/" enctype="multipart/form-data" novalidate="novalidate">
                    <div id="order_review" class="woocommerce-checkout-review-order" style="width:100%;">
                      <table class="shop_table woocommerce-checkout-review-order-table">
                        <tbody>
                        <tr class="cart_item">
                          <td class="product-name">
                            <h6 class="cart-item-title">Your Order</h6>
                            <ul class="cart-item-card">
                              <li class="cart-item-card__header">
                                <div>
                                  <#if order.hotel.coverPic?? && order.hotel.coverPic?length gt 0>
                                    <img class="cart-item-card__img" src="/photo/view?filename=${order.hotel.coverPic}" alt="..." />
                                    <#else>
                                      <img class="cart-item-card__img" src="/home/assets/images/card_items-photo.jpg" alt="..." />
                                  </#if>
                                </div>
                                <div>
                                  <p class="cart-item-card__title">${order.hotel.name!""}</p>
                                  <p class="cart-item-card__date">
                                    <span>createTime </span>
                                    <span>${order.createTime!""}</span>
                                  </p>
                                </div>
                              </li>
                              <li class="cart-item-card__item">

                                <div class="cart-item-card__iconcontainer">
                                      <span>
                                        <i class="rlr-icon-font flaticon-three-o-clock-clock"></i>
                                      </span>
                                </div>
                                <div class="cart-item-card__item-title">
                                  <p>CHECK_IN_DATE-CHECK_OUT_DATE</p>
                                </div>
                                <p class="cart-item-card__item-price" style="width: 100px;">${order.checkInDate!""}</p>
                                <p class="cart-item-card__item-price">${order.checkOutDate!""}</p>
                              </li>
                              <li class="cart-item-card__item">

                                <div class="cart-item-card__iconcontainer">
                                      <span>
                                        <i class="rlr-icon-font flaticon-carbon-user"></i>
                                      </span>
                                </div>
                                <div class="cart-item-card__item-title">
                                  <p>Adult</p>

                                </div>
                                <p class="cart-item-card__item-number">
                                  <span class="times"> x </span>
                                  ${order.num!"0"}
                                </p>
                              </li>
                              <#list orderItemList as orderItem>

                                <li class="cart-item-card__item">
                                  <div class="cart-item-card__iconcontainer">
                                      <span>
                                        <i class="rlr-icon-font flaticon-hotel-bed"></i>
                                      </span>
                                  </div>
                                  <div class="cart-item-card__item-title">
                                    <p>${orderItem.hotelType.name}</p>
                                    <p>
                                      <b> $${orderItem.hotelType.price!""} </b>
                                    </p>
                                  </div>
                                  <p class="cart-item-card__item-number">
                                    <span class="times"> x </span>
                                    ${orderItem.day!"0"}
                                  </p>
                                  <p class="cart-item-card__item-price">$${orderItem.money}</p>
                                </li>
                              </#list>
                              <li class="cart-item-card__footer">
                                <p>Total</p>
                                <p>$${order.totalPrice!"0.00"}.00</p>
                              </li>
                            </ul>
                          </td>
                        </tr>
                        </tbody>
                        <tfoot>

                        <tr class="order-total">
                          <th>Total payment</th>
                          <td>
                            <strong>
                                  <span class="woocommerce-Price-amount amount">
                                    <bdi>
                                      <span class="woocommerce-Price-currencySymbol"> $ </span>
                                      ${order.totalPrice!"0.00"}
                                    </bdi>
                                  </span>
                            </strong>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <button type="button" class="button alt"
                                    name="woocommerce_checkout_place_order"
                                    id="place_order"
                                    data-value="Place order">Place Order</button>
                          </td>
                        </tr>
                        </tfoot>
                      </table>
                    </div>
                  </form>
                </div>
              </div>
              <!-- .entry-content -->
            </div>
          </article>
          <!-- #post-7 -->
        </div>
      </div>
    </div>
  </main>

  <#include "../common/footer.ftl"/>
  <script type="text/javascript">
    //支付订单操作
    $("#place_order").click(function () {
    ajaxRequest('/home/hotel_order/order_pay',"POST",{oid:${order.id}},function (result) {
      showSuccessMsg("Payment successful! Please go to your personal center to view my order.",function () {
        window.location.href="/home/user_center/hotel_orders";
      })
    })
    })
  </script>
  </body>
</html>
