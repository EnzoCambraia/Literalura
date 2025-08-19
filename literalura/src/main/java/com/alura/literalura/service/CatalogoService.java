package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogoService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public CatalogoService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }


    @Transactional
    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAllWithLivros();
    }

    @Transactional
    public List<Livro> listarLivrosRegistrados() {
        return livroRepository.findAllWithAutores();
    }

    @Transactional
    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    @Transactional
    public List<Autor> listarAutoresVivosPorAno(int ano) {
        return autorRepository.findActorsAliveOnTheYearOfChoice(ano);
    }
}
