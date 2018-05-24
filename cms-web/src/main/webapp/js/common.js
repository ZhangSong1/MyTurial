"use strict";
function MyInput() {
  var _self;
  var placeholder;
  var labelId;
  var validator;
  var msgId;
  var error;
  function MyInputFun() {
  };
  MyInputFun.prototype = {
    init: function(target) {
      _self = target;
      labelId = $(_self).attr("id") + "_label";
      msgId = $(_self).attr("id") + "_error";
      $(_self).addClass("myInput");
      placeholder = $(_self).attr('placeholder');
      $(_self).attr('placeholder', '');
      $(_self)
              .before(
                      '<label id="'
                              + labelId
                              + '" class="myinput_label blur" style="font-size: 14px;">'
                              + placeholder + '</label>');
      $(_self)
              .after('<label id="' + msgId + '" class="myinput_error"></label>');
      return this;
    },
    validator: function(fn, msg) {
      validator = fn;
      error = msg;
      return this;
    },
    render: function() {
      $("#" + labelId).on("click", function() {
        $(this).animate({
          fontSize: "10px"
        }, "fast", function() {
          $(this).addClass("focus");
        });
        $(_self).focus();
      });
      $(_self).on("mouseover", function() {
        $(self).addClass("bottom-black");
      });
      $(_self).on("mouseout", function() {
        $(self).removeClass("bottom-black");
      });
      $(_self).on("focus", function() {
        $("#" + labelId).animate({
          fontSize: "10px"
        }, "fast", function() {
          $("#" + labelId).addClass("focus");
        });
      });
      $(_self).on("blur", function() {
        console.log(_self);
        if ($(this).val() != '') {
          $("#" + labelId).html('');
        } else {
          $("#" + labelId).html(placeholder);
        }
        $("#" + labelId).removeClass("focus");
        $("#" + labelId).animate({
          fontSize: "14px"
        }, "fast");
        $(this).removeClass("bottom-black");
        if (validator != undefined) {
          if (!validator.call(this, $(this).val())) {
            $("#" + msgId).html(error);
          } else {
            $("#" + msgId).html("");
          }
        }
      });
    }
  };
  return new MyInputFun();
};
function loadBody(urlStr) {
  $.ajax({
    url: urlStr,
    async: true,
    success: function(data, textStatus, xhr) {
      $(document).trigger("successAsynLoading");
      if (xhr.status == 203) {
        window.location = 'ui/login';
      } else {
        $("#body").html(data);
      }
    },
    error: function(data) {
      window.location = 'ui/login';
    }
  });
};
function upload(target, review) {
  var file = target.files[0];
  var name = file.name.toLowerCase();
  if (name.endsWith('gif') || name.endsWith('jpg') || name.endsWith('png')) {
    $(review).attr('src', window.URL.createObjectURL(file));
  }
};

