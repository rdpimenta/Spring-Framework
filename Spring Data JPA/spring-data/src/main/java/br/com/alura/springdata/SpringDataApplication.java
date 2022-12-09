package br.com.alura.springdata;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CargoRepository cargoRepository;

    public SpringDataApplication(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cargo cargo = new Cargo();
        cargo.setDescricao("Desenvolvedor de software");

		cargoRepository.save(cargo);
    }
}
