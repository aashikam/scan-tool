package org.wso2.security.tools.util.database_tut;


import java.net.UnknownHostException;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

public class SimpleQueryDemo {

    public static void main(String args[]) throws UnknownHostException {
        // To connect to mongodb server
        MongoClient mongoClient = DatabaseUtils.getMongoClient();
        // Now connect to your databases
        DB db = mongoClient.getDB(Constants.DB_NAME);

        DBCollection dept = db.getCollection("Department");

        // Query
        DBCursor cursor = dept.find();
        int i = 1;
        while (cursor.hasNext()) {
            System.out.println("Document: " + i);
            System.out.println(cursor.next());
            i++;
        }

    }
}