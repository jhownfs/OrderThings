
public class Ordenador extends Object{
	
   private String vetor[];
   private ContadorTempo t = new ContadorTempo();
   private int tamanho, contagem;
   private String[] vetorB = null;
   private String[] vetorS = null;
   private String[] vetorI = null;
   private double vetorTB[], vetorTS[], vetorTI[];
   private double tempMB, tempMS, tempMI;
	//criar vetor de numeros
	public void criarVetor( int n){
		vetor = new String[n];
		this.tamanho = n;
		this.contagem = 0;
	}
	//adicionar numero ao vetor
	public void add(String f){
		this.vetor[contagem] = f;
		this.contagem++;
	}
	
	//pegar tamanho do vetor
	public int length(){
		return this.tamanho;
	}
	//metodo que retornar o tempo de execução em cada loop do for
	public double tempo(String t,int n){
		t = t.toUpperCase();
		if(t == "B"){
		    return this.vetorTB[n];
		}else if(t == "S"){
			return this.vetorTS[n];
		}else if(t == "I"){
			return this.vetorTI[n];
		}
		return 0;
	}
	
	//metodo responsavel por retornar as medias de tempo
	public double tempoMedio(String t){
		t = t.toUpperCase();
		if(t == "B"){
			return this.tempMB;
		}else if(t == "S"){
			return this.tempMS;
		}else if(t == "I"){
			return this.tempMI;
		}
	
		return 0;
	}
	
	//exibir vetor Ordenado de acordo com a escolha
	public String imprimir(String nome, int n){
		nome = nome.toUpperCase();
		if(nome == "B"){
			return this.vetorB[n];
		}else if(nome == "S"){
			return this.vetorS[n];
		}else if(nome == "I"){
			return this.vetorI[n];
		}else
			return null;
	}
	
	  //ordenação do vetor pelo procedimento de bolha
		public void sortBolha(int repet){
			String aux = "";
			this.vetorTB = new double[repet]; // criar vetor que vai receber os tempos de cada for
			//inicia barra de progresso
			for(int f=0; f<repet;f++){
				t.iniciar();
				
			this.vetorB = this.vetor.clone();
			
			for(int i=0; i<this.vetor.length;i++){
				for(int j=0; j<i+1; j++){					
					if(vetorB[i].compareTo(vetorB[j]) < 0){
						aux = this.vetorB[i];
						this.vetorB[i] = this.vetorB[j];
						this.vetorB[j] = aux;
					}
				  }
				}
		       
			   t.parar();
			 this.vetorTB[f] = t.valor()/1000;
			 this.tempMB += this.vetorTB[f];
			}
			
		this.tempMB = this.tempMB/repet;
      }

		//ordenação do vetor pelo procedimento de seleção
		public void sortSelecao(int repet){
			String aux;
			int menor;
			this.vetorTS = new double[repet];
			
			for(int f =0; f<repet;f++){
				t.iniciar(); // inicia relogio
			this.vetorS = this.vetor.clone();
			
			for(int i=0;i<this.vetor.length;i++){
				menor = i;
				for(int j =i+1;j<this.vetor.length;j++){
					
					if(vetorS[j].compareTo(vetorS[menor]) < 0){
						menor = j;
					}
				}
					if(menor != i){
					   aux = this.vetorS[i];
					   this.vetorS[i] = this.vetorS[menor];
					   this.vetorS[menor] = aux;
					}
				}
			 t.parar(); // para relogio
			 this.vetorTS[f] = t.valor()/1000;
			 this.tempMS += this.vetorTS[f];
			}
			this.tempMS = this.tempMS/repet;
		}
		
		//ordenação do vetor pelo procedimento de inserção
		public void sortInsert(int repet){
			String aux;
			int i=0;
			this.vetorTI = new double[repet];
			for(int f =0; f<repet;f++){
				t.iniciar(); //inicia relogio		
				this.vetorI = this.vetor.clone();
				
				for(int j=0;j<this.vetor.length;j++){
					aux = this.vetorI[j];
					
					i = j-1;
					while((i >=0) && (vetorI[i].compareTo(aux)) > 0){
						this.vetorI[i+1] = this.vetorI[i];
						this.vetorI[i] = aux;
						i = i -1;
			   }
		   }
				 t.parar(); // para relogio
				 this.vetorTI[f] = t.valor()/1000;
				 this.tempMI += this.vetorTI[f];
		}
			this.tempMI = this.tempMI/repet;
	}
	
		
}