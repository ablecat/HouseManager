# 一、环境配置

1. mysql版本：8.0.29 [下载链接](https://dev.mysql.com/downloads/installer/)
2. IDEA版本：ideaIU 2022.1.1
3. Tomcat版本：apache-tomcat-9.0.63
# 二、数据库设计
## 1.系统说明书
### （1）系统要求
某房屋租赁公司欲建立一个房屋租赁服务系统，统一管理房主和租赁者信息，以便快速地提供租赁服务。该系统具有以下功能：
* 登记房主信息。对于每名房主，系统需登记其姓名、住址和联系电话。
* 登记房屋信息。所有在系统中登记的房屋都有一个唯一的识别号（对于新增加的房屋，系统会自动为其分配一个识别号）。除此之外，还需登记房屋的地址、房型（如平房、带阳台的楼房、独立式住宅等）、最多能容纳的房客数、租金及房屋状态（待租赁、已出租）。一名房主可以在系统中登记多个待租赁的房屋。
* 登记租赁者信息。所有想通过该系统租赁房屋的租赁者，必须事先在系统中登记个人信息，包括：姓名、住址、电话号码、身份证编号、性别。
* 租赁房屋。已经在系统中登记的租赁者，可以得到一分系统提供的待租赁房屋列表。一旦租赁者从中找到合适的房屋，就可以提出看房请求。系统会安排租赁者与房主见面。对于每次看房，系统会生成一条看房记录。
* 收取手续费。每成功一次租赁服务，系统根据租赁价格按比例生成费用清单。
* 变更房屋状态。当租赁者与房主达成租房或退房协议后，房主向系统提交变更房屋状态请求。
* 用户论坛。租赁者在论坛上寻找合租对象、与房主进行交流。
* 创建视图查询当前空闲的房屋的识别号、地址、房型、最多能容纳的房客数、租金、房主身份证号、房主姓名、房主联系电话。
* 建立数据库相关表之间的参照完整性约束。

### （2）E-R图
![房屋租赁系统E-R图](https://i-blog.csdnimg.cn/blog_migrate/2a89156ce49d94125a0117ec202477eb.png)
### （3）数据流图

![房主用户数据流图](https://i-blog.csdnimg.cn/blog_migrate/a71cd802ff9c3c458dc43cca0effb747.png)
![租赁者用户数据流图](https://i-blog.csdnimg.cn/blog_migrate/771dabae0c067ada2e1a952d228d14d9.png)
### （4）数据结构
1、Owner_users（房主用户）数据结构：
House_owner=房主用户编号+用户名称+密码+真实姓名+住址+联系电话
2、Tenant_users（租赁者用户）数据结构：
Tenant_users=租赁者用户编号+用户名称+密码+真实姓名+住址+联系电话+性别
3、House（房屋）数据结构：
House=识别号+地址+房型+容量+租金+房屋状态+房主用户编号
4、Record（看房记录）数据结构：
Record=看房记录编号+租赁者用户编号+房屋识别号
5、Charge（收费记录）数据结构：
Charge=收费记录编号+金额+记录编号
6、Message（留言）数据结构：
Message=留言编号+留言内容+留言时间+房主用户编号+租赁者用户编号
### （5）关系模式
根据E-R图向关系模型的转换原则，房屋租赁管理系统的E-R图可以转换为下列关系模式：
* Owner_users(OID,userName,password,OName,OAddress,OTelephone)，主码OID，无外码
* Tenant_users(TID,userName,password,TName,TAddress,TTelephone,TSex)，主码TID，无外码
* House(HID,HAddress,layout,capacity,rent,con,OID)，主码HID，外码OID
* Record(RID,TID,HID)，主码RID，外码TID，HID
* Charge(CID,amount,RID)，主码CID，外码RID
* Message(MID,content,createDate,OID,TID)，主码MID，外码OID,TID
## 2.数据库实施
### （1）房主用户表 
```sql
CREATE TABLE Owner_users (
	OID INT AUTO_INCREMENT PRIMARY KEY,
	userName CHAR ( 10 ) NOT NULL,
    password BLOB NOT NULL,
	OName VARCHAR ( 10 ) NOT NULL,
	OAddress VARCHAR ( 30 ) NOT NULL,
	OTelephone VARCHAR ( 20 ) NOT NULL);
```
### （2）租赁者用户表
```sql
CREATE TABLE Tenant_users (
	TID INT AUTO_INCREMENT PRIMARY KEY,
	userName CHAR ( 10 ) NOT NULL,
    password BLOB NOT NULL,
	TName VARCHAR ( 10 ) NOT NULL,
	TAddress VARCHAR ( 30 ) NOT NULL,
	TTelephone VARCHAR ( 20 ) NOT NULL,
	TSex ENUM('男','女') DEFAULT '男');
```
### （3）房屋表
```sql
CREATE TABLE House (
	HID INT AUTO_INCREMENT PRIMARY KEY,
	HAddress CHAR ( 30 ) NOT NULL,
	layout VARCHAR ( 10 ) NOT NULL,
	capacity INT NOT NULL,
	rent INT NOT NULL,
	con TINYINT NOT NULL,
	OID INT NOT NULL,
	FOREIGN KEY ( OID ) REFERENCES Owner_users( OID ));
```
### （4）看房记录表
```sql
CREATE TABLE Record (
	RID INT AUTO_INCREMENT PRIMARY KEY,
	TID INT NOT NULL,
	HID INT NOT NULL,
	FOREIGN KEY ( TID ) REFERENCES Tenant_users ( TID ),
	FOREIGN KEY ( HID ) REFERENCES House ( HID ));
```
### （5）收费记录表
```sql
CREATE TABLE Charge(
	CID INT AUTO_INCREMENT PRIMARY KEY,
	amount DOUBLE NOT NULL,
	RID INT NOT NULL,
	FOREIGN KEY ( RID ) REFERENCES Record ( RID ));
```
### （6）留言表
```sql
CREATE TABLE Message(
	MID INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR( 500 ) NOT NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    OID INT,
    TID INT,
	FOREIGN KEY ( OID ) REFERENCES Owner_users ( OID ),
	FOREIGN KEY ( TID ) REFERENCES Tenant_users ( TID ));
```
### （7）空闲房屋视图
```sql
CREATE VIEW Vacant_house(HID,HAddress,layout,capacity,rent,OID,OName,OTelephone)
AS 
SELECT HID,HAddress,layout,capacity,rent,House.OID,OName,OTelephone
FROM House,Owner_users
WHERE House.OID = Owner_users.OID AND House.con = 0;
```
### （8）已看房屋视图
```sql
CREATE VIEW Seen_house(RID,HID,HAddress,layout,capacity,rent,OID,OName,OTelephone,TID)
AS 
SELECT RID,House.HID,HAddress,layout,capacity,rent,House.OID,OName,OTelephone,Tenant_users.TID
FROM House,Tenant_users,Record,Owner_users
WHERE House.HID = Record.HID AND Tenant_users.TID = Record.TID AND House.OID = Owner_users.OID;
```
### （9）已租房屋视图
```sql
CREATE VIEW Rented_house(CID,TID,HID,HAddress,layout,capacity,rent,OID,OName,OTelephone,amount)
AS
SELECT CID,TID,House.HID,HAddress,layout,capacity,rent,House.OID,OName,OTelephone,amount
FROM House,Charge,Owner_users,Record
WHERE Charge.RID = Record.RID AND Record.HID = House.HID AND Owner_users.OID = House.OID;
```
