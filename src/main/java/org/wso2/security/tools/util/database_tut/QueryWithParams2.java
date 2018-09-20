package org.wso2.security.tools.util.database_tut;


import java.net.UnknownHostException;
import java.util.regex.Pattern;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

//
// Find Department like SQL:
// where dept_name like '%S%' and description is null
//
public class QueryWithParams2 {

    // dept_name like '%S%' and description is null
    // Building JSON:
    // { "dept_name" : { "$regex" : ".*S.*"} , "description" : null }

    public static DBObject getWhereClause_1() {

        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();

        // Using append (same as 'add')
        whereBuilder.push("dept_name").add("$regex", ".*S.*") ;
        whereBuilder.pop();
        whereBuilder.append("description", null);
        //
        DBObject where = whereBuilder.get();
        System.out.println("Where " + where.toString());
        return where;
    }
    // dept_name like '%S%' and description is null
    // Building JSON:
    // { "dept_name" : { "$regex" : ".*S.*"} , "description" : null }
    public static DBObject getWhereClause_2() {

        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();


        // Regular expression describes a string
        // started by any characters appear 0 or more times
        // next by 'S'
        // and any character appear 0 or more times
        String regex = ".*S.*";
        Pattern pattern = Pattern.compile(regex);

        whereBuilder.append("dept_name", pattern);
        whereBuilder.append("description", null);
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

        DBCollection dept = db.getCollection("Department");

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