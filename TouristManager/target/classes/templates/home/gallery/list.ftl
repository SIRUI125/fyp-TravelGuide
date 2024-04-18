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
      <section class="rlr-icon-font-page rlr-section rlr-section__content--md-top">
        <div class="container">
          <!-- Section heading -->
          <div class="rlr-section-header">
            <!-- Section heading -->
            <div class="rlr-section__title">
              <h2 class="rlr-section__title--main">Travel gallery</h2>
              <span class="rlr-section__title--sub">Sost Brilliant reasons Emprise should be your one-stop-shop!</span>
            </div>
            <div class="button-row">

            </div>
          </div>
          <div class="rlr-lightbox--gallery">
            <div class="rlr-lightbox--gallery__wrapper">
              <#if galleryList?? && galleryList?size gt 0>
                <#list galleryList as gallery>
                    <figure class="rlr-lightbox--gallery__figure">
                      <#list gallery.images?split(";") as image>
                        <img class="rlr-lightbox--gallery__img"
                             src="/photo/view?filename=${image}" alt="Anchor Button" />
                      </#list>
                      <figcaption class="rlr-lightbox--gallery__figcaption">
                        <span>${gallery_index+1}</span>
                      </figcaption>
                    </figure>
                </#list>
              </#if>



            </div>
          </div>
        </div>
      </section>
    </main>

  <#include "../common/footer.ftl"/>
  </body>
</html>
