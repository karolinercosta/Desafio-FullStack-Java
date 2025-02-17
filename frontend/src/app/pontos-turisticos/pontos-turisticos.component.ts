import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { HttpService } from '../service/http-service.service';

@Component({
  selector: 'app-pontos-turisticos',
  templateUrl: './pontos-turisticos.component.html',
  styleUrls: ['./pontos-turisticos.component.css']
})
export class PontosTuristicosComponent implements OnInit {
  lsActions: Array<PoTableAction> = this.carregarActions();
  lsColumns: Array<PoTableColumn> = this.carregarColunas();
  lsPontoTuristico: Array<PontoTuristico> = [];
  lsPais: Array<{ value: string, label: string }> = [];

  constructor(
    private httpService: HttpService,
    private poNotification: PoNotificationService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.buscaDadosPais();
    this.carregarPontoTuristico();
  }

  navegarParaPontoTuristico(codigoPontoTuristico: string = "") {
    this.router.navigate(['cadastro', codigoPontoTuristico], { relativeTo: this.activatedRoute });
  }

  carregarActions(): Array<PoTableAction> {
    return [
      {
        label: 'Editar',
        icon: 'po-icon-edit',
        action: (row: PontoTuristico) => { this.navegarParaPontoTuristico(row.id) }
      },
      {
        label: 'Excluir',
        icon: 'po-icon-delete',
        action: (row: PontoTuristico) => { this.deletarCadastro(row.id) }
      }
    ];
  }

  carregarPontoTuristico() {
    return this.httpService.get('pontos-turisticos').subscribe({
      next: (resposta) => {
        let registros: Array<PontoTuristico> = [];
        resposta.forEach(item => {
          let novoPontoTuristico: PontoTuristico = {
            id: item.id,
            nome: item.nome,
            cidade: item.cidade,
            pais: item.pais,
            melhorEstacao: item.melhorEstacao,
            resumo: item.resumo 
          };
          registros.push(novoPontoTuristico);
        });

        this.lsPontoTuristico = [...registros];
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });

  }


  deletarCadastro(id: string): void {
    this.httpService.delete('pontos-turisticos/' + id).subscribe({
      next: (response) => {
        this.poNotification.success('Registro excluido com sucesso!');
        this.carregarPontoTuristico();
      },
      error: (error) => {
        this.poNotification.error(error);
      }
    });
  }

  carregarColunas(): Array<PoTableColumn> {
    return [
      {
        property: 'nome',
        label: 'Nome'
      },
      {
        property: 'pais',
        label: 'País'
      },
      {
        property: 'cidade',
        label: 'Cidade',
      },
      {
        property: 'melhorEstacao',
        label: 'Melhor Estação'
      },
      {
        property: 'resumo',
        label: 'Resumo'
      }
    ];
  }

  buscaDadosPais() {
    this.httpService.get('pais').subscribe({
      next: (resposta) => {
        let registros: Array<{ value: string, label: string }> = [];
        resposta.forEach(item => {
          registros.push({
            value: item.id,
            label: item.nome
          });
        });

        this.lsPais = [...registros];
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });
  }

}

interface PontoTuristico {
  id: string,
  nome: string,
  cidade: string,
  pais: string,
  melhorEstacao: string,
  resumo: string
}