package com.practice.blogTest;

import com.practice.blogTest.model.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BlogTestApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BlogTestApplication.class, args);

		DBConnection dbConnection = new DBConnection();
//		dbConnection.testConnection();
//		dbConnection.insertData("tes blog post", " jim halpert");
//		dbConnection.deleteData(4);
//		dbConnection.retrieveData(1);
		dbConnection.updateData(1, "This is the updated post");
	}

}
