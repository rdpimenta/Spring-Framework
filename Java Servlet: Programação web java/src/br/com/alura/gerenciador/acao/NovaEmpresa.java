package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NovaEmpresa {
    public void executa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
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

        response.sendRedirect("entrada/acao=ListaEmpresas");
    }
}
