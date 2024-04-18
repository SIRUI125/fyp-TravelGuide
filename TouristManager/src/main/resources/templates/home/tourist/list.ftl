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
    <!-- Header -->
   <#include "../common/header.ftl"/>
    <!-- Main Content -->
    <main id="rlr-main" class="rlr-main--fixed-top">
      <div class="rlr-search-results-page container">
        <div class="rlr-search-results-page__breadcrumb-section">
          <!-- Breadcrumb -->
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb rlr-breadcrumb__items">
              <li class="breadcrumb-item rlr-breadcrumb__item"><a href="/">Home</a></li>
              <li class="breadcrumb-item rlr-breadcrumb__item active" aria-current="page">Tour</li>
            </ol>
          </nav>
          <div class="rlr-icon-text"><i class="rlr-icon-font flaticon-phone-receiver-silhouette"> </i> <span class="rlr-search-results-page__phone">Questions? +977 (985) 110-8896 </span></div>
        </div>
        <aside class="row">
          <!-- Search results header -->
          <div class="rlr-search-results-header rlr-search-results-header__wrapper">
            <!-- Title -->
            <h1 class="rlr-search-results-header__value">
              Showing 1 - ${pageBean.pageSize} of ${pageBean.total} results for
            </h1>
            <!-- Sort order -->
            <div class="rlr-search-results-header__sorting-wrapper">
              <span class="rlr-search-results-header__label">Sort by:</span>
              <div class="dropdown rlr-dropdown rlr-js-dropdown">
                <button class="btn dropdown-toggle rlr-dropdown__button rlr-js-dropdown-button" type="button"
                        id="rlr_dropdown_menu_search_results" data-bs-toggle="dropdown" aria-expanded="false"
                        data-bs-offset="-50,35">CreateTime</button>
                <ul class="dropdown-menu rlr-dropdown__menu" aria-labelledby="rlr_dropdown_menu_search_results">
                  <li>
                    <a class="dropdown-item rlr-dropdown__item rlr-js-dropdown-item" href="/home/tourist/list?title=${name!""}&minPrice=${minPrice!""}&maxPrice=${maxPrice!""}&orderByPrice=2">Price (Low to High)</a>
                  </li>
                  <li>
                    <a class="dropdown-item rlr-dropdown__item rlr-js-dropdown-item" href="/home/tourist/list?title=${name!""}&minPrice=${minPrice!""}&maxPrice=${maxPrice!""}&orderByPrice=1">Price (High to Low)</a>
                  </li>

                </ul>
              </div>
            </div>
          </div>
        </aside>

        <!-- Product cards -->
        <div class="row rlr-search-results-page__product-details">
          <aside class="col-xl-3 rlr-search-results-page__sidebar">
            <form action="/home/tourist/list" method="get">
            <div class="rlr-product-filters__filters-wrapper">

              <!-- Search filter -->
              <div class="rlr-product-filters__filter">
                <label class="rlr-form-label rlr-form-label-- rlr-product-filters__label">  Please search based on the title </label>
                <div class="rlr-input-groups rlr-input-groups--daterange js-date-ranges">
                  <div class="rlr-input-group rlr-input-group__datefield rlr-custom-input
                  rlr-custom-input--numberfield rlr-custom-input--option">
                    <input type="text" name="title" value="${name!""}"  class="form-control" placeholder="Please search based on the title" />
                  </div>

                </div>
              </div>

              <!-- Price range filter -->
              <div class="rlr-product-filters__filter">
                <label class="rlr-form-label rlr-form-label-- rlr-product-filters__label"> Price Range </label>
                <div class="rlr-range-slider">
                  <span class="rlr-range-slider__price-box">
                    <input type="number" autocomplete="off" class="form-control" name="minPrice" value="${minPrice!"0"}" data-name-min="0"
                           data-name-max="10000"  />
                    <input type="number" autocomplete="off" class="form-control" name="maxPrice" value="${maxPrice!"0"}" data-name-min="0"
                           data-name-max="10000" />
                  </span>
                  <input value="${minPrice!"0"}" min="0" max="10000"  step="500" type="range" />
                  <input value="${maxPrice!"0"}" min="0" max="10000"  step="500" type="range" />
                </div>
              </div>



                <div>
                  <button type="submit" class="btn btn-success" style="float:right">搜索</button>
                </div>


            </div>
            </form>
          </aside>
          <div class="rlr-search-results-page__product-list col-lg-9">
            <div class="row rlr-search-results-page__card-wrapper">
              <#if pageBean.content?size gt 0>
              <#list pageBean.content as scenic>
                <div class="col-md-6 col-lg-4">
                  <!-- Product card item -->
                  <article class="rlr-product-card rlr-product-card--v3" itemscope
                           itemtype="https://schema.org/Product">
                    <!-- Product card image -->
                    <figure class="rlr-product-card__image-wrapper">
                      <div class="rlr-product-detail-header__button-wrapper">
                        <button type="button" class="btn rlr-button rlr-button--circle
                        rlr-wishlist rlr-wishlist-button--light rlr-wishlist-button
                        rlr-js-action-wishlist" aria-label="Save to Wishlist">
                          <i class="rlr-icon-font flaticon-heart-1"> </i>
                        </button>
                        <span class="rlr-product-detail-header__helptext rlr-js-helptext"></span>
                      </div>
                      <a href="/home/tourist/detail?id=${scenic.id}">
                        <div class="swiper rlr-js-product-multi-image-swiper">
                          <div class="swiper-wrapper">
                            <#list scenic.images?split(";") as images>
                              <div class="swiper-slide">
                                <img itemprop="image" data-sizes="auto"
                                     data-src="/photo/view?filename=${images}"
                                     data-srcset="/photo/view?filename=${images}"
                                     class="lazyload" alt="product-image" style="width: 336px;height: 290px;"/>
                              </div>

                              </#list>
                          </div>
                          <button type="button" class="btn rlr-button splide__arrow splide__arrow--prev" aria-label="prev button">
                            <i class="rlr-icon-font flaticon-left-chevron"> </i>
                          </button>
                          <button type="button" class="btn rlr-button splide__arrow splide__arrow--next" aria-label="next button">
                            <i class="rlr-icon-font flaticon-chevron"> </i>
                          </button>
                        </div>
                      </a>
                    </figure>
                    <div class="rlr-product-card__detail-wrapper rlr-js-detail-wrapper">
                      <!-- Product card header -->
                      <header class="rlr-product-card__header">
                        <div>
                          <a href="/home/tourist/detail?id=${scenic.id}" class="rlr-product-card__anchor-title">
                            <h2 class="rlr-product-card__title" itemprop="name">${scenic.title!""}</h2>
                          </a>
                          <div>
                            <a href="/home/tourist/detail?id=${scenic.id}" class="rlr-product-card__anchor-cat">
                              <span class="rlr-product-card__sub-title">${scenic.address!""}</span>
                            </a>
                            <span class="rlr-product-card__sub-title">|</span>
                            <a href="/home/tourist/detail?id=${scenic.id}" class="rlr-product-card__anchor-sub-cat">
                              <span class="rlr-product-card__sub-title">${scenic.openTime}</span>
                            </a>
                          </div>
                        </div>
                      </header>
                      <!-- Product card body -->
                      <div class="rlr-product-card__details">
                        <div class="rlr-product-card__prices" itemprop="offers" itemscope itemtype="https://schema.org/Offer">
                          <span class="rlr-product-card__from">from </span>
                          <span class="rlr-product-card__price">
                            <mark itemprop="priceCurrency">$</mark><mark itemprop="price">
                              ${scenic.price!"0"}</mark> </span>
                        </div>
                        <div class="rlr-product-card__ratings" itemprop="aggregateRating"
                        >
                          <div class="rlr-review-stars" itemprop="ratingValue" >
                            <#if scenic.rateScore!=0>
                              <#list 1..scenic.rateScore as x>
                                <i class="rlr-icon-font flaticon-star-1"> </i>
                              </#list>
                            </#if>

                          </div>
                          <span class="rlr-product-card__rating-text" itemprop="reviewCount">
                          ${scenic.rateScore!"0"}(${scenic.rateNumber!"0"})
                          </span>
                        </div>
                      </div>
                      <!-- Product card footer -->
                      <div class="rlr-product-card__footer">
                        <div class="rlr-icon-text rlr-product-card__icon-text">
                          <i class="rlr-icon-font flaticon-copyright"> </i>
                          <span class="">Official phone :${scenic.officialPhone!""} </span></div>
                        <ul class="rlr-product-card__icon-text-list">
                          <li class="rlr-icon-text rlr-product-card__icon-text"><i class="rlr-icon-font flaticon-check"> </i> <span class="rlr-icon-text__title">New on Emprise </span></li>
                          <li class="rlr-icon-text rlr-product-card__icon-text"><i class="rlr-icon-font flaticon-check"> </i> <span class="rlr-icon-text__title">Free Cancellation </span></li>
                        </ul>
                      </div>
                    </div>
                  </article>
                </div>
              </#list>
              </#if>

            </div>
            <hr class="rlr-search-results-page__divider" />
            <nav class="rlr-pagination" aria-label="Product list navigation">
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
                         href="list?&currentPage=1" aria-label="Previous">
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
                          <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--counter" href="list?currentPage=${showPage}">${showPage}</a>
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
                      <path d="M4.167 10h11.666m0 0L10 4.167M15.833 10 10 15.833" stroke="var(--body-color)" stroke-width="1.67" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                  </a>
                </li>
                <#else>
                  <li class="page-item rlr-pagination__page-item">
                    <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--next"
                       href="list?currentPage=${pageBean.totalPage}" aria-label="Next">
                      <span aria-hidden="true">Next</span>
                      <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M4.167 10h11.666m0 0L10 4.167M15.833 10 10 15.833" stroke="var(--body-color)" stroke-width="1.67" stroke-linecap="round" stroke-linejoin="round" />
                      </svg>
                    </a>
                  </li>
                </#if>
              </ul>
              </#if>
            </nav>
          </div>
        </div>
      </div>
    </main>

  <#include "../common/footer.ftl"/>
  </body>
</html>
