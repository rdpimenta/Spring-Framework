package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("Cadastrando nova empresa");

        String nomeEmpresa = request.getParameter("nome");
        String dataDeAbertura = request.getParameter("data");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Empresa empresa = new Empresa();
        empresa.setNome(nomeEmpresa);
        try {
            empresa.setDataDeAbertura(sdf.parse(dataDeAbertura));
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        Banco banco = new Banco();
        banco.adiciona(empresa);

        request.setAttribute("empresa", empresa.getNome());

        response.sendRedirect("listaEmpresas");

        //chamar o JSP
//        RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas");
//        request.setAttribute("empresa", empresa.getNome());
//        rd.forward(request, response);
    }
}
