import java.util.List;
import java.util.LinkedList;

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class AlgoritmoGenetico{

	private int quantidadeGeracoes;
	private int tamanhoPopulacao;
	private int tamanhoCromossomo;	
	private double percentualMutacao;
	private List<Populacao> geracoes;	
	
	public AlgoritmoGenetico(){
		this(0,0,0,0.0);
	}
	
	public AlgoritmoGenetico(int quantidadeGeracoes, int tamanhoPopulacao, int tamanhoCromossomo, double percentualMutacao){
		setQuantidadeGeracoes(quantidadeGeracoes);
		setTamanhoPopulacao(tamanhoPopulacao);
		setTamanhoCromossomo(tamanhoCromossomo);
		setPercentualMutacao(percentualMutacao);
		geracoes = new LinkedList<Populacao>();		
	}
	
	public int getQuantidadeGeracoes(){
		return quantidadeGeracoes;
	}
	
	public void setQuantidadeGeracoes(int quantidadeGeracoes){
		this.quantidadeGeracoes = quantidadeGeracoes;
	}	
	
	public int getTamanhoPopulacao(){
		return tamanhoPopulacao;
	}
	
	public void setTamanhoPopulacao(int tamanhoPopulacao){
		this.tamanhoPopulacao = tamanhoPopulacao;
	}	
	
	public int getTamanhoCromossomo(){
		return tamanhoCromossomo;
	}
	
	public void setTamanhoCromossomo(int tamanhoCromossomo){
		this.tamanhoCromossomo = tamanhoCromossomo;
	}	

	public double getPercentualMutacao(){
		return percentualMutacao;
	}
	
	public void setPercentualMutacao(double percentualMutacao){
		this.percentualMutacao = percentualMutacao;
	}
	
	/**	
	* Operacoes da lista de geracoes
	*/
	public List<Populacao> getGeracoes(){
		return geracoes;
	}
	
	public void setGeracoes(List<Populacao> geracoes){
		this.geracoes = geracoes;
	}

	public Populacao getPopulacao(int i){
		return geracoes.get(i);
	}
	
	public void adicionaPopulacao(Populacao populacao){
		geracoes.add(populacao);
	}	
	
	public void removePopulacao(int i){
		geracoes.remove(i);
	}
	
	public void atualiza(int i, Populacao populacao){
		geracoes.set(i, populacao);
	}
	
	public int getTamanho(){
		return geracoes.size();
	}
	
	public void esvaziar(){
		geracoes.clear();
	}	
	
	/**
	* Retorna a ultima populacao da lista de geracoes
	*/
	public Populacao getUltimaPopulacao(){
		return geracoes.get(geracoes.size()-1);
	}
	
	/**
	* Executa o algoritmo genetico
	*/
	public void executa(){
		//Tempo da geracao atual
		int t = 0;
		//Instacia a populacao inicial
		Populacao populacao = new Populacao(getTamanhoPopulacao(),getTamanhoCromossomo());		
		//Inicializa os cromossomos e os genes da populacao inicial
		populacao.inicializaPopulacao();
		//Avalia os cromossomos da populacao inicial
		populacao.avaliaCromossomos();
		//Mostra as estatisticas da populacao inicial
		populacao.mostraEstatisticas();
		//Adiciona a polucacao a lista de geracoes
		this.adicionaPopulacao(populacao); 
		//Executa durante o numero geracoes especificadas
		while (t < getQuantidadeGeracoes()){
			//Incrementa o tempo da nova geracao
			t = t + 1;			
			System.out.println("\nCalculando a geracao " + t + "...");
			//Gera a nova populacao a partir da anterior
			populacao = populacao.geracao(getPercentualMutacao()); 
			//Avalia os cromossomos da populacao gerada
			populacao.avaliaCromossomos();
			//Mostra as estatisticas da populacao gerada
			populacao.mostraEstatisticas();
			//Adiciona a populacao gerada a lista de populacoes
			this.adicionaPopulacao(populacao);
		}
		//Mostra o melhor cromossomo da ultima populacao gerada
		getUltimaPopulacao().mostraDadosMelhorCromossomo();	
	}	
}