## 访问Rabbitmq：
http://192.168.33.10:15672/#/

## 启动rabbitmq

```
// 登陆到虚拟机上
vagrant ssh

// 启动服务
cd /opt/rabbitmq/sbin
sudo ./rabbitmq-server start

```
## 运行应用程序：
./gradlew clean bootRun

## 发送消息： 
* fanout exchange
使用postman发送post请求`http://localhost:8080/rabbitmq/message/hello`
与fanout exchange相连接的queue都会收到该消息。

* direct exchange
使用postman发送post请求`http://localhost:8080/rabbitmq?message=hello&routingKey=routing-key-for-first-queue`
与direct exchange相连接的，并且`routingKey`匹配的queue才会收到该消息。

* topic exchange
使用postman发送post请求`http://localhost:8080/rabbitmq?message=hello&routingKey=routing-key-for-anystring-queue`
与topic exchange相连接的，并且`routingKey`*模糊*匹配的queue才会收到该消息。

* headers exchange
使用rabbitmq console`http://192.168.33.10:15672/#/exchanges/%2F/HEADERS.EXCHANGE`publish message，同时设置`Headers`
为`type`=`headers`, `number`=`second`
headers exchange相连接的，并且Message中的`headers`与程序定义的headers属性*模糊*匹配的queue才会收到该消息。

