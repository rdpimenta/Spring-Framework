package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {
    private final FuncionarioRepository funcionarioRepository;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Qual acao quer executar?");
        System.out.println("0 - Busca funcionário por nome");
        System.out.println("1 - Busca funcionário por nome, salário e data de contratação");
        System.out.println("2 - Busca funcionário por data de contratação");
        System.out.println("3 - Mostra projeção de funcionários");

        int action = scanner.nextInt();

        switch (action) {
            case 0:
                buscaFuncionarioPorNome(scanner);
                break;
            case 1:
                buscaFuncionarioSalarioMaiorData(scanner);
                break;
            case 2:
                buscaFuncionarioDataMaior(scanner);
                break;
            case 3:
                buscaFuncionarioSalario();
                break;
            default:
                break;
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        System.out.println("Nome do funcionário:");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
    }

    private void buscaFuncionarioSalarioMaiorData(Scanner scanner) {
        System.out.println("Nome do funcionário:");
        String nome = scanner.next();
        System.out.println("Data de contratação do funcionário:");
        String data = scanner.next();
        System.out.println("Salário do funcionário:");
        Double salario = scanner.nextDouble();

        LocalDate dataContratacao = LocalDate.parse(data, dateTimeFormatter);

        List<Funcionario> funcionarios = funcionarioRepository.buscaNomeSalarioMaiorDataContratacao(nome, salario, dataContratacao);

        funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
    }

    private void buscaFuncionarioDataMaior(Scanner scanner) {
        System.out.println("Data de contratação do funcionário:");
        String data = scanner.next();

        LocalDate dataContratacao = LocalDate.parse(data, dateTimeFormatter);

        List<Funcionario> funcionarios = funcionarioRepository.buscaDataContratacaoMaior(dataContratacao);

        funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
    }

    private void buscaFuncionarioSalario() {
        List<FuncionarioProjecao> funcionarios = funcionarioRepository.buscaFuncionarioSalario();

        funcionarios.forEach(funcionario -> {
            System.out.println(
                    "Funcionário: [id: " + funcionario.getId() +
                            ", nome: " + funcionario.getNome() +
                            ", salário: " + funcionario.getSalario() + "]");
        });
    }
}
