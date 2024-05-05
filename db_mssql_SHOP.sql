USE master
GO
-- +++++++++++++++++++++++++++++++ CREATE DATABASE +++++++++++++++++++++++++++++++ --
-- Drop database
IF EXISTS (SELECT name FROM sys.databases WHERE name = N'DB_SHOP')
    DROP DATABASE [DB_SHOP]
GO
-- Create database
CREATE DATABASE [DB_SHOP]
GO
USE [DB_SHOP]
GO

-- +++++++++++++++++++++++++++++++ CREATE TABLES +++++++++++++++++++++++++++++++ --

/* ACCOUNTS AND AUTHORIZATIONS */
-- Create USERS table
IF OBJECT_ID('USERS', 'U') IS NOT NULL
DROP TABLE [USERS]
GO
CREATE TABLE [USERS]
(
   -- use email
   [username] varchar(20) primary key,
   [password] binary(70) not null,
   [email] varchar(100) not null unique,
   [name] nvarchar(100) not null,
   -- soft delete
   [active] bit default 0,
   [regTime] datetime default GETDATE(),
   [image] varchar(80) default 'default.png'
);
GO

-- Create ROLES table
IF OBJECT_ID('ROLES', 'U') IS NOT NULL
DROP TABLE [ROLES]
GO
CREATE TABLE [ROLES]
(
   [rid] int primary key,
   [role] nvarchar(50) unique not null
);
GO

-- Create AUTHS table
IF OBJECT_ID('AUTHS', 'U') IS NOT NULL
DROP TABLE [AUTHS]
GO
CREATE TABLE [AUTHS]
(
   [u_id] varchar(20) foreign key references [USERS]([username])
    on update cascade on delete cascade not null,
   [r_id] int foreign key references [ROLES]([rid]) not null,
   primary key ([u_id], [r_id])
);
GO

/* PRODUCTS AND CLASSIFICATION */
-- Create PRODUCTS table
IF OBJECT_ID('CATEGORIES', 'U') IS NOT NULL
DROP TABLE [CATEGORIES]
GO
CREATE TABLE [CATEGORIES]
(
   [cgid] int primary key,
   [name] nvarchar(50) not null,
   [image] varchar(80) default 'default.png'
);
GO

-- Create PRODUCTS table
IF OBJECT_ID('PRODUCTS', 'U') IS NOT NULL
DROP TABLE [PRODUCTS]
GO
CREATE TABLE [PRODUCTS]
(
   [prid] bigint identity primary key,
   [name] nvarchar(150) not null,
   [note] nvarchar(256),
   [active] bit default 1,
   [price] float default 0,
   [quantity] int default 100,
   [regTime] datetime default GETDATE(),
   [image] varchar(80) default 'default.png',
   [cg_id] int foreign key references [CATEGORIES]([cgid]),
   [u_id] varchar(20) foreign key references [USERS]([username])
   on update cascade on delete cascade
);
GO



GO
-- +++++++++++++++++++++++++++++++ CREATE VIEWS +++++++++++++++++++++++++++++++ --

-- VIEW COUNT PRODUCT OF USERS
IF EXISTS (SELECT name FROM sys.views WHERE name = N'V_USER_COUNT_PRODUCT') DROP VIEW V_USER_COUNT_PRODUCT
GO
CREATE VIEW V_USER_COUNT_PRODUCT
AS SELECT u.*, COUNT(p.prid) as 'productCount'
   FROM USERS u INNER JOIN PRODUCTS p ON u.username = p.u_id
   GROUP BY [u].[username], [u].[password], [u].[email],
   [u].[name], [u].[active], [u].[regTime], [u].[image]
GO

-- VIEW COUNT PRODUCT OF USERS
IF EXISTS (SELECT name FROM sys.views WHERE name = N'V_CATEGORY_COUNT_PRODUCT') DROP VIEW V_CATEGORY_COUNT_PRODUCT
GO
CREATE VIEW V_CATEGORY_COUNT_PRODUCT
AS SELECT c.*, COUNT(p.prid) as 'productCount'
   FROM CATEGORIES c INNER JOIN PRODUCTS p ON c.cgid = p.cg_id
   GROUP BY [c].[cgid], [c].[name], [c].[image]
GO



-- +++++++++++++++++++++++++++++++ CREATE PROCEDURES +++++++++++++++++++++++++++++++ --

-- Create procedure update less password
IF EXISTS (SELECT [object_id] FROM sys.procedures WHERE name = N'pr_update_less_pass') DROP PROC pr_update_less_pass
GO
CREATE PROCEDURE pr_update_less_pass
   @username varchar(256), @email varchar(256), @name nvarchar(256), @active bit, @image varchar(256) 
AS BEGIN
   UPDATE USERS SET username=@username, email=@email,name=@name,active=@active,image=@image WHERE username=@username;
   SELECT password FROM USERS WHERE username = @username;
END

-- Create procedure update password
IF EXISTS (SELECT [object_id] FROM sys.procedures WHERE name = N'pr_update_pass') DROP PROC pr_update_pass
GO
CREATE PROCEDURE pr_update_pass
   @username varchar(256), @password varchar(256)
AS BEGIN
   UPDATE USERS SET password=PWDENCRYPT(@password) WHERE username = @username OR email = @username;
   SELECT username, password FROM USERS WHERE username = @username OR email = @username;
END

-- Create procedure login >>> login by (username or email) and password
IF EXISTS (SELECT [object_id] FROM sys.procedures WHERE name = N'pr_login') DROP PROC pr_login
GO
CREATE PROCEDURE pr_login
   @username varchar(256), @password varchar(256)
AS
BEGIN
   DECLARE @meserror nvarchar(256) = CONCAT (
		'username:', @username, ' and password:', @password, ' is incorrect'
	);

   IF @username is null OR LEN(@password) = 0 RAISERROR('username is empty',15,1);
   IF @password is null OR LEN(@password) = 0 RAISERROR('password is empty',15,1);

   SELECT u.* INTO #USER FROM USERS u
   WHERE (username = @username OR email = @username) AND PWDCOMPARE(@password, password) = 1;

   IF ((SELECT active FROM #USER) = 0)
      RAISERROR('This account is not activated yet!!!', 15,1);
   ELSE IF EXISTS(SELECT username FROM #USER) 
      SELECT * FROM #USER;
   ELSE RAISERROR(@meserror, 12,1);
END
GO



-- +++++++++++++++++++++++++++++++ CREATE TRIGGERS +++++++++++++++++++++++++++++++ --

-- Create trigger product > return data inserted
IF EXISTS (SELECT [object_id] FROM sys.triggers WHERE name = N'TG_PRODUCTS') DROP TRIGGER TG_PRODUCTS
GO
CREATE TRIGGER TG_PRODUCTS ON PRODUCTS
AFTER INSERT AS BEGIN -- return all products after inserted
	DECLARE @qty int = (SELECT COUNT(prid) FROM inserted);
   DECLARE @size int = (SELECT COUNT(prid) FROM PRODUCTS);
   SELECT * FROM PRODUCTS ORDER BY prid
   OFFSET (@size-@qty) ROWS FETCH NEXT @qty ROWS ONLY;
END
GO

-- +++++++++++++++++++++++++++++++ DELETE DATA +++++++++++++++++++++++++++++++ --
GO
DELETE PRODUCTS
DELETE CATEGORIES
DELETE USERS
DELETE ROLES
DBCC CHECKIDENT ('[PRODUCTS]', RESEED, 1);
GO



-- +++++++++++++++++++++++++++++++ INSERT DATA +++++++++++++++++++++++++++++++ --
-- Insert role data
INSERT INTO dbo.[ROLES]
   ([rid], [role])
VALUES
   (1, N'ADMIN'),
   (2, N'STAFF'),
   (3, N'SELLER'),
   (4, N'BUYER');

-- Insert user data
INSERT INTO dbo.[USERS]
   ([username], [password], [email], [name],[active] ,[image])
VALUES
   ('admin', PWDENCRYPT('admin'), 'f429780ecb2d001@fruit.com', N'admin', 1, 'admin.png'),
   ('staff', PWDENCRYPT('staff'), 'f429780ecb2d002@fruit.com', N'staff', 1, 'staff.png'),
   ('buyer1', PWDENCRYPT('buyer1'), 'f429780ecb2d005@fruit.com', N'buyer 1', 1, 'buyer1.png'),
   ('buyer2', PWDENCRYPT('buyer2'), 'f429780ecb2d006@fruit.com', N'buyer 2', 1, 'buyer2.png'),
   ('buyer3', PWDENCRYPT('buyer3'), 'f429780ecb2d007@fruit.com', N'buyer 3', 1, 'buyer3.png'),
   ('buyer4', PWDENCRYPT('buyer4'), 'f429780ecb2d008@fruit.com', N'buyer 4', 1, null),
   ('buyer5', PWDENCRYPT('buyer5'), 'f429780ecb2d009@fruit.com', N'buyer 5', 0, null),
   ('seller1', PWDENCRYPT('seller1'), 'f429780ecb2d003@fruit.com', N'seller 1', 1, 'seller1.png'),
   ('seller2', PWDENCRYPT('seller2'), 'f429780ecb2d004@fruit.com', N'seller 2', 1, null);

-- Insert authorities data
INSERT INTO dbo.[AUTHS]
   ([u_id], [r_id])
VALUES
   ('admin', 1),
   ('admin', 2),
   ('admin', 3),
   ('admin', 4),

   ('staff', 2),
   ('staff', 3),
   ('staff', 4),

   ('seller1', 3),
   ('seller2', 3),

   ('buyer1', 4),
   ('buyer2', 4),
   ('buyer3', 4),
   ('buyer4', 4),
   ('buyer5', 4);

-- Insert categories data
INSERT INTO dbo.[CATEGORIES]
   ([cgid], [name], [image])
VALUES
   (1, N'Unknow', null),
   (2, N'Other', null),
   (3, N'Cake', 'cake.png'),
   (4, N'Candy', 'candy.png'),
   (5, N'Soft-Drink', 'soft-drink.png');

-- Insert products data
INSERT INTO dbo.[PRODUCTS]
   ([u_id], [cg_id], [active], [name], [note], [price], [quantity], [image], [regTime])
VALUES
   ('staff', 3, 1, N'Bánh bao lá dứa Lim Kee 280g', N'Bánh bao lá dứa Lim Kee 280g', 35.0 , 49, '8959830392265646.jpg', '2023-01-10 17:04:45'),
   ('staff', 3, 0, N'Bánh quy mè Gouté 8 gói', N'Bánh quy mè Gouté hộp 288g', 52.0 , 100, '7238624444017541.jpg', '2022-12-08 01:52:04'),
   ('seller1', 3, 1, N'Bánh cracker rau củ Lurich 6 gói', N'Bánh cracker rau củ Lurich hộp 180g', 22.0 , 74, '2620132208097447.jpg', '2022-12-22 21:45:07'),
   ('seller2', 3, 1, N'Bánh cracker phô mai Gery 20 gói', N'Bánh cracker phô mai Gery hộp 180g', 37.0 , 73, '1742551373584424.jpg', '2023-01-29 01:09:51'),
   ('seller1', 3, 1, N'5 cái bánh bông lan sợi thịt gà Orion C`est Bon', N'Bánh bông lan sợi thịt gà Orion C`est Bon gói 85g (5 bánh)', 22.0 , 66, '8468464725691438.jpg', '2022-12-18 22:35:07'),
   ('seller1', 3, 1, N'Bánh gạo nướng vị tảo biển Orion An gói 111.3g', N'Bánh gạo nướng vị tảo biển Orion An gói 111.3g', 23.0 , 71, '7727277356686887.jpg', '2023-01-01 22:49:48'),
   ('admin', 3, 0, N'Snack vị tảo biển Lay`s 58g', N'Snack khoai tây vị tảo biển Nori Lay`s gói 58g', 11.6 , 45, '2803178002758156.jpg', '2023-01-03 06:33:37'),
   ('admin', 3, 1, N'Snack mì gà Enaak 30g', N'Snack mì hương gà Enaak gói 30g', 6.00 , 81, '770632367481970.jpg', '2022-12-06 02:26:50'),
   ('staff', 3, 1, N'Snack bắp vị phô mai Oishi gói 35g', N'Snack bắp vị phô mai Oishi gói 35g', 6.50 , 89, '7548627550057144.jpg', '2023-02-23 05:22:23'),
   ('admin', 3, 1, N'Snack bắp ngọt Oishi 40g', N'Snack bắp ngọt Oishi gói 40g', 6.50 , 56, '7579644370857865.jpg', '2023-02-20 07:08:45'),
   ('seller1', 3, 1, N'Snack pho mát miếng Oishi 39g', N'Snack pho mát miếng Oishi gói 39g', 6.50 , 65, '3006181857423940.jpg', '2023-01-27 06:56:46'),
   ('admin', 3, 1, N'Snack phồng mực Oishi 39g', N'Snack phồng mực Oishi Indo Chips gói 39g', 6.50 , 85, '5347706540875645.jpg', '2023-01-04 15:55:01'),
   ('staff', 3, 1, N'Bánh bông lan tươi bơ nho Kinh Đô gói 38g', N'Bánh bông lan tươi bơ nho Kinh Đô gói 38g', 7.00 , 87, '8888273036716952.jpg', '2023-02-21 19:40:53'),
   ('seller1', 3, 1, N'Bánh trứng tươi chà bông Karo 6 gói x 26g', N'Bánh trứng tươi chà bông Karo gói 156g', 39.0 , 72, '5824150104434632.jpg', '2023-01-07 22:34:40'),
   ('staff', 3, 1, N'Bánh trứng tươi phô mai hoàng kim Karo 6 cái x 26g', N'Bánh trứng tươi phô mai hoàng kim Karo gói 156g', 39.0 , 94, '7927701892602109.jpg', '2022-12-19 04:40:12'),
   ('staff', 3, 1, N'Bánh mịn nhân phô mai Sandochi Bảo Minh gói 200g', N'Bánh mịn nhân phô mai Sandochi Bảo Minh gói 200g', 45.0 , 62, '3223675900411105.jpg', '2022-12-20 12:55:05'),
   ('seller1', 3, 1, N'Bánh trứng tươi sợi gà Kisshu gói 4 cái x 24g', N'Bánh trứng tươi sợi gà Kisshu gói 96g', 22.0 , 83, null, '2023-02-05 16:44:35'),
   ('admin', 3, 1, N'Bánh quy kem socola Oreo 20.4g', N'Bánh quy Oreo Mini kem socola gói 20.4g', 6.00 , 48, null, '2023-02-11 09:18:07'),
   ('admin', 3, 1, N'Bánh Choco-pie Đậm ca cao 6 cái', N'Bánh Choco-pie Đậm vị ca cao hộp 180g (6 cái)', 32.0 , 47, null, '2022-12-02 13:20:17'),
   ('admin', 3, 1, N'Snack vị tảo biển O`Star 32g', N'Snack khoai tây vị tảo biển O`Star gói 32g', 6.50 , 50, null, '2023-02-14 06:44:55'),
   ('staff', 4, 1, N'Kẹo dâu Dynamite Energy 330g', N'Kẹo có nhân hương dâu Dynamite Energy gói 330g', 29.5 , 46, '7994268752903125.jpg', '2022-12-27 20:18:00'),
   ('admin', 4, 1, N'Kẹo búp bê Justine League Relkon 10g', N'Kẹo búp bê Justine League Relkon bộ 10g', 32.0 , 93, '4412899057801685.jpg', '2022-12-10 06:48:03'),
   ('seller1', 4, 1, N'Kẹo Dynamite cam nhân socola 330g', N'Kẹo Dynamite cam nhân socola gói 330g', 29.5 , 59, '5246520582724827.jpg', '2022-12-23 22:19:01'),
   ('staff', 4, 1, N'Kẹo Dynamite dâu nhân socola 330g', N'Kẹo Dynamite dâu nhân socola gói 330g', 29.5 , 98, '6113684577630998.jpg', '2023-01-05 11:45:33'),
   ('staff', 4, 0, N'Kẹo Bebeto vị quả mọng 36g', N'Kẹo Bebeto Cool Beans Fliptop vị quả mọng hộp 36g', 6.50 , 96, '1772960625489706.jpg', '2022-12-31 03:32:55'),
   ('admin', 4, 1, N'Kẹo Golia chanh mật ong 112.2g', N'Kẹo Golia chanh mật ong gói 112.2g', 13.5 , 42, '7996787077394331.jpg', '2022-12-12 18:26:40'),
   ('admin', 4, 1, N'Kẹo chanh muối Alpenliebe 84.1g', N'Kẹo hương chanh muối Alpenliebe gói 84.1g', 13.5 , 75, '19862249965251.jpg', '2023-01-16 11:36:36'),
   ('admin', 4, 1, N'Kẹo dẻo trái cây Haribo Goldbears 80g', N'Kẹo dẻo hình trái cây Haribo Goldbears gói 80g', 26.0 , 88, '5070678527064898.jpg', '2023-02-24 05:42:10'),
   ('staff', 4, 1, N'Kẹo dẻo Chupa Chups Cool 90g', N'Kẹo dẻo Chupa Chups Cool gói 90g', 22.5 , 78, '437431015163393.jpg', '2023-01-30 23:19:37'),
   ('staff', 4, 1, N'Kẹo dẻo cola burger Yupi 96g', N'Kẹo dẻo Cola Burger Yupi gói 96g', 31.0 , 92, '2315222468056471.jpg', '2023-01-14 18:03:33'),
   ('seller2', 4, 1, N'Kẹo dừa sọc ca cao Yến Hoàng 250g', N'Kẹo dừa sọc ca cao Yến Hoàng hộp 250g', 28.0 , 84, '2590092628218918.jpg', '2023-02-09 15:49:05'),
   ('admin', 4, 1, N'Kẹo dẻo Play More Robot 48g', N'Kẹo dẻo Play More Robot gói 48g', 21.5 , 52, '1019006701702243.jpg', '2022-12-25 21:06:20'),
   ('admin', 4, 1, N'Kẹo dẻo Chupa Chups Cool Cola 160g', N'Kẹo dẻo Chupa Chups Cool Cola gói 160g', 30.5 , 86, '4688377765482454.jpg', '2022-12-26 13:26:15'),
   ('admin', 4, 1, N'Kẹo chuối Thanh Bình 300g', N'Kẹo chuối Thanh Bình tươi hộp 300g', 33.0 , 44, '3759311065580480.jpg', '2023-02-26 23:10:58'),
   ('admin', 4, 1, N'Kẹo dẻo Amos Robot 4D 72g', N'Kẹo dẻo Amos Robot 4D gói 72g', 24.3 , 82, '4393556837553699.jpg', '2023-02-15 08:32:42'),
   ('seller1', 4, 1, N'Kẹo dẻo trái cây Amos 72g', N'Kẹo dẻo Amos trái cây 4D gói 72g', 27.0 , 40, '5182712243851206.jpg', '2023-02-03 01:36:58'),
   ('seller2', 4, 1, N'Kẹo dẻo gấu Pop Amos 72g', N'Kẹo dẻo Amos 4D gấu Pop gói 72g', 27.0 , 80, '1715923002797836.jpg', '2023-01-18 19:08:00'),
   ('seller1', 4, 1, N'Kẹo dẻo hình ly Bebeto 120g', N'Kẹo dẻo hình ly Soft Drinks Bebeto hũ 120g', 23.0 , 54, '5677650277774795.jpg', '2022-12-28 10:15:15'),
   ('seller1', 4, 1, N'Kẹo marshmallows CorNichE Mini White 70g', N'Kẹo marshmallows CorNichE Mini White gói 70g', 17.5 , 70, '3907670613893497.jpg', '2023-01-08 04:45:33'),
   ('seller1', 4, 0, N'Kẹo trái cây Bibica Zoo Jelly 200g', N'Kẹo dẻo hương trái cây Bibica Zoo Jelly gói 200g', 20.5 , 57, '6220480985306836.jpg', '2023-02-17 06:27:20'),
   ('seller1', 5, 1, N'soda Schweppes 320ml', N'soda Schweppes 320ml', 40 , 99, '6259260131241495.jpg', '2023-01-06 16:39:02'),
   ('seller1', 5, 1, N'lon nước ngọt Mirinda soda kem 320ml', N'lon nước ngọt Mirinda vị soda kem 320ml', 230 , 69, '1549198435151654.jpg', '2023-01-31 10:09:07'),
   ('seller1', 5, 1, N'chai nước ngọt Sprite chanh 390ml', N'chai nước ngọt Sprite hương chanh 390ml', 175 , 91, '6698121261987533.jpg', '2023-02-02 02:09:19'),
   ('seller1', 5, 1, N'lon nước ngọt Sprite chanh 320ml', N'lon nước ngọt Sprite hương chanh 320ml', 196 , 64, '3200432041890461.jpg', '2022-12-16 18:52:13'),
   ('staff', 5, 1, N'lon nước ngọt Coca Light 320ml', N'lon nước ngọt Coca Cola Light 320ml', 235 , 90, '2664393619970421.jpg', '2023-01-13 04:07:27'),
   ('admin', 5, 1, N'nước ngọt Mirinda soda kem 320ml', N'nước ngọt Mirinda vị soda kem 320ml', 58 , 60, '4192815074888575.jpg', '2023-01-22 06:04:47'),
   ('seller2', 5, 1, N'nước ngọt Coca Cola Zero 600ml', N'nước ngọt Coca Cola Light 320ml', 60 , 58, '6444141498563285.jpg', '2023-01-20 12:04:04'),
   ('admin', 5, 1, N'nước ngọt Sprite chanh 320ml', N'nước ngọt Sprite hương chanh 320ml', 56 , 51, '2510406753400117.jpg', '2023-02-27 15:09:19'),
   ('seller1', 5, 0, N'chai nước ngọt Coca Cola 390ml', N'chai nước ngọt Coca Cola 390ml', 175 , 55, '6421303897452950.jpg', '2023-01-23 20:19:19'),
   ('admin', 5, 1, N'nước ngọt Fanta soda kem trái cây 600ml', N'nước ngọt Coca Cola 600ml', 58 , 77, '8278792837017455.jpg', '2022-12-04 06:11:31'),
   ('seller1', 5, 1, N'lon soda Schweppes 320ml', N'lon soda Schweppes 320ml', 150 , 53, '5302192776752643.jpg', '2023-02-08 18:41:52'),
   ('seller1', 5, 1, N'chai nước ngọt Fanta cam 390ml', N'chai nước ngọt Fanta hương cam 390ml', 175 , 79, '3871624267284199.jpg', '2023-01-15 22:09:23'),
   ('admin', 5, 1, N'sá xị Chương Dương Sleek 330ml', N'sá xị Chương Dương Sleek 330ml', 48 , 76, '7773084061318296.jpg', '2023-01-17 21:43:53'),
   ('staff', 5, 1, N'lon sá xị Chương Dương Sleek 330ml', N'lon sá xị Chương Dương Sleek 330ml', 180 , 67, '838776229347890.jpg', '2023-02-10 14:28:47'),
   ('seller1', 5, 1, N'lon nước ngọt Fanta nho 320ml', N'lon nước ngọt Fanta hương cam 320ml', 100 , 61, '1417625780169523.jpg', '2023-01-12 13:04:25'),
   ('staff', 5, 1, N'lon nước ngọt Mirinda xá xị 320ml', N'lon nước ngọt Mirinda hương xá xị 320ml', 104 , 63, '4510927830333134.jpg', '2023-02-07 02:50:45'),
   ('staff', 5, 1, N'nước ngọt 7 Up chanh 330ml', N'nước ngọt 7 Up vị chanh 330ml', 58 , 95, '2701111682587783.jpg', '2023-02-06 12:16:03'),
   ('seller2', 5, 0, N'chai nước ngọt Mirinda soda kem 1.5 lít', N'chai nước ngọt Mirinda vị soda kem 1.5 lít', 192 , 43, '6773027158454085.jpg', '2023-01-02 03:52:44'),
   ('seller1', 5, 0, N'nước ngọt Coca Cola Plus 320ml', N'nước ngọt Coca Cola Plus 320ml', 72 , 97, '3231989994644393.jpg', '2022-12-29 11:44:13'),
   ('seller1', 5, 1, N'nước ngọt Pepsi không calo vị chanh 320ml', N'nước ngọt Sprite hương chanh 600ml', 58 , 93, '6308647899646315.jpg', '2023-02-13 18:08:04'),
   ('seller1', 5, 1, N'nước ngọt Fanta soda kem trái cây 1.5 lít', N'nước ngọt Fanta hương soda kem trái cây 1.5 lít', 115 , 59, '7684491942394270.jpg', '2023-01-28 07:58:48'),
   ('seller2', 5, 1, N'lon nước ngọt Pepsi không calo vị chanh 320ml', N'lon nước ngọt Pepsi không calo vị chanh 320ml', 235 , 95, '446709807489996.jpg', '2022-12-07 12:41:04'),
   ('admin', 5, 1, N'lon nước ngọt Pepsi không calo 320ml', N'lon nước ngọt Pepsi không calo 320ml', 235 , 94, '3928952342417991.jpg', '2023-01-21 22:41:37'),
   ('seller1', 5, 0, N'nước ngọt Coca Cola 600ml', N'nước ngọt có ga Coca Cola Zero 600ml', 58 , 60, '189635163322741.jpg', '2023-01-11 18:55:33'),
   ('staff', 5, 1, N'chai nước ngọt CocaCola Zero 390ml', N'chai nước ngọt có ga Coca Cola Zero 390ml', 175 , 75, '6028924851325591.jpg', '2023-02-01 14:16:05'),
   ('admin', 5, 1, N'chai nước ngọt Sprite chanh 300ml', N'chai nước ngọt có ga Sprite hương chanh 300ml', 120 , 71, '2584883961661840.jpg', '2022-12-09 02:36:28'),
   ('seller2', 5, 1, N'nước ngọt Mirinda soda kem việt quất 320ml', N'nước ngọt Coca Cola 390ml', 90 , 96, '6308902777243832.jpg', '2023-02-18 07:23:14'),
   ('seller1', 5, 1, N'nước ngọt Sprite chanh 390ml', N'nước ngọt có ga Coca Cola Zero 390ml', 90 , 68, '2018586277320884.jpg', '2023-02-04 13:39:14'),
   ('seller2', 5, 1, N'nước ngọt Coca Cola Zero 390ml', N'nước ngọt Fanta hương cam 390ml', 90 , 43, '2519491825731302.jpg', '2022-12-24 15:33:15'),
   ('seller2', 5, 1, N'nước ngọt Fanta cam 390ml', N'nước ngọt Fanta vị soda kem 320ml', 98 , 50, '6937479622042722.jpg', '2022-12-15 20:45:34'),
   ('staff', 5, 1, N'nước ngọt Fanta soda kem 320ml', N'nước ngọt Fanta hương xá xị 320ml', 98 , 86, '3421444700894118.jpg', '2023-01-26 05:12:34'),
   ('admin', 5, 1, N'nước ngọt 7 Up ít calo bổ sung chất xơ 320ml', N'nước ngọt Sprite hương chanh 390ml', 90 , 45, '2043556816968871.jpg', '2023-02-16 07:23:26'),
   ('seller1', 5, 1, N'nước ngọt Pepsi Cola 320ml', N'nước ngọt 7 Up ít calo bổ sung chất xơ 320ml', 62 , 89, '474744229123913.jpg', '2022-12-17 06:16:47'),
   ('admin', 5, 1, N'lon nước ngọt 7 Up ít calo bổ sung chất xơ 320ml', N'lon nước ngọt 7 Up ít calo bổ sung chất xơ 320ml', 240 , 78, '1587799413757838.jpg', '2023-01-19 00:26:42'),
   ('seller2', 5, 0, N'chai nước ngọt Mirinda soda kem 390ml', N'chai nước ngọt Mirinda Soda kem Pet 390ml', 175 , 91, '1976352120863065.jpg', '2023-02-12 23:19:53'),
   ('seller1', 5, 1, N'nước ngọt Coca Cola Light 320ml', N'nước ngọt có ga Mirinda vị soda kem việt quất 320ml', 62 , 41, '439790212130807.jpg', '2022-12-30 21:43:16'),
   ('staff', 5, 1, N'nước ngọt Fanta nho 1.5 lít', N'nước ngọt Fanta hương nho 1.5 lít', 11.5 , 80, '8584868344326522.jpg', '2023-02-22 06:20:43'),
   ('admin', 5, 1, N'nước ngọt Fanta xá xị 320ml', N'lon nước ngọt Fanta hương nho 320ml', 100 , 83, '7801772738921879.jpg', '2023-02-25 20:27:55'),
   ('seller2', 5, 1, N'chai nước ngọt 7 Up chanh 390ml', N'chai nước ngọt 7 Up vị chanh 390ml', 175 , 70, '5122035623009769.jpg', '2022-12-05 09:57:04'),
   ('staff', 5, 1, N'lon nước ngọt 7 Up chanh 330ml', N'lon nước ngọt 7 Up vị chanh 330ml', 230 , 72, '7547652466145703.jpg', '2022-12-13 18:16:17'),
   ('seller2', 5, 1, N'lon nước ngọt Mirinda cam 320ml', N'lon nước ngọt Mirinda hương cam 320ml', 218 , 100, '473491064789393.jpg', '2023-01-25 13:08:10'),
   ('seller2', 5, 1, N'chai nước ngọt Mirinda cam 390ml', N'chai nước ngọt Mirinda hương cam 390ml', 175 , 44, '2248085072570947.jpg', '2023-01-09 02:06:31'),
   ('seller2', 5, 1, N'chai nước ngọt Mirinda xá xị 1.5 lít', N'chai nước ngọt Mirinda hương xá xị 1.5 lít', 192 , 67, '145848301526691.jpg', '2022-12-14 03:07:22'),
   ('seller2', 5, 1, N'chai nước ngọt Mirinda xá xị 390ml', N'chai nước ngọt Mirinda hương xá xị 390ml', 175 , 81, '7736634109107281.jpg', '2022-12-11 15:45:42'),
   ('seller2', 5, 1, N'chai nước ngọt Pepsi Cola 390ml', N'chai nước ngọt Pepsi Cola 390ml', 175 , 69, '7909010260248385.jpg', '2023-02-19 00:28:50'),
   ('admin', 5, 1, N'nước ngọt Pepsi Cola 390ml', N'nước ngọt Pepsi Cola 390ml', 45 , 97, '7952792795326971.jpg', '2022-12-03 21:23:40'),
   ('admin', 5, 1, N'lon Pepsi Cola 320ml', N'lon nước ngọt Pepsi Cola 320ml', 230 , 90, '8816771418902786.jpg', '2022-12-01 04:19:31'),
   ('admin', 5, 1, N'nước ngọt Coca Cola 390ml', N'nước ngọt Coca Cola 390ml', 45 , 74, '4874613703395984.jpg', '2022-12-21 23:34:19'),
   ('seller2', 5, 1, N'nước ngọt Sprite chanh 600ml', N'nước ngọt Pepsi Cola 320ml', 58 , 84, '5130321247374724.jpg', '2023-01-24 01:16:43'),
   ('seller1', 5, 1, N'lon nước ngọt Fanta cam 320ml', N'lon nước ngọt Fanta hương cam 320ml', 196 , 54, '3694190375398524.jpg', '2022-12-21 22:27:10'),
   ('seller2', 5, 0, N'nước ngọt Pepsi không calo 320ml', N'nước ngọt Fanta hương xá xị 320ml', 56 , 79, null, '2023-03-03 15:07:04'),
   ('staff', 5, 1, N'nước ngọt Fanta cam 600ml', N'nước ngọt Pepsi không calo vị chanh 320ml', 60 , 73, null, '2023-03-12 05:49:09'),
   ('seller1', 5, 1, N'nước ngọt Mirinda cam 320ml', N'nước ngọt có ga Fanta hương soda kem trái cây 600ml', 58 , 58, null, '2023-03-25 22:46:36'),
   ('seller1', 5, 1, N'nước ngọt Mirinda xá xị 320ml', N'nước ngọt Pepsi không calo 320ml', 60 , 73, null, '2023-03-15 12:23:13'),
   ('admin', 5, 1, N'nước ngọt Fanta cam 235ml', N'nước ngọt Fanta hương cam 600ml', 58 , 58, null, '2023-03-07 15:39:22');
GO

USE master