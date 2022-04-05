import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './core/components/layout/layout.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { HomeComponent } from './views/home/home.component';
import { ListarClientesComponent } from './views/clientes/listar-clientes/listar-clientes.component';
import { ListarLivrosComponent } from './views/livros/listar-livros/listar-livros.component';
import { CadastroLivroComponent } from './views/livros/cadastro-livro/cadastro-livro.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CadastroClienteComponent } from './views/clientes/cadastro-cliente/cadastro-cliente.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PhonemaskDirective } from './global/diretivas/phonemask.directive';
import { ListarAutoresComponent } from './views/autores/listar-autores/listar-autores.component';
import { CadastroAutorComponent } from './views/autores/cadastro-autor/cadastro-autor.component';

//Diretivas

//Some mats
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatTabsModule } from '@angular/material/tabs';

//Pipe
import { TruncatePipe } from './global/pipes/truncate.pipe';
import { ListarEmprestimosComponent } from './views/emprestimos/listar-emprestimos/listar-emprestimos.component';
import { CadastroEmprestimoComponent } from './views/emprestimos/cadastro-emprestimo/cadastro-emprestimo.component';




@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    ListarClientesComponent,
    FooterComponent,
    CadastroClienteComponent,
    PhonemaskDirective,
    ListarAutoresComponent,
    CadastroAutorComponent,
    TruncatePipe,
    ListarLivrosComponent,
    CadastroLivroComponent,
    ListarEmprestimosComponent,
    CadastroEmprestimoComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatTableModule,
    MatSnackBarModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
