package geral;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/geral")
public class GeralSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		showAtribs(request, response);

	}

	private void showAtribs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();
		PrintWriter pw = response.getWriter();

		pw.append("ENTRANDO NO WHILE");
		pw.println();
		pw.append("AGUARDE...");

		Enumeration<String> atribs = s.getAttributeNames();
		while (atribs.hasMoreElements()) {
			String nome = atribs.nextElement();
			String valor = (String) s.getAttribute(nome);
			pw.append("ATRIBUTO: " + nome + " = " + valor);

		}

	}
}
