function loadBody(urlStr){
    $.ajax({
      url: urlStr,
      async: true,
      success: function(data){
      $(document).trigger("successAsynLoading");
      $("#body").html(data);
    }});
  };
function upload(target,review){
    var file = target.files[0];
    var name = file.name.toLowerCase();
    if(name.endsWith('gif')||name.endsWith('jpg')||name.endsWith('png')){
      $(review).attr('src',window.URL.createObjectURL(file));
    }
};
  
