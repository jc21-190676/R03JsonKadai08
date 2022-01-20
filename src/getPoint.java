

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Ticket;

/**
 * Servlet implementation class getPoint
 */
@WebServlet("/JsonKadai08/getPoint")
public class getPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String tenpo_Id = request.getParameter("TENPO_ID");
		String user_Id = request.getParameter("USER_ID");
		
//		String user_Id = "190000@jc-21.jp";
//		String tenpo_Id = "0000000002";
		try {
			InitialContext 	ic 	= new InitialContext();	//データソースを取得する前処理
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/TEAM08");	//データベースに接続するためのオブジェクトを生成
			Connection con = ds.getConnection();//コネクションを取得

			String sql = "SELECT * FROM Point WHERE TENPO_ID = ? AND USER_ID = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,tenpo_Id);
			st.setString(2, user_Id);
			ResultSet rs = st.executeQuery();
			
			int point= 0;
			int line = 0;
			while(rs.next()) {
				line++;
				point = rs.getInt("Point");
			}
			
			if(line==0) {	//該当するデータがないときに登録
				sql = "INSERT INTO Point(TENPO_ID,USER_ID,Point) VALUES(?,?,500)";
				st = con.prepareStatement(sql);
				st.setString(1,tenpo_Id);
				st.setString(2,user_Id);
				point = 500;
				st.executeUpdate();
			}
			
			request.setAttribute("point", point);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
			rd.forward(request, response);
			
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
