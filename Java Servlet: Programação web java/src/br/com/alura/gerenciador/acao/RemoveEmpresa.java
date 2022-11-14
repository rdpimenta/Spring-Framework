package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveEmpresa implements Acao {
    public String executa(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        Banco banco = new Banco();
        Empresa empresaEncontrada = banco.encontraEmpresaPelaId(id);

        banco.removeEmpresa(empresaEncontrada);

        return "redirect:entrada?acao=ListaEmpresas";
    }
}
