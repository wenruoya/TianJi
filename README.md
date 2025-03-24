# 天机-风险/漏洞管理平台

# 部署
## 环境准备
任意发行版的linux(推荐ubuntu)
mysql
java 17 
nginx

## 打包
前端地址 https://github.com/wenruoya/TianJi_frontend 
使用命令 pnpm build

后端java 
maven clean & package

## 配置
1. mysql
创建数据库tianji  create database tianji;
soucre 下 /resources/tianji.sql
2. java  (默认密码为123456789Abc.)
修改文件mysql配置 /resources/application-pro.properties
3. 定义默认密码
/resources/application.properties 此处为默认管理员用户的账号密码，也是重置用户的密码
![image](https://github.com/user-attachments/assets/bd4db4f5-352e-4156-87a5-12bb0f838777)

5. 配置nginx


# 示例
登录页面
![image](https://github.com/user-attachments/assets/b28d3b62-32cc-46b8-bb4c-cd6b4bb85e6f)
态势展示
![image](https://github.com/user-attachments/assets/a57f5555-98a8-4546-a62b-1011f14e5a80)
定义风险
![image](https://github.com/user-attachments/assets/943296bf-b0a0-45d0-a272-652ef1ce7fb2)
风险处置流
![image](https://github.com/user-attachments/assets/cd2a5fd3-8e53-47fa-b7af-87479e762dd5)
