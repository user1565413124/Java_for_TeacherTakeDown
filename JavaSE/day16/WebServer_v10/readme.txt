本次改动
完成处理业务操作:注册功能

通过本版本更新,理解服务端如何处理一个业务的流程.

1:在webapps/myweb/下提供注册页面reg.html

2:当用户注册提交时,GET形式提交会将用户数据包含在
  url部分提交过来,所以在解析请求时,要对请求行中
    的url部分做进一步解析工作.将请求的路径与用户传递
    的数据拆分出来,分别保存在HttpRequest中.
  2.1:在HttpRequest中定义三个新的属性:
  String requestURI:请求路径部分
  String queryString:参数部分
  Map<String,String> parameters:每个具体参数    
   例如:    
  /myweb/reg?username=123&password=123
   请求路径部分         参数部分 
   注:在一个URL中规定了使用"?"进行分割.  
    
  2.2:添加一个方法:parseURL
   该方法对请求行中的url部分进行进一步的解析工作,
   并将解析出来的内容对应的设置到2.1定义的相关属性上
   
  2.3:在parseRequestList方法中得到url后,调用2.2
   定义的parseURL,对请求进行进一步解析. 
   
     
    
    
    
    
    
    
    
    
    
    
    