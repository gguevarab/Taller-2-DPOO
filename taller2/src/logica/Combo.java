package logica;

public class Combo{
	
	private float descuento;
	private String nombreCombo;
	
	public Combo(String nombreCombo, float descuento){
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
	}
	
	public float getDescuento() {
		return descuento;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombreCombo;
	}

}
