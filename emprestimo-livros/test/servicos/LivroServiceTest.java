package servicos;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositorio.EmprestimoRepository;
import repositorio.LivroRepository;
import util.GeradorCodigoEmprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LivroServiceTest {

    private LivroService livroService;
    private List<Livro> livroCatalogo;
    private List<Emprestimo> emprestimoCatalogo;

    @BeforeEach
    void initSetup() {

        livroCatalogo = new ArrayList<>( Arrays.asList(
                new Livro("Titulo Teste 1", "111111", "Editora teste 1", "Autor teste 1"),
                new Livro("Titulo Teste 2", "222222", "Editora teste 2", "Autor teste 2"),
                new Livro("Titulo Teste 3", "333333", "Editora teste 3", "Autor teste 3")
        ));

        emprestimoCatalogo = new ArrayList<>(Arrays.asList(
                new Emprestimo(
                        GeradorCodigoEmprestimo.gera(),
                        livroCatalogo.get(0),
                        new Pessoa("PessoaTeste1", "12334557", LocalDate.of(1993, 10, 20), "11111111"),
                        LocalDate.of(2023, 3, 20),
                        LocalDate.of(2023, 4, 1)
                ),
                new Emprestimo(
                        GeradorCodigoEmprestimo.gera(),
                        livroCatalogo.get(1),
                        new Pessoa("PessoaTeste2", "987654", LocalDate.of(2000, 1, 13), "222222"),
                        LocalDate.of(2023, 2, 27),
                        LocalDate.of(2023, 3, 2)
                )
        ));

        livroService = new LivroService(
                new LivroRepository(livroCatalogo),
                new EmprestimoRepository(emprestimoCatalogo)
        );
    }

    @Test
    void isLivroDisponivelDeveRetornarTrueParaLivroQueNaoEstaEmprestado() {
        Emprestimo emprestimoAtual = emprestimoCatalogo.get(0);
        String isbnAtual = emprestimoAtual.getLivro().getIsbn();

        assertFalse(livroService.isLivroDisponivel(isbnAtual));

        emprestimoAtual.setData_devolucao(LocalDate.of(2023, 3, 30));

        assertTrue(livroService.isLivroDisponivel(isbnAtual));
    }

    @Test
    void isLivroDisponivelDeveRetornarFalseParaLivroQueEstaEmprestado() {
        assertFalse(livroService.isLivroDisponivel("111111"));
    }

    @Test
    void isLivroDisponivelDeveLancarExcecaoParaLivroInexistenteNoCatalogo() {
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> livroService.isLivroDisponivel("000000")
        );

        assertEquals("Livro não existe no catalogo", ex.getMessage());
    }


}
