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
      <div class="container">
        <div class="row rlr-search-results-page__product-details rlr-section__py">
          <aside class="col-xl-3 rlr-search-results-page__sidebar">
            <aside class="rlr-sidebar">
              <div class="rlr-sidebar__widget rlr-sidebar--search widget_search">
                <form role="search" method="get" action="list" class="wp-block-search__button-outside wp-block-search__text-button wp-block-search">
                  <label for="wp-block-search__input-1" class="wp-block-search__label">Search</label>
                  <div class="wp-block-search__inside-wrapper">
                    <input type="search"  id="wp-block-search__input-1" class="wp-block-search__input"
                           name="title" value="${name!""}" placeholder="Please enter a title"  /></div>
                </form>
              </div>

            </aside>
          </aside>
          <section class="col-xl-9 rlr-search-results-page__product-list">
            <div class="row rlr-listings__header">
              <!-- Breadcrumb -->
              <nav aria-label="breadcrumb">
                <ol class="breadcrumb rlr-breadcrumb__items">
                  <li class="breadcrumb-item rlr-breadcrumb__item"><a href="/home/index/index">Home</a></li>
                  <li class="breadcrumb-item rlr-breadcrumb__item active" aria-current="page">Blog</li>
                </ol>
              </nav>
              <h1 class="rlr-section__heading--main">Blog List</h1>
            </div>
            <div class="row rlr-search-results-page__card-wrapper">
              <#if pageBean.content?size gt 0>
              <#list pageBean.content as blog>
              <div class="col-md-6 col-lg-4">
                <article class="rlr-postcard">
                  <#if blog.coverPic?? && blog.coverPic?length gt 0>
                    <img class="rlr-postcard__thumbnail" src="/photo/view?filename=${blog.coverPic!""}" alt="blog image" />
                  <#else>
                    <img class="rlr-postcard__thumbnail" src="/home/assets/images/blog/blog-listing01.jpg" alt="blog image" />
                  </#if>
                  <div class="rlr-postcard__summary">
                    <span class="rlr-postcard__author">${blog.author!""}| ${blog.createTime!""}</span>
                    <a href="/home/blog/detail?id=${blog.id}" class="rlr-product-card__anchor-title">
                      <h2 class="rlr-product-card__title">${blog.title!""}</h2>
                    </a>
                    <div class="rlr-product-card__footer">
                      <div class="rlr-product-card__icon-text-list">
                        <#if blog.tags?? && blog.tags?length gt 0>
                            <#list blog.tags?split("|") as tag>
                              <a href="#" class="rlr-icon-text rlr-icon-text--anchor rlr-icon-text__anchor
                         rlr-product-card__icon-text">
                                <i class="rlr-icon-font flaticon-check"> </i>
                                <span class="rlr-postcard__tag">${tag} </span>
                              </a>
                            </#list>
                        </#if>

                      </div>
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
                         href="list?title=${name!""}&currentPage=1" aria-label="Previous">
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
                               href="list?title=${name!""}&currentPage=${showPage}">${showPage}</a>
                          </li>
                        </#if>
                      </#list>
                    </ul>
                  </li>
                  <#if pageBean.currentPage == pageBean.totalPage>
                    <li class="page-item rlr-pagination__page-item disabled">
                      <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--next "
                         href="#" aria-label="Next">
                        <span aria-hidden="true">Next</span>
                        <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <path d="M4.167 10h11.666m0 0L10 4.167M15.833 10 10 15.833"
                                stroke="var(--body-color)" stroke-width="1.67" stroke-linecap="round"
                                stroke-linejoin="round" />
                        </svg>
                      </a>
                    </li>
                  <#else>
                    <li class="page-item rlr-pagination__page-item">
                      <a class="page-link rlr-pagination__page-link rlr-pagination__page-link--next"
                         href="list?title=${name!""}&currentPage=${pageBean.totalPage}" aria-label="Next">
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
          </section>
        </div>
      </div>
    </main>

  <#include "../common/footer.ftl"/>
  </body>
</html>
