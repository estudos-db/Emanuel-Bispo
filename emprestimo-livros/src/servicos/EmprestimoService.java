package servicos;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import repositorio.IEmprestimoRepository;

import java.time.LocalDate;

public class EmprestimoService {

    private final LivroService livroService;
    private final IEmprestimoRepository emprestimoRepository;

    public EmprestimoService(LivroService livroService, IEmprestimoRepository emprestimoRepository) {
        this.livroService = livroService;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo emprestar(Livro livro, Pessoa pessoa, LocalDate data_prev_devolucao) {
        if(!livroService.isDisponivel(livro)) throw new RuntimeException("");

        Emprestimo novoEmprestimo = new Emprestimo(livro, pessoa, LocalDate.now(), data_prev_devolucao);
        emprestimoRepository.addOne(novoEmprestimo);

        return novoEmprestimo;
    }

    public String darBaixa(String codigoEmprestimo) {
        emprestimoRepository.deleteOne(codigoEmprestimo);
        return "Livro devolvido! Agora ele está novamente disponível";
    }

}
