/**
 * 
 */
"use strict";$(function() {
  new MyInput().init($('#email')).validator(function(value) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    return reg.test(value);
  }, "Invaild Email!").render();
})