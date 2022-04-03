import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import Autor from 'src/app/global/models/autor.model';
import ApiUrl from 'src/app/global/constant/api-urls.constant';

@Injectable({
  providedIn: 'root',
})
export class AutoresService {
  constructor(private http: HttpClient) {}

  listarAutores(): Observable<Autor[]> {
    return this.http
      .get<Autor[]>(ApiUrl.autores)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));//ES6 probably
  }

  listarUmAutor(id: string): Observable<Autor> {
    return this.http
      .get<Autor>(`${ApiUrl.autores}/${id}`);
  }

  adicionarAutor(autor: Autor): Observable<Autor> {
    return this.http
      .post<Autor>(ApiUrl.autores, autor);
  }

  editarAutor(autor: Autor, id: string): Observable<Autor> {
    return this.http
      .put<Autor>(`${ApiUrl.autores}/${id}`, autor);
  }

  excluirAutor(id: string): Observable<Autor> {
    return this.http
      .delete<Autor>(`${ApiUrl.autores}/${id}`);
  }

}
