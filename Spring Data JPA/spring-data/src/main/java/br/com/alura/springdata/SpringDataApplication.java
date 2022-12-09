package br.com.alura.springdata;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.service.CrudCargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCargoService crudCargoService;

    private Boolean system = true;

    public SpringDataApplication(CrudCargoService crudCargoService) {
        this.crudCargoService = crudCargoService;
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

            int action = scanner.nextInt();

            if (action == 1) {
                crudCargoService.inicial(scanner);
            } else {
                system = false;
            }
        }
    }
}
