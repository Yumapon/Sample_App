package jisaku_servlet.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.mockito.Mockito;

public class HogeHogeServletTest extends Mockito{

	@Ignore
	public void doGetテスト1_WEB() throws ServletException, IOException {
		// request responseをmock化
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("actionName")).thenReturn("loginAction");
		when(request.getParameter("actionMethodName")).thenReturn("login");
		when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

		Map<String, String> map = new HashMap<>();
		map.put("actionName", "loginAction");
		map.put("actionMethodName", "login");
		map.put("button", "value3");

		Enumeration<String> e = Collections.enumeration(map.keySet());
		when(request.getParameterNames()).thenReturn(e);

		HogeHogeServlet servlet = new HogeHogeServlet();
		servlet.doGet(request, response);
	}

	/**
	 * PrintWriterクラスをうまく渡せない。。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Ignore
	public void doGetテスト2_JSON() throws ServletException, IOException {
		// request responseをmock化
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		//RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("actionName")).thenReturn("loginAction");
		when(request.getParameter("actionMethodName")).thenReturn("login2");
		//when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
		when(response.getWriter()).thenReturn(System.console().writer());

		Map<String, String> map = new HashMap<>();
		map.put("actionName", "loginAction");
		map.put("actionMethodName", "login2");
		map.put("button", "value3");

		Enumeration<String> e = Collections.enumeration(map.keySet());
		when(request.getParameterNames()).thenReturn(e);

		HogeHogeServlet servlet = new HogeHogeServlet();
		servlet.doGet(request, response);
	}

	@Ignore
	public void Formにリクエストの値をインジェクトするテスト() throws ServletException, IOException {
		// request responseをmock化
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("actionName")).thenReturn("loginAction");
		when(request.getParameter("actionMethodName")).thenReturn("login");
		when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
		when(request.getParameter("formName")).thenReturn("userInfo");
		when(request.getParameter("user_id")).thenReturn("1234");
		when(request.getParameter("password")).thenReturn("password");

		Map<String, String> map = new HashMap<>();
		map.put("actionName", "loginAction");
		map.put("actionMethodName", "login");
		map.put("button", "value3");
		map.put("user_id", "1234");
		map.put("password", "password");

		Enumeration<String> e = Collections.enumeration(map.keySet());
		when(request.getParameterNames()).thenReturn(e);

		HogeHogeServlet servlet = new HogeHogeServlet();
		servlet.doGet(request, response);

	}
}
