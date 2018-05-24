/**
 * 
 */

$.fn.clock=function(options){
  var defaults={
     size:'500'
  };
  var opts = $.extend({}, $.fn.clock.defaults, options);
  
  var clock=$(this)[0];
  var width=$(this).width();
  var height=$(this).height();
  var cxt=clock.getContext('2d');
  
function drawClock(){
  //清除画布
  cxt.clearRect(0,0,width,height);
  var now =new Date();
  var sec=now.getSeconds();
  var min=now.getMinutes();
  var hour=now.getHours();
  var radius=width/2-10;
  var x_point=width/2;
  var y_point=height/2;
  //小时必须获取浮点类型(小时+分数转化成的小时)
  hour=hour+min/60;
  //问题 19:23:30
  //将24小时进制转换为12小时
  hour=hour>12?hour-12:hour;
  
  //表盘(蓝色)
  cxt.lineWidth=2;
  cxt.strokeStyle="blue";
  cxt.beginPath();
  cxt.arc(x_point,y_point,radius,0,360,false);
  cxt.closePath();
  cxt.stroke();
  //刻度
    //时刻度
    for(var i=0;i<12;i++){
      cxt.save();
      //设置时针的粗细
      cxt.lineWidth=2;
      //设置时针的颜色
      cxt.strokeStyle="#000";
      //先设置0,0点
      cxt.translate(x_point,y_point);
      //再设置旋转角度
      cxt.rotate(i*30*Math.PI/180);//角度*Math.PI/180=弧度
      cxt.beginPath();
      cxt.moveTo(0,-radius);
      cxt.lineTo(0,-radius+6);
      cxt.closePath();
      cxt.stroke();
      cxt.restore();
    }
    
    //分刻度
    for(var i=0;i<60;i++){
      cxt.save();
      //设置分刻度的粗细
      cxt.lineWidth=1;
      //设置颜色(使用时刻度的颜色)
      cxt.strokeStyle="#000";
      //设置或者重置画布的0，0点
      cxt.translate(x_point,y_point);
      //设置旋转角度
      cxt.rotate(i*6*Math.PI/180);
      //画分针刻度
      cxt.beginPath();
      cxt.moveTo(0,-radius);
      cxt.lineTo(0,-radius+4);
      cxt.closePath();
      cxt.stroke();
      cxt.restore();
    }
    
  //时针
    cxt.save();
    //设置时针风格
    cxt.lineWidth=2;
    //设置时针的颜色
    cxt.strokeStyle="#000";
    //设置异次元空间的0，0点
    cxt.translate(x_point,y_point);
    //设置旋转角度
    cxt.rotate(hour*30*Math.PI/180);
    cxt.beginPath();
    cxt.moveTo(0,2);
    cxt.lineTo(0,-radius/2);
    cxt.closePath();
    cxt.stroke();
    cxt.restore();
  
  
  //分针
    cxt.save();
    //设置分针的风格
    cxt.lineWidth=2;
    cxt.strokeStyle="#000";
    //设置异次元空间分针画布的圆心
    cxt.translate(width/2,y_point);
    //设置旋转角度
    cxt.rotate(min*6*Math.PI/180);
    cxt.beginPath();
    cxt.moveTo(0,4);
    cxt.lineTo(0,-radius*3/4);
    cxt.closePath();
    cxt.stroke();
    cxt.restore();
  
    
  //秒针
  
    cxt.save();
    //设置秒针的风格
    //颜色红色
    cxt.strokeStyle="red";
    //粗细 3像素
    cxt.lineWidth=2;
    //重置0，0点
    cxt.translate(width/2,y_point);
    //设置旋转角度
    cxt.rotate(sec*6*Math.PI/180);
    //画图
    cxt.beginPath();
    cxt.moveTo(0,5);
    cxt.lineTo(0,-radius*4/5);
    cxt.closePath();
    cxt.stroke();
    //画出时针、分针、秒针的交叉点、
    cxt.beginPath();
    cxt.arc(0,0,2,0,360,false);
    cxt.closePath();
    //设置填充样式
    cxt.fillStyle="gray";
    cxt.fill();
    //设置笔触样式(秒针已设置)
    cxt.stroke();
    //设置秒针前段的小圆点
    cxt.beginPath();
    cxt.arc(0,-radius*4/5+5,2,0,360,false);
    cxt.closePath();
    //设置填充样式
    cxt.fillStyle="gray";
    cxt.fill();
    //设置笔触样式(秒针已设置)
    cxt.stroke();
    
    cxt.restore();
  }
  //使用setInterval(代码,毫秒时间)  让时钟动起来
  drawClock();
  setInterval(drawClock,1000);
};
