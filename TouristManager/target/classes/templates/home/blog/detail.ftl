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
      <!-- Blog Element -->
      <div class="container-xxl">
        <article class="rlr-article rlr-article-single--v2">
          <header class="rlr-article__header">
            <div class="rlr-article__header__timestamp">Published ${blog.createTime!""} -Author:${blog.author!""}</div>
            <h1 class="type-h1">${blog.title!""}(${blog.type!""})</h1>
          </header>
          <div class="rlr-article__featured-photo">
            <#if blog.coverPic?? && blog.coverPic?length gt 0>
              <img src="/photo/view?filename=${blog.coverPic!""}" alt="Featured Photo" />
            <#else>
                <img src="/home/assets/images/blog-image--main.jpg" alt="Featured Photo" />
            </#if>
          </div>
          <div class="rlr-article__wrapper">
            <div class="content">
              <div class="content__highlight">
                <h2 class="type-h3">Content</h2>
                <p class="type-lead">
                 ${blog.content!""}
                </p>
              </div>
              <div class="rlr-article__badges">
                <#if blog.tags?? && blog.tags?length gt 0>
                    <#list blog.tags?split("|") as tag>
                       <span class="rlr-badge rlr-badge--left rlr-badge--
                      rlr-badge rlr-badge--left"> ${tag} </span>
                    </#list>
                </#if>
              </div>
            </div>
          </div>
        </article>

      </div>
    </main>

  <#include "../common/footer.ftl"/>
  </body>
</html>
