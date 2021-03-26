public class NBody {
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

		int size = in.readInt();//first line of file contains the amount of planets as an integer.

		Body[] bodies = new Body[size];

		in.readDouble();//skip radius

		for (int i = 0; i < size; i++){
			bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}

		return bodies;
	}
} 
