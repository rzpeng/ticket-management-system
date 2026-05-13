# 工单管理系统

一个基于 Spring Boot + Vue3 的企业级工单管理系统，支持工单创建、流转、状态管理等核心功能。

## 技术栈

**后端**
- Spring Boot 3.2.0
- Spring Security + JWT
- MyBatis-Plus 3.5.5
- MySQL 8.0
- Redis

**前端**
- Vue 3.4
- Element Plus 2.4
- Vue Router 4
- Pinia
- Axios

## 核心功能

- 用户登录注册
- 工单创建与管理
- 工单状态流转（待处理 → 处理中 → 已完成）
- 优先级管理（低/中/高）
- 分页查询
- RBAC 权限控制

## 快速开始

### 环境要求

- JDK 17+
- Node.js 16+
- MySQL 8.0+
- Redis 6.0+

### 后端启动

```bash
# 1. 创建数据库
mysql -u root -p < database/init.sql

# 2. 修改配置
cd backend/src/main/resources
# 编辑 application.yml，修改数据库和Redis连接信息

# 3. 启动项目
cd backend
mvn spring-boot:run
```

### 前端启动

```bash
cd frontend
npm install
npm run dev
```

访问 http://localhost:3000

### 默认账号

- 用户名：admin
- 密码：123456

## 项目结构

```
ticket-management-system/
├── backend/                # 后端代码
│   ├── src/main/java/com/ticket/
│   │   ├── controller/     # 控制器
│   │   ├── service/        # 业务逻辑
│   │   ├── mapper/         # 数据访问
│   │   ├── entity/         # 实体类
│   │   ├── dto/            # 数据传输对象
│   │   ├── config/         # 配置类
│   │   └── utils/          # 工具类
│   └── src/main/resources/
│       ├── application.yml # 配置文件
│       └── mapper/         # MyBatis XML
├── frontend/               # 前端代码
│   ├── src/
│   │   ├── views/          # 页面组件
│   │   ├── api/            # API 接口
│   │   ├── router/         # 路由配置
│   │   └── main.js         # 入口文件
│   └── package.json
└── database/               # 数据库脚本
    └── init.sql
```

## Docker 部署

```bash
# 构建镜像
docker-compose build

# 启动服务
docker-compose up -d
```

## API 文档

### 认证接口

- POST /api/auth/login - 用户登录
- POST /api/auth/register - 用户注册

### 工单接口

- GET /api/tickets - 工单列表（分页）
- POST /api/tickets - 创建工单
- GET /api/tickets/{id} - 工单详情
- PUT /api/tickets/{id}/status - 更新状态

## 扩展计划

- [ ] 工作流引擎
- [ ] 消息通知（邮件/短信）
- [ ] 数据统计报表
- [ ] 文件附件上传
- [ ] 操作日志记录

## 作者

资深 Java 开发工程师，5-10年企业级系统开发经验，擅长微服务架构、中间件技术。

## 许可证

MIT License
