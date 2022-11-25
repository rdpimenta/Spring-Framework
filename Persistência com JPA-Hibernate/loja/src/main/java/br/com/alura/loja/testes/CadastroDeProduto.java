package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager entityManager = JPAUtil.createEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        Produto produto = produtoDAO.buscarPorId(1L);
        System.out.println(produto.getDescricao());

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(p -> System.out.println(p.getDescricao()));

        List<Produto> produtos1 = produtoDAO.buscarPorNome("Xiaomi Redmi");
        produtos1.forEach(p -> System.out.println(p.getDescricao()));

        List<Produto> produtos2 = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        produtos2.forEach(p -> System.out.println(p.getDescricao()));

        BigDecimal preco = produtoDAO.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
        System.out.println("Preço: " + preco);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal!", new BigDecimal("800"), celulares);

        EntityManager entityManager = JPAUtil.createEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);

        entityManager.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
