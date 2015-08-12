package com.hand.Exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("请输入CountryID");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		System.out.println("属于Country"+getCountry(id)+"的城市有");
		query(id);
		
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila?characterEncoding=utf8", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void query(int id) {
		Connection conn = getConn();
		String sql = "select city_id,city from city,country where  city.country_id=country.country_id AND country.country_id =  "+id;
		try {
			Statement statement = conn.createStatement();
			ResultSet	rs = statement.executeQuery(sql);
			System.out.println("城市名称"+"\t|"+"城市名字");
			while (rs.next()) {
				System.out.println(rs.getInt("city_id") + "\t"+rs.getString("city"));
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String getCountry(int id) {
		Connection conn = getConn();
		String sql = "select country from country where  country_id =  "+id;
		String country = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet	rs = statement.executeQuery(sql);
			rs.next();
			country = rs.getString("country");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return country;
	}
}
