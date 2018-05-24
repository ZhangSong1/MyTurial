/**
 * 
 */
;$(function() {
  $('#password').hideShowPassword({
    show: false,
    innerToggle: true,
    toggle: {
      className: 'hideShowPassword-toggle'
    }
  });
  $('#createUserLink').click(function (e) {
    $('#createModel').foundation('reveal', 'open');
  });
  $('#clock').clock();
  
  $('#login').click(function(e){
    var username=$('#username').val();
    var password=$('#password').val();
    if(username.length == 0 || password.length == 0){
      return;
    }
    $('#loginForm').submit();
  });
});