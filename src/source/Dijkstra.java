package source;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {





	private double[] distTo;

	private double[][] minPaths;

	private IndexMinPQ<Double> minPQ;

	private ArrayList<ArrayList<Edge>> grafo;


	private int numVertices;


	public Dijkstra(int[][] matrizVertices, int n)
	{
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
			dijkstra(i);
		}

		System.out.println("La tabla con los caminos mas cortos es la siguiente");
		for (int i = 0; i < minPaths.length; i++) {
			String fila ="";
			for (int j = 0; j < matrizVertices.length; j++) {
				System.out.print( " " + (int)minPaths[i][j]);
			}
			System.out.println(fila);
		}


	}

	public void addEdge(int from, int to, int cost) {

		if(cost>-1)
		{
			grafo.get(from).add(new Edge(to, cost));
		}
	}

	public void dijkstra(int inicio)
	{
		minPQ = new IndexMinPQ<>(numVertices);
		for (int v = 0; v < numVertices; v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[inicio] = 0.0;
		minPQ.insert(inicio, distTo[inicio]);
		boolean[] visitados = new boolean[numVertices];
		Arrays.fill(visitados, false);

		while(!minPQ.isEmpty())
		{
			int idNodo = minPQ.delMin();
			if(visitados[idNodo])
				continue;
			visitados[idNodo] = true;
			verCaminos(idNodo);

		}

		for (int i = 0; i < visitados.length; i++) {
			minPaths[inicio][i] = distTo[i];
		}
	}

	public void verCaminos(int idNodo)
	{
		ArrayList<Edge> caminos = grafo.get(idNodo);

		for(int i = 0;i<caminos.size();i++)
		{
			Edge e = caminos.get(i);
			relax(idNodo, e.to, e.cost);
		}
	}



	public void relax(int actual, int donde, double adicion)
	{
		if(distTo[actual] + adicion < distTo[donde])
		{
			distTo[donde]= distTo[actual] + adicion;

			if(minPQ.contains(donde))
			{
				minPQ.decreaseKey(donde, distTo[donde]);
			}
			else {
				minPQ.insert(donde, distTo[donde]);
			}
		}
	}

}



