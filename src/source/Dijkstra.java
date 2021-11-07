package source;

import java.util.ArrayList;

public class Dijkstra {




	private Integer[] predecesor;

	private double[] distTo;

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
		for (int i = 0; i < n; i++) grafo.add(new ArrayList<>());


		for(int j=0 ; j<n ; j++)
		{
			for (int i = 0; i < matrizVertices.length; i++) {
				addEdge(j, i, matrizVertices[j][i]);
			}
		}


	}

	public void addEdge(int from, int to, int cost) {

		if(cost>-1)
		{
			numEdges++;
			grafo.get(from).add(new Edge(to, cost));
		}
	}

	public double dijkstra(int inicio)
	{
		minPQ = new IndexMinPQ<>(numVertices);
		for (int v = 0; v < numVertices; v++)
            distTo[v] = Double.POSITIVE_INFINITY;
		distTo[inicio] = 0.0;
		minPQ.insert(inicio, distTo[inicio]);
		boolean[] visitados = new boolean[numVertices];
		predecesor = new Integer[numVertices];
		
		
		while(!minPQ.isEmpty())
		{
			int idNodo = minPQ.delMin();
			visitados[idNodo] = true;
			double minimo = minPQ.minKey();
		}
	}



	public class Dijkstra {

		private double[] distTo;
		private int[] predecessors;
		private IndexMinPQ<Double> pq;
		private ArrayList<Edge>[] adj;
		private Graph G;
		private int src;

		public Dijkstra(Graph G, int src) {

			this.adj = G.adjacencyList();
			this.src = src;
			this.G = G;
			distTo = new double[adj.length];
			predecessors = new int[adj.length];

			pq = new IndexMinPQ<Double>(adj.length);
			initializeSingleSource();
			pq.insert(src, distTo[src]);

			while (!pq.isEmpty()) {
				int u = pq.delMin();
				for (int i = 0; i < adj[u].size(); i++) {
					int v = adj[u].get(i).destination;
					double w = adj[u].get(i).weight;
					relax(u, v, w);
				}
			}
		}

		public void initializeSingleSource() {
			for (int i = 0; i < G.size(); i++) {
				predecessors[i] = -1;
				distTo[i] = Double.POSITIVE_INFINITY;
			}
			distTo[src] = 0.0;
		}

		public void relax(int u, int v, double w) {

			if(distTo[v] > distTo[u] + w)
			{
				distTo[v] = distTo[u] + w;
				predecessors[v] = u;

				if(pq.contains(v)) pq.decreaseKey(v, distTo[v]);
				else pq.insert(v, distTo[v]);
			}
		}



		public void printPath(int dest) {
			String msg = "";
			if (src == dest) {
				msg = msg + src;
			} else if (predecessors[dest] == -1) {
				System.out.println("no path from " + src + " to " + dest + " exists");
			} else {
				printPath(predecessors[dest]);
				msg = msg + "->" + dest;
			}
			System.out.print(msg);
		}

		public void printPaths() {
			for (int i = 0; i < G.size(); i++) {
				printPath(i);
				System.out.print(" | Costo de " + src + " a " + i + ": " + distTo[i]);
				System.out.println();
			}
		}

		public double[] costosMinimos() {
			return distTo;
		}
	}
}



