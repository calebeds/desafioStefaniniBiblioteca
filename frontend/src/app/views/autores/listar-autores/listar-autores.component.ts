import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import Autor from 'src/app/global/models/autor.model';
import { AutoresService as AutorService } from '../autores.service';

@Component({
  selector: 'app-listar-autores',
  templateUrl: './listar-autores.component.html',
  styleUrls: ['./listar-autores.component.scss']
})
export class ListarAutoresComponent implements OnInit {
  displayedColumns = ['ISNI','Nome', 'Email', 'Data de Nascimento', 'Biografia' ,'Ações'];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort) 
  sort!: MatSort;

  public dataSource = new MatTableDataSource<Autor>([]);

 

  constructor
    (
      private autorApi: AutorService,
      private router: Router,
      private snackBar: MatSnackBar,
    ) {}

  ngOnInit(): void {
    this.autorApi.listarAutores().subscribe((res) => {
      this.dataSource.data = res;
    });
    console.log(this.router.url); 
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  criarNovoAutor = () => {
    this.router.navigate(['autores/cadastro']);
  }
    

  editarAutor = (isni: string) => {
    console.log(isni);
    this.router.navigate([`autores/cadastro/${isni}`]);
  }

  excluirAutor = (isni: string) => {
    console.log(isni);
    this.autorApi.excluirAutor(isni).subscribe(
      success => {
        this.snackBar.open("Autor Excluído", "Certo!");
        this.autorApi.listarAutores().subscribe((res) => {
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
    return (this.router.url === '/autores/listar'); 
  }
}
