import java.util.List;
import java.util.LinkedList;

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Cromossomo{

	private List<Gene> genes;	
	private int tamanho;
	
	public Cromossomo(){
		this(0);
	}
	
	public Cromossomo(int tamanho){
		setTamanho(tamanho);		
		//Cria a lista de genes do cromossomo
		genes = new LinkedList<Gene>();
		//Instancia os genes do cromossomo
		for(int i = 0; i < getTamanho(); i++){
			adicionaGene(new Gene());
		}
	}
	
	/**
	* Operacoes com a lista de genes do cromossomo
	*/
	public List<Gene> getGenes(){
		return genes;
	}
	
	public void setGene(List<Gene> genes){
		this.genes = genes;
	}

	public Gene getGenes(int i){
		return genes.get(i);
	}
	
	public void adicionaGene(Gene gene){
		genes.add(gene);
	}	
	
	public void removeGene(int i){
		genes.remove(i);
	}
	
	public void atualiza(int i, Gene gene){
		genes.set(i, gene);
	}
	
	public int getTamanhoGenes(){
		return genes.size();
	}
	
	public void esvaziar(){
		genes.clear();
	}
	
	/**
	* Retorna uma String com o cromossomo em binario
	*/
	public String getGeneString(){
		String sequenciaGene = "";
		for(Gene g : genes){
		    sequenciaGene = sequenciaGene + g.getAlelo();
		}
		return sequenciaGene;
	}
	
	/**
	* Retorna um inteiro com o cromossomo em um numero decimal
	*/
	public int getGeneDecimal(){		
		return converteBinarioParaDecimal();
	}
	
	/**
	* Retorna um inteiro com o tamanho do cromossomo
	*/
	public int getTamanho(){
		return tamanho;
	}
	
	public void setTamanho(int tamanho){
		this.tamanho = tamanho;
	}
	
	/**
	* Gera genes aleatorio para o cromossomo
	*/
	public void gerarGenesAleatorios(){
		java.util.Random gerador = new java.util.Random();         		
		for(int i = 0; i < getTamanho(); i++){						
			int valor = 1;				
			if (java.lang.Math.random()<0.5) {
				valor = 0;
			} 						
			adicionaGene(new Gene(valor));
		}		
	}
	
	public int converteBinarioParaDecimal(){
		return Integer.parseInt(getGeneString(), 2);  
	}
	
	public int converteBinarioParaDecimal(int i, int j){
		String parte = getGeneString().substring(i,j);		
		return Integer.parseInt(parte, 2);  
	}
		
	public double getAvaliacao(){	
		int x = converteBinarioParaDecimal(0,4);
		int y = converteBinarioParaDecimal(4,8);
		return (2 * x + y);
	}	

	public int getX(){
		return converteBinarioParaDecimal(0,3);
	}
	
	public int getY(){
		return converteBinarioParaDecimal(4,7);
	}
	
	
	/**
	* Realiza a mutacao do cromossomo segundo o percentual especificado
	*/
	public void mutacao(double percentual) {
		int i;
		int tamanho = getTamanho();
		String aux, inicio, fim;
		//System.out.println("Antes Mutacao:" + getGeneString());
		for(Gene g : genes){		     
			if (java.lang.Math.random()<percentual) {				
				g.mutar();
			}
		}
		//System.out.println("Depois Mutacao:" + getGeneString());		
	}	
}