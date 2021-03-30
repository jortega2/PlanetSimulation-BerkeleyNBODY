public class NBody {
	public static void main(String[] args){
		if (args.length < 2){
			System.out.println("Please supply a filename as a command line");
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		double time = 0;

		String filename = args[2];

		//radius for the universe (total size)
		double radius = readRadius(filename);
		//bodies for the universe (planets, sun, etc.)
		Body[] bodies = readBodies(filename);

		StdDraw.enableDoubleBuffering();
		//set the scale (how big) the universe is
		StdDraw.setScale(-radius, radius);

		//==================Animation============================
		//=======================================================
		while (time <= T){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			//calculate forces
			for (int i = 0; i < bodies.length; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			//update the bodies movement
			for (int i = 0; i < bodies.length; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			//draw background
			StdDraw.picture(0,0, "./images/starfield.jpg");
			//draw bodies
			for (int i = 0; i < bodies.length; i++){
				bodies[i].draw();
			}
			//draw
			StdDraw.show();
			//pause
			StdDraw.pause(10);
			//increment time
			time = time + dt;

		}
		//output data to console
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
							bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
	public static double readRadius(String pathToFile){
		//receives a string containing a path to file, then returns the 2nd line in that file as a double. 
		In in = new In(pathToFile);

		double radius = 0;

		for (int i = 0; i < 2; i++){
			radius = in.readDouble();
		}
		System.out.println(in);
		return radius;
	}
	public static Body[] readBodies(String pathToFile){
		//receives a string containing a path to a file, then creates and returns an array of bodies using data from the file. 
		In in = new In(pathToFile);
		//first line of file contains the amount of planets as an integer.
		int size = in.readInt();
		//skip radius
		in.readDouble();

		for (int i = 0; i < size; i++){
			bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}

		return bodies;
	}
} 
