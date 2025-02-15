import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';


@Component({
  selector: 'app-cadastro-pontos-turisticos',
  templateUrl: './cadastro-pontos-turisticos.component.html',
  styleUrls: ['./cadastro-pontos-turisticos.component.css']
})
export class CadastroPontosTuristicosComponent implements OnInit {
  idPontoTuristico: string ;
  formPontoTuristico: FormGroup;
  title: string = "Novo cadastro de Ponto Turístico";
  lsTipo: Array<{ value: string, label: string }> = [];
  constructor(private formBuilder: FormBuilder,
    private poNotification: PoNotificationService,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpService
  ) {

    this.formPontoTuristico = this.formBuilder.group({
      nome: ['', Validators.compose([Validators.required])],
      pais: ['', Validators.compose([Validators.required])],
      cidade: ['', Validators.compose([Validators.required])],
      melhorEstacao: ['', Validators.compose([Validators.required])],
      resumo: ['', Validators.compose([Validators.required])],
      
    })
  }

  ngOnInit(): void {
    this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
    this.buscaDadosPais();

    if (this.idPontoTuristico !== null) {
      this.buscaDadosPontoTuristico();
      this.title = "Alteração do Ponto Turistico"
    }
  }

  salvar(){
		if (this.validarRegistro()){
			if (this.idPontoTuristico === null){
				this.enviarPost();
			} else {
				this.enviarPut();
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}

  
	validarRegistro(): boolean{
		return this.formPontoTuristico.valid;
	} 

	enviarPost(){
		this.http.post('pontos-turisticos', this.formPontoTuristico.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro criado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	enviarPut(){
		this.http.put('pontos-turisticos/' + this.idPontoTuristico, this.formPontoTuristico.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

  voltar() {
    this.router.navigate(['/ponto-turistico'], { relativeTo: this.route })
  }

  buscaDadosPontoTuristico() {
    this.http.get('pontos-turisticos/' + this.idPontoTuristico).subscribe({
      next: (resposta) => {
        this.formPontoTuristico.patchValue({
          nome: resposta.nome,
          cidade: resposta.cidade,
          pais: resposta.pais,
          melhorEstacao: resposta.melhorEstacao,
          resumo: resposta.resumo,
        })
      },
      error: (erro) => {
        this.poNotification.error(erro)
      }
    })
  }

  
// buscando os dados do select de pais
  buscaDadosPais() {
    this.http.get('pais').subscribe({
      next: (resposta) => {
        let registros: Array<{ value: string, label: string }> = [];
        resposta.forEach(item => {
          registros.push({
            value: item.id,
            label: item.nome
          });
        });

        this.lsTipo = [...registros];
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });
  }

}
