import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { AutoresService } from './autores.service';

describe('ClientesService', () => {
  let service: AutoresService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(AutoresService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('>> listarClientes | deve retornar Clientes[]', () => {
    const expectedData = [
      {
        posicao: 0,
        nome: 'teste',
        email: 'teste',
        contato: 'teste',
        qtdEmprestimos: 0
      },
    ];

    // service.listarClientes().subscribe((res) => {
    //   expect(res).toEqual(expectedData);
    // });

    const req = httpMock.expectOne('http://localhost:8080/api/cliente');
    expect(req.request.method).toBe('GET');
    req.flush(expectedData);
  });
});
