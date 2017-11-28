/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal{		
	public static void main(String args[]){
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		ag.setQuantidadeGeracoes(10);
		ag.setTamanhoPopulacao(30);
		ag.setTamanhoCromossomo(8);
		ag.setPercentualMutacao(0.05); //5% de mutacao
		ag.executa();
	}	
}

