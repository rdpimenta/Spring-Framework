package br.com.alura.loja;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProdutoTest {

	@Test
	public void test() {
		Produto produto = new Produto("Teste", BigDecimal.TEN);
		Assert.assertEquals("Teste", produto.getNome());
		Assert.assertEquals(BigDecimal.TEN, produto.getPreco());
	}

}
