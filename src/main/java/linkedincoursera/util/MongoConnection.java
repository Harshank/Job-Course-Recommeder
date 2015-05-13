package linkedincoursera.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.IOException;

/**
 * Created by harsh on 5/13/15.
 */
public class MongoConnection {



    private static MongoClient connection;
    private static DB dbConnection;

    private MongoConnection() {

    }

    public static DB getConnection() throws IOException {

        if (dbConnection == null) {
            String url = "mongodb://harshank:password@ds047581.mongolab.com:47581/cmpe273";
            MongoClientURI uri = new MongoClientURI(url);
            connection = new MongoClient(uri);
            dbConnection = connection.getDB("cmpe273");
        }
        return dbConnection;

    }

}