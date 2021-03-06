package chapter9.httprequesttest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	class Result {
		int result;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Gson gson = new Gson();
			AddRequest request = gson.fromJson(req.getReader(), AddRequest.class);
			Result result = new Result();
			result.result = request.a + request.b;
			resp.getWriter().write(gson.toJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		}
	}
}
