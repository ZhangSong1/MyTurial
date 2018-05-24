$(function(){
  $(document).on("loading",function(){
    $("#loading-over").show();
    $("#loading").show();
  });
  $(document).on("cancelLoading",function(){
    $("#loading-over").hide();
    $("#loading").hide();
  });
  $(document).on("asynLoading",function(){
    $(".progressBar").width("10%");
    $(".progressBar").show();
    $(".progressBar").animate({width:"90%"},"slow");
  });
  $(document).on("successAsynLoading",function(){
    $(".progressBar").animate({width:"100%"},"fast",function(){
      $(this).width("10%");
      $(this).hide();
    });
  });
  $(".progressBar").animate({width:"100%"},"slow",function(){
    $(this).width("10%");
    $(this).hide();
  });
  $("header a").on("click",function(){
    $(document).trigger("asynLoading");
    loadBody($(this).attr("data-link"));
  });
  $(document).on('scroll', function() {
//    if($(document).scrollTop() == 0){
//      $("header").removeClass("navbar-fixed-top");
//    }else{
//      $("header").addClass("navbar-fixed-top");
//    }
  }
  );
});