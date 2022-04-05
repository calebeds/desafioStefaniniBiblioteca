const BASE = 'http://localhost:8080/api';

const ApiUrl = {
  BASE,
  clientes: BASE + '/cliente',
  autores: BASE + '/autor',
  livros: BASE + '/livro',
  emprestimos: BASE + '/emprestimo'
};

export default ApiUrl;
