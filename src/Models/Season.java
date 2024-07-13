package Models;

public class Season {

	private int year;
	
	

	public Season(int year) {
		super();
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
    public String toString() {
        return String.valueOf(year); // Devuelve el año como una cadena
    }
	
}
