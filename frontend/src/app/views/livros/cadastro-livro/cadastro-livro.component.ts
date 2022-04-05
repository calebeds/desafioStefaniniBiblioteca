import { HttpClient } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EventManager } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import Autor from 'src/app/global/models/autor.model';
import Livro from 'src/app/global/models/livro.model';
import { LivrosService } from '../livros.service';

@Component({
  selector: 'app-cadastro-livro',
  templateUrl: './cadastro-livro.component.html',
  styleUrls: ['./cadastro-livro.component.scss']
})
export class CadastroLivroComponent implements OnInit {

  @ViewChild('message')
  message!: ElementRef;


  respostaApi: any;
  mostrarMensagensSucesso: boolean = false;
  id!: string;
  registerForm!: FormGroup; //Form Group
  submitted = false;
  textoBotao: string = 'Cadastrar';

  livro: Livro = { //Implementation
    isbn10: 0,
    isbn13: 0,
    nome: '',
    autor: {
      isni: 0,
      nome: '',
      email: '',
      dataDeNascimento: '',
      biografia: ''
    },
    editora: '',
    anoDePublicacao: '',
    qtdDeExemplares: 0

  };

  autor: Autor =  {
    isni: 0,
    nome: '',
    email: '',
    dataDeNascimento: '',
    biografia: ''
  }

  

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private livroService: LivrosService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.registerForm = this.fb.group({ //Reactive form | Validations
      isbn10: ['', [
        Validators.required, Validators.pattern('[0-9]*'), Validators.minLength(10)]
      ],
      isbn13: ['', [
        Validators.required, Validators.minLength(13), Validators.pattern('[0-9]*')]
      ],
      nome: ['', [Validators.required, Validators.maxLength(50)]],
      autor: ['', [
        Validators.required]
      ],
      editora: ['', [Validators.required, Validators.maxLength(50)] ],
      anoDePublicacao: ['', [Validators.required, Validators.min(1000)]],
      qtdDeExemplares: ['', [Validators.required, Validators.min(0), Validators.pattern('[0-9]*')]]
    });

    this.activatedRoute.params.subscribe(
      params => {
        if(params['id']) {
          this.textoBotao = 'Editar';
          this.id = params['id'];
          this.livroService.listarUmLivro(this.id).subscribe(
            l => {
              if(l.autor == null) {
                this.livro.autor = this.autor;
                this.livro.isbn10 = l.isbn10;
                this.livro.isbn13 = l.isbn13;
                this.livro.nome = l.nome;
                this.livro.editora = l.editora;
                this.livro.anoDePublicacao = l.anoDePublicacao;
                this.livro.qtdDeExemplares = l.qtdDeExemplares;
              } else {
                this.livro = l;
              }
              
              
            }
          );
        }
      }
    );

  }

  get registerFormControl() {
    return this.registerForm.controls;
  }

  onSubmit() {
    console.log(this.registerForm);
    this.submitted = true;
    if (this.registerForm.valid) {
      console.log('AQui');
      if(this.textoBotao == 'Cadastrar') {
        this.livroService.adicionarLivro(this.livro).subscribe(
          success => {
            this.router.navigate(['livros/listar']);
            this.snackBar.open("Livro Cadastrado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Livro não adicionado", "Oks")
          
        );
      } else {
        this.livroService.editarLivro(this.livro, this.id.toString()).subscribe(
          success => {
            this.router.navigate(['livros/listar']);
            this.snackBar.open("Livro Editado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Livro não editado", "Oks")
        );
      }
      
      
    } 
  }

  validarNaApi() {
    
    const isbn = this.message.nativeElement.value;
    this.http.get(`https://openlibrary.org/isbn/${isbn}`).subscribe(
        res => {
          console.log("Worked")
        },
        err => {
          this.http.get(`${err.url}.json`).subscribe( //Não faz sentido, mas é o que temos
            res => {
              console.log(res);
              this.respostaApi = res;
              ///ISBN Válido
              this.snackBar.open('Isbn Válido', 'Eba!')
              this.mostrarMensagensSucesso = true;
            }, err => {
              ///ISBN INVÁLIDO
              this.snackBar.open('Isbn Inválido', 'Oks!')
              this.mostrarMensagensSucesso = false;
            }
          );
        }
        
      );
  }

  

}
