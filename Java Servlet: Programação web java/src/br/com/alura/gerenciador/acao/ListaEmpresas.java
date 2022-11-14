package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListaEmpresas implements Acao {
    @Override
    public String executa(HttpServletRequest request) {
        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        request.setAttribute("listaDeEmpresas", lista);

        return "forward:listaEmpresas.jsp";
    }
}
