(function ($, undefined) {
  
  var AbstractService,Service,message,options,data;
  
  message = {
          success : false,
          failcode :"",
          response : "",
        };
  options = {
          url:undefined,
          type:'GET',
          timeout: 5000,
          async: true,
          data: '',
          dataType: 'json',
          contentType: 'application/x-www-form-urlencoded',
          beforeSend: undefined,
          success: undefined,
          error: undefined          
  };
  var Ajax={
          get: function(url){
            send('GET',url);
          },
          post: function(url){
            send('POST',url);
          },
          put: function(url){
            send('PUT',url);
          },
          del: function(url){
            send('DELETE',url);
          }
      }  
  function send(type,url){
    if(options.beforeSend != undefined){
        options.beforeSend.call(this);
    }
    var obj = new XMLHttpRequest(); 
    url = (url != undefined)?url:options.url;
    obj.open(type, url, true);
    if(type != 'GET'){
      obj.setRequestHeader("Content-type", options.contentType); 
    }
    obj.onreadystatechange = function() {
        if (obj.readyState == 4 && obj.status.toString().indexOf(2)>-1) {
            message.success = true;
            if(options.success != undefined){
              options.success.call(this);
            }
        }else{
          message.failcode = obj.status;
          message.response = obj.responseText;
          if(options.error != undefined){
            options.error.call(message);
          }
        }
    };
    obj.send(options.data);
  }
  
  /**
     * Creates a new class
     *
     * @param superClass
     * @param methods
     */
    function clazz(SuperClass, methods) {
        var child = new Object(options);
        child.prototype = SuperClass;
        child.prototype.constructor = Object;
        $.extend(child,methods);
        return child;
    }
    
    AbstractService = clazz(Object, {
      init: function(opt){
        options = $.extend({},options,opt);
      },
      queryAll: function(){
        Ajax.get();
      },
      querySingle: function(opt){
        options = $.extend({},options,opt);
        Ajax.get(options.url + '/' + options.data);
      },
      add: function(){
        Ajax.post();
      },
      
      remove: function(opt){
        options = $.extend({},options,opt);
        Ajax.del(options.url + '/' + options.data);
      },
      
      update: function(){
        Ajax.put();
      }     
    });
    
    Service = clazz(AbstractService, {      
    });
    // exports
    window.Service = Service;
}(jQuery));