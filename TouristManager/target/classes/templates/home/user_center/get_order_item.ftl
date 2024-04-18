<#include "../common/header_css.ftl"/>
<div class="table">
    <table class="rlr-data-table woocommerce-orders-table woocommerce-MyAccount-orders shop_table shop_table_responsive my_account_orders account-orders-table">
        <thead class="rlr-data-table__header">
        <tr class="rlr-data-table__header-row">

            <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-date">
                <span class="nobr"> HotelRoom Name </span>
            </th>
            <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-status">
                <span class="nobr"> HotelRoom Number </span>
            </th>
            <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                <span class="nobr"> day </span>
            </th>
            <th class="woocommerce-orders-table__header woocommerce-orders-table__header-order-total">
                <span class="nobr"> money </span>
            </th>
        </tr>
        </thead>
        <tbody>
        <#list orderItemList as orderItem>
            <tr class="rlr-data-table__row woocommerce-orders-table__row
         woocommerce-orders-table__row--status-on-hold
        order">
                <td class="rlr-data-table__cell woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number" data-title="Order">
                    <a href="/home/hotel/detail?id=${orderItem.hotelType.id}">
                        ${orderItem.hotelType.name!""}
                    </a>
                </td>
                <td class="rlr-data-table__cell woocommerce-orders-table__cell
                woocommerce-orders-table__cell-order-status" data-title="Status">
                   ${orderItem.hotelType.number!""}</td>

                <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Total">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                             ${orderItem.day!"0"}
                            </span>
                </td>
                <td class="rlr-data-table__cell rlr-data-table__cell-total woocommerce-orders-table__cell woocommerce-orders-table__cell-order-total" data-title="Total">
                            <span class="rlr-data-table__amount woocommerce-Price-amount amount">
                             ${orderItem.money!"0"}
                            </span>
                </td>

            </tr>

        </#list>
        </tbody>
    </table>
</div>
