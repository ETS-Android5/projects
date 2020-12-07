package br.edu.pucgoias.linhasdeonibus.servlet.linha;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.pucgoias.sistemaestoque.controle.LinhaControle;
import br.edu.pucgoias.sistemaestoque.modelo.Linha;

/**
 * Classe servlet responsavel por retornar para a pagina html uma lista de todas as
 * linhas cadastradas no banco de dados que tenham a descri��o informada ou parte dela.
 * 
 * @author Jo�o Victor
 * @data 01/12/2020
 */

@WebServlet("/servletconsultapordescricaolinha")
public class ServletConsultaPorDescricaoLinha extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public ServletConsultaPorDescricaoLinha() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pesquisar = request.getParameter("pesquisar");

		gson = new GsonBuilder().setPrettyPrinting().create();
		List<Linha> lista = new ArrayList<>();

		LinhaControle lc = new LinhaControle();
		lista = lc.buscaLinhaPorDescricao(pesquisar);

		String retornoJsonString = this.gson.toJson(lista);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(retornoJsonString);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}