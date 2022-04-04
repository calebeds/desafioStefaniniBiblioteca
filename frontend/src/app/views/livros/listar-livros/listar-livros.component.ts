import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import Livro from 'src/app/global/models/livro.model';
import { LivrosService } from '../livros.service';

@Component({
  selector: 'app-listar-livros',
  templateUrl: './listar-livros.component.html',
  styleUrls: ['./listar-livros.component.scss']
})
export class ListarLivrosComponent implements OnInit {

  
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort) 
  sort!: MatSort;

  livros: Livro[] = [];
  
  displayedColumns = 
    [
      'isbn10',
      'isbn13',
      'nome',
      'autor',
      'editora',
      'anoDePublicacao',
      'qtdDeExemplares', 
      'acoes'
    ];

  public dataSource = new MatTableDataSource<Livro>([]);

  constructor
    (
      private livroApi: LivrosService,
      private router: Router,
      private snackBar: MatSnackBar,
    ) {}

  ngOnInit(): void {
    this.livroApi.listarLivros().subscribe((res) => {
      this.dataSource.data = res;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  criarNovoLivro = () => {
    this.router.navigate(['livros/cadastro']);
  }
    

  editarLivro = (email: string) => {
    console.log(email);
    this.router.navigate([`livros/cadastro/${email}`]);
  }

  excluirLivro = (email: string) => {
    console.log(email);
    this.livroApi.excluirLivro(email).subscribe(
      success => {
        this.snackBar.open("Livro Excluído", "Certo!");
        this.livroApi.listarLivros().subscribe((res) => {
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

}
