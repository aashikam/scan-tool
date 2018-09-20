package org.wso2.security.tools.util.database_tut;


import java.net.UnknownHostException;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

public class QueryWithParams1{

    // Building JSON:
    // { "dept_name" : "ACCOUNTING"}
    private static DBObject getWhereClause_1() {
        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();

        // Use the append method (similar to the use of add method)
        whereBuilder.append("method_name", "getDB");
        //
        DBObject where = whereBuilder.get();
        System.out.println("Where: " + where.toString());
        return where;
    }

    public static void main(String args[]) throws UnknownHostException {
        // To connect to mongodb server
        MongoClient mongoClient = DatabaseUtils.getMongoClient();

        // Now connect to your databases
        DB db = mongoClient.getDB(Constants.DB_NAME);

        DBCollection dept = db.getCollection("Usages");

        DBObject where = getWhereClause_1();

        // Query
        DBCursor cursor = dept.find(where);
        int i = 1;
        while (cursor.hasNext()) {
            System.out.println("Document: " + i);
            System.out.println(cursor.next());
            i++;
        }

        System.out.println("Done!");
    }
}