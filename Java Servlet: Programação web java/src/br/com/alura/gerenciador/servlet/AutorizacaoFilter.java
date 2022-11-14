package br.com.alura.gerenciador.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/entrada")
public class AutorizacaoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AutorizacaoFilter");

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        String paramAcao = request.getParameter("acao");

        HttpSession sessao = servletRequest.getSession();
        boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
        boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));

        if(ehUmaAcaoProtegida & usuarioNaoEstaLogado) {
            servletResponse.sendRedirect("entrada?acao=LoginForm");
            return;
        }

        chain.doFilter(request, response);
    }
}
