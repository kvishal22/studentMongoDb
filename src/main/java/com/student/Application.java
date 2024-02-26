package com.student;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.bson.Document;
import java.net.URLEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		/*try{
			String username = URLEncoder.encode("vishalkanna", "UTF-8");
			String password = URLEncoder.encode("p9PnqAPhLN4X8vWU", "UTF-8");
			String cluster = "student";
			String authSource = "<authSource>";
			String authMechanism = "<authMechanism>";

			String uri = "mongodb+srv://" + username + ":" + password + "@" + cluster +
					"/?authSource=" + authSource + "&authMechanism=" + authMechanism;
			MongoClient mongoClient = MongoClients.create(uri);

			MongoDatabase database = mongoClient.getDatabase("student");
			MongoCollection<Document> collection = database.getCollection("students");

			collection.find().forEach(doc -> System.out.println(doc.toJson()));
		} catch(Exception e){
			System.err.println(e.getCause());
		}*/
		SpringApplication.run(Application.class, args);
	}

}
