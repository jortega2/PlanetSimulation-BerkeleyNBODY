public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	final static double gConstant = 6.67e-11; // final = entity that can only be assigned once. No value change. 
											// Static variable = global variable essentially

	public Body (double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName){ // signature of constructor
		//this constructor creates a new Body and initializes body's elements with the given inputs.
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;

	}

	public Body(Body b){
		// this constructor takes an existing body as input and creates a new copy of that body.
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance (Body a){ //O(1)
		// r = sqrt(dx^2 + dy^2)
		// r = distance, dx = change in x, dy = change in y
		double dx = (a.xxPos - this.xxPos);
		double dy = (a.yyPos - this.yyPos);

		return Math.sqrt(Math.pow((dx),2) + Math.pow((dy), 2));
	}

	public double calcForceExertedBy (Body c){//O(1)
		//get distance
		double distance = this.calcDistance(c);
		//(G*M1*M2)/r^2
		return (gConstant*this.mass*c.mass)/Math.pow(distance,2);
	}

	public double calcForceExertedByX (Body d){//O(1)
		return (this.calcForceExertedBy(d)*(d.xxPos -  this.xxPos))/this.calcDistance(d);
	}

	public double calcForceExertedByY (Body e){//O(1)
		return (this.calcForceExertedBy(e)*(e.yyPos -  this.yyPos))/this.calcDistance(e);
	}

	public double calcNetForceExertedByX (Body [] allBodys){// Fnet = F1+F2+....FN. O(n)

		double netX = 0;
			//enhanced for loop
		for (Body element : allBodys){
			if (this.equals(element)){
				continue;
			}
			netX += this.calcForceExertedByX(element);
		}
		return netX;
	}

	public double calcNetForceExertedByY (Body [] allBodys){// Fnet = F1+F2...+FN. 0(n)

		double netY = 0;

		for (Body element : allBodys){
			if (this.equals(element)){
				continue;
			}
			netY += this.calcForceExertedByY(element);
		}
		return netY;
	}

	public void update (double dt, double fX, double fY){
		//update a bodies acceleration, velocity, and position as they change 
		//due to time (dt), external force (fX and fY)
		double accX = fX / this.mass;
		double accY = fY / this.mass;

		this.xxVel = this.xxVel + dt*accX;
		this.yyVel = this.yyVel + dt*accY;

		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}

	public void draw(){
		//stores the image that will represent the body when drawn
		StdDraw.picture(xxPos,yyPos, "./images/" + imgFileName);
	}
}