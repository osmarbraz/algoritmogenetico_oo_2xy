import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Populacao {
		
	private List<Cromossomo> cromossomos;	
	private int tamanho;
	private int tamanhoCromossomo;	
	private double somaAvaliacao;
	
	public Populacao(){
		this(0,0);
	}
	
	public Populacao(int tamanho, int tamanhoCromossomo){
		setTamanho(tamanho);
		setTamanhoCromossomo(tamanhoCromossomo);
		cromossomos = new LinkedList<Cromossomo>();		
		setSomaAvaliacao(0);
	}
	/**
	* Retorna a lista de cromossomos da populacao
	*/
	public List<Cromossomo> getCromossomos(){
		return cromossomos;
	}
	
	public void setCromossomos(List<Cromossomo> cromossomos){
		this.cromossomos = cromossomos;
	}

	public Cromossomo getCromossomo(int i){
		return cromossomos.get(i);
	}
	
	public void adicionaCromossomo(Cromossomo cromossomo){
		cromossomos.add(cromossomo);
	}	
	
	public void removeCromossomo(int i){
		cromossomos.remove(i);
	}
	
	public void atualiza(int i, Cromossomo cromossomo){
		cromossomos.set(i, cromossomo);
	}
	
	/**
	* Tamanho dos cromossomos da populacao da lista
	*/
	public int getTamanhoCromossomos(){
		return cromossomos.size();
	}
	
	public void esvaziar(){
		cromossomos.clear();
	}
	
	/**
	* Tamanho da populacao
	*/
	public int getTamanho(){
		return tamanho;
	}
	
	public void setTamanho(int tamanho){
		this.tamanho = tamanho;
	}
	
	public int getTamanhoCromossomo(){
		return tamanhoCromossomo;
	}
	
	public void setTamanhoCromossomo(int tamanhoCromossomo){
		this.tamanhoCromossomo = tamanhoCromossomo;
	}	
	
	public double getSomaAvaliacao(){
		return somaAvaliacao;
	}
	
	public void setSomaAvaliacao(double somaAvaliacao){
		this.somaAvaliacao = somaAvaliacao;
	}	
	
	/**
	* Inicializa a populacao inicial deforma aleatoria
	*/
	public void inicializaPopulacao(){				
		for(int i = 0; i < getTamanho(); i++){			
			Cromossomo cromossomo = new Cromossomo(getTamanhoCromossomo());
			cromossomo.gerarGenesAleatorios();
			cromossomos.add(cromossomo);
		}		
	}

	/**
	* Realiza a avaliacao dos cromossomos e armazena o resultado da soma
	*/
	public void avaliaCromossomos(){
		double soma = 0;
		for(Cromossomo c : cromossomos){
		    soma = soma + c.getAvaliacao();
		}
		somaAvaliacao = soma;
	}
	/**
	* Sorteia um numero na roleta como base na avaliacao do cromossomo
	*/
	public int getNumeroRoleta(){
		double soma = getSomaAvaliacao();		
		java.util.Random gerador = new java.util.Random();      
		double limite = gerador.nextDouble() * soma;
		double aux = 0;		
		int i = 0;
		Iterator it = cromossomos.iterator();
		while ( (i < getTamanho()) && (aux <limite) ) {
			Cromossomo c = (Cromossomo)it.next();
			aux = aux + c.getAvaliacao();
			i++;
		}
		i--;
		if (i < 0) {
			i = 0;
		}
		return i;
	}	
		
	/**
	* Realiza o crossover de um ponto apartir do indice de dois cromossomos
	*/
	public Cromossomo crossoverUmPonto(int indicePai, int indiceMae) {						
		Cromossomo pai = getCromossomo(indicePai);
		Cromossomo mae = getCromossomo(indiceMae);
		return crossoverUmPonto(pai, mae);				
	}
		
	/**
	* Realiza o crossover de um ponto em apartir de dois cromossomos
	*/
	public Cromossomo crossoverUmPonto(Cromossomo pai, Cromossomo mae) {			
		Cromossomo filho = new Cromossomo(getTamanhoCromossomo());	
		//Gera o ponto de corte		
		int pontoCorte=(new Double(java.lang.Math.random()*getTamanhoCromossomo())).intValue();
		/* Percentual de 50 porcento de realizar o crossover do pai para o mae ou do mae para o pai
		*/
		if (java.lang.Math.random()<0.5) {			
			for(int i=0;i<pontoCorte;i++){				
				filho.getGenes(i).setAlelo(pai.getGenes(i).getAlelo());
			}
			for(int i=pontoCorte;i<getTamanhoCromossomo();i++){
				filho.getGenes(i).setAlelo(mae.getGenes(i).getAlelo());
			}
		} else {			
			for(int i=0;i<pontoCorte;i++){
				filho.getGenes(i).setAlelo(mae.getGenes(i).getAlelo());
			}
			for(int i=pontoCorte;i<getTamanhoCromossomo();i++){
				filho.getGenes(i).setAlelo(pai.getGenes(i).getAlelo());
			}
		}		
		//System.out.println("Filho:" + filho.getGeneString());
		return filho;
	}

	/**
	* Realizaca a geracao de uma populacao a partir de uma outra.
	* Realiza crossover para geracao dos filhos e mutacao no filho gerado.
	*/
	public Populacao geracao(double PercentualMutacao) {
		//Instancia a nova populacao
		Populacao populacaoNova = new Populacao(getTamanho(),getTamanhoCromossomo());			
		for(int i=0;i<this.getTamanho();++i) {
			//Realiza o crossover entre dois cromossomos
			Cromossomo filho = this.crossoverUmPonto(this.getNumeroRoleta(),this.getNumeroRoleta());
			//Realiza a mutacao no cromossomo filho de acordo com o percentual especificado
			filho.mutacao(PercentualMutacao);
			//Adiciona o filho a nova populacao
			populacaoNova.adicionaCromossomo(filho);
		}
		return populacaoNova;
	}

	/**
	* Retorno o indice do melhor cromossomo da ultima populacao gerada segundo a funcao de avaliacao
	*/
	public int getMelhorCromossomo(){
		int i, indiceMelhor = 0;			
		//Avalia os cromossomos
		this.avaliaCromossomos();
		//Recupera o primeiro cromossomo da populacao
		double melhor = this.getCromossomo(0).getAvaliacao();
		for(i=1;i<this.getTamanho();++i) {
			Cromossomo aux =this.getCromossomo(i);			
			if (aux.getAvaliacao() > melhor) {
				melhor = aux.getAvaliacao();
				indiceMelhor = i;
			}
		}
		return(indiceMelhor);
	}		
	
	/**
	* Mostra as estatisticas da populacao
	*/
	public void mostraEstatisticas(){
		System.out.println("\n>>>Estatisticas");
		System.out.println("Numero\tIndividuo\tx\tf(x)\tProbabilidade\t\tRoleta");		
		double soma = this.getSomaAvaliacao();
		double somaProbabilidade = 0;		
		for(int i = 0; i < this.getTamanho(); i ++){	
			double probabilidade = ((this.getCromossomo(i).getAvaliacao()/soma))*100;
			System.out.println(i + "\t" + this.getCromossomo(i).getGeneString() + "\t\t" 
										+ this.getCromossomo(i).getGeneDecimal() +"\t"
										+ this.getCromossomo(i).getAvaliacao() + "\t" 
										+ probabilidade + "\t"
										+ this.getNumeroRoleta() );
			somaProbabilidade = somaProbabilidade + probabilidade;				
		}
		System.out.println("Somatorio:\t\t\t" + soma + "\t" + somaProbabilidade);
	}	
	
	
	/**
	* Mostra os dados do melhor cromossomo da ultima populacao gerada
	*/
	public void mostraDadosMelhorCromossomo(){
		int i = this.getMelhorCromossomo();
		Cromossomo melhor = this.getCromossomo(i);
		System.out.println("\n>>>Dados melhor Cromossomo");
		System.out.println("Indice        : " + i);		
		System.out.println("Crom Binario  : " + melhor.getGeneString());	
		System.out.println("Crom xDecimal : " + melhor.getX());			
		System.out.println("Crom yDecimal : " + melhor.getY());			
		System.out.println("Avaliacao     : " + melhor.getAvaliacao());					
	}
}