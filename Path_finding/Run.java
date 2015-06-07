
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid g = new Grid(10,10);
		System.out.println("Before");
		g.printGrid();
		g.findBestPath();
		System.out.println("After");
		g.printGrid();

	}

}
