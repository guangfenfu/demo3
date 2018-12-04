package com.example.myfood.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public MySQLiteOpenHelper(Context context, String name){
        this(context, name, null, 1, null);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS `restaurant` (\n" +
                    "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t`name`\tTEXT,\n" +
                    "\t`address`\tTEXT,\n" +
                    "\t`open_time`\tTEXT\n" +
                    ")");

            db.execSQL("INSERT INTO `restaurant` VALUES (1,'Golden Loong Restaurant','2237 Pembina Hwy, Winnipeg, MB R3T 2H1','12:00-21:00')");
            db.execSQL("INSERT INTO `restaurant` VALUES (2,'Nhu Quynh Restaurant','609 Ellice Ave, Winnipeg, MB R3G 0A4','11:00-21:00')");
            db.execSQL("INSERT INTO `restaurant` VALUES (3,'Kimbaek Restaurant','193 Isabel St, Winnipeg, MB R3A 1G8','11:30-21:00')");
            db.execSQL("INSERT INTO `restaurant` VALUES (4,'Wonderful Restaurant','525 Lanark St, Winnipeg, MB R3N 1L8','10:00-22:00')");


            db.execSQL("CREATE TABLE IF NOT EXISTS `flavor` (\n" +
                    "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t`description`\tTEXT NOT NULL\n" +
                    ")");

            db.execSQL("INSERT INTO `flavor` VALUES (1,'Spicy')");
            db.execSQL("INSERT INTO `flavor` VALUES (2,'Sweet')");
            db.execSQL("INSERT INTO `flavor` VALUES (3,'Normal')");
            db.execSQL("INSERT INTO `flavor` VALUES (4,'Multiple')");

            db.execSQL("CREATE TABLE IF NOT EXISTS `dish` (\n" +
                    "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t`img_url`\tTEXT,\n" +
                    "\t`name`\tTEXT NOT NULL,\n" +
                    "\t`price`\tNUMERIC,\n" +
                    "\t`flavor_id`\tINTEGER,\n" +
                    "\t`ingredient`\tTEXT,\n" +
                    "\t`restaurant_id`\tINTEGER,\n" +
                    "\t`available_time`\tTEXT,\n" +
                    "\t`cooking_instruction_url`\tTEXT,\n" +
                    "\t`style`\tTEXT,\n" +
                    "\t`is_favourite`\tNUMERIC DEFAULT 0,\n" +
                    "\tFOREIGN KEY(`restaurant_id`) REFERENCES `restaurant`(`id`)\n" +
                    ")");

            db.execSQL("INSERT INTO `dish` VALUES (1,'http://www.tzcy37.com/uploads/pic/a1020.jpg','Cold Noodle Xi''an Style',8,1,'cold noodle, cucumber, cilantro, green bean sprouts',1,'12:00-17:00','https://www.youtube.com/watch?v=Ru8F4vesAUo','China',0)");
            db.execSQL("INSERT INTO `dish` VALUES (2,'http://www.mingjih.com/userfiles/images/001/VW8_2688_%E9%A0%86%E5%8C%96%E7%B1%B3%E7%B2%89%E6%B9%AF%20small.jpg','Beef Vermicelli in Spicy Soup',7.75,1,'rice noodle, beef, cilantro, oinion, sea food, green bean sprouts, mint leaf, lemon',2,'11:00-21:00','https://www.youtube.com/watch?v=zCaGOQLpTt0','Vietnam',0)");
            db.execSQL("INSERT INTO `dish` VALUES (3,'http://img.88tph.com/production/20180723/12702938-0.jpg!/watermark/url/L3BhdGgvbG9nby5wbmc/align/center','HAEMUL PAJEON',10.99,3,'seafood pancake, green onion, vegetables',3,'11:30-21:00','https://www.youtube.com/watch?v=DuNnkO-ZVTE','Korea ',0)");
            db.execSQL("INSERT INTO `dish` VALUES (4,'http://i2.chuimg.com/040438d0890511e6b87c0242ac110003_375w_375h.jpg?imageView2/2/w/660/interlace/1/q/90','Xinjiang Large Plate Chicken',0,4,'chicken, potato, onion, carrot, red pepper, green pepper',4,'10:00-22:00','https://www.youtube.com/watch?v=8QTY3diOnGo','China',0)");
            db.execSQL("INSERT INTO `dish` VALUES (5,'https://cp1.douguo.com/upload/caiku/c/8/9/yuan_c821f9323c3b90ae6bf46c9fc8acbda9.jpg','Green Bean Jelly',0,4,'green bean starch, water',4,'10:00-22:00','https://www.youtube.com/watch?v=Ut39_zR7L_o&list=PLsqGXA4funcPz5TGAHTf95NgcAJSTKzdN','China',0)");

        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
