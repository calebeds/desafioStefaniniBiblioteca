import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import Cliente from 'src/app/global/models/cliente.model';
import { ClienteService as ClienteService } from '../clientes.service';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.scss'],
})
export class ListarClientesComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort) 
  sort!: MatSort;

  clientes: Cliente[] = [];
  
  displayedColumns = ['nome', 'email', 'contato', 'acoes'];

  public dataSource = new MatTableDataSource<Cliente>([]);

  constructor
    (
      private clienteApi: ClienteService,
      private router: Router,
      private snackBar: MatSnackBar,
    ) {}

  ngOnInit(): void {
    this.clienteApi.listarClientes().subscribe((res) => {
      this.dataSource.data = res;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  criarNovoCliente = () => {
    this.router.navigate(['clientes/cadastro']);
  }
    

  editarCliente = (email: string) => {
    console.log(email);
    this.router.navigate([`clientes/cadastro/${email}`]);
  }

  excluirCliente = (email: string) => {
    console.log(email);
    this.clienteApi.excluirCliente(email).subscribe(
      success => {
        this.snackBar.open("Cliente Excluído", "Certo!");
        this.clienteApi.listarClientes().subscribe((res) => {
          this.dataSource.data = res;
        });
      },
      err => this.snackBar.open("Erro ao excluir", "OKS!")
    );  
  }


  applyFilter(event: Event) {
    
    this.dataSource.filter = (event.target as HTMLInputElement).value.trim().toLowerCase();
    console.log();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  esconderBtn(): boolean {
    return (this.router.url === '/clientes/listar'); 
  }
  
}
