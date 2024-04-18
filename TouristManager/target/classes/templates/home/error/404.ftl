<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Your vacation, tours and travel theme needs are all met at emprise." />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tourist Attractions Platform | 404</title>

    <#include "../common/header_css.ftl"/>
  </head>

  <body class="rlr-body">

  <#include "../common/header.ftl"/>
    <!-- Main Content -->
    <main id="rlr-main" class="rlr-main--fixed-top">
      <section class="rlr-section rlr-error">
        <div class="container">
          <div class="row">
            <div class="col-lg-6 rlr-error__content">
              <!-- Heading text -->
              <p class="type-lead-semibold">404 Error</p>
              <h1 class="type-h1">  ${msg!"Page not found"}</h1>
            </div>
            <div class="offset-lg-6"></div>
          </div>
        </div>
      </section>
    </main>

  <#include "../common/footer.ftl"/>
  </body>
</html>
