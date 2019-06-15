DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `endTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `couponId` int(11) DEFAULT NULL,
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `activity` WRITE;
INSERT INTO `activity` VALUES
                              (2,'春季外卖节','春季外卖节','2019-04-23 17:55:59',5,'2019-04-20 17:55:59'),
                              (3,'春季外卖节','春季外卖节','2019-04-23 17:55:59',6,'2019-04-20 17:55:59'),
                              (4,'测试活动','测试活动','2019-04-26 16:00:00',8,'2019-04-20 16:00:00');
UNLOCK TABLES;

DROP TABLE IF EXISTS `activityMovie`;
CREATE TABLE `activityMovie` (
  `activityId` int(11) DEFAULT NULL,
  `movieId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `targetAmount` int DEFAULT NULL,
  `discountAmount` int DEFAULT NULL,
  `startTime` timestamp NOT NULL,
  `endTime` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `coupon` WRITE;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',20,5,'2019-04-20 17:47:54','2019-04-23 17:47:59'),
                            (5,'测试优惠券','品质联盟',30,4,'2019-04-20 21:14:46','2019-04-24 21:14:51'),
                            (6,'春节电影节优惠券','电影节优惠券',50,10,'2019-04-20 21:15:11','2019-04-21 21:14:56'),
                            (8,'测试优惠券','123',100,99,'2019-04-20 16:00:00','2019-04-26 16:00:00');
UNLOCK TABLES;

DROP TABLE IF EXISTS `couponUser`;
CREATE TABLE `couponUser` (
  `couponId` int(11) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `couponUser` WRITE;
INSERT INTO `couponUser` VALUES (8,15),(5,15),(8,15),(6,15),(5,15),(8,15),(6,15);
UNLOCK TABLES;

DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `column` int(11) DEFAULT NULL,
  `row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `hall` WRITE;
INSERT INTO `hall` VALUES (1,'1号厅',10,5),
                          (2,'2号厅',12,8);
UNLOCK TABLES;

DROP TABLE IF EXISTS `invalidSeats`;
CREATE TABLE `invalidSeats` (
                        `id` int(11) NOT NULL,
                        `column` int(11) NOT NULL,
                        `row` int(11) NOT NULL,
                        PRIMARY KEY (`id`, `column`, `row`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posterUrl` varchar(1023) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `startDate` timestamp NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `movie` WRITE;
INSERT INTO `movie` VALUES
(1, "https://p0.meituan.net/movie/47af2656af6cd0110057bc527b862c665484423.jpg@464w_644h_1e_1c", "动作,冒险,科幻", "美国", "英语", 114, "2019-06-06", "X战警：黑凤凰", "在一次危及生命的太空营救行动中，琴·葛蕾（苏菲·特纳 饰）被神秘的宇宙力量击中，成为最强大的变种人。此后琴不仅要设法掌控日益增长、极不稳定的力量，更要与自己内心的恶魔抗争，她的失控让整个X战警大家庭分崩离析，也让整个星球陷入毁灭的威胁之中……", 0),
(2, "https://p0.meituan.net/movie/71fba05fbe4912cb70d27b87c3c856393364925.jpg@464w_644h_1e_1c", "科幻,灾难,动作", "美国", "英语", 132, "2019-05-31", "哥斯拉2：怪兽之王", "随着《哥斯拉》和《金刚：骷髅岛》在全球范围内的成功，华纳兄弟影片公司和传奇影业联手开启了怪兽宇宙系列电影的新篇章—一部史诗级动作冒险巨制。在这部电影中，哥斯拉将和众多大家在流行文化中所熟知的怪兽展开较量。全新故事中，研究神秘动物学的机构“帝王组织”成员将勇敢直面巨型怪兽，其中强大的哥斯拉也将和魔斯拉、拉顿和它的死对头——三头王基多拉展开激烈对抗。当这些只存在于传说里的超级生物再度崛起时，它们将展开王者争霸，人类的命运岌岌可危……", 0),
(3, "https://p0.meituan.net/movie/b6e77d67efdc6ac89a52b956ead366ae5785152.jpg@464w_644h_1e_1c", "爱情,青春", "中国大陆", "国语", 110, "2019-06-06", "最好的我们", "每个人的心里大概都藏着一个念念不忘的人。一个偶然被提及的名字，让女摄影师耿耿（何蓝逗 饰）内心掀起万千波澜，触动了回忆的开关，那个撩人心动的少年余淮（陈飞宇 饰）再度闯进她的思绪。那是记忆里最好的时光，“学渣”耿耿和“学霸”余淮成了同桌，还结识了简单（王初伊 饰）、贝塔（周楚濋 饰）、徐延亮（陈帅 饰）。校园里充盈着专属少男少女们的懵懂、青涩、怦然心动和勇敢，耿耿余淮也拥有了他们的约定。高考后，当耿耿满怀期待憧憬约定兑现之时，余淮却忽然消失不见了。七年后两人重逢，余淮当年未说出口的那句话、他不辞而别的秘密，耿耿能否得到解答？这段耿耿于怀的过往，让两人再度面临情感的抉择……", 0),
(4, "https://p0.meituan.net/movie/c0a02897fa49e5c529bfe3cead0f49652270427.jpg@464w_644h_1e_1c", "犯罪,剧情,动作", "中国大陆,中国香港", "国语", 103, "2019-06-06", "追龙Ⅱ", "悍匪龙志强（梁家辉 饰），在香港回归前，趁香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天（古天乐 饰）卧底潜入龙志强犯罪团伙，发现他正策划绑架澳门富豪贺不凡，最终陆港警察合力勇擒龙志强，救出贺不凡。", 0),
(5, "https://p0.meituan.net/moviemachine/262f95bad79b6ae45b978593157cb68550938.jpg@464w_644h_1e_1c", "动作,科幻,冒险", "美国", "英语", 115, "2019-06-14", "黑衣人：全球追缉", "英国黑衣人总部王牌探员H（克里斯·海姆斯沃斯 饰）与新晋探员M（泰莎·汤普森 饰）在阻止外星团伙入侵的过程中意外铲除了隐藏在黑衣人组织中的内奸，成功拯救世界。", 0),
(6, "https://p1.meituan.net/moviemachine/42ef79dd1d894b67dd57de383d753c03556009.jpg@464w_644h_1e_1c", "爱情,奇幻,冒险", "美国", "英语", 128, "2019-05-24", "阿拉丁", "在充满异域风情的古代阿拉伯王国，善良的穷小子阿拉丁（莫纳·马苏德 饰）和勇敢的茉莉公主（娜奥米·斯科特 饰）浪漫邂逅，在可以满足主人三个愿望的神灯精灵帮助下，两人踏上了一次寻找真爱和自我的魔幻冒险。", 0),
(7, "https://p1.meituan.net/movie/06daf32301ff2fd54f6ffd59299d0326672524.jpg@464w_644h_1e_1c", "动画,剧情", "日本", "日语/国语", 111, "2019-06-01", "哆啦A梦：大雄的月球探险记", "月球探测器在月亮上捕捉到了白影，大雄（大原惠美 配音）认为这道白影是月亮上的兔子，惹来了大家的耻笑，于是哆啦A梦（水田山葵 配音）为了帮助大雄，利用道具“异说俱乐部徽章”，在月球背面制造了一个兔子王国。一天，神秘少年露卡（皆川纯子 配音）转学而来，与大雄和伙伴们一同前往月亮上的月兔王国展开了一场别开生面的浪漫想象力之旅。", 0),
(8, "https://p0.meituan.net/movie/36b245c374ec10f6aba4d337f78e97f12022575.jpg@464w_644h_1e_1c", "剧情,爱情,犯罪", "印度", "英语", 133, "2019-06-05", "无所不能", "盲人罗汉（赫里尼克·罗斯汉 饰）对生活积极向上的态度让他跟平常人没什么两样。经人介绍，他认识了独立自信的盲人女孩苏皮莉亚（亚米·高塔姆 饰）。本来不想结婚的苏皮莉亚在罗汉的关心下慢慢意识到了幸福是存在的，两个有缺陷的人同样可以组成完整的家庭。 本应该开始幸福婚姻的两人，不幸遭遇到无妄之灾，突如其来的重大变故让罗汉陷入深深的悲痛、无助和绝望，继而开始了“独行侠”式的反击复仇。", 0),
(9, "https://p0.meituan.net/movie/30b20139e68c46d02e0893277d633b701292458.jpg@464w_644h_1e_1c", "动画,奇幻,冒险,家庭", "日本", "日语/国语", 125, "2019-06-21", "千与千寻", "千寻和爸爸妈妈一同驱车前往新家，在郊外的小路上不慎进入了神秘的隧道——他们去到了另外一个诡异世界—一个中世纪的小镇。远处飘来食物的香味，爸爸妈妈大快朵颐，孰料之后变成了猪！这时小镇上渐渐来了许多样子古怪、半透明的人。千寻仓皇逃出，一个叫小白的人救了他，喂了她阻止身体消失的药，并且告诉她怎样去找锅炉爷爷以及汤婆婆，而且必须获得一份工作才能不被魔法变成别的东西。千寻在小白的帮助下幸运地获得了一份在浴池打杂的工作。渐渐她不再被那些怪模怪样的人吓倒，并从小玲那儿知道了小白是凶恶的汤婆婆的弟子。一次，千寻发现小白被一群白色飞舞的纸人打伤，为了救受伤的小白，她用河神送给她的药丸驱出了小白身体内的封印以及守封印的小妖精，但小白还是没有醒过来。为了救小白，千寻又踏上了她的冒险之旅。", 0),
(10, "https://p0.meituan.net/movie/f29c0f9ff0340d00085f4bc1a395ecf02603950.jpg@464w_644h_1e_1c", "冒险,奇幻,喜剧", "美国", "英语", 104, "2019-05-10", "大侦探皮卡丘", "蒂姆·古德曼（贾斯提斯·史密斯 饰） 为寻找下落不明的父亲来到莱姆市，意外与父亲的前宝可梦搭档大侦探皮卡丘（瑞恩·雷诺兹 配音）相遇，并惊讶地发现自己是唯一能听懂皮卡丘说话的人类，他们决定组队踏上揭开真相的刺激冒险之路。探案过程中他们邂逅了各式各样的宝可梦，并意外发现了一个足以毁灭整个宝可梦宇宙的惊天阴谋。", 0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `movieLike`;
CREATE TABLE `movieLike` (
  `movieId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movieId`, `userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `movieLike` WRITE;
INSERT INTO `movieLike` VALUES
                               (10,12,'2019-03-25 02:40:19'),
                               (11,1,'2019-03-22 09:38:12'),
                               (11,2,'2019-03-23 09:38:12'),
                               (11,3,'2019-03-22 08:38:12'),
                               (12,1,'2019-03-23 09:48:46'),
                               (12,3,'2019-03-25 06:36:22'),
                               (14,1,'2019-03-23 09:38:12'),
                               (16,12,'2019-03-23 15:27:48');
UNLOCK TABLES;

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hallId` int(11) NOT NULL,
  `movieId` int(11) NOT NULL,
  `startTime` timestamp NOT NULL,
  `fare` double NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `schedule` WRITE;
INSERT INTO `schedule` VALUES
      (1, 1, 3,'2019-06-10 02:40:19', 70, "英语");
UNLOCK TABLES;

DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
                          `orderId` int(11) DEFAULT NULL,
                          `columnIndex` int(11) DEFAULT NULL,
                          `rowIndex` int(11) DEFAULT NULL,
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                        `userId` int(11) DEFAULT NULL,
                        `scheduleId` int(11) DEFAULT NULL,
                        `time` timestamp DEFAULT CURRENT_TIMESTAMP,
                        `status` tinyint(4) DEFAULT NULL,
                        `price` int(11) DEFAULT NULL,
                        `method` tinyint(4) DEFAULT NULL,
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
                         `hours` int(11) DEFAULT NULL,
                         `get` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES
                          (1,'testname','123456',0),
                          (3,'test','123456',0),
                          (5,'test1','123456',0),
                          (7,'test121','123456',0),
                          (8,'root','123456',1),
                          (9,'root2','123456',2),
                          (10,'roottt','123123',0),
                          (12,'zhourui','123456',0),
                          (13,'abc123','abc123',0),
                          (15,'dd','123',0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `view`;
CREATE TABLE `view` (
  `day` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `view` WRITE;
INSERT INTO `view` VALUES (30);
UNLOCK TABLES;

DROP TABLE IF EXISTS `vipCard`;
CREATE TABLE `vipCard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `joinDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `vipCard` WRITE;
INSERT INTO `vipCard` VALUES (1,15,375,'2019-04-21 13:54:38'),
                             (2,12,660,'2019-04-17 18:47:42');
UNLOCK TABLES;

DROP TABLE IF EXISTS `cardPromotion`;
CREATE TABLE `cardPromotion` (
  `target` int(11) DEFAULT NULL,
  `gift` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `cardPromotion` WRITE;
INSERT INTO `cardPromotion` VALUES (30000, 3000),
                                   (50000, 8000),
                                   (80000, 16000),
                                   (100000000, 200000000);
UNLOCK TABLES;


DROP TABLE IF EXISTS `cardCharge`;
CREATE TABLE `cardCharge` (
                 `id` int(11) DEFAULT NULL,
                 `charge` int(11) DEFAULT NULL,
                 `gift` int(11) DEFAULT NULL,
                 `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                 `mention` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
