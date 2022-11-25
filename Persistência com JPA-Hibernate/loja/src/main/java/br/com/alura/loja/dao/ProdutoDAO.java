package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String JPQL = "SELECT p FROM Produto p";
        return entityManager.createQuery(JPQL, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String JPQL = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(JPQL, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String JPQL = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return entityManager.createQuery(JPQL, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String nome) {
        String JPQL = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(JPQL, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}
