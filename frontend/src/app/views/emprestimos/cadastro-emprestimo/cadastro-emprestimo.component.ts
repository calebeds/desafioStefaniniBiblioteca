import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import Emprestimo from 'src/app/global/models/emprestimo.model';
import { EmprestimosService } from '../emprestimos.service';

@Component({
  selector: 'app-cadastro-emprestimo',
  templateUrl: './cadastro-emprestimo.component.html',
  styleUrls: ['./cadastro-emprestimo.component.scss']
})
export class CadastroEmprestimoComponent implements OnInit {

  @ViewChild('message')
  message!: ElementRef;


  respostaApi: any;
  mostrarMensagensSucesso: boolean = false;
  id!: string;
  registerForm!: FormGroup; //Form Group
  submitted = false;
  textoBotao: string = 'Cadastrar';

  emprestimo: Emprestimo = { //Implementation
   id: 0,
   isbn: 0,
   dataDeEntrega: '',
   emailCliente: '',
   dataDeInicio: ''


  };

  

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private emprestimoService: EmprestimosService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.registerForm = this.fb.group({ //Reactive form | Validations
      isbn: ['', [
        Validators.required, Validators.pattern('[0-9]*'), Validators.minLength(10)],
      ],
      emailCliente: ['', [
        Validators.required, Validators.email
      ]],
      dataDeInicio: ['', [
        Validators.required
      ]],
      dataDeEntrega: ['']

    });

    this.activatedRoute.params.subscribe(
      params => {
        if(params['id']) {
          this.textoBotao = 'Editar';
          this.id = params['id'];
          this.emprestimoService.listarUmEmprestimo(this.id).subscribe(
            a => {
              this.emprestimo = a;
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
        this.emprestimoService.adicionarEmprestimo(this.emprestimo).subscribe(
          success => {
            this.router.navigate(['emprestimos/listar']);
            this.snackBar.open("Emprestimo Cadastrado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Emprestimo não adicionado", "Oks")
          
        );
      } else {
        this.emprestimoService.editarEmprestimo(this.emprestimo, parseInt(this.id)).subscribe(
          success => {
            this.router.navigate(['emprestimos/listar']);
            this.snackBar.open("Emprestimo Editado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Emprestimo não editado", "Oks")
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
