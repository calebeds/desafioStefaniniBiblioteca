import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import Livros from 'src/app/global/models/livro.model';
import ApiUrl from 'src/app/global/constant/api-urls.constant';

@Injectable({
  providedIn: 'root',
})
export class LivrosService {
  constructor(private http: HttpClient) {}

  listarLivros(): Observable<Livros[]> {
    return this.http
      .get<Livros[]>(ApiUrl.livros)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));//ES6 probably
  }

  listarUmLivro(id: string): Observable<Livros> {
    return this.http
      .get<Livros>(`${ApiUrl.livros}/${id}`);
  }

  adicionarLivro(Livros: Livros): Observable<Livros> {
    return this.http
      .post<Livros>(ApiUrl.livros, Livros);
  }

  editarLivro(Livros: Livros, id: string): Observable<Livros> {
    return this.http
      .put<Livros>(`${ApiUrl.livros}/${id}`, Livros);
  }

  excluirLivro(id: string): Observable<Livros> {
    return this.http
      .delete<Livros>(`${ApiUrl.livros}/${id}`);
  }

}
