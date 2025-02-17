import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { HttpService } from '../service/http-service.service';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {
  title: string = "Cadastro de Comentários";
  lsComentarios: Array<Comentario> = [];
  lsPontoTuristico: Array<{ value: string, label: string }> = [];
  actions: Array<PoTableAction>;

  constructor(
    private httpService: HttpService,
    private poNotification: PoNotificationService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.carregarActions();
    this.carregarPontoTuristico();
  }

  navegarParaComentarios(codigoComentario: string = "") {
    this.router.navigate(['cadastro', codigoComentario], { relativeTo: this.activatedRoute });
  }

  carregarPontoTuristico() {
    return this.httpService.get('pontos-turisticos').subscribe({
      next: (resposta) => {
        let registros: Array<{ value: string, label: string }> = [];
        resposta.forEach(item => {
          registros.push({
            value: item.id,
            label: item.nome
          });
        });
        this.lsPontoTuristico = registros;
        this.carregarComentarios(); // Carregar comentários após carregar pontos turísticos
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });
  }

  carregarComentarios() {
    return this.httpService.get('comentarios').subscribe({
      next: (resposta) => {
        let registros: Array<Comentario> = [];
        resposta.forEach(item => {
          let novoComentario: Comentario = {
            id: item.id,
            user: item.user,
            comentario: item.comentario,
            pontoTuristico: this.getPontoTuristico(item.pontoTuristico),
            data: new Date(item.data).toLocaleDateString('pt-BR', { year: 'numeric', month: '2-digit', day: '2-digit' })
          };
          registros.push(novoComentario);
        });
        this.lsComentarios = registros;
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });
  }

  carregarActions() {
    this.actions = [
      {
        label: 'Editar',
        icon: 'po-icon-edit',
        action: (row: Comentario) => { this.navegarParaComentarios(row.id) }
      },
      {
        label: 'Excluir',
        icon: 'po-icon-delete',
        type: 'danger',
        action: (row: Comentario) => { this.excluirComentario(row.id) }
      }
    ];
  }

  excluirComentario(id: string) {
    this.httpService.delete(`comentarios/${id}`).subscribe({
      next: () => {
        this.poNotification.success("Comentário excluído com sucesso!");
        this.carregarComentarios();
      },
      error: (erro) => {
        this.poNotification.error("Erro ao excluir comentário: " + erro.message);
      }
    });
  }

  getPontoTuristico(id: string): string {
    let pontoTuristico = this.lsPontoTuristico.find(p => p.value == id);
    return pontoTuristico ? pontoTuristico.label : '--';
  }
}

interface Comentario {
  id: string;
  user: string;
  pontoTuristico: string;
  comentario: string;
  data: string;
}