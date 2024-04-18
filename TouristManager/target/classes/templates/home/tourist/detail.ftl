<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise."/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Tourist Attractions Platform| ${title!""}</title>

    <#include "../common/header_css.ftl"/>
    <link type="text/css" rel="stylesheet" href="/home/styles/calendar-pro.css">

</head>

<body class="rlr-body">

<#include "../common/header.ftl"/>
<!-- Main Content -->
<main id="rlr-main" class="rlr-main--fixed-top">
    <!-- Main Content -->
    <div class="container">
        <!-- Media Slider -->
        <aside class="row">
            <!-- Media main image carousel -->
            <div class="col-md-10 rlr-media">
                <div class="splide rlr-media--wrapper rlr-js-media">
                    <!-- Arrows -->
                    <div class="splide__arrows">
                        <button class="rlr-media__arrow splide__arrow splide__arrow--prev">
                            <svg width="10" height="16" viewBox="0 0 10 16" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M1.889 14.942 8.47 8.36 1.889 1.778" stroke="var(--white)" stroke-width="2"
                                      stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
                        </button>
                        <button class="rlr-media__arrow splide__arrow splide__arrow--next">
                            <svg width="10" height="16" viewBox="0 0 10 16" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M1.889 14.942 8.47 8.36 1.889 1.778" stroke="var(--white)" stroke-width="2"
                                      stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
                        </button>
                    </div>
                    <!-- Media main images -->
                    <div class="splide__track rlr-media__strack">
                        <ul id="image-preview" class="splide__list">
                            <#list scenic.images?split(";") as image>
                                <li class="splide__slide rlr-media__image-view">
                                    <img class="lazyload" data-src="/photo/view?filename=${image}"
                                         alt="media image" style="width:1301px;height: 466px;"/>
                                </li>
                            </#list>
                        </ul>
                    </div>
                    <!-- Media pagination counter -->
                    <div class="rlr-media__custom-pagination rlr-js-custom-pagination">
                        <svg width="18" height="14" viewBox="0 0 18 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path
                                    d="M1.2 0C.542 0 0 .558 0 1.235v11.53C0 13.442.542 14 1.2 14h15.6c.658 0 1.2-.558 1.2-1.235V1.235C18 .558 17.458 0 16.8 0H1.2zm0 .824h15.6c.228 0 .4.176.4.411v9.844l-3.506-3.95a.4.4 0 0 0-.588 0l-2.862 3.126L6.1 5.488a.4.4 0 0 0-.362-.135.4.4 0 0 0-.232.129L.8 10.687V1.235C.8 1 .972.823 1.2.823zm9.2 2.058c-.879 0-1.6.743-1.6 1.647 0 .905.721 1.647 1.6 1.647.879 0 1.6-.742 1.6-1.647 0-.904-.721-1.647-1.6-1.647zm0 .824c.447 0 .8.363.8.823 0 .46-.353.824-.8.824a.806.806 0 0 1-.8-.824c0-.46.353-.823.8-.823zm-4.606 2.67 5.912 6.8H1.2a.397.397 0 0 1-.4-.411v-.869l4.994-5.52zm7.6 1.64 3.806 4.285v.464a.397.397 0 0 1-.4.411h-4.019l-2-2.303 2.613-2.856z"
                                    fill="#212529"
                            ></path>
                        </svg>
                        <span class="rlr-media__page-counter rlr-js-page"> ${scenic.images?split(";")?size!"0"} </span>
                    </div>
                </div>
            </div>
            <!-- Media Thumbnails -->
            <div class="col-md-2 rlr-media">
                <!-- Media sidebar -->
                <div class="splide rlr-media--wrapper rlr-media--sidebar rlr-js-thumbnail-media">
                    <!-- Arrows -->
                    <div class="splide__arrows">
                        <button class="rlr-media__arrow splide__arrow splide__arrow--prev">
                            <svg width="10" height="16" viewBox="0 0 10 16" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M1.889 14.942 8.47 8.36 1.889 1.778" stroke="var(--white)" stroke-width="2"
                                      stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
                        </button>
                        <button class="rlr-media__arrow splide__arrow splide__arrow--next">
                            <svg width="10" height="16" viewBox="0 0 10 16" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M1.889 14.942 8.47 8.36 1.889 1.778" stroke="var(--white)" stroke-width="2"
                                      stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
                        </button>
                    </div>
                    <!-- Thumbnails -->
                    <div class="splide__track rlr-media__strack">
                        <ul id="image-preview-thumb" class="splide__list">
                            <#list scenic.images?split(";") as image>
                                <li class="splide__slide rlr-media__image-view">
                                    <img class="rlr-media__thumb lazyload"
                                         data-src="/photo/view?filename=${image}" style="width: 235px;height: 109px;"
                                         alt="media image"/>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </aside>
        <!-- Product Detail Sextion -->
        <section class="row rlr-product-detail-section">
            <div class="rlr-product-detail-section__details">
                <!-- Product Detail Header -->
                <div class="rlr-product-detail-header" id="rlr-js-detail-header">
                    <div class="rlr-product-detail-header__contents">
                        <!-- Breadcrumb -->
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb rlr-breadcrumb__items">
                                <li class="breadcrumb-item rlr-breadcrumb__item"><a href="/home/index/index">Home</a>
                                </li>
                                <li class="breadcrumb-item rlr-breadcrumb__item active" aria-current="page">Tour</li>
                            </ol>
                        </nav>
                        <h1 class="rlr-section__heading--main rlr-product-detail-header__title">${scenic.title!""}</h1>
                        <div class="rlr-review-stars" itemscope itemtype="https://schema.org/Product">
                            <div class="rlr-review-stars" itemprop="ratingValue" itemscope
                                 itemtype="https://schema.org/Product">
                            </div>
                            <div class="rlr-review-stars__content">
                                <span class="rlr-review-stars__count">${scenic.viewNumber!"0"}</span>
                                <span> Views</span>
                                <span class="rlr-review-stars__count">${commentList?size!"0"}</span>
                                <span> Reviews</span>
                            </div>
                        </div>
                    </div>
                    <div class="rlr-product-detail-header__actions">


                        <div class="rlr-product-detail-header__button-wrapper">
                            <button type="button" data-id="${scenic.id}"
                                    class="btn rlr-button rlr-button--circle rlr-wishlist
                                    rlr-wishlist-button rlr-js-action-wishlist add-favorites-btn <#if favorites??>is-active</#if>"
                                    aria-label="Save to Wishlist">
                                <i class="rlr-icon-font flaticon-heart-1"> </i>
                            </button>
                            <span class="rlr-product-detail-header__helptext rlr-js-helptext"></span>
                        </div>
                    </div>
                </div>
                <!-- Secondary Menu -->
                <nav class="rlr-product-detail-secondary-menu">
                    <ul class="rlr-product-detail-secondary-menu__tabitems" id="rlr-js-secondary-menu">
                        <li class="rlr-product-detail-secondary-menu__tabitem js-tabitem is-active"
                            id="rlr-product-sec-overview"><span>Overview</span></li>
                        <li class="rlr-product-detail-secondary-menu__tabitem js-tabitem"
                            id="rlr-product-sec-itinerary"><span>Itinerary</span></li>
                        <li class="rlr-product-detail-secondary-menu__tabitem js-tabitem"
                            id="rlr-product-sec-inclusion"><span>Inclusions</span></li>
                        <li class="rlr-product-detail-secondary-menu__tabitem js-tabitem" id="rlr-product-sec-review">
                            <span>Reviews</span></li>
                    </ul>
                </nav>
                <!-- Overview -->
                <div class="rlr-secondary-menu-desc" data-id="rlr-product-sec-overview">
                    <div class="rlr-secondary-menu-desc__icon">
                        <svg width="41" height="51" viewBox="0 0 41 51" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M40.327 13.61H28.296c-.334 0-.558-.221-.558-.55l.002-11.852c0-.329.224-.55.558-.55.334 0 .558.221.558.55l-.002 11.304h11.473c.334 0 .558.22.558.55 0 .33-.224.547-.558.547z"
                                  fill="#99A3AD"/>
                            <path
                                    d="M36.54 50.707H4.568C2.005 50.707 0 48.73 0 46.207L.002 5.047c0-2.525 2.005-4.5 4.568-4.5h23.728c.11 0 .334.109.445.109L40.885 12.51c.11.11.11.22.11.439v33.255c.113 2.527-1.892 4.503-4.455 4.503zM4.568 1.756c-1.892 0-3.342 1.428-3.342 3.292v41.158c0 1.867 1.56 3.402 3.453 3.402H36.65c1.894 0 3.453-1.537 3.453-3.402l.002-32.926-11.92-11.524H4.567z"
                                    fill="#99A3AD"
                            />
                            <path
                                    d="M33.309 19.756h-19.27c-.335 0-.558-.22-.558-.55 0-.329.223-.549.557-.549h19.273c.334 0 .558.22.558.55-.002.329-.226.55-.56.55zM33.309 25.133H7.91c-.334 0-.558-.22-.558-.55 0-.328.224-.549.558-.549h25.399c.334 0 .558.22.558.55 0 .331-.224.55-.558.55zM33.309 30.622H7.91c-.334 0-.558-.22-.558-.55 0-.329.224-.55.558-.55h25.399c.334 0 .558.221.558.55 0 .33-.224.55-.558.55zM33.309 36.11H7.91c-.334 0-.558-.22-.558-.55 0-.329.224-.549.558-.549h25.399c.334 0 .558.22.558.55 0 .329-.224.55-.558.55zM33.309 41.487H7.91c-.334 0-.558-.22-.558-.549 0-.33.224-.55.558-.55h25.399c.334 0 .558.22.558.55 0 .33-.224.55-.558.55z"
                                    fill="#99A3AD"
                            />
                        </svg>
                    </div>
                    <div class="rlr-secondary-menu-desc__details">
                        <div class="rlr-overview-detail">

                            <div class="rlr-overview-detail__icon-groupset">
                                <div class="rlr-overview-detail__icon-group">
                                    <span class="rlr-overview-detail__label">OpenTime</span>
                                    <div class="rlr-overview-detail__icons">
                                        <figure class="rlr-icon-text"><i
                                                    class="rlr-icon-font flaticon-three-o-clock-clock"> </i>
                                            <span class="">${scenic.openTime}</span></figure>
                                    </div>
                                </div>

                                <div class="rlr-overview-detail__icon-group">
                                    <span class="rlr-overview-detail__label">Location</span>
                                    <div class="rlr-overview-detail__icons">
                                        <figure class="rlr-icon-text"><i class="rlr-icon-font flaticon-map-marker"> </i>
                                            <span class="">${scenic.address!""}</span></figure>
                                    </div>
                                </div>
                                <div class="rlr-overview-detail__icon-group">
                                    <span class="rlr-overview-detail__label">Phone</span>
                                    <div class="rlr-overview-detail__icons">
                                        <figure class="rlr-icon-text"><i class="rlr-icon-font flaticon-telephone"> </i>
                                            <span class="">${scenic.officialPhone!""}</span></figure>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="rlr-readmore-desc rlr-overview-detail__description">
                            <p class="rlr-readmore-desc__content rlr-js-desc">
                                ${scenic.content!""}
                            </p>
                            <span class="rlr-readmore-desc__readmore rlr-js-readmore"></span>
                        </div>
                    </div>
                </div>
                <!-- Itinerary -->
                <div class="rlr-secondary-menu-desc" data-id="rlr-product-sec-itinerary">
                    <!-- Icon -->
                    <div class="rlr-secondary-menu-desc__icon">
                        <svg width="43" height="38" viewBox="0 0 43 38" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path
                                    d="M43 .442c0-.016-.008-.03-.01-.047-.002-.015.003-.031 0-.047-.003-.014-.013-.024-.018-.037a.372.372 0 0 0-.035-.082.41.41 0 0 0-.177-.172.409.409 0 0 0-.08-.031.397.397 0 0 0-.085-.017C42.581.007 42.568 0 42.553 0c-.018 0-.032.008-.049.01-.016.002-.031-.003-.047 0L28.519 3.04 14.577.01h-.007c-.015-.003-.033 0-.05-.002-.013 0-.024-.008-.039-.008-.013 0-.025.006-.037.008-.018 0-.037 0-.054.003h-.004L.35 3.06c-.013.003-.024.013-.037.017a.44.44 0 0 0-.152.082.389.389 0 0 0-.059.062.438.438 0 0 0-.046.068.459.459 0 0 0-.049.165c0 .014-.008.025-.008.04v34.063c0 .017.008.03.01.047s-.001.03.002.047c.004.016.013.028.018.043a.377.377 0 0 0 .08.141c.014.017.026.033.042.047.026.023.056.04.087.058.017.009.031.02.049.026a.448.448 0 0 0 .255.022l13.94-3.03 13.94 3.03c.032.008.064.011.095.011.005 0 .01-.003.014-.003a.432.432 0 0 0 .082-.008l14.036-3.05c.014-.002.024-.012.037-.017a.47.47 0 0 0 .152-.082.44.44 0 0 0 .056-.06.43.43 0 0 0 .049-.07.432.432 0 0 0 .03-.075.374.374 0 0 0 .018-.089c0-.014.008-.025.008-.04V.443zM.894 3.85 14.037.993v16.584a.446.446 0 0 0-.272.136.44.44 0 0 0 .032.625c.082.073.158.151.24.225v15.589L.894 37.008V3.85zM14.93 19.413c.275.273.544.544.785.792a.448.448 0 0 0 .63.011.44.44 0 0 0 .012-.625 48.965 48.965 0 0 0-1.427-1.407V.993l13.144 2.856v21.217c-.259.11-.517.208-.776.284a.44.44 0 0 0 .125.866.446.446 0 0 0 .126-.019c.174-.051.35-.113.525-.178v10.988l-13.144-2.856V19.413zm14.036 6.215a9.751 9.751 0 0 0 1.137-.678.44.44 0 0 0 .11-.616.448.448 0 0 0-.62-.11 9.369 9.369 0 0 1-.627.398V3.85L42.108.993v33.159l-13.143 2.855V25.628z"
                                    fill="#99A3AD"
                            />
                            <path
                                    d="M5.452 26.484a26.968 26.968 0 0 1-.167-.966.445.445 0 0 0-.508-.37.442.442 0 0 0-.373.504c.055.352.105.634.147.857a2.365 2.365 0 0 0-1.848 2.297 2.37 2.37 0 0 0 2.378 2.358 2.37 2.37 0 0 0 2.378-2.358c-.001-1.174-.873-2.143-2.007-2.322zM5.08 30.28c-.82 0-1.487-.661-1.487-1.474S4.26 27.33 5.08 27.33c.82 0 1.486.662 1.486 1.475 0 .813-.667 1.474-1.486 1.474zM38.218 6.8l1.051-1.043a.44.44 0 0 0 0-.625.449.449 0 0 0-.63 0l-1.051 1.043-1.05-1.043a.449.449 0 0 0-.631 0 .44.44 0 0 0 0 .625L36.958 6.8l-1.051 1.043a.44.44 0 0 0 .316.755.45.45 0 0 0 .315-.13l.66-.656c.005.106.01.204.013.323.006.24.204.432.445.432h.012a.444.444 0 0 0 .434-.453l-.006-.185.542.539a.45.45 0 0 0 .631 0 .44.44 0 0 0 0-.626L38.22 6.8zM12.178 15.992c-.9-.55-1.764-.9-2.644-1.073a.441.441 0 1 0-.173.867c.773.152 1.542.466 2.35.959a.448.448 0 0 0 .613-.146.44.44 0 0 0-.146-.607zM4.551 23.348l.028-.001a.445.445 0 0 0 .418-.468 21.743 21.743 0 0 1-.042-1.335c0-.433.014-.869.048-1.3a.443.443 0 0 0-.41-.475c-.244-.032-.46.164-.479.406-.036.453-.05.913-.05 1.369 0 .47.015.938.043 1.388.014.235.21.416.444.416zM4.94 17.99a.447.447 0 0 0 .577-.254c.34-.877.834-1.479 1.47-1.788a.44.44 0 0 0 .202-.592.448.448 0 0 0-.596-.202c-.844.411-1.486 1.174-1.908 2.265a.439.439 0 0 0 .255.57zM18.24 21.53a.45.45 0 0 0-.63-.004.44.44 0 0 0-.003.626 27.948 27.948 0 0 0 2.017 1.868.444.444 0 0 0 .628-.057.44.44 0 0 0-.057-.623 27.215 27.215 0 0 1-1.954-1.81zM37.629 10.418a.443.443 0 0 0-.478.41c-.07.942-.19 1.822-.357 2.612a.442.442 0 0 0 .438.533c.206 0 .39-.143.435-.351.175-.828.301-1.746.374-2.729a.446.446 0 0 0-.412-.475zM36.584 15.69a.447.447 0 0 0-.594.209c-.051.105-.104.209-.16.311-.306.561-.686 1.257-1.138 2.015a.44.44 0 0 0 .157.606.45.45 0 0 0 .611-.155c.459-.77.845-1.476 1.155-2.045.062-.115.123-.232.18-.35a.44.44 0 0 0-.211-.59zM33.236 20.46a20.156 20.156 0 0 1-1.677 2.046.441.441 0 0 0 .326.745.446.446 0 0 0 .325-.14c.585-.62 1.174-1.339 1.75-2.137a.44.44 0 0 0-.102-.616.446.446 0 0 0-.622.101zM24.791 25.553a7.526 7.526 0 0 1-2.417-.754.446.446 0 0 0-.631.222.44.44 0 0 0 .21.558l.017.01c.904.448 1.816.731 2.712.842a.445.445 0 0 0 .498-.384.443.443 0 0 0-.389-.494z"
                                    fill="#99A3AD"
                            />
                        </svg>
                    </div>
                    <div class="rlr-secondary-menu-desc__details">
                        <!-- Itinerary item -->
                        <div class="accordion rlr-accordion">
                            <#if scenicPlanList?? && scenicPlanList?size gt 0>
                                <#list scenicPlanList as plan>
                                    <div class="accordion-item rlr-accordion__item">
                                        <div class="accordion-header rlr-accordion__header"
                                             id="rlr-itinerary-header${plan_index+1}">
                                            <button class="accordion-button rlr-accordion__button"
                                                    type="button"
                                                    data-bs-toggle="collapse"
                                                    data-bs-target="#rlr-itinerary-collapse${plan_index+1}"
                                                    <#if plan_index==0>
                                                        aria-expanded="true"
                                                    <#else>
                                                        aria-expanded="false"
                                                    </#if>
                                                    aria-controls="rlr-itinerary-collapse${plan_index+1}">
                                                <span class="rlr-accordion__badge">${plan_index+1}</span>
                                                ${plan.name}
                                            </button>
                                        </div>
                                        <div id="rlr-itinerary-collapse${plan_index+1}"
                                             class="accordion-collapse collapse show"
                                             aria-labelledby="rlr-itinerary-header${plan_index+1}">
                                            <div class="accordion-body rlr-accordion__body">
                                                <h3 class="rlr-itinerary__title">${plan.intro!""}</h3>
                                                <div class="rlr-readmore-desc">
                                                    <p class="rlr-readmore-desc__content rlr-js-desc">
                                                        ${plan.content!""}
                                                    </p>
                                                </div>
                                                <div class="rlr-itinerary__media-group">
                                                    <#if plan.images?? && plan.images?length gt 0>
                                                        <#list plan.images?split(";") as image>
                                                            <div class="rlr-itinerary__media">
                                                                <a data-fslightbox="demo-images" class="images_gallery" data-foo="bar"
                                                                   href="javascript:void(0);" data-url="${image}">
                                                                    <figure class="rlr-lightbox--gallery__figure">
                                                                        <img class="rlr-lightbox--gallery__img"
                                                                             src="/photo/view?filename=${image!""}"
                                                                             alt="Anchor Button"/>
                                                                        <figcaption class="rlr-lightbox--gallery__figcaption">
                                                                            <span>${image_index+1!""}</span>
                                                                        </figcaption>
                                                                    </figure>
                                                                </a>
                                                            </div>
                                                        </#list>
                                                    </#if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </#list>
                            </#if>

                        </div>

                    </div>
                </div>
                <!-- Inclusion and Exclusions -->
                <div class="rlr-secondary-menu-desc" data-id="rlr-product-sec-inclusion">
                    <!-- Icon -->
                    <div class="rlr-secondary-menu-desc__icon">
                        <svg width="50" height="56" viewBox="0 0 50 56" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path
                                    d="M25.0157 0.127686H24.9679C21.6869 0.129864 18.4385 0.787955 15.4081 2.06438C12.3776 3.34081 9.62461 5.21058 7.30612 7.56694C4.98764 9.92329 3.14911 12.7201 1.89553 15.7976C0.641942 18.8752 -0.00215716 22.1732 5.42768e-06 25.5034C0.000252218 25.6667 0.0590139 25.8244 0.165333 25.9469C0.271652 26.0695 0.41827 26.1487 0.577845 26.1696C0.737421 26.1906 0.89906 26.1519 1.03262 26.0609C1.16618 25.9698 1.26255 25.8325 1.30374 25.6746C1.47986 24.998 2.10654 23.0634 2.89022 22.2039C3.99055 21.1594 5.33307 20.6056 6.77668 20.6172C8.20636 20.6136 9.57983 21.182 10.5995 22.1992C11.6258 23.3104 12.1684 24.6663 12.1684 26.1199C12.1684 26.2984 12.2382 26.4695 12.3626 26.5957C12.4869 26.7219 12.6555 26.7928 12.8313 26.7928C13.0071 26.7928 13.1758 26.7219 13.3001 26.5957C13.4244 26.4695 13.4942 26.2984 13.4942 26.1199C13.4942 24.6681 14.035 23.3147 15.058 22.2046C16.1518 21.1661 17.4853 20.6173 18.9187 20.6173C19.6312 20.6115 20.3379 20.7482 20.9984 21.0196C21.6589 21.2911 22.2602 21.6919 22.768 22.1993C23.7943 23.3105 24.3369 24.6665 24.3369 26.1199V44.326C24.3369 45.5699 23.8501 46.7629 22.9835 47.6424C22.1169 48.522 20.9416 49.0162 19.7161 49.0162C18.4905 49.0162 17.3152 48.522 16.4486 47.6424C15.582 46.7629 15.0952 45.5699 15.0952 44.326V42.1116C15.0952 41.9331 15.0253 41.762 14.901 41.6358C14.7767 41.5096 14.6081 41.4387 14.4323 41.4387C14.2565 41.4387 14.0878 41.5096 13.9635 41.6358C13.8392 41.762 13.7694 41.9331 13.7694 42.1116V44.326C13.7694 45.9268 14.3959 47.4621 15.5111 48.594C16.6263 49.7259 18.1389 50.3619 19.7161 50.3619C21.2932 50.3619 22.8058 49.7259 23.921 48.594C25.0362 47.4621 25.6628 45.9268 25.6628 44.326V26.1199C25.6628 24.6679 26.2034 23.3148 27.2265 22.2051C28.3203 21.1667 29.6536 20.6179 31.0872 20.6179H31.1138C32.5435 20.6143 33.917 21.1828 34.9366 22.2C35.9629 23.3111 36.5055 24.6665 36.5055 26.1206C36.5055 26.2991 36.5754 26.4702 36.6997 26.5964C36.824 26.7226 36.9926 26.7935 37.1685 26.7935C37.3443 26.7935 37.5129 26.7226 37.6372 26.5964C37.7615 26.4702 37.8314 26.2991 37.8314 26.1206C37.8314 24.6689 38.372 23.3155 39.3952 22.2052C40.489 21.1667 41.8224 20.618 43.2557 20.618H43.2824C44.7121 20.6144 46.0856 21.1827 47.1053 22.1999C47.8914 23.0567 48.5195 24.9971 48.6963 25.6745C48.7375 25.8324 48.8338 25.9697 48.9674 26.0608C49.101 26.1518 49.2626 26.1905 49.4222 26.1695C49.5818 26.1486 49.7284 26.0694 49.8347 25.9468C49.941 25.8242 49.9998 25.6665 50 25.5032V25.4707C49.9958 18.7479 47.3617 12.3019 42.6767 7.5496C37.9917 2.79732 31.6392 0.127744 25.0157 0.127686ZM48.0616 21.268C48.0558 21.2616 48.0497 21.2553 48.0435 21.2491C46.7765 19.9788 45.0662 19.2682 43.2853 19.272H43.2522C41.4716 19.272 39.8184 19.9522 38.4714 21.239C38.4617 21.2483 38.4522 21.258 38.443 21.2678C37.9254 21.8213 37.4952 22.4528 37.168 23.1398C36.841 22.4529 36.4112 21.8214 35.8938 21.268C35.8878 21.2616 35.8817 21.2552 35.8757 21.2491C34.6086 19.9788 32.8983 19.2682 31.1173 19.272H31.0842C29.3035 19.272 27.6503 19.9522 26.3035 21.239C26.2937 21.2483 26.2843 21.2579 26.2752 21.2677C25.7574 21.8211 25.3272 22.4525 24.9998 23.1395C24.6726 22.4525 24.2425 21.821 23.7248 21.2675C23.7189 21.2611 23.7129 21.2548 23.7067 21.2487C22.4397 19.9784 20.7294 19.2678 18.9485 19.2716H18.9154C17.1347 19.2716 15.4816 19.9517 14.1344 21.2386C14.1247 21.2479 14.1152 21.2575 14.106 21.2674C13.5885 21.8209 13.1586 22.4525 12.8314 23.1395C12.5042 22.4524 12.0741 21.821 11.5564 21.2675C11.5505 21.2611 11.5445 21.2548 11.5383 21.2487C10.2714 19.9785 8.56139 19.2678 6.78066 19.2715H6.74751C4.9668 19.2715 3.3136 19.9516 1.96655 21.2385C1.95683 21.2478 1.94733 21.2574 1.93816 21.2674C1.82613 21.3891 1.72132 21.5174 1.62426 21.6517C2.52389 16.0239 5.36592 10.9042 9.64244 7.20767C13.9189 3.51112 19.3512 1.47861 24.9683 1.47341H24.9998C25.0051 1.47273 25.0105 1.47273 25.0157 1.47341C30.6357 1.47473 36.0719 3.50557 40.3517 7.20259C44.6315 10.8996 47.4758 16.0216 48.3756 21.6523C48.2785 21.518 48.1737 21.3896 48.0616 21.268Z"
                                    fill="#99A3AD"
                            />
                            <path
                                    d="M49.6287 34.0793L39.8939 29.2311C39.8032 29.1859 39.7034 29.1624 39.6023 29.1624C39.5011 29.1624 39.4014 29.1859 39.3106 29.2311L29.5758 34.0655C29.4644 34.1209 29.3705 34.2069 29.3048 34.3137C29.2391 34.4206 29.2043 34.544 29.2043 34.67V43.919C29.2043 44.9461 29.4805 46.0925 30.0031 47.2341C30.6726 48.6962 31.6698 49.9592 32.739 50.6977L39.2287 55.1857C39.3387 55.2618 39.4688 55.3025 39.6019 55.3025C39.7351 55.3025 39.8651 55.2618 39.9751 55.1857L46.465 50.6976C48.447 49.3275 49.9997 46.3499 49.9997 43.9189V34.6834C49.9997 34.5576 49.9649 34.4343 49.8993 34.3275C49.8337 34.2207 49.74 34.1347 49.6287 34.0793ZM48.6738 43.9187C48.6738 45.8829 47.3205 48.4779 45.7186 49.5853L39.6019 53.8151L33.4852 49.5853C32.6078 48.9787 31.777 47.915 31.2054 46.6666C30.7699 45.7153 30.53 44.7394 30.53 43.9191V35.0895L39.6009 30.5845L48.6733 35.1027L48.6738 43.9187Z"
                                    fill="#99A3AD"
                            />
                            <path
                                    d="M35.9457 41.4619C35.8196 41.3385 35.6505 41.2707 35.4754 41.2734C35.3002 41.2761 35.1332 41.349 35.0109 41.4763C34.8885 41.6036 34.8208 41.7749 34.8225 41.9527C34.8242 42.1305 34.8952 42.3004 35.0199 42.4253L37.9605 45.3362C38.0844 45.4587 38.2505 45.5274 38.4234 45.5274C38.5964 45.5274 38.7625 45.4587 38.8863 45.3362L44.1839 40.0917C44.3086 39.9668 44.3796 39.7969 44.3813 39.6191C44.383 39.4413 44.3153 39.27 44.193 39.1427C44.0706 39.0154 43.9036 38.9425 43.7285 38.9398C43.5533 38.9371 43.3842 39.0049 43.2581 39.1284L38.4239 43.9146L35.9457 41.4619Z"
                                    fill="#99A3AD"
                            />
                        </svg>
                    </div>
                    <!-- Overview -->
                    <div class="rlr-secondary-menu-desc__details">
                        <div class="rlr-readmore-desc">
                            <#if ylrc_home??>
                                <div class="calendar-box demo-box">

                                </div>
                                <#else>
                                <div><h2>Please log in to view booking information</h2></div>
                            </#if>

                        </div>

                    </div>
                </div>
                <!-- Reviews -->
                <div class="rlr-secondary-menu-desc" data-id="rlr-product-sec-review">
                    <!-- Icon -->
                    <div class="rlr-secondary-menu-desc__icon">
                        <svg width="51" height="52" viewBox="0 0 51 52" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path
                                    d="M51 26.0569C51 11.9289 39.5833 0.47583 25.5 0.47583C11.4167 0.47583 0 11.9289 0 26.0569C0 40.1849 11.4167 51.6379 25.5 51.6379C29.9108 51.6379 34.1604 50.5124 37.9266 48.4C37.9928 48.4321 38.0883 48.4895 38.2244 48.5807C38.2823 48.6195 38.741 48.9376 38.907 49.0479C39.2393 49.2688 39.5554 49.4601 39.9016 49.6434C42.0271 50.7687 44.7903 51.3004 48.7583 51.0338C49.483 50.9851 49.7938 50.0869 49.2549 49.5983C47.8766 48.3484 46.4596 46.762 45.4972 45.3922C45.0172 44.7088 44.6728 44.1109 44.498 43.6599C44.43 43.4842 44.3928 43.3435 44.383 43.2488C48.6135 38.5755 51 32.502 51 26.0569ZM40.6695 48.1838C40.3757 48.0282 40.1053 47.8646 39.8158 47.6722C39.6669 47.5732 39.2115 47.2574 39.1383 47.2083C38.4835 46.7696 38.0509 46.609 37.4761 46.7944C37.3816 46.8249 37.2904 46.8654 37.2024 46.9152C33.6605 48.9194 29.6586 49.9875 25.5 49.9875C12.3253 49.9875 1.64516 39.2734 1.64516 26.0569C1.64516 12.8403 12.3253 2.12622 25.5 2.12622C38.6747 2.12622 49.3548 12.8403 49.3548 26.0569C49.3548 32.125 47.0956 37.837 43.0914 42.2203C41.9783 43.4387 43.8851 46.5383 46.7284 49.4515C44.0844 49.4414 42.173 48.9798 40.6695 48.1838Z"
                                    fill="#99A3AD"
                            />
                            <path
                                    d="M31.9727 35.6478L25.6956 31.2659C25.4132 31.0688 25.0384 31.0688 24.756 31.2659L18.4789 35.6478L20.6934 28.3049C20.793 27.9745 20.6771 27.6169 20.403 27.4084L14.3091 22.7736L21.9548 22.6173C22.2987 22.6103 22.602 22.3892 22.715 22.0632L25.2258 14.8169L27.7366 22.0632C27.8496 22.3892 28.1529 22.6103 28.4969 22.6173L36.1425 22.7736L30.0486 27.4084C29.7745 27.6169 29.6586 27.9745 29.7582 28.3049L31.9727 35.6478ZM25.2258 32.9486L32.9755 38.3584C33.6156 38.8053 34.4585 38.191 34.2327 37.4421L31.4987 28.3766L39.0222 22.6545C39.6437 22.1819 39.3218 21.1879 38.542 21.1719L29.1027 20.979L26.0028 12.0327C25.7467 11.2937 24.7049 11.2937 24.4488 12.0327L21.349 20.979L11.9096 21.1719C11.1299 21.1879 10.8079 22.1819 11.4294 22.6545L18.9529 28.3766L16.219 37.4421C15.9931 38.191 16.836 38.8053 17.4761 38.3584L25.2258 32.9486Z"
                                    fill="#99A3AD"
                            />
                        </svg>
                    </div>
                    <div class="rlr-secondary-menu-desc__details">

                        <#if commentList?? && commentList?size gt 0>
                            <#list commentList as comment>
                                <!-- Review -->
                                <article class="rlr-review-card" itemscope itemtype="https://schema.org/Product">
                                    <div class="rlr-review-card__contact">
                                        <!--Using in Components -->
                                        <div class="rlr-avatar">
                                            <#if comment.account.headPic?? && comment.account.headPic?length gt 0>
                                                <img class="rlr-avatar__media--rounded" style="height: 56px;width: 56px;"
                                                     src="/photo/view?filename=${comment.account.headPic}" itemprop="avatar"
                                                     alt="avatar icon"/>
                                                <#else>
                                                    <img class="rlr-avatar__media--rounded" style="height: 56px;width: 56px;"
                                                         src="/home/assets/images/misc/image-1_56x56.jpg" itemprop="avatar"
                                                         alt="avatar icon"/>
                                            </#if>

                                            <span class="rlr-avatar__name" itemprop="name">${comment.account.username!""}</span>
                                        </div>

                                        <div class="rlr-review-stars" itemprop="ratingValue" itemscope
                                             itemtype="https://schema.org/Product">
                                            <#list 1..comment.score as x>
                                                <i class="rlr-icon-font flaticon-star-1"> </i>
                                            </#list>
                                        </div>
                                    </div>
                                    <div class="rlr-review-card__details">
                                        <div class="rlr-review-card__title">
                                            <h3 class="rlr-review-card__title-review">“${comment.content!""}”</h3>
                                            <span class="rlr-review-card__review-date" itemprop="review date">Reviewed ${comment.createTime!""}</span>
                                        </div>
                                        <#if ylrc_home??>

                                            <button class="btn btn-primary reply-comment-btn" data-id="${comment.id}" style="float:right">Reply</button>
                                        </#if>
                                    </div>
                                    <#if comment.commentReplies?? && comment.commentReplies?size gt 0>
                                        <#list comment.commentReplies as reply>
                                            <article class="rlr-review-card" itemscope="" itemtype="https://schema.org/Product" style="margin-left: 100px;">
                                                <div class="rlr-review-card__contact">
                                                    <!--Using in Components -->
                                                    <div class="rlr-avatar">
                                                        <#if reply.replyAccount.headPic?? && reply.replyAccount.headPic?length gt 0>
                                                            <img class="rlr-avatar__media--rounded" style="height: 56px;width: 56px;"
                                                                 src="/photo/view?filename=${reply.replyAccount.headPic}" itemprop="avatar"
                                                                 alt="avatar icon"/>
                                                        <#else>
                                                            <img class="rlr-avatar__media--rounded" style="height: 56px;width: 56px;"
                                                                 src="/home/assets/images/misc/image-1_56x56.jpg" itemprop="avatar"
                                                                 alt="avatar icon"/>
                                                        </#if>

                                                        <span class="rlr-avatar__name" itemprop="name">
                                                            <#if reply.account??>
                                                                ${reply.replyAccount.username!""}->reply:${reply.account.username}
                                                                <#else>
                                                               ${reply.replyAccount.username!""}
                                                            </#if>
                                                        </span>
                                                    </div>

                                                </div>
                                                <div class="rlr-review-card__details">
                                                    <div class="rlr-review-card__title">
                                                        <h3 class="rlr-review-card__title-review">“${reply.content!""}”</h3>
                                                        <span class="rlr-review-card__review-date" itemprop="review date">
                                                            Reviewed ${reply.createTime!""}
                                                        </span>
                                                    </div>
                                                    <#if ylrc_home??>
                                                        <#if ylrc_home.id!=reply.replyAccount.id>
                                                            <button class="btn btn-primary reply-comment-btn" data-id="${comment.id}"
                                                                    data-accountId="${reply.replyAccount.id!""}"
                                                                    style="float:right">Reply</button>
                                                        </#if>

                                                    </#if>
                                                </div>
                                            </article>
                                        </#list>

                                    </#if>

                                </article>
                            </#list>

                            <#else>
                                No comments
                        </#if>



                    </div>
                </div>

            </div>

        </section>

    </div>
</main>


<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/home/js/calendar-pro.js"></script>
<script src="/home/js/layer.js" ></script>

<script type="text/javascript">
    $(".rlr-accordion__button").click(function () {

        if ($(this).parent().next().hasClass("show")) {
            $(this).parent().next().removeClass("show");
            $(this).addClass("collapsed");
        } else {
            $(this).parent().next().addClass("show");
            $(this).removeClass("collapsed");
        }
        /* $(this).parent().parent().siblings().find(".accordion-collapse").removeClass("show");
         */
    });
    var sale =${scenicPriceList!""};
    $('.calendar-box').calendar({
        ele: '.demo-box', //依附
        title: 'Select appointment time',
        data: sale
    });
    //日期选择后
    function getActive() {

        var data = $('.calendar-box').calendarGetActive();
        $.confirm({
            title: 'Booking information',
            type: 'green',
            content: '' +
                '<form action="" class="formName"><input type="hidden"  value='+data.id+' name="id" id="scenic-price-id" /> ' +
                '<div class="form-group">' +
                '<label>belonging date</label>' +
                '<input type="text" value='+data.date+' readonly class="name form-control"  />' +
                '</div>' +
                '<div class="form-group">' +
                '<label>price</label>' +
                '<input type="text" value='+data.money+' readonly class="name form-control"  />' +
                '</div>' +
                '<div class="form-group">' +
                '<label>inventory</label>' +
                '<input type="text" value='+data.inventory+' readonly class="name form-control" id="buy-inventory" />' +
                '</div>' +
                '<div class="form-group">' +
                '<label>single purchase limit</label>' +
                '<input type="text" value='+data.psid+' readonly class="name form-control" id="buy-limit"   />' +
                '</div>' +
                '<div class="form-group">' +
                '<label>number of sheets purchased</label>' +
                '<input type="number" value="1" id="buy-num" oninput="if(value>10)value=10;if (value<0)value=0" class="name form-control"  />' +
                '</div>' +
                '</form>',
            buttons: {
                formSubmit: {
                    text: 'buy',
                    btnClass: 'btn-blue',
                    action: function () {
                        var number = this.$content.find('#buy-num').val();
                        if(number=="" || number==null){
                            $.alert('Please enter the number of tickets purchased');
                            return false;
                        }
                        var limitNumber = this.$content.find('#buy-limit').val();
                        if(limitNumber=="" || limitNumber==null){
                            $.alert('Single purchase limit cannot be empty');
                            return false;
                        }
                        if(parseInt(number)>parseInt(limitNumber)){
                            $.alert("The purchase quantity exceeds the single purchase limit");
                            return false;
                        }
                        var inventory = this.$content.find('#buy-inventory').val();
                        if(number>inventory){
                            $.alert("There are not enough tickets left on the day!");
                            return false;
                        }
                        var id=this.$content.find('#scenic-price-id').val();
                        ajaxRequest('/home/tourist/appoint_scenic','POST',{count:number,pid:id},function (result) {
                            showSuccessMsg("The purchase is successful, please go to the personal center to view my order.",function () {
                                window.location.reload();
                            })
                        })

                    }
                },
                cancel: {
                    text: 'cancel'
                },
            },
            onContentReady: function () {
                var jc = this;
                this.$content.find('form').on('submit', function (e) {
                    e.preventDefault();
                    jc.$$formSubmit.trigger('click');
                });
            }
        });
    }

    $(".images_gallery").click(function () {
        var url=$(this).attr("data-url");
        $.alert({
            title: 'Picture view',
            content: '<img  src="/photo/view?filename='+url+'"/>',
            buttons: {
                cancel: {
                    text: 'cancel',
                    action: function () {
                    }
                }
            }
        });
    })


    //添加收藏
    $(".add-favorites-btn").click(function () {
        var id=$(this).attr("data-id");
        ajaxRequest('add_favorites','POST',{id:id},function (result) {
            window.location.reload();
        })
    })

    //回复评论
    $(".reply-comment-btn").click(function () {
        var id=$(this).attr("data-id");
        var accountId=$(this).attr("data-accountId");
        $("#reply-id").val(id);
        $("#reply-account-id").val(accountId);
        layer.open({
            type: 1,
            shade: false,
            area: ['600px', '350px'],
            title: "Reply to comment content", //是否显示标题
            content: $("#reply-content-div"), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){

            }
        });
        $("#reply-content-btn").click(function () {
            var content=$("#reply-content").val();
            if(content ==null || content == ""){
                showWarningMsg("Reply content cannot be empty");
                return ;
            }
            var accountId=$("#reply-account-id").val();
            var id=$("#reply-id").val();
            ajaxRequest('/home/tourist/reply_comment','POST',{commentId:id,content:content,accountId:accountId},function (result) {
                showSuccessMsg("Reply successful",function () {
                    window.location.reload();
                })
            })
        })

    });



</script>
</body>
<div style="display: none;" id="reply-content-div">
    <form>
        <input type="hidden" id="reply-id"/>
        <input type="hidden" id="reply-account-id"/>
        <div class="mb-3">
            <textarea  class="form-control" id="reply-content" style="height: 240px;" placeholder="Reply content" ></textarea>
        </div>
        <button type="button" style="float: right;" id="reply-content-btn" class="btn btn-primary ">Reply</button>
    </form>
</div>
</html>
