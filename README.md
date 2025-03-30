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

4. 配置nginx
/var/www/dist 为前端打包文件的路径

```
server {
                listen 8000;
                server_name localhost;
                location / {
                        root   /var/www/dist;
                        index  index.html index.htm;
                        # 用于配合前端路由为h5模式使用，防止刷新404 https://router.vuejs.org/zh/guide/essentials/history-mode.html#nginx
                        try_files $uri $uri/ /index.html;
                        }

                # 第一个代理后端地址（vite.config.ts里叫 /api，这里也要保持一致）
                location /api {
                # 如果后端在本地比如127.0.0.1或者localhost请解开下面的rewrite注释即可
                rewrite  ^.+api/?(.*)$ /$1 break;
                # 这里写后端地址（后面一定不要忘记添加 / ）
                proxy_pass http://127.0.0.1:8080;
                proxy_set_header Host $host;
                proxy_set_header Cookie $http_cookie;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_redirect default;
                add_header Access-Control-Allow-Origin *;
                add_header Access-Control-Allow-Headers X-Requested-With;
                add_header Access-Control-Allow-Methods GET,POST,OPTIONS;
                        }

        }
```
5. 启动
启动nginx sudo nginx
启动Java  java -jar xx.jar
# 示例
登录页面
![image](https://github.com/user-attachments/assets/b28d3b62-32cc-46b8-bb4c-cd6b4bb85e6f)
态势展示
![image](https://github.com/user-attachments/assets/a57f5555-98a8-4546-a62b-1011f14e5a80)
定义风险
![image](https://github.com/user-attachments/assets/943296bf-b0a0-45d0-a272-652ef1ce7fb2)
风险处置流
![image](https://github.com/user-attachments/assets/cac5e749-d2b3-4d87-8d0f-c91b731a552e)

