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

public class UpdateWithIncDemo {

    public static void main(String[] args) throws UnknownHostException {

        // To connect to mongodb server
        MongoClient mongoClient = DatabaseUtils.getMongoClient();

        // Now connect to your databases
        DB db = mongoClient.getDB(Constants.DB_NAME);

        // Lấy ra Collection với tên City.
        DBCollection city = db.getCollection("City");

        // Find City has city_no = 'NYO'
        DBObject whereClause = new BasicDBObject("city_no", "NYO");

        DBObject values = new BasicDBObject();
        values.put("population", 10000);


        DBObject valuesWithInc = new BasicDBObject();
        valuesWithInc.put("$inc", values);


        // Execute update.
        WriteResult result = city.update(whereClause, valuesWithInc);

        int effectedCount = result.getN();
        System.out.println("Effected Count: " + effectedCount);
        System.out.println("Done!");
    }

}


//10.4- Update example with $set and $inc
//    DBObject values1 = new BasicDBObject();
//values1.put("population", 10000);
//
//
//        DBObject values2 = new BasicDBObject();
//        values2.put("description", "Pop 2014");
//
//
//        DBObject valuesSI = new BasicDBObject();
//        valuesSI.put("$inc", values1);
//        valuesSI.put("$set", values2);
//
//
//        WriteResult result = city.update(whereClause, valuesSI);


//10.5- Update multiple documents
// DBCollection class:

   // public WriteResult updateMulti( DBObject query , DBObject value )