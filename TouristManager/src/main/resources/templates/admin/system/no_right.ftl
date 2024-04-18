<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}Backend management system home page</title>
<#include "../common/header.ftl"/>
<style>
body{
    background-color: #fff;
}
.error-page {
    height: 100%;
    position: fixed;
    width: 100%;
}
.error-body {
    padding-top: 5%;
}
.error-body h1 {
    font-size: 110px;
    font-weight: 700;
    text-shadow: 4px 4px 0 #f5f6fa, 6px 6px 0 #33cabb;
    line-height: 210px;
    color: #33cabb;
}
.error-body h4 {
    margin: 30px 0px;
}
</style>
</head>
  
<body>
<section class="error-page">
  <div class="error-box">
    <div class="error-body text-center">
      <h1>No permission</h1>
      <h4>Sorry, your role does not have this permission。</h4>
      <a href="/system/index" class="btn btn-primary ">Return to homepage</a>
    </div>
  </div>
</section>
<#include "../common/footer.ftl"/>
<script type="text/javascript">
$(document).ready(function(){
	
});

</script>
</body>
</html>