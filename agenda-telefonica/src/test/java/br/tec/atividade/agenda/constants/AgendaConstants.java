package br.tec.atividade.agenda.constants;

import br.tec.atividade.agenda.model.Agenda;
import br.tec.atividade.agenda.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class AgendaConstants {
    public static final Agenda AGENDA = new Agenda(1L, new ArrayList<Contato>());
    public static final Agenda AGENDA_COM_CONTATO = new Agenda(1L, new ArrayList<Contato>(List.of(ContatoConstants.CONTATO)));
}
