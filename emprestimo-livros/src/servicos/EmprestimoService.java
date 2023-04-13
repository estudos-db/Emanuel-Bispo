package servicos;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import repositorio.IEmprestimoRepository;
import util.GeradorCodigoEmprestimo;

import java.time.LocalDate;

public class EmprestimoService {

    private final LivroService livroService;
    private final IEmprestimoRepository emprestimoRepository;

    public EmprestimoService(LivroService livroService, IEmprestimoRepository emprestimoRepository) {
        this.livroService = livroService;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo emprestar(Livro livro, Pessoa pessoa, LocalDate data_prev_devolucao) {
        if(!livroService.isLivroDisponivel(livro.getIsbn()))
            throw new RuntimeException("Livro não disponível para emprestimo");

        Emprestimo novoEmprestimo = new Emprestimo(
                                        GeradorCodigoEmprestimo.gera(),
                                        livro,
                                        pessoa,
                                        LocalDate.now(),
                                        data_prev_devolucao
                                    );

        emprestimoRepository.addOne(novoEmprestimo);
        return novoEmprestimo;
    }

    public boolean darBaixa(String codigoEmprestimo, LocalDate data) {
        Emprestimo emprestimo = emprestimoRepository.getOne(codigoEmprestimo)
                .orElseThrow(
                        () -> new RuntimeException("Erro na operação: Codigo inválido")
                );

        emprestimo.setData_devolucao(data);
        emprestimoRepository.updateOne(codigoEmprestimo, emprestimo);

        return true;
    }

}
