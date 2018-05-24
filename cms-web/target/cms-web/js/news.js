(function($, undefined) {
  $("tbody tr:odd").addClass("active");
  $("tbody tr:even").addClass("success");
  $("#createBtn").on("click",function(){
    $('#newsForm')[0].reset();
    CKEDITOR.instances.content.setData("");
    $('#modal-create-news').modal('show');
  });  
  $("#newsReviewBtn").on("click", news_review);
  $("#saveNews").on("click", function() {
    var option = {
      success: function() {
        $(document).trigger("cancelLoading");
        $("#modal-create-news").modal('toggle');
        $('#newsForm')[0].reset();
        $('#modal-create-news').on('hidden.bs.modal', function (e) {
          loadBody($("#newsMrg").attr("data-link"));
        });  

      },
      error: function() {
        $(document).trigger("cancelLoading");
      }
    }
    $(document).trigger("loading");
    $("#content").val(CKEDITOR.instances.content.getData());
    $("#newsForm").ajaxSubmit(option);
  });
  
  $("button.editorNews").on("click", function() {
    var doc = $(this).attr("data-news-doc");
    var option = {
      url:'news/query?name=' + doc,
      method: 'GET',            
      success: function(data) {
        $(document).trigger("cancelLoading");
        var result = $.parseJSON(data.map.result);
        $("#newsForm")[0].reset();
        $("#title").val(result.title);
        $("#type").val(result.type);
        CKEDITOR.instances.content.setData(result.content);
        $("#author").val(result.author);
        $("#doc").val(result.doc);
        $("#createDate").val(result.createDate);
        $("#modal-create-news").modal('toggle');
      },
      error: function() {
        $(document).trigger("cancelLoading");
      }
    }
    $(document).trigger("loading");
    $.ajax(option);
  });

  $("button.deleteNews").on("click", deleteNews);

  $("#previousPage").on("click", function() {
    var index = currentPage - 1;
    var pageSize = $("#pageSize").html();
    loadBody(generateUrl(index, pageSize));
  });
  $("#nextPage").on("click", function() {
    var index = currentPage + 1;
    var pageSize = $("#pageSize").html();
    loadBody(generateUrl(index, pageSize));
  });
  
  $("ul.pagination li.pageIndex a").on("click",function(){
    var pageIndex=$(this).html();
    var size=$("#pageSize").html();
    loadBody(generateUrl(pageIndex, size));
  });
  
  $("ul.pageSize a").on("click",function(){
    var size=$(this).html();
    loadBody(generateUrl(currentPage, size));
  });

  function generateUrl(index, pageSize) {
    var url = 'news';
    if (url.indexOf("?") > -1) {

      if (url.indexOf("currentPage") > -1) {
        url = url.replace(/currentPage=[0-9]*&?/g, "");
      }
      if (url.indexOf("pageSize") > -1) {
        url = url.replace(/pageSize=[0-9]*&?/g, "");
      }
      if (url.indexOf("?") != url.length - 1) {
        url = url + "&";
      }
      url = url + "currentPage=" + index + "&pageSize=" + pageSize;
    } else {
      url = url + "?currentPage=" + index + "&pageSize=" + pageSize;
    }
    return url;
  }

  function deleteNews() {
    var doc = $(this).attr("data-news-doc");
    var option = {
      url: 'news/delete?name=' + doc,
      method: 'POST',
      success: function() {
        $(document).trigger("cancelLoading");
        location.reload();
      },
      error: function() {
        $(document).trigger("cancelLoading");

      }
    }
    $.ajax(option);
  }

  function news_review() {
    sessionStorage.setItem("title", $("#title").val());
    sessionStorage.setItem("type", $("#type").val());
    sessionStorage.setItem("content", CKEDITOR.instances.content.getData());
    sessionStorage.setItem("author", $("#author").html());
    window.open('news/review', '_blank');
  }
})(jQuery);