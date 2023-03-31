package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("GET 요청");
		Gson gson = new Gson();
		Map<String,String> userMap = new HashMap<>();
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		userMap.put("name", name);
		userMap.put("phone", phone);
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		/**
		 * 1. 주소창, src, href, replace 등으로 요청할 수 있음.
		 * 2. 데이터를 전달하는 방법(Query String)
		 * 		http(s):////서버주소:포트/요청메세지?key=value&key=value
		 */
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("Content-Type", "text/html;charset-UTF-8");
//		response.addHeader("test", "testdata");
//		response.setContentType("text/html;charset-UTF-8");
		response.setContentType("application/json:charset=UTF-8");
		
		PrintWriter out = response.getWriter();
//		out.println("name: " + name);
//		out.println("phone: " + phone);
		out.println(userJson);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(("POST 요청"));
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String json = "";
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			json += line;
		}
		json.replaceAll(" ",  "");
		System.out.println(json);
		/**
		 * 1.<form action = "요청메세지 " method= "post">
		 * 		<input name="key" value="value">
		 * 		<button type = "submit">요청</button>
		 * </form>
		 * 
		 */
	}

}
