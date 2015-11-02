package aa;

public class l implements bInter{
	private l() {
	}

	@Override
	public void gan() {
		System.out.println("l");
	}

	@Override
	public bInter getInstance() {
		// TODO Auto-generated method stub
		return new l();
	}

}
