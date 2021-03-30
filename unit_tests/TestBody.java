/* Integration test for the Body class. */

public class TestBody {
	public static void main(String[] args){
		checkBody();
	}

	private static void checkEquals(double actual, double expected, String label){
		if (Math.abs(expected - actual) <= 0.01 * Math.max(expected, actual)){
			System.out.println("PASS: " + label + " Expected: " + 3.6e22 + " and you gave: " + actual );
		} else {
			System.out.println("FAIL: " + label + " Expected: " + 3.6e22 + " and you gave: " + actual );
		}
	}

	private static void checkBody(){
		System.out.println("Testing Body.java...");
		
		Body a = new Body(1.0e12, 2.1e11, 0, 0, 2.0e30, "sun.gif");
		Body b = new Body(2.3e12, 9.5e11, 0, 0, 6.0e26, "saturn.gif");

		checkEquals(a.calcForceExertedBy(b), 3.6e22, "Pairwise Force");
	}
}