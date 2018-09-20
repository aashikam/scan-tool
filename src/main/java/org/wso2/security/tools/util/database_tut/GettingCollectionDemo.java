package org.wso2.security.tools.util.database_tut;


import java.net.UnknownHostException;
import java.util.Set;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

public class GettingCollectionDemo {

    public static void main(String[] args) throws UnknownHostException {

        MongoClient mongoClient = DatabaseUtils.getMongoClient();
        DB db = mongoClient.getDB(Constants.DB_NAME);

        // Collection in MongoDB corresponding to one Table
        // in the relational database_tut.
        DBCollection dept = db.getCollection("Department");

        System.out.println("Collection: "+ dept);

        // Document count.
        // Note: Document in MongoDB corresponding to one record
        // in the relational database_tut.
        long rowCount = dept.count();
        System.out.println(" Document count: "+ rowCount);

        System.out.println(" ------------ ");

        // List of Collections
        Set<String> collections = db.getCollectionNames();

        for(String coll: collections)  {
            System.out.println("Collection: "+ coll);
        }

    }

}