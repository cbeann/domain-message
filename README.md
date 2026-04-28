# 领域消息系统

## 项目简介

这是一个基于 Canal 监听 MySQL binlog，通过预先设计的领域、领域实体、数据库、领域事件配置，将 binlog 转换为消息发送出去的系统。本项目适合中台系统，如订单系统、广告系统等。

作为一个多租户的中台系统，上游承接着不同的租户，本项目解决了下游系统反向异步通知上游消息的标准化问题，实现了 binlog 消息的标准化处理。

![领域结构](src/main/resources/github/pic/%E9%A2%86%E5%9F%9F%E7%BB%93%E6%9E%84.jpeg)

## 核心价值

- **消息标准化**：将数据库 binlog 变更转换为标准化的领域事件消息
- **多租户支持**：通过配置满足不同租户的消息需求
- **解耦设计**：通知消息与业务代码解耦，提高系统可维护性
- **灵活扩展**：支持不同类型的领域事件和过滤条件
- **水平/垂直解析**：支持主表和附表的不同解析策略
- **Web 界面配置**：通过 Web 界面配置领域、领域实体、数据源和事件

## 技术栈

- **后端**：Spring Boot 3.2.5
- **数据库**：MySQL 5.7+
- **消息队列**：RocketMQ (可选，支持 SQL 过滤能力)
- **Binlog 解析**：Canal 1.1.7
- **ORM**：MyBatis 3.0.3
- **本地缓存**：Guava（缓存配置数据）
- **前端**：Thymeleaf + Bootstrap 5

## 方案设计

### 消息链路设计

1. **Binlog 监听**：使用 Canal 实时监听 MySQL 数据库变更
2. **消息标准化**：将 binlog 变更转换为标准化的领域事件
3. **消息发送**：将标准化事件发送到 RocketMQ
4. **上游消费**：上游系统自行消费感兴趣的消息

### 层级设计

1. **领域 (AdDomain)**：DDD 领域驱动设计中的领域，如订单领域、物流领域等
2. **领域实体 (DomainEntity)**：领域下的具体实体，如图片、视频、文案、落地页等
3. **数据源 (DomainDataSource)**：真实的物理表映射，支持主表和附表
4. **领域事件 (DomainEvent)**：在物理表上衍生的事件，如新增、删除、状态变更等

## 快速开始

### 环境要求

- JDK 8+
- MySQL 5.7+
- Canal 1.1.7+

### 安装步骤

1. **克隆项目**

```bash
git clone https://github.com/yourusername/domain-message.git
cd domain-message
```

2. **配置数据库**

#### 使用 Docker 安装 MySQL

1. 创建 MySQL 配置文件 `my.cnf`，开启二进制日志：

```ini
[mysqld]
log-bin=mysql-bin
binlog-format=ROW
server-id=1
```

2. 启动 MySQL 容器：

```bash
docker run --name mysql-57 -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /opt/mysql/my.cnf:/etc/mysql/my.cnf mysql:5.7
```

3. 验证二进制日志是否开启：

```sql
SHOW MASTER STATUS;
```

4. 创建数据库并执行 SQL 脚本：

```bash
mysql -u root -p < src/main/resources/sql/domain_message.sql
```

3. **配置 Canal**

#### 使用 Docker 安装 Canal

```bash
docker run --name canal -p 11111:11111 -e canal.instance.master.address=localhost:3307 -e canal.instance.dbUsername=root -e canal.instance.dbPassword=123456 -d canal/canal-server:v1.1.7
```

#### 手动安装 Canal

1. 安装 JDK：

```bash
sudo yum install java-1.8.0-openjdk
```

2. 下载并解压 Canal：

```bash
wget https://github.com/alibaba/canal/releases/download/canal-1.1.5/canal.deployer-1.1.5.tar.gz
mkdir /usr/local/canal
tar -zxvf canal.deployer-1.1.5.tar.gz -C /usr/local/canal
```

3. 配置 Canal：

编辑 `/usr/local/canal/conf/example/instance.properties` 文件：

```properties
# 修改为你的MySQL实例地址
canal.instance.master.address=localhost:3307
# MySQL用户名和密码
canal.instance.dbUsername=root
canal.instance.dbPassword=123456
```

4. 启动 Canal：

```bash
cd /usr/local/canal
sh bin/startup.sh
```

5. 查看日志确认启动状态：

```bash
tail -f logs/canal/canal.log
```

4. **配置项目**

修改 `src/main/resources/application.properties` 文件：

```properties
# MySQL 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/domain_message?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456

# Canal 配置
canal.server.host=localhost
canal.server.port=11111
canal.instance.name=example

# 线程池配置
threadpool.core.size=2
threadpool.max.size=4
threadpool.queue.size=10
```

5. **构建并运行**

```bash
mvn clean package
java -jar target/domain-message-1.0.0.jar
```

6. **访问系统**

打开浏览器访问：`http://localhost:8080`

## 项目结构

```
src/main/
├── java/com/demo/
│   ├── canal/          # Canal 相关代码
│   ├── config/         # 配置类
│   ├── constant/       # 常量定义
│   ├── controller/     # 控制器
│   ├── dto/            # 数据传输对象
│   ├── entity/         # 实体类
│   ├── enums/          # 枚举类
│   ├── listener/       # 监听器
│   ├── mapper/         # MyBatis 映射器
│   ├── mock/           # 模拟服务
│   ├── model/          # 模型类
│   ├── parser/         # 事件解析器
│   ├── pipeline/       # 处理管道
│   ├── repository/     # 数据访问层
│   ├── utils/          # 工具类
│   └── App.java        # 应用入口
└── resources/
    ├── mapper/         # MyBatis XML 映射文件
    ├── sql/            # SQL 脚本
    ├── templates/      # Thymeleaf 模板
    └── application.properties  # 应用配置
```

## 核心功能模块

### 1. 领域管理

管理系统中的领域，每个领域包含多个实体。

### 2. 实体管理

管理领域下的实体，每个实体对应一个业务对象。

### 3. 数据源管理

管理数据库表与实体的映射关系，支持主表和附表。

### 4. 事件管理

配置领域事件，包括事件类型、关注字段、输出主题等。

### 5. Binlog 处理

- **BinlogNormalizeProcedure**：标准化 binlog 数据
- **EventProcedure**：将 binlog 转换为领域事件
- **MessageProdure**：发送事件消息

## 设计模式

本项目使用了**责任链模式**，当收到 binlog 消息后，通过多个处理器依次处理：
1. 过滤逻辑（黑白名单用户、无关的表变更等）
2. 标准化处理（将 binlog 转换为统一格式）
3. 事件解析（根据配置解析为领域事件）
4. 消息发送（发送到 RocketMQ）

## 性能优化

1. **本地缓存**：使用 Guava 缓存配置数据，减少数据库查询
2. **批处理**：批量处理 binlog 消息，提高处理效率
3. **异步处理**：使用线程池异步处理消息，避免阻塞
4. **消息过滤**：在 RocketMQ 端使用 SQL 过滤，减少无效消息传递

## 测试方法

### 1. 测试插入领域事件

在 `domain_message` 库的 `order` 表中新增一条记录，触发 `ORDER_NEW` 事件：

```sql
INSERT INTO `order` (name, creator_id, order_status, product_id) VALUES ('测试订单', 1, 1, 123456);
```

```shell
模拟发送消息--> 
消息属性:{databaseName=domain_message, operateType=INSERT, eventName=新增订单事件, tableName=order} , 
消息key:7 ,
消息Topic:YX_ORDER_TOPIC，
消息内容：
{
    "databaseName": "domain_message",
    "eventName": "新增订单事件",
    "msgKey": "7",
    "newData": {
        "order_status": "1",
        "product_id": "123456",
        "name": "测试订单",
        "creator_id": "1",
        "id": "7"
    },
    "oldData": {

    },
    "operateType": "INSERT",
    "tableName": "order",
    "topic": "YX_ORDER_TOPIC"
}
```

### 2. 测试修改领域事件

修改 `order` 表中 id=1 的记录，触发 `ORDER_STATUS_CHANGE` 事件：

```sql
UPDATE `order` SET order_status = 2 WHERE id = 1;
```

### 3. 测试删除领域事件

删除 `order` 表中 id=1 的记录，触发 `ORDER_DELETE` 事件：

```sql
DELETE FROM `order` WHERE id = 1;
```

## 部署建议

### 1. 生产环境

- **Canal**：使用集群部署，提高可靠性
- **RocketMQ**：使用集群，确保消息可靠性
- **监控**：配置监控告警，及时发现问题
- **配置中心**：使用配置中心管理配置，支持动态更新

### 2. 测试环境

- **Docker**：使用 Docker 快速搭建环境
- **模拟测试**：模拟真实场景进行测试
- **验证**：验证消息处理的正确性

## 常见问题

### 1. Canal 连接失败

- 检查 MySQL 二进制日志是否开启
- 检查 Canal 配置是否正确
- 检查网络连接是否正常

### 2. 消息发送失败

- 检查 RocketMQ 服务是否正常
- 检查消息格式是否正确
- 检查网络连接是否正常

### 3. 事件解析错误

- 检查领域事件配置是否正确
- 检查数据源映射是否正确
- 检查 binlog 格式是否支持

## 贡献指南

1. **Fork** 本项目
2. **创建** feature 分支
3. **提交**代码
4. **发起** Pull Request

## 许可证

本项目采用 MIT 许可证，详见 [LICENSE](LICENSE) 文件。

## 鸣谢

感谢 Canal、RocketMQ、Spring Boot 等开源项目的支持，使得本项目能够快速实现。

## 项目地址

- **GitHub**：https://github.com/yourusername/domain-message
- **Gitee**：https://gitee.com/cbeann/domain-message
