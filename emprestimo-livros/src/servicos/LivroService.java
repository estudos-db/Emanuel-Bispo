package servicos;

import modelo.Livro;
import repositorio.IEmprestimoRepository;
import repositorio.LivroRepository;

public class LivroService {

    private LivroRepository livroRepository;
    private IEmprestimoRepository emprestimoRepository;

    public LivroService(IEmprestimoRepository emprestimoRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    public boolean isDisponivel(Livro livro) {
        Livro livroEncontrado = livroRepository.getOne(livro.getIsbn())
                .orElseThrow(() -> new RuntimeException("Livro não existe no catalogo"));

        return !emprestimoRepository.getByIsbnLivro(livro.getIsbn()).isPresent();
    }

}