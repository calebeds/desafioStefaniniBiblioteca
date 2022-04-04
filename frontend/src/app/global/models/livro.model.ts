import Autor from "./autor.model";

export default interface Livro {
  posicao?: number;
  isbn10: number;
  isbn13: number;
  nome: string;
  autor?: Autor;
  editora: string;
  anoDePublicacao: string;
  qtdDeExemplares: number;
}
