package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
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
}
