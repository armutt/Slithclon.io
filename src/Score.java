
public class Score {
	String name;
	int score;
	@Override
	public String toString() {
		
		if(score!=0)	
			return name+" "+score;
		else return name+" --";
	}
	public Score(String s) {
		name=s;
		score=0;
	}
	public Score(String s, int i) {
		
		name=s;
		score=i;

	}
}
