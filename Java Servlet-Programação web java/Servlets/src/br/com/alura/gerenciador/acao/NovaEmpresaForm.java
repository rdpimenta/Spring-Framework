package br.com.alura.gerenciador.acao;

import javax.servlet.http.HttpServletRequest;

public class NovaEmpresaForm implements Acao {
    public String executa(HttpServletRequest request) {
        return "forward:formNovaEmpresa.jsp";
    }
}
