package source;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {


	private double[] distTo;

	private double[][] minPaths;

	private IndexMinPQ<Double> minPQ;

	private ArrayList<ArrayList<Edge>> grafo;

	private int[][] matriz;

	private int numVertices;

	public BellmanFord(int[][] matrizVertices, int n)
	{
		matriz = matrizVertices;
		numVertices = n;
		grafo = new ArrayList<>(n);
		distTo = new double[n];
		minPaths = new double[n][n];
		for(double[] m :minPaths)
			Arrays.fill(m, Double.POSITIVE_INFINITY);
		for (int i = 0; i < n; i++) grafo.add(new ArrayList<>());


		for(int j=0 ; j<n ; j++)
		{
			for (int i = 0; i < matrizVertices.length; i++) {
				addEdge(j, i, matrizVertices[j][i]);
			}
		}

		for (int i = 0; i < matrizVertices.length; i++) {
			bellmanFord(i);
		}

		System.out.println("La tabla con los caminos mas cortos es la siguiente");
		for (int i = 0; i < minPaths.length; i++) {
			String fila ="";
			for (int j = 0; j < matrizVertices.length; j++) {
				System.out.print( " " + (int)minPaths[i][j]);
			}
			System.out.println();
		}


	}

	public void addEdge(int from, int to, int cost) {

		if(cost>0)
		{
			grafo.get(from).add(new Edge(to, cost));
		}
	}

	public void bellmanFord(int inicio)
	{


		minPaths[inicio][inicio] = 0.0;
		int cont =0;
		while(cont <numVertices) {
			for (int i = 0; i < numVertices; i++) {
				ArrayList<Edge> caminos = grafo.get(i);
				if(minPaths[inicio][i]!=Double.POSITIVE_INFINITY) {
					for (int j = 0; j < caminos.size(); j++) {
						{
							int to = caminos.get(j).to;
							double cost = caminos.get(j).cost;
							if(minPaths[inicio][to]>minPaths[inicio][i]+cost)
							{
								minPaths[inicio][to] = minPaths[inicio][i]+cost;
							}
						}


					}
				}
			}
			cont++;
		}

	}
}
