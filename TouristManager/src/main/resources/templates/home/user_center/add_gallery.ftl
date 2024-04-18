<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise." />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tourist Attractions Platform | Add photo</title>

    <#include "../common/header_css.ftl"/>
  </head>

  <body class="rlr-body">

  <#include "../common/header.ftl"/>
    <!-- Main Content -->
    <main id="rlr-main" class="rlr-main--fixed-top">
      <div class="rlr-section rlr-section__content--lg-top rlr-product-form">
        <div class="container">
          <div class="row">
            <aside class="col-xl-3">
              <div class="rlr-progress">
                <ul class="rlr-progress__steps js-progress-bar-slider" role="tablist">

                </ul>
              </div>
            </aside>
            <div class="col-xl-6 offset-xl-1">
              <form id="gallery-form">
                <fieldset class="rlr-product-form--hide stop" data-attr="js-step-1">

                  <!-- Section heading -->
                  <div class="rlr-section__heading">
                    <h2 class="rlr-section__heading--main">Upload photos</h2>
                    <span class="rlr-section__heading--sub"></span>
                  </div>
                  <div class="rlr-fieldrow">
                    <div class="rlr-fieldrow__form-element">
                      <div class="col-xl-10">
                        <label class="rlr-form-label rlr-form-label--dark" for="rlr-product-form-product-title"> Photo Title </label>
                        <input type="text" autocomplete="off" maxlength="70"
                               id="rlr-product-form-product-title"
                               class="form-control js-form-title required" name="title" tips="Please fill in the title"
                               placeholder="Enter title here">
                      </div>
                      <div class="rlr-fieldrow__item js-fieldrow__item">
                        <label class="rlr-form-label rlr-form-label--" for="select-picture-file">  Photos </label>
                        <div class="rlr-drop-region js-rlr-drop-region">
                          <div class="rlr-drop-region__add-section">
                            <input type="hidden" name="images" class="required" tips="Please upload pictures"
                                   id="add-images"/>
                            <input  id="select-file"
                                   class="rlr-drop-region__image-input js-rlr-drop-input" type="file"
                                   accept="image/*"  onchange="upload('preview-images','add-images')" />
                            <svg width="48" height="48" viewBox="0 0 48 48" fill="none"
                                 xmlns="http://www.w3.org/2000/svg" id="add-photo-btn">
                              <path
                                d="M24 1.928c12.144 0 22.072 9.928 22.072 22.072 0 12.144-9.928 22.072-22.072 22.072-12.144 0-22.072-9.928-22.072-22.072C1.928 11.856 11.856 1.928 24 1.928zM24 0A23.94 23.94 0 0 0 0 24c0 13.302 10.794 24 24 24 13.204 0 24-10.698 24-24A23.94 23.94 0 0 0 24 0z"
                                fill="#99A3AD"
                              />
                              <path d="M22.844 11.374h1.928v25.06h-1.928v-25.06z" fill="#99A3AD" />
                              <path d="M11.18 23.132h24.868v1.928H11.18v-1.928z" fill="#99A3AD" />
                            </svg>
                            <div class="type-lead rlr-drop-region__add-section__text">Add Photos</div>
                          </div>
                        </div>

                        <div >
                          <ul class="splide__list" id="prew-images">
                           <li class="splide__slide" style="width: 100%">
                              <img src="/home/assets/01.jpg" id="preview-images" style="height: 500px;width: 100%;">

                            </li>
                          </ul>

                        </div>
                      </div>
                    </div>
                  </div>
                </fieldset>
              </form>
              <hr />
              <div>
                <nav class="rlr-pagination" aria-label="Page navigation example">
                  <ul class="pagination rlr-pagination__list">

                    <li class="page-item rlr-pagination__page-item--form jsNext">
                      <a class="page-link rlr-pagination__page-link--form" id="add-save-photo-btn" href="javascript:void(0);"> save </a>
                    </li>
                  </ul>
                </nav>
              </div>
            </div>
            <aside class="col-xl-2 d-none d-xl-block"></aside>
          </div>
        </div>
      </div>
    </main>

  <#include "../common/footer.ftl"/>

  <script type="text/javascript">

    //选择图片
    $("#add-photo-btn").click(function () {
      $("#select-file").click();
    })

    //保存照片库
    $("#add-save-photo-btn").click(function () {
      if(!checkForm('gallery-form')){
        return;
      }

      var data=$("#gallery-form").serialize();
      ajaxRequest('/home/user_center/add_gallery',"POST",data,function (result) {
        showSuccessMsg("Added successfully",function () {
          window.location.href="/home/user_center/gallery_list";
        })
      })
    })
  </script>
  </body>
</html>
