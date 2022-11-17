package br.com.alura.cliente;

import org.apache.http.client.fluent.Request;
import java.io.IOException;

public class ClienteWebService {
    public static void main(String[] args) throws IOException {
        String conteudo = Request
                .Post("http://localhost:8080/gerenciador/empresas")
                .addHeader("Accept", "application/xml")
                .execute()
                .returnContent()
                .asString();

        System.out.println(conteudo);
    }
}
