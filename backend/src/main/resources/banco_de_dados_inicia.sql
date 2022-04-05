insert into Cliente(email,nome,contato, qtdEmprestimos) values('maratona@stefanini.com','Developer Up','(11) 99999-9999', '0');
insert into Cliente(email,nome,contato, qtdEmprestimos) values('calebe@stefanini.com','Calebe','(11) 99999-9999', '0');
insert into Cliente(email,nome,contato, qtdEmprestimos) values('jao@stefanini.com','Jao','(11) 94399-9999', '0');
insert into Cliente(email,nome,contato, qtdEmprestimos) values('asaf@stefanini.com','Asaf','(11) 96599-9999', '0');
insert into Cliente(email,nome,contato, qtdEmprestimos) values('filho@stefanini.com','Filho','(11) 97899-9999', '0');

insert into Autor(isni,nome, email, dataDeNascimento, biografia) values('2423429993642345','Machado','machado@gmail.com', '04/04/1212', 'Um homem belo e moral');
insert into Autor(isni,nome, email, dataDeNascimento, biografia) values('2423429993642346','Clarisse','clarisse@gmail.com', '04/04/1212', 'Um homem belo e moral');
insert into Autor(isni,nome, email, dataDeNascimento, biografia) values('2423429993642347','Stendhal','stendhal@gmail.com', '04/04/1212', 'Um homem belo e moral');
insert into Autor(isni,nome, email, dataDeNascimento, biografia) values('2423429993642348','Urso Feliz','urso@gmail.com', '04/04/1212', 'Um homem belo e moral');


insert into Livro(isbn10, isbn13, nome, anoDePublicacao, editora, qtdDeExemplares) values('2423423436', '3333333333333', 'As Noites','345345', 'Bom viver', '4');
insert into Livro(isbn10, isbn13, nome, anoDePublicacao, editora, qtdDeExemplares) values('2423423426', '3333333333333', 'As Noites','345345', 'Bom viver', '4');

insert into Emprestimo(id, emailCliente, isbn, dataDeInicio) values('1','maratona@stefanini.com', '2423423436', '2021-10-12');