package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }
}
