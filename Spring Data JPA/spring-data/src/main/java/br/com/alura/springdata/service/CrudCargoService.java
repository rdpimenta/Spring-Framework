package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Qual acao quer executar?");
        System.out.println("0 - Salvar");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Visualizar");
        System.out.println("3 - Deletar");

        int action = scanner.nextInt();

        switch (action) {
            case 0:
                salvar(scanner);
                break;
            case 1:
                atualizar(scanner);
                break;
            case 2:
                visualizar(scanner);
                break;
            case 3:
                deletar(scanner);
                break;
            default:
                break;
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do cargo:");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id do cargo:");
        int id = scanner.nextInt();
        Cargo cargo = cargoRepository.findById(id).orElse(null);
        System.out.println("Descrição do cargo:");
        String descricao = scanner.next();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado");
    }

    private void visualizar(Scanner scanner) {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo.getDescricao()));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id do cargo:");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado");
    }
}
