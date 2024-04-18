<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="description" content="Your vacation, tours and travel theme needs are all met here." />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Tourist Attractions Platform | Home Page</title>

  <#include "../common/header_css.ftl"/>
</head>

<body class="rlr-body">

<#include "../common/header.ftl"/>
<!-- Main Content -->
<main id="rlr-main" class="rlr-main--fixed-top">
  <!-- Hero Banner -->
  <aside class="rlr-hero--half-mast">
    <div class="container">
      <div id="rlr_banner_slider" class="splide rlr-banner-splide rlr-banner-splide--v3">
        <div class="splide__track rlr-banner-splide__track">
          <ul class="splide__list">
            <!-- Banner slide 1 -->
            <li class="splide__slide rlr-banner-splide__slide">
              <div class="rlr-banner-splide__image-wrapper">
                <img class="rlr-banner-splide__banner-img lazyload" data-sizes="auto"
                     data-src="/home/assets/images/banner/banner01.jpg"
                     src="/home/assets/images/banner/banner01.jpg" alt="#" />
              </div>
              <article class="rlr-banner-splide__content-wrapper">
                <header class="rlr-banner-splide__header">
                  <h2 class="rlr-banner-splide__slogan">Next mountain to climb</h2>
                  <span class="rlr-banner-splide__sub-title">The Himalayan Mountain Ranges</span>
                </header>
                <div class="rlr-banner-splide__content-desc">
                  <div class="rlr-banner-splide__temperature">
                    <span>-02° C Very Cold</span>
                    <div class="rlr-banner-splide__arrows">
                      <button class="rlr-banner-splide__arrow rlr-banner-splide__arrow--prev rlr-banner-js-arrow-prev" aria-label="prev button">
                        <span> <i class="rlr-icon-font flaticon-left"> </i> </span>
                      </button>
                      <button class="rlr-banner-splide__arrow rlr-banner-splide__arrow--next rlr-banner-js-arrow-next" aria-label="next button">
                        <span> <i class="rlr-icon-font flaticon-right"> </i> </span>
                      </button>
                    </div>
                  </div>

                </div>
              </article>
            </li>
            <!-- Banner slide 2 -->
            <li class="splide__slide rlr-banner-splide__slide">
              <div class="rlr-banner-splide__image-wrapper">
                <img class="rlr-banner-splide__banner-img lazyload" data-sizes="auto"
                     data-src="/home/assets/images/banner/banner02.jpg"
                     src="/home/assets/images/banner/banner02.jpg" alt="#" />
              </div>
              <article class="rlr-banner-splide__content-wrapper">
                <header class="rlr-banner-splide__header">
                  <h2 class="rlr-banner-splide__slogan">Find your next adventure</h2>
                  <span class="rlr-banner-splide__sub-title">The Himalayan Mountain Ranges</span>
                </header>
                <div class="rlr-banner-splide__content-desc">
                  <div class="rlr-banner-splide__temperature">
                    <span>27° C Moderate</span>
                    <div class="rlr-banner-splide__arrows">
                      <button class="rlr-banner-splide__arrow rlr-banner-splide__arrow--prev rlr-banner-js-arrow-prev" aria-label="prev button">
                        <span> <i class="rlr-icon-font flaticon-left"> </i> </span>
                      </button>
                      <button class="rlr-banner-splide__arrow rlr-banner-splide__arrow--next rlr-banner-js-arrow-next" aria-label="next button">
                        <span> <i class="rlr-icon-font flaticon-right"> </i> </span>
                      </button>
                    </div>
                  </div>

                </div>
              </article>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </aside>

  <!-- Product Carousel -->
  <section class="rlr-section rlr-section__mb">
    <div class="container">
      <!-- Swiper -->
      <div class="rlr-carousel__items">
        <div class="swiper rlr-js-product-card-swiper">
          <!-- Carousel header -->
          <div class="rlr-section-header">
            <!-- Section heading -->
            <div class="rlr-section__title">
              <h2 class="rlr-section__title--main">Trending 2024</h2>
              <span class="rlr-section__title--sub">
                 Latest attractions!
              </span>
            </div>
            <div class="button-row">
              <button type="button" class="btn rlr-button button button--previous rlr-button--carousel" aria-label="Previous">
                <i class="rlr-icon-font flaticon-left-chevron"> </i>
              </button>
              <div class="button-group button-group--cells">
                <button class="button is-selected">1</button>
                <button class="button">2</button>
              </div>
              <button type="button" class="btn rlr-button button button--next rlr-button--carousel" aria-label="Next">
                <i class="rlr-icon-font flaticon-chevron"> </i>
              </button>
            </div>
          </div>
          <div class="swiper-wrapper">
            <#if scenics?? && scenics?size gt 0>
                <#list scenics as scenic>
                  <div class="swiper-slide">
                    <!-- Product card item -->
                    <article class="rlr-product-card rlr-product-card--v3">
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
                                  <img itemprop="image" data-sizes="auto" data-src="/photo/view?filename=${images!""}"
                                       data-srcset="/photo/view?filename=${images!""}"
                                       class="lazyload" alt="product-image" style="width: 348px;height: 300px;" />
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
                              <h2 class="rlr-product-card__title" itemprop="name">
                                ${scenic.title!""}
                              </h2>
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
                              <mark itemprop="priceCurrency">$</mark>
                              <mark itemprop="price">${scenic.price!"0"}</mark>
                            </span>
                          </div>
                          <div class="rlr-product-card__ratings" itemprop="aggregateRating">
                            <div class="rlr-review-stars" itemprop="ratingValue">
                              <#if scenic.rateScore!=0>
                                <#list 1..scenic.rateScore as x>
                                  <i class="rlr-icon-font flaticon-star-1"> </i>
                                </#list>
                              </#if>
                            </div>
                            <span class="rlr-product-card__rating-text" itemprop="reviewCount"> ${scenic.rateScore!"0"}(${scenic.rateNumber!"0"})</span>
                          </div>
                        </div>
                        <!-- Product card footer -->
                        <div class="rlr-product-card__footer">
                          <div class="rlr-icon-text rlr-product-card__icon-text"><i class="rlr-icon-font flaticon-three-o-clock-clock"> </i> <span class="">Official phone :${scenic.officialPhone!""} </span></div>
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
        </div>
      </div>
    </div>
  </section>

  <!-- Best Sellers -->
  <section class="rlr-section rlr-section__mb rlr-section__cards">
    <div class="container">
      <!-- Section heading -->
      <div class="rlr-section-header">
        <!-- Section heading -->
        <div class="rlr-section__title">
          <h2 class="rlr-section__title--main">Hotel Seller</h2>
          <span class="rlr-section__title--sub">
            Sost Brilliant reasons Hotel should be your hotel-seller!
          </span>
        </div>
        <div class="button-row">
          <a href="/home/hotel/list"
             class="btn rlr-button rlr-button--large rlr-button--rounded  rlr-button--brand ">
            Check All
          </a>
        </div>
      </div>
      <div class="row rlr-featured__cards">

        <#if hotelList?? && hotelList?size gt 0>
            <#list hotelList as hotel>
              <div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-offset="250"
                   data-aos-duration="700">
                <!-- Featured prodcut card -->
                <article class="rlr-product-card rlr-product-card--featured">
                  <!-- Image -->
                  <figure class="rlr-product-card__image-wrapper">
                    <img itemprop="image" data-src="/photo/view?filename=${hotel.coverPic!""}"
                         data-srcset="/photo/view?filename=${hotel.coverPic!""}"
                         data-sizes="auto" class="lazyload" style="width: 501px;height: 339px;"
                         alt="" />
                  </figure>
                  <!-- Card body -->
                  <div class="rlr-product-card--featured__inner">
                        <span class="rlr-badge rlr-badge--right rlr-badge--
                          rlr-badge--abs rlr-badge--abs-right">
                        ${hotel.rateScore!"0"}.0 (${hotel.rateNumber!"0"})
                        </span>
                    <div class="rlr-product-card--featured__body card-img-overlay">
                      <div class="rlr-product-card--featured__duration">
                        <p class="body">${hotel.address!""}</p>
                        <h4 class="type-h4">$${hotel.avgPrice!""}.00</h4>
                      </div>
                      <div class="rlr-product-detail-header__actions">

                        <a href="/home/hotel/detail?id=${hotel.id}"

                           class="btn rlr-button product-card-buttonrlr-button--medium rlr-button--rounded  rlr-button--brand ">
                          Detail
                        </a>            </div>
                    </div>
                  </div>
                  <a href="./product-detail-page.html"
                     class="rlr-product-card__anchor rlr-product-card__anchor--featured"></a>
                </article>
                <!-- Summary -->
                <div class="rlr-product-card--featured__summary">
                  <h4 class="type-h6-semi-bold">${hotel.name!""}</h4>
                  <p class="type-body">${hotel.service!""}</p>
                </div>
              </div>
            </#list>
        </#if>


      </div>
    </div>
  </section>


  <section class="rlr-section">
    <div class="container">
      <!-- Section heading -->
      <div class="rlr-section__title rlr-section__title--centered">
        <h2 class="rlr-section__title--main">Still have a question?</h2>    <span class="rlr-section__title--sub">Sost Brilliant reasons Emprise should be your one-stop-shop!</span></div>        <div class="row">
        <div class="offset-lg-1 col-lg-5 aos-init aos-animate" data-aos="fade-down-right" data-aos-duration="800" data-aos-once="true">
          <div class="rlr-support-card rlr-support-card--sale">
            <div class="rlr-support-card__content">
              <div class="rlr-support-card__img-wrapper">
                <img src="/home/assets/svg/headset.svg" alt="headset">
              </div>
              <h2 class="rlr-support-card__title">For Sales</h2>
              <p class="rlr-support-card__subtitle rlr-support-card__text"> The
                Brilliant reasons Emprise be your one-stop-shop! </p>
              <p class="rlr-support-card__text"> sales@emprise.tours </p>
              <p class="rlr-support-card__text"> +977(985) 110-88-96 </p>
            </div>
          </div>            </div>
        <div class="col-lg-5 aos-init aos-animate" data-aos="fade-up" data-aos-anchor-placement="bottom-bottom" data-aos-duration="1000" data-aos-once="true">
          <div class="rlr-support-card rlr-support-card--help">
            <div class="rlr-support-card__content">
              <div class="rlr-support-card__img-wrapper">
                <img src="/home/assets/svg/help-circle.svg" alt="headset">
              </div>
              <h2 class="rlr-support-card__title">Help &amp; Support</h2>
              <p class="rlr-support-card__subtitle rlr-support-card__text"> The
                Brilliant reasons Emprise be your one-stop-shop! </p>
              <p class="rlr-support-card__text"> sales@emprise.tours </p>
              <p class="rlr-support-card__text"> +977(985) 110-88-96 </p>
            </div>
          </div>            </div>
      </div>
    </div>
  </section>
</main>

<#include "../common/footer.ftl"/>
</body>
</html>
