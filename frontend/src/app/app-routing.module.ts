import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroAutorComponent } from './views/autores/cadastro-autor/cadastro-autor.component';
import { ListarAutoresComponent } from './views/autores/listar-autores/listar-autores.component';
import { CadastroClienteComponent } from './views/clientes/cadastro-cliente/cadastro-cliente.component';
import { ListarClientesComponent } from './views/clientes/listar-clientes/listar-clientes.component';
import { HomeComponent } from './views/home/home.component';
import { CadastroLivroComponent } from './views/livros/cadastro-livro/cadastro-livro.component';
import { ListarLivrosComponent } from './views/livros/listar-livros/listar-livros.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: { pageTitle: 'Página Inicial' },
  },
  {
    path: 'clientes/listar',
    component: ListarClientesComponent,
    data: { pageTitle: 'Listar Clientes' },
  },
  {
    path: 'clientes/cadastro',
    component: CadastroClienteComponent

  },
  {
    path: 'clientes/cadastro/:id', 
    component: CadastroClienteComponent
  },
  {
    path: 'autores/listar',
    component: ListarAutoresComponent,
  },
  {
    path: 'autores/cadastro',
    component: CadastroAutorComponent
  },
  {
    path: 'autores/cadastro/:id', 
    component: CadastroAutorComponent
  },
  {
    path: 'livros/listar',
    component: ListarLivrosComponent,
  },
  {
    path: 'livros/cadastro',
    component: CadastroLivroComponent
  },
  {
    path: 'livros/cadastro/:id', 
    component: CadastroLivroComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
