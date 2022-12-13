package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {
    List<Funcionario> findByNome(String nome);

    @Query(
            "SELECT f " +
                    "FROM Funcionario f " +
                    "WHERE f.nome = :nome " +
                    "AND f.salario >= :salario " +
                    "AND f.dataContratacao = :dataContratacao"
    )
    List<Funcionario> buscaNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> buscaDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> buscaFuncionarioSalario();
}
