
public class Banker {

	private int offer;
	private int max;
	private int min;
	
	public Banker(Integer max, Integer min){
		this.setMax(max);
		this.setMin(min);
	}
	public void calculateOffer(){
		int expectedValue = min + max;
		this.offer = (int) (expectedValue / 2);
	}
	
	public int getOffer(){
		return offer;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	public String toString(){
		return "Bankers offer: $"+this.getOffer();
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	
}
