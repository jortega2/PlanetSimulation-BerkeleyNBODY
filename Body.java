public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	final static double gConstant = 6.67e-11;

	public Body (double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName){
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;

	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance (Body a){
		// r = sqrt(dx^2 + dy^2)
		// r = distance, dx = change in x, dy = change in y
		double dx = (a.xxPos - this.xxPos);
		double dy = (a.yyPos - this.yyPos);

		return Math.sqrt(Math.pow((dx),2) + Math.pow((dy), 2));
	}

	public double calcForceExertedBy (Body c){

		double distance = this.calcDistance(c);

		return (gConstant*this.mass*c.mass)/Math.pow(distance,2);
	}

	public double calcForceExertedByX (Body d){
			return (this.calcForceExertedBy(d)*(d.xxPos -  this.xxPos))/this.calcDistance(d);
		}

		public double calcForceExertedByY (Body e){
			return (this.calcForceExertedBy(e)*(e.yyPos -  this.yyPos))/this.calcDistance(e);
		}

	public double calcNetForceExertedByX (Body [] allBodys){

		double netX = 0;

			for (int i = 0; i < allBodys.length; i++){
				if (this.equals(allBodys[i])){
					continue;
				}
				netX += this.calcForceExertedByX(allBodys[i]);
			}
			return netX;
	}

	public double calcNetForceExertedByY (Body [] allBodys){

		double netY = 0;

			for (int i = 0; i < allBodys.length; i++){
				if (this.equals(allBodys[i])){
					continue;
				}
				netY += this.calcForceExertedByY(allBodys[i]);
			}
			return netY;
	}


}