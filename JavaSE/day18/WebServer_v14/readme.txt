本次改动
受限于HTTP协议要求,凡是通过地址栏请求传递过来的中文
都需要进行转码,因为HTTP协议要求只能使用ISO8859-1编
码的字符(欧洲编码,不支持中文).

解决办法:(浏览器已自行解决,过程如下)
1:先将中文按照指定字符集转换为对应一组字节
    例如,按照UTF-8编码先将文字转换:
    范:

2:将转换的每个字节(8位2进制)用2位16进制描述:    
  11101000 10001100 10000011
  E8       8C       83

3:将每组2位16进制前面添加"%",表示一字节数据.  
  E88C83
  %E8%8C%83   
  
4:在url中,中文会变为下面的样子:  
  /myweb/reg?username=%E8%8C%83&password=1234 
  
在HttpRequest读取请求行时,可以对含有%XX这样的字符
进行解码操作,这样才能得到正确的内容.
java提供了一个类URLDecoder,其提供了静态方法,专门
可以针对含有"%XX"这样的字符串进行解码操作.还原内容  
  
 
 
 
 
 
 
 
 
  