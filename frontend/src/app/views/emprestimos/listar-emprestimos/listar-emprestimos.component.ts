import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import Emprestimo from 'src/app/global/models/emprestimo.model';
import { EmprestimosService } from '../emprestimos.service';

@Component({
  selector: 'app-listar-emprestimos',
  templateUrl: './listar-emprestimos.component.html',
  styleUrls: ['./listar-emprestimos.component.scss']
})
export class ListarEmprestimosComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort) 
  sort!: MatSort;

  emprestimos: Emprestimo[] = [];
  
  displayedColumns = 
    [
      'id',
      'emailCliente',
      'isbn',
      'dataDeInicio',
      'dataDeEntrega',
      'acoes'
    ];

  public dataSource = new MatTableDataSource<Emprestimo>([]);

  constructor
    (
      private emprestimoApi: EmprestimosService,
      private router: Router,
      private snackBar: MatSnackBar,
    ) {}

  ngOnInit(): void {
    this.emprestimoApi.listarEmprestimos().subscribe((res) => {
      this.dataSource.data = res;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  criarNovoEmprestimo = () => {
    this.router.navigate(['emprestimos/cadastro']);
  }
    

  editarEmprestimo = (id: string) => {
    console.log(id);
    this.router.navigate([`emprestimos/cadastro/${id}`]);
  }

  excluirEmprestimo = (email: string) => {
    console.log(email);
    this.emprestimoApi.excluirEmprestimo(email).subscribe(
      success => {
        this.snackBar.open("Livro Devolvido", "Certo!");
        this.emprestimoApi.listarEmprestimos().subscribe((res) => {
          this.dataSource.data = res;
        });
      },
      err => this.snackBar.open("Erro ao devolver", "OKS!")
    );  
  }


  applyFilter(event: Event) {
    
    this.dataSource.filter = (event.target as HTMLInputElement).value.trim().toLowerCase();
    console.log();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


}
