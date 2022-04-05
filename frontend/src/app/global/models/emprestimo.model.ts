export default interface Emprestimo {
  posicao?: number;
  id: number; 
  emailCliente: string;
  isbn: number;
  dataDeInicio: string;
  dataDeEntrega?: string;
}
