package geral;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bd.ColecaoDeUsuario;
import bd.ColecaoDeUsuarioEmBDR;
import bd.ColecaoException;
import bd.ConexaoException;
import bd.ConexaoSingleton;
import bd.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logon")
public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
     	PrintWriter pw = response.getWriter();
		HttpSession s = request.getSession(false);
		Usuario u = (Usuario) s.getAttribute("usuario");
		if (u!=null) 
			pw.append("ID: '"+u.getId()+"' USUÁRIO: '"+u.getNome()+"' SENHA: "+u.getSenha());
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		HttpSession s = request.getSession(false);
		
		if (s!=null) 
			s.invalidate();
		
		if (nome!=null && senha !=null) {
			
			try {

				Connection con = ConexaoSingleton.getConexao();
				ColecaoDeUsuario cu = new ColecaoDeUsuarioEmBDR(con);
				Usuario u= cu.porNomeExato(nome);
				
				if (u!=null) {
					if(u.getSenha().equals(senha)) {
						u.setSenha("****");
						s = request.getSession();
						s.setAttribute("usuario", u);
						response.setStatus(200);
						pw.append("Login bem sucedido");
					} else {
						response.setStatus(400);
						pw.append("Senha incorreta");
					}
					
				} else {
					response.setStatus(400);
					pw.append("Usuario inexistente!");
				}

			} catch (ConexaoException e) {
				response.setStatus(500);
				pw.append("Erro ao se conectar com o banco de dados");
			} catch (ColecaoException e) {
				response.setStatus(500);
				pw.append("Falha na operação com o banco de dados");
			}
		} else {
			response.setStatus(400);
			pw.append("Usuário ou Senha não informados");
			
		}
		
		
		
	}
	

}
