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
  lsComentarios: Array<Comentario> = []

  constructor(
    private httpService: HttpService,
    private poNotification: PoNotificationService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.carregarComentarios();
  }

  navegarParaComentarios(codigoPontoTuristico: string = "") {
    this.router.navigate(['cadastro', codigoPontoTuristico], { relativeTo: this.activatedRoute });
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
            pontoTuristico: item.pontoturistico,
          }
          registros.push(novoComentario);
        })
        this.lsComentarios = registros;
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    })
  }



}

interface Comentario{
	id: string,
	user: string,
	pontoTuristico: string,
	comentario: string,
}
