package geral;

import java.io.IOException;
import java.io.PrintWriter;

import bd.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter pw = response.getWriter();
		
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		HttpSession s = request.getSession(false);
		
		if (s!=null) 
			s.invalidate();
		
		if (nome!=null && senha !=null) {
			
			Usuario u = new Usuario (nome, senha);
		}
		
		
		
	}
	

}
