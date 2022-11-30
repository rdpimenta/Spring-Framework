package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager entityManager = JPAUtil.createEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        Produto produto = produtoDAO.buscarPorId(1L);

        entityManager.getTransaction().begin();

        Cliente cliente = new Cliente("Rodrigo", "123456");

        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        clienteDAO.cadastrar(cliente);

        Pedido pedido = new Pedido(cliente);

        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        pedidoDAO.cadastrar(pedido);

        entityManager.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Valor total: " + totalVendido);

        List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
        relatorio.forEach(System.out::println);
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
