import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import Cliente from 'src/app/global/models/cliente.model';
import ApiUrl from 'src/app/global/constant/api-urls.constant';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  constructor(private http: HttpClient) {}

  listarClientes(): Observable<Cliente[]> {
    return this.http
      .get<Cliente[]>(ApiUrl.listarClientes)
      .pipe(map((res) => res.map((c, i) => ({ ...c, posicao: i }))));//ES6 probably
  }

  listarUmCliente(id: string): Observable<Cliente> {
    return this.http
      .get<Cliente>(`${ApiUrl.BASE}/${id}`);
  }

  adicionarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http
      .post<Cliente>(ApiUrl.adicionarCliente, cliente);
  }

  editarCliente(cliente: Cliente, id: string): Observable<Cliente> {
    return this.http
      .put<Cliente>(`${ApiUrl.BASE}/${id}`, cliente);
  }

  excluirCliente(id: string): Observable<Cliente> {
    return this.http
      .delete<Cliente>(`${ApiUrl.BASE}/${id}`);
  }

}
