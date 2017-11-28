/**
 * @author Osmar de Oliveira Braz Junior
 */ 
public class Gene{

	private int alelo;
	
	public Gene(){
		this(0);
	}
	public Gene(int alelo){
		setAlelo(alelo);
	}
	
	public void setAlelo(int alelo){
		this.alelo = alelo;
	}
	
	public int getAlelo(){
		return alelo;
	}

	/**	
	* Realiza a mutação do gene
	*/
	public void mutar(){
		if (getAlelo()==1){
			setAlelo(0);
		} else {
			setAlelo(1);
		}
	}
}