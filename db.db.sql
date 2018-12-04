BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `dish` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`img_url`	TEXT,
	`name`	TEXT NOT NULL,
	`price`	NUMERIC,
	`flavor_id`	INTEGER,
	`ingredient`	TEXT,
	`restaurant_id`	INTEGER,
	`available_time`	TEXT,
	`cooking_instruction_url`	TEXT,
	`style`	TEXT,
	`is_favourite`	NUMERIC DEFAULT 0,
	FOREIGN KEY(`restaurant_id`) REFERENCES `restaurant`(`id`)
);
INSERT INTO `dish` VALUES (1,'http://www.tzcy37.com/uploads/pic/a1020.jpg','Cold Noodle Xi''an Style',8,1,'cold noodle, cucumber, cilantro, green bean sprouts',1,'12:00-17:00','https://www.youtube.com/watch?v=Ru8F4vesAUo','China',0);
INSERT INTO `dish` VALUES (2,'http://www.mingjih.com/userfiles/images/001/VW8_2688_%E9%A0%86%E5%8C%96%E7%B1%B3%E7%B2%89%E6%B9%AF%20small.jpg','Beef Vermicelli in Spicy Soup',7.75,1,'rice noodle, beef, cilantro, oinion, sea food, green bean sprouts, mint leaf, lemon',2,'11:00-21:00','https://www.youtube.com/watch?v=zCaGOQLpTt0','Vietnam',0);
INSERT INTO `dish` VALUES (3,'http://img.88tph.com/production/20180723/12702938-0.jpg!/watermark/url/L3BhdGgvbG9nby5wbmc/align/center','HAEMUL PAJEON',10.99,3,'seafood pancake, green onion, vegetables',3,'11:30-21:00','https://www.youtube.com/watch?v=DuNnkO-ZVTE','Korea ',0);
INSERT INTO `dish` VALUES (4,'http://i2.chuimg.com/040438d0890511e6b87c0242ac110003_375w_375h.jpg?imageView2/2/w/660/interlace/1/q/90','Xinjiang Large Plate Chicken',0,4,'chicken, potato, onion, carrot, red pepper, green pepper',4,'10:00-22:00','https://www.youtube.com/watch?v=8QTY3diOnGo','China',0);
INSERT INTO `dish` VALUES (5,'https://cp1.douguo.com/upload/caiku/c/8/9/yuan_c821f9323c3b90ae6bf46c9fc8acbda9.jpg','Green Bean Jelly',0,4,'green bean starch, water',4,'10:00-22:00','https://www.youtube.com/watch?v=Ut39_zR7L_o&list=PLsqGXA4funcPz5TGAHTf95NgcAJSTKzdN','China',0);
COMMIT;
