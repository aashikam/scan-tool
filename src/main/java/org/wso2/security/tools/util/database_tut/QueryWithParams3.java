package org.wso2.security.tools.util.database_tut;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import org.wso2.security.tools.util.Constants;
import org.wso2.security.tools.util.DatabaseUtils;

//
// Fine Department like SQL:
// where dept_name in ('ACCOUNTING', 'RESEARCH') or location  = 'BOSTON'.
//
public class QueryWithParams3 {


    // Option 1:
    //
    // dept_name in ('ACCOUNTING', 'RESEARCH') or location  = 'BOSTON'.
    // { "$or" : [ { "dept_name" : { "$in" : [ "ACCOUNTING" , "RESEARCH"]}} , {"location" : "BOSTON"}]}

    protected static DBObject getWhereClase_1() {
        List<String> list = new ArrayList<String>();
        list.add("ACCOUNTING");
        list.add("RESEARCH");

        //
        BasicDBObjectBuilder ob1 = BasicDBObjectBuilder.start();
        ob1.push("dept_name").add("$in", list);
        DBObject clause1 = ob1.get();
        //
        BasicDBObjectBuilder ob2 = BasicDBObjectBuilder.start();
        ob2.append("location", "BOSTON");
        DBObject clause2 = ob2.get();
        //
        BasicDBList or = new BasicDBList();
        or.add(clause1);
        or.add(clause2);
        //
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        builder.add("$or", or);
        DBObject query = builder.get();
        System.out.println("Query = " + query);
        return query;
    }

    // Option 2:
    // Using QueryBuilder:
    // dept_name in ('ACCOUNTING', 'RESEARCH') or location  = 'BOSTON'.
    // { "$or" : [ { "dept_name" : { "$in" : [ "ACCOUNTING" , "RESEARCH"]}} , {"location" : "BOSTON"}]}

    protected static DBObject getWhereClause_2() {
        List<String> list = new ArrayList<String>();
        list.add("ACCOUNTING");
        list.add("RESEARCH");
        //
        QueryBuilder qb1 = new QueryBuilder();
        qb1.put("dept_name").in(list);
        DBObject q1 = qb1.get();
        //
        QueryBuilder qb2 = new QueryBuilder();
        qb2.put("location").is("BOSTON");
        DBObject q2 = qb2.get();

        //
        QueryBuilder queryBuilder = QueryBuilder.start();
        queryBuilder.or(q1, q2);

        DBObject query = queryBuilder.get();
        return query;
    }

    // Option 3 (brief for option 2):
    // Using QueryBuilder.
    // dept_name in ('ACCOUNTING', 'RESEARCH') or location  = 'BOSTON'.
    // { "$or" : [ { "dept_name" : { "$in" : [ "ACCOUNTING" , "RESEARCH"]}} , {"location" : "BOSTON"}]}
    protected static DBObject getWhereClause_3() {
        List<String> list = new ArrayList<String>();
        list.add("ACCOUNTING");
        list.add("RESEARCH");
        //
        QueryBuilder queryBuilder = QueryBuilder.start();
        queryBuilder.or(QueryBuilder.start().put("dept_name").in(list).get(),
                QueryBuilder.start().put("location").is("BOSTON").get());

        DBObject query = queryBuilder.get();
        return query;
    }

    public static void main(String args[]) throws UnknownHostException {
        // To connect to mongodb server
        MongoClient mongoClient = DatabaseUtils.getMongoClient();
        // Now connect to your databases
        DB db = mongoClient.getDB(Constants.DB_NAME);
        DBCollection dept = db.getCollection("Department");

        DBObject query = getWhereClase_1();

        DBCursor cursor = dept.find(query);
        int i = 1;
        while (cursor.hasNext()) {
            System.out.println("Document: " + i);
            System.out.println(cursor.next());
            i++;
        }
        System.out.println("Done!");
    }
}