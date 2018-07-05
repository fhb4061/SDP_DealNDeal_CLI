
public class Cases {
	
	private int caseNumber;
	private int money;
	
	public Cases(int caseNumber, int money){
		this.setCaseNum(caseNumber);
		this.setMoney(money);
	}
	
	public int getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNum(int caseNum) {
		this.caseNumber = caseNum;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public String toString(){
		return "Case "+this.caseNumber+": $"+this.money;
	}

}
