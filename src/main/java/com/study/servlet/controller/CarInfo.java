package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/car")
public class CarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Map<String , String> carMap = new HashMap<>();
		carMap.put("1", "쏘나타");
		carMap.put("2", "K5");
		carMap.put("3", "SM6");
		
		String id = request.getParameter("id");
		String findModel  = carMap.get(id);
		
		JsonObject responseData = new JsonObject();
		if(findModel == null) {
			responseData.addProperty("statusCode", 400);
			responseData.addProperty("errorMessage", "not Found!!");
		} else {
			responseData.addProperty("id", id);
			responseData.addProperty("model", findModel);
		}
		
		
		response.setContentType("appllication/json;charset=UTF-8");
		response.getWriter().println(responseData.toString());
		


		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String requestJson = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		List<Map<String, String>> requestMap = gson.fromJson(requestJson, List.class);
		PrintWriter out = response.getWriter();
		requestMap.forEach(obj -> {
			System.out.println("id("+ obj.get("id") + "):" + obj.get("model"));
			out.println("id("+ obj.get("id") + "):" + obj.get("model"));
		});
		
	}

}
