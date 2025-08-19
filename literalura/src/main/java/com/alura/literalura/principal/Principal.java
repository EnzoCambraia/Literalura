package com.alura.literalura.principal;

import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.DadosPesquisa;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ApiConsumoService;
import com.alura.literalura.service.CatalogoService;
import com.alura.literalura.service.ConversorDadosService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private final String URL_API = "https://gutendex.com/books/";
    private final Scanner leitura = new Scanner(System.in);

    private final ApiConsumoService consumo;
    private final LivroRepository livroRepository;
    private final ConversorDadosService conversor;
    private final AutorRepository autorRepository;
    private final CatalogoService catalogoService;

    public Principal(ApiConsumoService consumo, ConversorDadosService conversor, LivroRepository livroRepository, AutorRepository autorRepository, CatalogoService catalogoService, CatalogoService catalogoService1) {
        this.consumo = consumo;
        this.conversor = conversor;
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.catalogoService = catalogoService;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """               
                    
                    Selecione uma opção:                
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em determinado ano
                    5 - listar livros em um determinado idioma                        
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando aplicação");
                    break;
                default:
                    System.out.println("A Opção não é válida");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o título do livro que deseja buscar:");
        var nomeLivro = leitura.nextLine();

        Optional<Livro> livroSalvo = livroRepository.findByTituloContainingIgnoreCase(nomeLivro);
        if (livroSalvo.isPresent()) {
            System.out.println("\nLivro já cadastrado no banco de dados:");
            System.out.println(livroSalvo.get());
            return;
        }

        var json = consumo.consumirDados(URL_API + "?search=" + nomeLivro.replace(" ", "+"));
        var dadosPesquisa = conversor.conversorDados(json, DadosPesquisa.class);

        Optional<DadosLivro> dadosLivro = dadosPesquisa.results().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst();

        if (dadosLivro.isPresent()) {
            Livro livro = new Livro(dadosLivro.get());
            livroRepository.save(livro);
            System.out.println("\nLivro cadastrado com sucesso:");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listarLivrosRegistrados() {
        catalogoService.listarLivrosRegistrados().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        catalogoService.listarAutoresRegistrados().forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para buscar autores vivos:");
        var ano = leitura.nextInt();
        leitura.nextLine();
        catalogoService.listarAutoresVivosPorAno(ano).forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma para buscar livros:");
        var idioma = leitura.nextLine();
        catalogoService.listarLivrosPorIdioma(idioma).forEach(System.out::println);
    }

}


