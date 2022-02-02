

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketListServlet
 */
@WebServlet("/GetTicketListServlet2")
public class GetTicketListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTicketListServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Ticket> list = new ArrayList<>();
		list.add(new Ticket(1,"トッピング無料券",500));
		list.add(new Ticket(2,"チャーハン無料券",1500));
		list.add(new Ticket(3,"ラーメン無料券",1000));
		list.add(new Ticket(4,"飲物無料券",300));
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/getTicketList2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
