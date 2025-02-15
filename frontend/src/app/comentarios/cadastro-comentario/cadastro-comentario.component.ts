import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';

@Component({
  selector: 'app-cadastro-comentario',
  templateUrl: './cadastro-comentario.component.html',
  styleUrls: ['./cadastro-comentario.component.css']
})
export class CadastroComentarioComponent implements OnInit {
  idComentario: string ;
  formComentario: FormGroup;
  title: string = "Novo cadastro de Ponto Turístico";
  lsPontoTuristico: Array<{ value: string, label: string }> = [];
  constructor(private formBuilder: FormBuilder,
    private poNotification: PoNotificationService,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpService
  ) {

    this.formComentario = this.formBuilder.group({
      user: ['', Validators.compose([Validators.required])],
      comentario: ['', Validators.compose([Validators.required])],
      pontoturistico: ['', Validators.compose([Validators.required])],
      
    })
  }

  ngOnInit(): void {
    this.idComentario = this.route.snapshot.paramMap.get('idComentario');
    this.buscaDadosPontosTuristicos();

    if (this.idComentario !== null) {
      this.buscaDadosComentario();
      this.title = "Alteração do Comentário"
    }
  }

  buscaDadosComentario() {
    this.http.get('comentarios/' + this.idComentario).subscribe({
      next: (resposta) => {
        this.formComentario.patchValue({
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

  // buscando os dados do select de Ponto Turistico
  buscaDadosPontosTuristicos() {
    this.http.get('pontos-turisticos').subscribe({
      next: (resposta) => {
        let registros: Array<{ value: string, label: string }> = [];
        resposta.forEach(item => {
          registros.push({
            value: item.id,
            label: item.nome
          });
        });

        this.lsPontoTuristico = [...registros];
       
      },
      error: (erro) => {
        this.poNotification.error(erro);
      }
    });
  }

  
  voltar() {
    this.router.navigate(['/comentario'], { relativeTo: this.route })
  }

  salvar(){
		if (this.validarRegistro()){
			if (this.idComentario === null){
				this.enviarPost();
			} else {
				this.enviarPut();
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}

  enviarPost(){
		this.http.post('comentarios', this.formComentario.value).subscribe({
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
		this.http.put('comentarios/' + this.idComentario, this.formComentario.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

  validarRegistro(): boolean{
		return this.formComentario.valid;
	} 
}
