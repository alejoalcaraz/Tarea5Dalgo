package source;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {




	private Integer[] predecesor;

	private double[] distTo;

	private double[][] minPaths;

	private IndexMinPQ<Double> minPQ;

	private ArrayList<ArrayList<Edge>> grafo;

	private int numEdges;

	private int numVertices;


	public Dijkstra(int[][] matrizVertices, int n)
	{
		numVertices = n;
		grafo = new ArrayList<>(n);
		predecesor = new Integer[n];
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

		for (int i = 0; i < minPaths.length; i++) {
			String fila ="";
			for (int j = 0; j < matrizVertices.length; j++) {
				fila = fila + " " + minPaths[i][j];
			}
			System.out.println(fila);
		}


	}

	public void addEdge(int from, int to, int cost) {

		if(cost>-1)
		{
			numEdges++;
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
		predecesor = new Integer[numVertices];


		while(!minPQ.isEmpty())
		{
			int idNodo = minPQ.delMin();
			if(visitados[idNodo])
				continue;
			visitados[idNodo] = true;
			verCaminos(idNodo);

		}

		for (int i = 0; i < visitados.length; i++) {
			if(minPaths[inicio][i] > distTo[i])
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



	//	public class Dijkstra {
	//
	//		private double[] distTo;
	//		private int[] predecessors;
	//		private IndexMinPQ<Double> pq;
	//		private ArrayList<Edge>[] adj;
	//		private Graph G;
	//		private int src;
	//
	//		public Dijkstra(Graph G, int src) {
	//
	//			this.adj = G.adjacencyList();
	//			this.src = src;
	//			this.G = G;
	//			distTo = new double[adj.length];
	//			predecessors = new int[adj.length];
	//
	//			pq = new IndexMinPQ<Double>(adj.length);
	//			initializeSingleSource();
	//			pq.insert(src, distTo[src]);
	//
	//			while (!pq.isEmpty()) {
	//				int u = pq.delMin();
	//				for (int i = 0; i < adj[u].size(); i++) {
	//					int v = adj[u].get(i).destination;
	//					double w = adj[u].get(i).weight;
	//					relax(u, v, w);
	//				}
	//			}
	//		}
	//
	//		public void initializeSingleSource() {
	//			for (int i = 0; i < G.size(); i++) {
	//				predecessors[i] = -1;
	//				distTo[i] = Double.POSITIVE_INFINITY;
	//			}
	//			distTo[src] = 0.0;
	//		}
	//
	//		public void relax(int u, int v, double w) {
	//
	//			if(distTo[v] > distTo[u] + w)
	//			{
	//				distTo[v] = distTo[u] + w;
	//				predecessors[v] = u;
	//
	//				if(pq.contains(v)) pq.decreaseKey(v, distTo[v]);
	//				else pq.insert(v, distTo[v]);
	//			}
	//		}
	//
	//
	//
	//		public void printPath(int dest) {
	//			String msg = "";
	//			if (src == dest) {
	//				msg = msg + src;
	//			} else if (predecessors[dest] == -1) {
	//				System.out.println("no path from " + src + " to " + dest + " exists");
	//			} else {
	//				printPath(predecessors[dest]);
	//				msg = msg + "->" + dest;
	//			}
	//			System.out.print(msg);
	//		}
	//
	//		public void printPaths() {
	//			for (int i = 0; i < G.size(); i++) {
	//				printPath(i);
	//				System.out.print(" | Costo de " + src + " a " + i + ": " + distTo[i]);
	//				System.out.println();
	//			}
	//		}
	//
	//		public double[] costosMinimos() {
	//			return distTo;
	//		}
	//	}
}



