package org.wso2.security.tools.util.database_tut;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

public class UpdateDemo {

    public static void main(String[] args) throws UnknownHostException {

        // To connect to mongodb server
        MongoClient mongoClient = DatabaseUtils.getMongoClient();

        // Now connect to your databases
        DB db = mongoClient.getDB(Constants.DB_NAME);

        // Lấy ra Collection với tên City.
        DBCollection city = db.getCollection("City");

        // Find City has city_no ='WAS'
        DBObject whereClause = new BasicDBObject("city_no", "WAS");

        DBObject values = new BasicDBObject();
        values.put("population", 1200000);
        values.put("description", "Pop 2014");
        values.put("note", "Document replaced!");

        // Execute update.
        WriteResult result = city.update(whereClause, values);
        int effectedCount = result.getN();
        System.out.println("Effected Count: " + effectedCount);
        System.out.println("Done!");
    }

}