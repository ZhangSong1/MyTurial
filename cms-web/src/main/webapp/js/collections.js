"use strict";$(function(){
  $("#saveCollections").click(function(){
    var option = {
            success: function() {
              $(document).trigger("cancelLoading");
              $("#modal-create-collections").modal('toggle');
              $('#collectionsForm')[0].reset();
              $('#modal-create-collections').on('hidden.bs.modal', function (e) {
                loadBody($("#shortcutMrg").attr("data-link"));
              });        
            },
            error: function() {
              $(document).trigger("cancelLoading");
            }
          }
          $(document).trigger("loading");
          $("#collectionsForm").ajaxSubmit(option);
  });
  
  $(".shortcutItem").on("click",editToggle);  
  $("#loading-over").unbind("click").on("click",editToggle);
  Service.init({url:"/cms-web/ui/collections"});
  $("span.shortcut-remove").on("click",function(){
    var id = $(this).attr('data-id');
    var self = $(this);
    Service.remove({data:id,success: function(){
      self.parent().parent().parent().html("");
    },});
  });
  function editToggle(e){
    e.preventDefault()
    e.stopPropagation();
    if($("#loading-over").is(":hidden")){
      $("#loading-over").show();
      $(".shortcutItem").addClass("focus");
      $(".shortcutItem").children(".shortcut-remove").show();
      $(".shortcutItem").children(".shortcut-edit").show();
    }else{
      if($(e.target).is($("#loading-over"))){
        $("#loading-over").hide();
        $(".shortcutItem").removeClass("focus");
        $(".shortcutItem").children(".shortcut-remove").hide();
        $(".shortcutItem").children(".shortcut-edit").hide();
      }
    }

  };
});
