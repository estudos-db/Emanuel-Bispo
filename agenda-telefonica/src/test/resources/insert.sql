insert into agenda (id) values (default);
insert into agenda (id) values (default);

insert into endereco (id, logradouro, numero, bairro, cidade, uf) values
 (default,'rua teste 1', '1111', 'bairro teste 1', 'cidade teste 1', 'uf');

insert into contato  (id, nome, sobrenome, telefone, email, endereco_id, agenda_id) values
(default,'contato 1', 'sobrenome 1', '9999999', 'teste.mail', 1, 1);