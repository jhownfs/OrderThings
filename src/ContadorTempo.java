
public class ContadorTempo {
	
	private double TempI;
	private double TempF;
	private double  dif;

	//inicia relogio
	public void iniciar(){
		this.TempI = System.currentTimeMillis();
	}
	
	//finaliza relogio
	public void parar(){
		
		
		this.TempF = System.currentTimeMillis();
		
		this.dif = this.TempF - this.TempI;	
	    
	}
	
	public double valor(){
		
		return dif;
		
	}

	


}
