/**
 * 
 */
;"use strict";$(function() {
  render();
  $("#signUpLink").on("click", function() {
    $(".logInShow").hide();
    $(".signUpShow").show();
  });

  $("#logInLink").on("click", function() {
    $(".logInShow").show();
    $(".signUpShow").hide();
  });

  $('#createUserLink').click(function(e) {
    $('#createModel').foundation('reveal', 'open');
  });
  $('#clock').clock();

  $('#login').click(function(e) {
    var username = $('#username').val();
    var password = $('#password').val();
    if (username.length == 0 || password.length == 0) { return; }
    $('#loginForm').submit();
  });

  function render() {
    $('#loginForm')[0].reset();
    new MyInput().init($('#username')).validator(function(value) {
      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      return reg.test(value);
    }, "Invaild Email!").render();
    new MyInput().init($('#password')).validator(function(value) {
      var reg = /^[A-Za-z0-9]{6,20}$/;
      return reg.test(value);
    }, "8-20位字母数字组合").render();
    new MyInput().init($('#signup_username')).validator(function(value) {
      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      return reg.test(value);
    }, "Invaild Email!").render();
    new MyInput().init($('#signup_password')).validator(function(value) {
      var reg = /^[A-Za-z0-9]{6,20}$/;
      return reg.test(value);
    }, "8-20位字母数字组合").render();
    $('#password').hideShowPassword({
      show: false,
      innerToggle: true,
      toggle: {
        className: 'hideShowPassword-toggle'
      }
    });
  }
  ;
});