import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { LivrosService } from './livros.service';

describe('LivrosService', () => {
  let service: LivrosService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(LivrosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('>> listarLivros | deve retornar Livros[]', () => {
    const expectedData = [
      {
        posicao: 0,
        nome: 'teste',
        email: 'teste',
        contato: 'teste',
        qtdEmprestimos: 0
      },
    ];

    // service.listarLivros().subscribe((res) => {
    //   expect(res).toEqual(expectedData);
    // });

    const req = httpMock.expectOne('http://localhost:8080/api/cliente');
    expect(req.request.method).toBe('GET');
    req.flush(expectedData);
  });
});
