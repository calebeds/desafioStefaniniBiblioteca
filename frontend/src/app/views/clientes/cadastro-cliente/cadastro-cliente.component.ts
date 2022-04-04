import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { ClienteService } from '../clientes.service';
import Cliente from '../../../global/models/cliente.model';import { ActivatedRoute, Router } from '@angular/router';
;

@Component({
  selector: 'app-cadastro-cliente',
  templateUrl: './cadastro-cliente.component.html',
  styleUrls: ['./cadastro-cliente.component.scss']
})
export class CadastroClienteComponent implements OnInit {

  id!: string;
  registerForm!: FormGroup; 
  submitted = false;
  textoBotao: string = 'Cadastrar';

  cliente: Cliente = { //Implementation
    nome: '',
    contato: '',
    email: '',
    qtdEmprestimos: 0
  };

  

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefone: ['', [Validators.required]]
    });

    this.activatedRoute.params.subscribe(
      params => {
        if(params['id']) {
          this.textoBotao = 'Editar';
          this.id = params['id'];
          this.clienteService.listarUmCliente(this.id).subscribe(
            c => {
              this.cliente = c;
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
        this.clienteService.adicionarCliente(this.cliente).subscribe(
          success => {
            this.router.navigate(['clientes/listar']);
            this.snackBar.open("Cliente Cadastrado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Cliente não adicionado", "Oks")
          
        );
      } else {
        this.clienteService.editarCliente(this.cliente, this.id).subscribe(
          success => {
            this.router.navigate(['clientes/listar']);
            this.snackBar.open("Cliente Editado com sucesso!", 'Certo!');
            console.table(this.registerForm.value);
          },
          error => this.snackBar.open("Erro!! Cliente não editado", "Oks")
        );
      }
      
      
    } 
  }

}


