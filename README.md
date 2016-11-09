* 访问Rabbitmq：
http://192.168.33.10:15672/#/

* 启动rabbitmq

```
// 登陆到虚拟机上
vagrant ssh

// 启动服务
cd /opt/rabbitmq/sbin
sudo ./rabbitmq-server start

```

* 运行应用程序：
./gradlew clean bootRun

* 使用postman发送POST请求

** fanout exchange
`http://localhost:8080/rabbitmq/message/hello`
与fanout exchange相连接的queue都会收到该消息。

