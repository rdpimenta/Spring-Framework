package br.com.alura.gerenciador.servlet;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static final List<Empresa> lista = new ArrayList<>();

    static {
        Empresa empresa = new Empresa();
        empresa.setNome("Alura");
        empresa.setId(1);
        Empresa empresa2 = new Empresa();
        empresa2.setNome("Caelum");
        empresa2.setId(2);
        lista.add(empresa);
        lista.add(empresa2);
    }

    public static List<Empresa> getLista() {
        return lista;
    }
    public void adiciona(Empresa empresa) {
        if (lista.size() > 0) {
            int ultimoIndex = lista.size() - 1;
            empresa.setId(lista.get(ultimoIndex).getId() + 1);
        } else {
            empresa.setId(1);
        }
        lista.add(empresa);
    }

    public Empresa encontraEmpresaPelaId(int id) {
        for (Empresa empresa : lista) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null;
    }

    public void removeEmpresa(Empresa empresa) {
        lista.remove(empresa);
    }
}
