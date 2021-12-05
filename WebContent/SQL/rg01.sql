create database if not exists rg01; 
use rg01;

DROP TABLE IF EXISTS PARTIINF;
DROP TABLE IF EXISTS EVENTINF;
DROP TABLE IF EXISTS EVENTCATEINF;

drop table if exists VENDORACT;
drop table if exists SITEACT;
drop table if exists VENDOR;
drop table if exists SITE;
drop table if exists ACTIVITY;
drop table if exists ARTICLE;
drop table if exists ARTICLE_CLASS;
drop table if exists REPORT;
drop table if exists LIKED;
drop table if exists REPLY_RECORD;
drop table if exists TICKET1;
drop table if exists ticket_backend;
drop table if exists order_detail;

DROP TABLE IF EXISTS BULLETIN;
DROP TABLE IF EXISTS BORROW;
DROP TABLE IF EXISTS LIBRARY_MAP;
DROP TABLE IF EXISTS LIBRARY;
DROP TABLE IF EXISTS MARKS;
DROP TABLE IF EXISTS STOCK_LIST;
DROP TABLE IF EXISTS STOCK;
DROP TABLE IF EXISTS STOCK_TYPE;
DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS STATUSNAME;
DROP TABLE IF EXISTS ORDERITEM;
DROP TABLE IF EXISTS ORDERDETAIL;


set auto_increment_offset=1;

-- 會員資料 -- 
CREATE TABLE `MEMBER` (
  `number` int NOT NULL AUTO_INCREMENT COMMENT '會員編號',
  `email` varchar(45) NOT NULL COMMENT '信箱',
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `birthday` datetime NOT NULL COMMENT '生日',
  `phoneNumber` varchar(45) NOT NULL COMMENT '電話',
  `address` varchar(45) NOT NULL COMMENT '地址',
  `ID` varchar(45) NOT NULL COMMENT '身分證字號',
  `password` varchar(45) NOT NULL COMMENT '密碼',
  `status` varchar(45) NOT NULL COMMENT '會員狀態',
  PRIMARY KEY (`number`)
)  COMMENT='會員資料' ;

INSERT INTO MEMBER values ('1', 'abc123@gmail.com', '王曉明', '1995-06-02 ', '0927378566', '台北市中山區南京東路三段87號5樓', 'F123456789', 'a123456', '管理員');
INSERT INTO MEMBER values ('2', 'efg123@gmail.com', '蔡依婷', '1986-03-27 ', '0978345672', '台北市大安區新生南路六段219號11樓', 'A234572345', 'b456789', '正常');
INSERT INTO MEMBER values ('3', 'klm123@gmail.com', '林進傑', '1992-04-28 ', '0915987356', '新北市中和區板南路三段34號7樓', 'B134689702', 'c3456786', '正常');
INSERT INTO MEMBER values ('4', 'opq123@gmail.com', '周杰德', '1978-08-15 ', '0984732654', '新北市板橋區文化路二段189號3樓', 'C178903456', 'd1456734', '正常');
INSERT INTO MEMBER values ('5', 'rst123@gmail.com', '徐佳欣', '1997-10-26 ', '0965413990', '台北市松山區民生東路六段55號2樓', 'D279645321', 'e5789345', '正常') ;

-- 館藏類別 start
CREATE TABLE STOCK_TYPE (
    typeId  	INT AUTO_INCREMENT NOT NULL COMMENT '類別ID',
    typeName     	VARCHAR(10) COMMENT '類別名稱',
    kind        VARCHAR(5) COMMENT '類型',
    CONSTRAINT STOCK_TYPE_typeId_PK PRIMARY KEY (typeId)
) AUTO_INCREMENT = 1;

-- INSERT INTO STOCK_TYPE (typeName) 
-- VALUES ('文學小說'),('商業理財');
-- INSERT INTO STOCK_TYPE (typeName) VALUES ('藝術設計'),('人文史地'),('心理勵志'),('生活風格'),('社會科學'),('自然科普'),
-- ('醫療保健'),('驚悚&恐佈'),('親子家庭'),('劇情'),('動作冒險'),('浪漫愛情'),('幽默喜劇'),('奇幻&科幻'),('犯罪&推理');



-- 館藏 start
CREATE TABLE STOCK (
    StockId		INT AUTO_INCREMENT NOT NULL COMMENT '館藏ID',
    typeId	INT NOT NULL COMMENT '類別ID_FK',
    stockName	VARCHAR(150) COMMENT '館藏名稱',
    author		VARCHAR(150) COMMENT '作者',
    press		VARCHAR(150) COMMENT '出版社',
    issuedDate		DATE COMMENT '出版日',
    stockContent 	TEXT COMMENT '館藏內容介紹',
    stockScore	DECIMAL(3,2) COMMENT '評價平均分數',
    stockImg		LONGBLOB COMMENT '館藏圖片',
    CONSTRAINT STOCK_stockId_PK PRIMARY KEY (stockId),
    CONSTRAINT STOCK_typeId_FK FOREIGN KEY (typeId) REFERENCES STOCK_TYPE (typeId)
) AUTO_INCREMENT = 1;

-- INSERT INTO STOCK (typeId,stockKind) VALUES (1,'書籍'),(2,'書籍'),(3,'書籍'),(4,'書籍'),(5,'書籍'),(6,'書籍'),(7,'書籍'),
-- (8,'書籍'),(9,'書籍'),(10,'書籍'),(11,'書籍'),(12,'書籍'),(13,'書籍'),(14,'書籍'),(15,'書籍'),(16,'書籍'),(17,'書籍');




-- 館藏清單 start
CREATE TABLE STOCK_LIST (
    listId 	INT AUTO_INCREMENT NOT NULL COMMENT '館藏清單ID',
    stockId 		INT NOT NULL COMMENT '館藏ID_FK',
    listStates 	INT COMMENT '狀態1_可借2_借出3_報廢',
    CONSTRAINT STOCK_LIST_listId_PK PRIMARY KEY (listId),
    CONSTRAINT STOCK_LIST_stockId_FK FOREIGN KEY (stockId) REFERENCES STOCK (stockId)
)AUTO_INCREMENT = 1;
-- INSERT INTO STOCK_LIST (stockId,listStates) VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3);



-- 圖書館資訊 start
CREATE TABLE LIBRARY (
    libId 		INT AUTO_INCREMENT NOT NULL COMMENT '圖書館ID',
    libName 	VARCHAR(50) COMMENT '圖書館名',
    libLoc	TEXT COMMENT '圖書館地址',
    libTime 	TEXT COMMENT '開放時間',
    libTel 		VARCHAR(20) COMMENT '聯絡電話',
    libEmail 	VARCHAR(50) COMMENT '聯絡信箱',
	CONSTRAINT LIBRARY_libId_PK PRIMARY KEY (libId)
)AUTO_INCREMENT = 1;
INSERT INTO LIBRARY(libName,libLoc,libTime,libTel,libEmail) VALUES ("Reader's Garden",'台北市中山區南京東路三段219號5樓','全年無休','02-2712-0589','tibame@gmail.com');


-- 圖書館地圖 start
CREATE TABLE LIBRARY_MAP (
    mapId 	INT AUTO_INCREMENT NOT NULL COMMENT '地圖ID_PK',
    libId 	INT NOT NULL COMMENT '圖書館編號_FK',
    floor 	VARCHAR(10) COMMENT '樓層',
    floorImg	LONGBLOB COMMENT '樓層圖', 
	CONSTRAINT LIBRARY_MAP_mapId_PK PRIMARY KEY (mapId),
	CONSTRAINT LIBRARY_MAP_libId_FK FOREIGN KEY (libId) REFERENCES LIBRARY (libId)
)AUTO_INCREMENT = 1;
INSERT INTO	LIBRARY_MAP (libId,floor) VALUES (1,'地下二樓'),(1,'地下一樓'),(1,'一樓'),(1,'二樓'),(1,'三樓');


-- 借閱訂單 start 建完會員表格才能建立
CREATE TABLE BORROW (
    borrowId   		INT AUTO_INCREMENT NOT NULL COMMENT '借閱ID',    
    memberId   		INT NOT NULL COMMENT '會員ID',
    stockId 		INT COMMENT '館藏ID',
    listId 	INT DEFAULT NULL COMMENT '館藏清單ID',
    preBoDate 	DATE COMMENT '預計借閱日',
    actBoDate 	DATE COMMENT '實際借閱日',
    preReDate 	DATE COMMENT '預計歸還日',
    actReDate 	DATE COMMENT '實際歸還日',
    scoreContent 	TEXT COMMENT '評價內容',
    scoreDate 		DATE COMMENT '評價日',
    score 			INT DEFAULT NULL COMMENT '評價分',
    boStates 	INT COMMENT '1.預訂 2.借閱 3.逾期未還 4.歸還 5.逾期歸還 6.取消',
    CONSTRAINT BORROW_borrowId_PK PRIMARY KEY (borrowId),
    CONSTRAINT BORROW_memberId_FK FOREIGN KEY (memberId) REFERENCES MEMBER (number),
    CONSTRAINT BORROW_stockId_FK FOREIGN KEY (stockId) REFERENCES STOCK (stockId),
    CONSTRAINT BORROW_listId_FK FOREIGN KEY (listId) REFERENCES STOCK_LIST (listId)
)AUTO_INCREMENT = 1;

-- INSERT INTO BORROW (memberId,stockId,listId,preBoDate,actBoDate,preReDate,actReDate,scoreContent,scoreDate,score,boStates) VALUES 
-- (1,1,1,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (2,2,2,STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-02-02','%Y-%m-%d'),STR_TO_DATE('2021-03-01','%Y-%m-%d'),STR_TO_DATE('2021-03-02','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,4),
-- (3,3,3,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (4,4,4,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (5,5,5,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (1,1,1,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (2,2,2,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (3,3,3,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (4,4,4,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3),
-- (5,5,5,STR_TO_DATE('2021-01-01','%Y-%m-%d'),STR_TO_DATE('2021-01-02','%Y-%m-%d'),STR_TO_DATE('2021-02-01','%Y-%m-%d'),STR_TO_DATE('2021-01-31','%Y-%m-%d'),'很棒',STR_TO_DATE('2021-01-15','%Y-%m-%d'),4,3);

-- 公告事項 start 要新增管理員才能run
CREATE TABLE BULLETIN (
	bulletinId INT AUTO_INCREMENT NOT NULL COMMENT '公告編號',
	memberId INT NOT NULL COMMENT '管理員編號_FK',
	buContent VARCHAR(100) COMMENT '公告事項',
	buDate DATE COMMENT '公告日期',
	CONSTRAINT BULLETIN_bulletinId_PK PRIMARY KEY (bulletinId),
	CONSTRAINT BULLETIN_memberId_FK FOREIGN KEY (memberId) REFERENCES MEMBER (number)
)AUTO_INCREMENT = 1;

INSERT INTO BULLETIN (memberId,buContent,buDate) VALUES (1,'PETER個人展即將推出',STR_TO_DATE('2021-01-01','%Y-%m-%d')),
(1,'PETER個人展2即將推出',STR_TO_DATE('2021-03-01','%Y-%m-%d')),(1,'PETER個人展3即將推出',STR_TO_DATE('2021-05-01','%Y-%m-%d')),
(1,'PETER個人展4即將推出',STR_TO_DATE('2021-07-01','%Y-%m-%d')),(1,'PETER個人展5即將推出',STR_TO_DATE('2021-09-01','%Y-%m-%d'));

-- 廠商資料
create table VENDOR(
	VENDORID int auto_increment not null comment '廠商編號',
    COMPANY varchar(60) not null comment '廠商名稱',
    `STATUS` varchar(20) not null default '停權' comment '廠商狀態', 
    TAXID int not null comment '統一編號',
    `NAME` varchar(60) not null comment '負責人',
    EMAIL varchar(60) not null,
    `PASSWORD` varchar(10) not null comment '密碼',
    TEL varchar(10) not null comment '電話',
    MOBILE varchar(10) comment '手機',
    ADDR varchar(100) not null comment '地址',
    constraint VENDOR_VENDORID_PK primary key (VENDORID)
);

insert into VENDOR (COMPANY, `STATUS`, TAXID, `NAME`, EMAIL, `PASSWORD`, TEL, MOBILE, ADDR)
values ('積體電路製造股份有限公司', '正常', 22099131, "張忠謀", 'chung@tsmc.com', 11111111, "035636688", "0912345678", '新竹市 東區 力行路25號');
insert into VENDOR (COMPANY, `STATUS`, TAXID, `NAME`, EMAIL, `PASSWORD`, TEL, ADDR)
values ('聯合數位文創', '正常', 25059316, '陳大人', 'cheng@gmail.com', 22222222, "0227923333", '新北市 汐止區 大同路一段369號');
insert into VENDOR (COMPANY, `STATUS`, TAXID, `NAME`, EMAIL, `PASSWORD`, TEL, ADDR)
values ('新通國際有限公司', '正常', 26062433, '艾嘉欣', 'aijaishin@gmail.com', 33333333, "0227923234", '台北市 中山區 八德路二段306號');
insert into VENDOR (COMPANY, `STATUS`, TAXID, `NAME`, EMAIL, `PASSWORD`, TEL, ADDR)
values ('好像有一間有限公司', '正常', 25039956, '張小小', 'aperson@gmail.com', 44444444, "0227923333", '台北市 大安區 辛亥路一段113號');


-- 場地資料
create table SITE (
	SITEID int auto_increment not null comment '場地資訊',
    `NAME` varchar(25) not null comment '場地名稱',
    AREA int comment '場地面積',
    DAYCOST int comment '單日費用',
    CAPACITY int comment '可容納人數',
    IMG longblob comment '場地圖片',
    constraint SITE_SITEID_PK primary key (SITEID),
    constraint SITE_NAME_UK unique key (`NAME`)
)AUTO_INCREMENT = 1;

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('神奇藝廊', 40, 14000, 50);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('日光通道', 52, 30000, 100);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('白色世界', 78, 60000, 150);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('視聽空間A區', 56, 30000, 80);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('視聽空間B區', 32, 15000, 35);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('藝術空間A區', 40, 10000, 50);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('藝術空間B區', 30, 6000, 40);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('藝術空間C區', 110, 100000, 50);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('藝術空間D區', 93, 92000, 60);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('演藝A棟', 85, 85000, 120);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('演藝B棟', 120, 130000, 200);

insert into SITE (`NAME`, AREA, DAYCOST, CAPACITY)
values ('演藝C棟', 60, 70000, 80);


-- 活動列表
create table ACTIVITY(
	ACTID int auto_increment not null comment '活動類型代號',
	ACTTYPE varchar(20) not null comment '活動類型',
	constraint ACTIVITY_ACTID_PK primary key (ACTID),
    constraint ACTIVITY_ACTTYPE_UK unique key (ACTTYPE)
)AUTO_INCREMENT = 1;

insert into ACTIVITY (ACTTYPE) values ('展覽');
insert into ACTIVITY (ACTTYPE) values ('畫廊');
insert into ACTIVITY (ACTTYPE) values ('講座');
insert into ACTIVITY (ACTTYPE) values ('主題市集');
insert into ACTIVITY (ACTTYPE) values ('電影放映');
insert into ACTIVITY (ACTTYPE) values ('舞台表演');

-- 場地活動清單
create table SITEACT (
	LISTID int auto_increment not null comment '清單編號',
    SITEID int comment '場地代號',
	ACTID int comment '活動類型代號',
    constraint SITEACT_LISTID_PK primary key (LISTID),
    constraint SITEACT_SITEID_FK foreign key (SITEID) references SITE (SITEID),
    constraint SITEACT_ACTID_FK foreign key (ACTID) references ACTIVITY (ACTID)
);

insert into SITEACT (SITEID, ACTID) values (1, 2);
insert into SITEACT (SITEID, ACTID) values (2, 4);
insert into SITEACT (SITEID, ACTID) values (3, 1);
insert into SITEACT (SITEID, ACTID) values (3, 2);
insert into SITEACT (SITEID, ACTID) values (3, 4);
insert into SITEACT (SITEID, ACTID) values (4, 3);
insert into SITEACT (SITEID, ACTID) values (4, 5);
insert into SITEACT (SITEID, ACTID) values (5, 3);
insert into SITEACT (SITEID, ACTID) values (5, 5);
insert into SITEACT (SITEID, ACTID) values (6, 1);
insert into SITEACT (SITEID, ACTID) values (6, 2);
insert into SITEACT (SITEID, ACTID) values (6, 4);
insert into SITEACT (SITEID, ACTID) values (7, 1);
insert into SITEACT (SITEID, ACTID) values (7, 2);
insert into SITEACT (SITEID, ACTID) values (8, 1);
insert into SITEACT (SITEID, ACTID) values (8, 2);
insert into SITEACT (SITEID, ACTID) values (9, 1);
insert into SITEACT (SITEID, ACTID) values (9, 2);
insert into SITEACT (SITEID, ACTID) values (10, 1);
insert into SITEACT (SITEID, ACTID) values (10, 3);
insert into SITEACT (SITEID, ACTID) values (10, 5);
insert into SITEACT (SITEID, ACTID) values (10, 6);
insert into SITEACT (SITEID, ACTID) values (11, 3);
insert into SITEACT (SITEID, ACTID) values (11, 5);
insert into SITEACT (SITEID, ACTID) values (11, 6);
insert into SITEACT (SITEID, ACTID) values (12, 1);
insert into SITEACT (SITEID, ACTID) values (12, 6);

-- 廠商活動
create table VENDORACT(
	VACTID int auto_increment not null comment '活動編號',
	`NAME` varchar(80) not null comment '活動名稱',
    VENDORID int not null comment '廠商編號',
	`DATE` timestamp default current_timestamp not null comment '訂單日期',
    AMOUNT int not null comment '訂單金額',
	SITEID int not null comment '場地代號',
    ACTID int not null comment '活動類型代號',
	PROGRESS varchar(25) default '0' comment '租借進度',
    RTLSTART date not null comment '租借開始日',
    RTLEND date not null comment '租借結束日',
    ACTSTART date not null comment '活動開始日',
    ACTEND date not null comment '活動結束日',
    TKSTART date not null comment '售票開始日',
    TKEND date not null comment '售票結束日',
    PRICE int not null comment '票價',
    ACTCNT longtext comment '活動內容',
    IMG longblob comment '活動照片',
    NOTE longtext comment '備註',
    constraint VENDORACT_VACTID_PK primary key (VACTID),
    constraint VENDORACT_VENDORID_FK foreign key (VENDORID) references VENDOR (VENDORID),
    constraint VENDORACT_SITEID_FK foreign key (SITEID) references SITE (SITEID),
    constraint VENDORACT_ACTID_FK foreign key (ACTID) references ACTIVITY (ACTID)
);

insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("華格納歌劇音樂會 《唐懷瑟》", 1, 45000, 11, 6, '3', 
		20210910, 20210915, 20210910, 20210915, 20210910, 20210915, 200,
        '來自海內外的創作者透過1秒24格的連續圖像，以3~20分鐘不等的短片，訴說溫馨童趣、奇思妙想的個人故事，到家庭關懷、社會、生態的議題。秋季的RG放映室帶你看見動畫的多元面向。');  
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《赤聲躁動音樂祭2021》", 1, 3000000, 11, 6, '3', 
		20210305, 20210320, 20210305, 20210320, 20210305, 20210320, 400,
        '胖團成軍13年，發行了第四張專輯「2049」，承襲著前三張專輯「玩」音樂的本質，繼續將成長的心路歷程化成旋律、生活體驗及喜怒哀樂轉為歌詞與聽眾產生共鳴。甜約翰以數字搖滾、爵士等元素為基底，融合取樣手法和豐富的聲響設計，開創了獨樹一格也能被大眾接受的流行曲風。老破麻樂團成軍於2016年，闖蕩台灣各大音樂節及Live House，首張創作EP《Extended Play》，曲風俏皮慵懶，偏執又隨性，厭世中又不忘詼諧的詞曲，深深擄獲眾歌迷的心。​預售票熱烈開賣中，80%卡司持即將公開！'); 
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("「永恆慕夏-線條的魔術」特展", 1, 660000, 4, 2, '3', 
		20211101, 20211130, 20211101, 20211130, 20211023, 20211130, 380,
        '★「永恆慕夏-線條的魔術」特展，睽違10年再次來台展出！台灣僅此一站！絕對不可錯過！ 
        ★超過200件展品，慕夏經典畫作、真跡手稿，以及意想不到各國受慕夏影響的設計作品，絕對要看！
        由慕夏基金會策劃的「永恆慕夏—線條的魔術」特展（Timeless Mucha-Mucha to Manga: The Magic of Line），
        以阿爾豐斯．慕夏作品中繾綣柔美的「線條」作為策展主軸，共分五大展區，展出逾200件展品，除了慕夏經典畫作、真跡手稿，更有影響其創作的收藏品。
        展區也將呈現深受「慕夏風格」影響的平面藝術家和設計師們作品，帶您以嶄新的觀點，去解開這位風格超越時代的「新藝術運動」代表人物的創作秘密。');

insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《奈良美智特展》", 1, 720000, 3, 1, '3',
		20211110, 20211225, 20211110, 20211225, 20211022, 20211225, 350,
        '為台灣創作的新作《朦朧潮溼的一天 Hazy Humid Day》、首度海外展出的《月光小姐Miss Moonlight》等53 件展品，更為高雄站增加 26件新作！包含今年初來台佈展隔離期間創作的 8 件素描作品、1件在今年初完成，也是首次在海外公開的繪畫作品、2019年畫在紙板上的《山子姊姊 YamakoSister/older》與《山子妹妹YamakoSister/younger》，還有《旅行的山子 Traveling Yamako》系列 15 件攝影作品。');
        
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("枝枒現象–臺灣當代金工與首飾創作譜記", 1, 90000, 2, 1, '3', 
		20220110, 20220325, 20220111, 20220325, 20210910, 20220325, 220,
        '「從一個描述的圖式談起，一株樹所延伸枝枒的現象圖式還原。」
		本次「枝枒現象–臺灣當代金工與首飾創作譜記」線上及實體特展，詳細梳理臺灣當代金工藝術脈絡，並展出新生代傑出作品。');      

insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("【Long Life Design展-貫穿生活的起點與設計】", 1, 900000, 7, 1, '3', 
		20220110, 20220325, 20220111, 20220325, 20210910, 20220325, 260,
        '貫穿生活的起點與設計-你是否曾經仔細觀察身邊那些長年陪伴至今的物品呢？這些物品不因時間的演進而消失，仍然與我們保有深厚的連結，即便市面上的商品不斷的推陳出新、或更加新穎合乎潮流，我們依然會選擇購買熟悉的物品，此狀態即是 LONG LIFE 的一種現象。
		今年8月，台灣設計館邀請選選研團隊，並與主辦Good Design Award的日本設計振興會合作，展出七十件在市場上長期生產，被無數人使用過的經典產品，並透過觀摩日本的長青設計品，瞭解 LONG LIFE 的概念，進而發現 DESIGN 對於生活的影響。希望傳遞「讓事物能長久存在並且持續對未來社會產生影響力」的觀念，那我們不僅優化了生活，更是共創了文化。');             
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("2022NTT遇見巨人─莎士比亞的妹妹們的劇團《混音理查三世》", 1, 425000, 10, 6, '2', 
		20220115, 20220122, 20220115, 20220122, 20211201, 20220122, 800,
        '要在邪惡的權利遊戲中存活下來，你只能變得比它更壞！
		英國萊斯特市中心教堂旁的停車場，挖出疑似遺骸，經DNA比對，證實為理查三世。這位莎翁筆下個性陰險的駝背跛子，歷史上聲名狼藉、童謠裡的怪物、邪惡的同義詞。四百年後，卻不斷有學者、作家企圖為之平反。
		2015首演口碑瘋傳，重演詢問度最高的《理查三世》，即將改版再現。重量級導演王嘉明變身DJ，將權力謀略的政治舞台化為音場，從具象到抽象的聲音跨幅取樣，重新混音莎翁歷史劇，讓歷史情節呼應臺灣政治亂象，探問何為歷史？何為真相？');        
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("【RG放映室】《2019-2020 TIAF得獎動畫片-小朋友篇》", 1, 45000, 7, 5, '1', 
		20220215, 20220222, 20220215, 20220222, 20220101, 20220222, 100,
        '來自海內外的創作者透過1秒24格的連續圖像，以3~20分鐘不等的短片，訴說溫馨童趣、奇思妙想的個人故事，到家庭關懷、社會、生態的議題。秋季的NTT放映室帶你看見動畫的多元面向。');  
 
 insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("【RG放映室】《2019-2020 TIAF得獎動畫片-大朋友篇》", 1, 45000, 7, 5, '0', 
		20220315, 20220322, 20220315, 20220322, 20220201, 20220322, 150,
        '來自海內外的創作者透過1秒24格的連續圖像，以3~20分鐘不等的短片，訴說溫馨童趣、奇思妙想的個人故事，到家庭關懷、社會、生態的議題。秋季的RG放映室帶你看見動畫的多元面向。');  
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《teamLab 未來遊樂園＆與花共生的動物們》", 1, 120000, 3, 1, '3', 
		20220415, 20220530, 20220415, 20220530, 20220415, 20220530, 260,
        ' 《teamLab未來遊樂園&與花共生的動物們》本次展覽由享譽國際、藝術團隊teamLab帶來引爆全球口碑熱議，超高人氣的9大數位藝術裝置，透過科技與藝術的交織，今年夏天將打造一個夢幻的數位遊樂園，讓每位入場的民眾，都能沉浸在感知自然、創造、藝術的夢幻世界，在夢幻的未來遊樂園中，與科技、藝術的互動，共同創造出與眾不同的藝術作品。');  

 insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("ART TAIPEI 2021 台北國際藝術博覽會", 1, 2000000, 7, 1, '3', 
		20220115, 20220220, 20220115, 20220220, 20211015, 20220220, 250,
        '今年雖持續遭逢新型冠狀病毒（COVID-19）疫情影響，ART TAIPEI 2021熱度不減，今年將帶來10個國家地區、超過100間畫廊的精彩展出，及12項嶄新的公共藝術及特區計畫呈現，兼具台灣特色與國際視野，讓來自各地的藝術愛好者看見台灣藝術蓬勃發展的生命力。
每年由文化部徵選八位年輕優秀藝術家，透過畫廊協會媒合商業畫廊於現場獨家展出，展現藝術界新血的創造力。
每年精選不同主題和形式，精心彙整而成的特展專區。以不同角度聚焦台灣藝術風貌，帶領觀眾們從各個層面體驗藝術，提升觀展體驗的深度及豐富度。
每年的台北國際藝術博覽會都會帶來精彩的藝術講座，豐富且具有深度的主題講座即將登場，敬請期待!'); 
 
 insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("大昆蟲展 我要當昆蟲王！", 1, 1800000, 7, 1, '2', 
		20220405, 20220430, 20220405, 20220430, 20220405, 20220430, 220,
        '歡迎來到英賽克特王國(InsectKingdom)，一場結合藝術、體驗、互動遊戲等最優質的生物情境教育展 本展藉「甲」想的英賽克特王國，五大城區來探索昆蟲世界！ 與超過『100』種的「活體昆蟲」零距離親密接觸，邀請「我要當昆蟲王」的你，一起身歷其境探訪英賽克特王國！ ▲王者聖殿 這裡是蟲蟲族人的家，來場零距離見面，百種以上的甲蟲居民都在這！還有珍貴稀有的甲蟲王族及蟲族烈士。 ▲蟲洞密道 穿梭人類與蟲蟲王國的秘密通道，融入蟲蟲國界真實體驗屬於它們的風俗民情。 ▲戰鬥大草原 進入蟲蟲們的練功場，一起和將士們修習舞藝，提升戰力！ ▲聖甲夜光城 一起加入蟲蟲的螢光派對，化身聖甲金龜推著巨型夜明珠，來場蟲界交際舞！ ▲英賽克特學院 典藏了蟲蟲世界的知識殿堂，有最美妙的蟲界音樂及瑰麗萬花筒，還可以挑戰蟲蟲博士，領取「蟲蟲勳章」喔！'); 
 
 insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("2022世界音樂節@臺灣", 1, 1500000, 11, 1, '3', 
		20220210, 20220215, 20220210, 20220215, 20211020, 20220215, 380,
        ' 在臺灣的你，絕對不能錯過的音樂節！來自世界各地才華洋溢的音樂團隊，遇見擁有多元風格且最具情感的臺灣特色音樂，交織策展人與音樂人的國際講座以及異國情調的特色市集！2021世界音樂節＠臺灣，同時具備探索、交流與跨界合作的藝術文化體驗，身為亞洲世界音樂城市之一的臺灣，將邀你一起拓展全球特色音樂的新視野！'); 
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《2022昭和夢之町：藝術家聯展》", 1, 2200000, 7, 1, '2', 
		20220420, 20220501, 20220420, 20220501, 20220220, 20220501, 280,
        '【昭和夢の町sleepwalking】，
首創微型展覽，將年代旅館與現代藝術家結合。
尋著帶點年代的氣息，歡迎闖進我們的夢裡，

在房間內肆意窺探創作者的夢，想像中穿梭各種口味的雲遊。

流水年華 昭和時光

將昭和意境和作品呈現有趣的動人的藝術感受。

2022/4/20開展日起，前200名入場者，將獲得限量酷卡包，內含7張7位藝術家紀念卡及活動限量明信片乙張。'); 


 insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《失敗博物館》", 1, 1200000, 7, 1, '3', 
		20220505, 20220520, 20220505, 20220520, 20220505, 20220520, 180,
        '「人們總是注意到成功，但失敗卻不被討論」
──失敗博物館館長 山謬．偉斯特
生活中數以萬計的產品因為各種需求被發明，隨著時代變遷及技術更新，有些產品在市場上大受歡迎，有些則因為不適用被淘汰。然而那些曾經被嘲笑的產品就應該被埋沒嗎？來自瑞典的失敗博物館蒐集了各領域中那些異想天開、令人費解或不敷使用的產品，每個失敗產品背後都有一個期待、創意和嘗試交織的故事，即使最終失敗了但還是值得尊敬。'); 
 
insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《小王子的藝想世界 75週年特展》", 1, 1200000, 6, 1, '2', 
		20220105, 20220120, 20220105, 20220120, 20220105, 20220120, 180,
        '睽違 7 年的《小王子》特展即將抵台！ 由台灣團隊展現的超人氣經典《小王子》故事文本，邀集國內外藝術家共同打造全新故事視角，從視覺藝術到驚喜引導的故事人聲，以及量身訂做的展場音樂環繞；透過奇幻氛圍的劇場空間設計與沈浸式互動投影聯手出擊，帶領台灣觀眾再次和小王子一起來趟暖心療癒的星球旅行。'); 

insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("《蒂姆・沃克：美妙事物》特展", 1, 1200000, 6, 1, '3', 
		20220201, 20220228, 20220201, 20220228, 20220201, 20220228, 180,
        '蒂姆・沃克的奇想世界｜真實打造魔幻童話
蒂姆．沃克擅長將自己的幻想化為攝影作品，風格鮮明、用色大膽，作品中常見巨大的道具、奇異的角色、浮誇的服裝、華麗隆重且充滿戲感的布景，令人感受到童話魔幻、浪漫詩意、荒誕暗黑等多重氛圍，看似衝突卻又散發著優雅自如的氣質。走進他的奇想世界，人們彷彿能暫時逃離現實的羈絆，享受意志上的完全自由。更重要的是，所有作品沒有使用電腦修圖，畫面所見一切都來自於真實的造景。
沃克的獨特之處在於不單單拍攝光鮮亮麗的時尚作品，他更透過源源不絕的創意與充滿觀點的思維，將時尚藝術化，成為一位遊走在時尚與藝術之間的創意思想家。'); 


insert into VENDORACT 
	(`NAME`, VENDORID, AMOUNT, SITEID, ACTID, PROGRESS, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT) 
values 
	("「角落小夥伴 歡樂城」", 1, 750000, 6, 1, '3', 
		20211018, 20211223, 20211018, 20211223, 20211018, 20211223, 350,
        '角落小夥伴主題限定將呈現於歡樂城每個角落，打造屬於大小朋友的歡樂天地！
看看是誰在夢時代購物中心，偷偷打造了屬於角落的魅力樂園！?

跟著「角落小夥伴們」闖遍五大童趣的層層關卡，隨即搭上「角落小夥伴 歡樂城」超萌小火車，一起遊遍整個樂園，找尋台灣獨家登場的小夥伴吧！

風靡全球！日本最Top！熱度蔓延燒至全台灣的「角落小夥伴」是一群既可愛又非常療癒的角色， 更是喜歡窩在角落裡的好夥伴！他們常常堆疊在一起，因為角落讓人好安心。 角落小夥伴是由五隻主要角色－「白熊 Shirokuma」、「企鵝？Penguin？」、「炸豬排 Tonkatsu」、「貓 Neko」、「蜥蜴Tokage」及他們的小小夥伴們，每個角色都有不同的特色與個性，讓我們一起期待【角落小夥伴 歡樂城】，大朋友帶著小朋友、爸爸媽媽牽著小寶貝們一起來到遊樂園區一同感受這歡樂城的遊戲魔力！

主題園區設有5大闖關關卡，讓大家身歷其境，「角落小夥伴 」等著你來搶先體驗，尋找歡樂旅程。成功闖關完成集章後，將會得到【角落小夥伴 歡樂城】限定可愛小禮物！快跟隨著「角落小夥伴」的腳步，一塊在歡樂城來一趟有趣的闖關之旅，開心暢遊一整天！'); 

 -- 訂單狀態 --

create table STATUSNAME (
	STATUSID int not null comment '狀態編號',
    `STATUS` varchar(20) comment '狀態',
    constraint STATUSNAME_STATUSID_PK primary key (STATUSID)
)AUTO_INCREMENT = 1;

INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('0', '訂單成立');
INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('1', '活動審核');
INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('2', '等待付款');
INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('3', '付款完成');
INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('99', '取消訂單');
INSERT INTO `rg01`.`STATUSNAME` (`STATUSID`, `STATUS`) VALUES ('100', '未通過審核');



 -- 討論區 -- 
        
CREATE TABLE article (
  AID INT NOT NULL,
  ACID INT NOT NULL,
  ACCTID INT NOT NULL,
  ANAME VARCHAR(45) NOT NULL,
  ADESCRIPT TEXT NOT NULL,
  APD timestamp default current_timestamp not null,
  PRIMARY KEY (AID)
) AUTO_INCREMENT = 1;

INSERT INTO article (AID, ACID, ACCTID, ANAME, ADESCRIPT) VALUES ('10', '100', '5', 'A', 'ABC');
INSERT INTO article (AID, ACID, ACCTID, ANAME, ADESCRIPT) VALUES ('20', '101', '6', 'B', 'DEF');
INSERT INTO article (AID, ACID, ACCTID, ANAME, ADESCRIPT) VALUES ('30', '102', '7', 'C', 'GHK');
INSERT INTO article (AID, ACID, ACCTID, ANAME, ADESCRIPT) VALUES ('40', '103', '8', 'D', 'LMN');


CREATE TABLE article_class (
  ACID INT NOT NULL,
  CLASSNAME VARCHAR(45) NULL,
  PRIMARY KEY (ACID)
) AUTO_INCREMENT = 1;

INSERT INTO article_class (ACID, CLASSNAME) VALUES ('100', 'Scary');
INSERT INTO article_class (ACID, CLASSNAME) VALUES ('101', 'Funny');
INSERT INTO article_class (ACID, CLASSNAME) VALUES ('102', 'Hilarious');
INSERT INTO article_class (ACID, CLASSNAME) VALUES ('103', 'Magical');

CREATE TABLE report (
  REPID INT NOT NULL,
  ACCTID INT NULL,
  AID INT NULL,
  PRIMARY KEY (REPID)
) AUTO_INCREMENT = 1;

INSERT INTO report (REPID, ACCTID, AID) VALUES ('11', '5', '10');
INSERT INTO report (REPID, ACCTID, AID) VALUES ('12', '6', '20');
INSERT INTO report (REPID, ACCTID, AID) VALUES ('13', '7', '30');
INSERT INTO report (REPID, ACCTID, AID) VALUES ('14', '8', '40');

CREATE TABLE liked (
  LIKEID INT NOT NULL,
  ACCTID INT NOT NULL,
  AID INT NULL,
  PRIMARY KEY (LIKEID)
) AUTO_INCREMENT = 1;

INSERT INTO liked (LIKEID, ACCTID, AID) VALUES ('1', '5', '10');
INSERT INTO liked (LIKEID, ACCTID, AID) VALUES ('2', '6', '20');
INSERT INTO liked (LIKEID, ACCTID, AID) VALUES ('3', '7', '30');
INSERT INTO liked (LIKEID, ACCTID, AID) VALUES ('4', '8', '40');

CREATE TABLE reply_record (
  SENUM INT NOT NULL,
  ACCTID INT NULL,
  AID INT NULL,
  REPDESCRIPT VARCHAR(45) NULL,
  PRIMARY KEY (SENUM)
) AUTO_INCREMENT = 1;

INSERT INTO reply_record (SENUM, ACCTID, AID, REPDESCRIPT) VALUES ('1', '5', '10', 'ABC');
INSERT INTO reply_record (SENUM, ACCTID, AID, REPDESCRIPT) VALUES ('2', '6', '20', 'DEF');
INSERT INTO reply_record (SENUM, ACCTID, AID, REPDESCRIPT) VALUES ('3', '7', '30', 'GHK');
INSERT INTO reply_record (SENUM, ACCTID, AID, REPDESCRIPT) VALUES ('4', '8', '40', 'LMN');

CREATE TABLE ticket_backend (
  TICKETID INT NOT NULL,
  TOTALNUMT INT NULL,
  TLEFT INT NULL,
  TPRICE INT NULL,
  ACTID INT NULL,
  PRIMARY KEY (TICKETID)
) AUTO_INCREMENT = 1;

INSERT INTO ticket_backend (TICKETID, TOTALNUMT, TLEFT, TPRICE, ACTID) VALUES ('10', '200', '30', '100', '40');
INSERT INTO ticket_backend (TICKETID, TOTALNUMT, TLEFT, TPRICE, ACTID) VALUES ('20', '200', '29', '150', '41');
INSERT INTO ticket_backend (TICKETID, TOTALNUMT, TLEFT, TPRICE, ACTID) VALUES ('30', '200', '28', '200', '42');
INSERT INTO ticket_backend (TICKETID, TOTALNUMT, TLEFT, TPRICE, ACTID) VALUES ('40', '200', '27', '250', '43');


-- 測試用 書籤表格
CREATE TABLE marks (
	MARKSID int auto_increment not null comment '書籤編號',
	memberId int not null comment '會員編號',
    stockId int comment '館藏ID',
    mark_status int not null default 1 comment '1.加入 2.取消',
    constraint marks_MARKSID_PK primary key (MARKSID),
    constraint marks_memberId_FK foreign key (memberId) references `MEMBER` (`number`),
    constraint marks_stockId_FK foreign key (stockId) references stock (stockId)
) AUTO_INCREMENT = 1;

CREATE TABLE ORDERITEM (
	ORDERID INT NOT NULL auto_increment COMMENT '訂單編號',
    VACTID INT NOT NULL COMMENT '活動編號',
	MEMBERID INT NOT NULL COMMENT '會員編號',
	ORDERCOUNT INT NOT NULL COMMENT '數量',
    TOTAL INT NOT NULL COMMENT '總價',
    ORDERTIME timestamp COMMENT '訂單完成時間',
    ORDERSTATUS INT NOT NULL DEFAULT 1 COMMENT '訂單狀態 1.待付款 2.付款 3.取消',
    PRIMARY KEY (ORDERID)
) AUTO_INCREMENT = 1;

CREATE TABLE ORDERDETAIL (
	DETAILID INT NOT NULL auto_increment COMMENT '明細編號',
    ORDERID INT NOT NULL COMMENT '訂單編號',
    VACTID INT NOT NULL COMMENT '活動編號',
    STARTDATE DATE NOT NULL COMMENT '開始時間',
    STOPDATE DATE NOT NULL COMMENT'結束時間',
    QRCODE LONGBLOB COMMENT 'QRCODE',
    ENTERDATE DATE COMMENT '入場時間',
	DETAILSTATUS INT NOT NULL DEFAULT 1 COMMENT '票券狀態1.有效 2.過期',
    PRIMARY KEY (DETAILID)
) AUTO_INCREMENT = 1;


DROP TABLE IF EXISTS PARTIINF;
DROP TABLE IF EXISTS EVENTINF;
DROP TABLE IF EXISTS EVENTCATEINF;


-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1; 
-- 揪團類別資訊
CREATE TABLE EVENTCATEINF (
 eventcateID     INT AUTO_INCREMENT NOT NULL comment '揪團類別ID',
 eventCateName      VARCHAR(25) not null comment '揪團類別名稱',
 CONSTRAINT EVENTCATEINF_eventcateID_PK PRIMARY KEY (eventcateID)
) AUTO_INCREMENT = 1;

INSERT INTO EVENTCATEINF (eventcateID, eventCateName)  VALUES (1,'逛展');
INSERT INTO EVENTCATEINF (eventcateID, eventCateName)  VALUES (2,'讀書會');
INSERT INTO EVENTCATEINF (eventcateID, eventCateName)  VALUES (3,'其他');



-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1; 
-- 揪團資訊

CREATE TABLE EVENTINF (
 eventID INT AUTO_INCREMENT NOT NULL comment '揪團編號',
 eventCateID   int not null comment '揪團類別編號',
 memberID   int not null comment '會員ID',
    capacity   int not null comment '人數限制',
    eventName   VARCHAR(25) not null comment '活動名稱',
    eventDescription  VARCHAR(100) null comment '活動內容',
    eventStart   datetime not null comment '活動開始時間',
    eventEnd   datetime not null comment '活動結束時間',
 eventappStart datetime not null comment '活動報名開始時間',
    eventappEnd  datetime not null comment '活動報名結束時間',
    eventStatus  int not null comment '活動狀態0正常 1中止',
 CONSTRAINT EVENTINF_eventID_PK PRIMARY KEY (eventID),
    CONSTRAINT EVENTINF_eventCateID_FK FOREIGN KEY (eventCateID) REFERENCES EVENTCATEINF (eventCateID),
    CONSTRAINT EVENTINF_memberID_FK FOREIGN KEY (memberID) REFERENCES MEMBER (`number`)
) AUTO_INCREMENT = 1;

INSERT INTO EVENTINF (eventCateID, memberID, capacity, eventName, eventDescription, eventStart, eventEnd, eventappStart, eventappEnd, eventStatus) 
 VALUES (2, 1, 4,'Java讀書會','需要一起複習奮鬥JAVA的夥伴～','2021-10-25 09:00:00','2021-10-25 18:00:00','2021-10-05 18:00:00','2021-10-20 18:00:00',0);
INSERT INTO EVENTINF (eventCateID, memberID, capacity, eventName, eventDescription, eventStart, eventEnd, eventappStart, eventappEnd, eventStatus) 
 VALUES (1, 5, 3,'逛慕夏展','不想一個人逛「永恆慕夏-線條的魔術」，徵求同行者第一天衝展','2022-08-05 09:00:00','2022-08-05 15:00:00','2022-05-01 00:00:00','2022-08-01-23:59',0);
INSERT INTO EVENTINF (eventCateID, memberID, capacity, eventName, eventDescription, eventStart, eventEnd, eventappStart, eventappEnd, eventStatus) 
 VALUES (2, 3, 10,'科幻主題閱讀分享會','歡迎對科幻小說跟電影有興趣的同好，一起來空間跟同好分享最近觀賞過的作品，以下近期主題特別徵求：沙丘、三體。','2022-01-09 13:00:00','2022-01-09 17:00:00','2021-12-01 00:00:00','2021-12-20 23:59:00',0);
INSERT INTO EVENTINF (eventCateID, memberID, capacity, eventName, eventDescription, eventStart, eventEnd, eventappStart, eventappEnd, eventStatus) 
 VALUES (3, 2, 6,'打籃球','公園內3對3','2022-02-05 13:00:00','2022-02-05 19:00:00','2022-01-01 00:00:00','2022-01-20 23:59:00',0);


-- 揪團參與人資訊
CREATE TABLE PARTIINF (
 eventID     INT not null comment '揪團編號',
 memberID     INT  NOT NULL comment '會員ID',
 CONSTRAINT PARTIINF_memberID_FK FOREIGN KEY (memberID) REFERENCES MEMBER (`number`),
 CONSTRAINT PARTIINF_eventID_FK FOREIGN KEY (eventID) REFERENCES EVENTINF (eventID),
    CONSTRAINT PARTIINF_PK PRIMARY KEY (memberID, eventID)
) ;




INSERT INTO PARTIINF (eventID, memberID)  VALUES (1,5);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (1,4);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (1,2);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (1,3);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (2,5);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (2,2);
INSERT INTO PARTIINF (eventID, memberID)  VALUES (2,4);





-- 以下測試變量用:
-- show variables like '%auto_inc%';
-- show session variables like '%auto_inc%';  -- //session變量
-- show global variables  like '%auto_inc%';  -- //global變量

-- 以下測試環境的 版本、SSL、 字元編碼用:
-- select version();
-- show variables like '%ssl%';  [ 或執行 mysql> \s ]
-- show variables like '%character%';

