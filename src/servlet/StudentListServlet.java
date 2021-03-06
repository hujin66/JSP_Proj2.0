package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Student;
import util.DBUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class StudentListServlet extends HttpServlet {
	
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入到学生信息列表操作");
		response.setContentType("text/html;charset=utf-8");
		
		Connection conn2=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		String sql2="select id,name,age from tbl_student ";
		List<Student> list=new ArrayList<Student>();
		try {
			conn2=DBUtil.getConnection();
			ps2=conn2.prepareStatement(sql2);
			rs2=ps2.executeQuery();
			while (rs2.next()) {
				Student s=new Student();
				s.setId(rs2.getString(1));
				s.setName(rs2.getString(2));
				s.setAge(rs2.getInt(3));
				list.add(s);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				DBUtil.DBClose(conn2, ps2, rs2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	//应该使用转发,使用request域將list传到index.jsp
		request.setAttribute("list",list );
		request.getRequestDispatcher("/jsp/student/index.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
