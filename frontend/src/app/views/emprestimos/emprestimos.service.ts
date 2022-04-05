import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import Emprestimo from 'src/app/global/models/emprestimo.model';
import ApiUrl from 'src/app/global/constant/api-urls.constant';

@Injectable({
  providedIn: 'root',
})
export class EmprestimosService {
  constructor(private http: HttpClient) {}

  listarEmprestimos(): Observable<Emprestimo[]> {
    return this.http
      .get<Emprestimo[]>(ApiUrl.emprestimos)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));//ES6 probably
  }

  listarUmEmprestimo(id: string): Observable<Emprestimo> {
    return this.http
      .get<Emprestimo>(`${ApiUrl.emprestimos}/${id}`);
  }

  adicionarEmprestimo(emprestimo: Emprestimo): Observable<Emprestimo> {
    return this.http
      .post<Emprestimo>(ApiUrl.emprestimos, emprestimo);
  }

  editarEmprestimo(emprestimo: Emprestimo, id: number): Observable<Emprestimo> {
    return this.http
      .put<Emprestimo>(`${ApiUrl.emprestimos}/${id}`, emprestimo);
  }

  excluirEmprestimo(id: string): Observable<Emprestimo> {
    return this.http
      .delete<Emprestimo>(`${ApiUrl.emprestimos}/${id}`);
  }

}
