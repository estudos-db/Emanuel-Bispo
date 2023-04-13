package br.tec.atividade.agenda.constants;

import br.tec.atividade.agenda.model.Contato;

public class ContatoConstants {
    public static final Contato CONTATO = new Contato(1L, "Bob", "Doe", "9999999", "bob.mail", EnderecoConstants.ENDERECO);
    public static final Contato CONTATO_ATUALIZADO = new Contato(1L, "Atualizou", "Dado", "000000", "atuzlizou.mail", EnderecoConstants.ENDERECO);
    public static final Contato INVALID_CONTATO = new Contato(null, null, "Doe", "9999999", "bob.mail", null);
}
