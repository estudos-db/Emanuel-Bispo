package servicos;

import modelo.Emprestimo;
import modelo.Livro;
import repositorio.IEmprestimoRepository;
import repositorio.LivroRepository;

import java.util.Optional;

public class LivroService {

    private LivroRepository livroRepository;
    private IEmprestimoRepository emprestimoRepository;

    public LivroService(LivroRepository livroRepository, IEmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    public boolean isLivroDisponivel(String isbn) {
        Livro livroEncontrado = livroRepository.getOne(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não existe no catalogo"));

        Optional<Emprestimo> emprestimo = emprestimoRepository.getByIsbnLivro(isbn);

        return !(emprestimo.isPresent() && emprestimo.get().getData_devolucao() == null);
    }

}