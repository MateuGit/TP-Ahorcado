package com.example.demo;

import com.example.demo.Model.ConnectionMYSQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		try {
	Connection connection= ConnectionMYSQL.getConnection();
			System.out.println("Conecto");
	} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}}
