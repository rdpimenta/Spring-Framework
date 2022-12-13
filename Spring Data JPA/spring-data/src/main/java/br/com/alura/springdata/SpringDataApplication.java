package br.com.alura.springdata;

import br.com.alura.springdata.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCargoService crudCargoService;
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
    private final RelatorioService relatorioService;

    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

    private Boolean system = true;

    public SpringDataApplication(
            CrudCargoService crudCargoService,
            CrudFuncionarioService crudFuncionarioService,
            CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
            RelatorioService relatorioService,
            RelatorioFuncionarioDinamico relatorioFuncionarioDinamico
    ) {
        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
        this.relatorioService = relatorioService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual acao quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionário");
            System.out.println("3 - Unidade de trabalho");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Relatórios dinâmicos");

            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    system = false;
                    break;
                case 1:
                    crudCargoService.inicial(scanner);
                    break;
                case 2:
                    crudFuncionarioService.inicial(scanner);
                    break;
                case 3:
                    crudUnidadeTrabalhoService.inicial(scanner);
                    break;
                case 4:
                    relatorioService.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                    break;
                default:
                    break;
            }
        }
    }
}
