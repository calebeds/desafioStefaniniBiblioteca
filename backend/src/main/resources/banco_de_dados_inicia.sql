insert into Cliente(email,nome,contato, qtdEmprestimos) values('maratona@stefanini.com','Developer Up','(11) 99999-9999', '0');
insert into Autor(isni,nome, email, dataDeNascimento, biografia) values('24234234','Machado','machado@gmail.com', '04/04/1212', 'Um homem belo e moral');

insert into Livro(isbn10, isbn13, nome,
anoDePublicacao, editora, qtdDeExemplares) 
values('24234234', '3333333333333', 'As Noites',
 '345345', 'Bom viver', '4');

 insert into Emprestimo(id, emailCliente, isbn, dataDeInicio) values('1','Livro@dfa', '32424', '4343', '23424');