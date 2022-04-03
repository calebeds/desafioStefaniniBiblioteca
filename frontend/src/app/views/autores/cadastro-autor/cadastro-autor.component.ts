import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import Autor from 'src/app/global/models/autor.model';
import { AutoresService } from '../autores.service';

@Component({
  selector: 'app-cadastro-autor',
  templateUrl: './cadastro-autor.component.html',
  styleUrls: ['./cadastro-autor.component.scss']
})
export class CadastroAutorComponent implements OnInit {

  
  id!: string;
  registerForm!: FormGroup; //Form Group
  submitted = false;
  textoBotao: string = 'Cadastrar';

  autor: Autor = { //Implementation
    isni: 0,
    nome: '',
    email: '',
    dataDeNascimento: '',
    biografia: ''

  };

  

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private autorService: AutoresService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.registerForm = this.fb.group({ //Reactive form | Validations
      isni: ['', Validators.required],
      nome: ['', [Validators.required, Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.email]],
      dataDeNascimento: ['', [Validators.required]],
      biografia: ['', Validators.required]
    });

    this.activatedRoute.params.subscribe(
      params => {
        if(params['id']) {
          this.textoBotao = 'Editar';
          this.id = params['id'];
          this.autorService.listarUmAutor(this.id).subscribe(
            a => {
              this.autor = a;
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
    this.submitted = true;
    if (this.registerForm.valid) {
      if(this.textoBotao == 'Cadastrar') {
        this.autorService.adicionarAutor(this.autor).subscribe(
          success => {
            this.router.navigate(['autores/listar']);
            this.snackBar.open("Autor Cadastrado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Autor não adicionado", "Oks")
          
        );
      } else {
        this.autorService.editarAutor(this.autor, this.id.toString()).subscribe(
          success => {
            this.router.navigate(['autores/listar']);
            this.snackBar.open("Autor Editado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Autor não editado", "Oks")
        );
      }
      
      
    } 
  }

}
