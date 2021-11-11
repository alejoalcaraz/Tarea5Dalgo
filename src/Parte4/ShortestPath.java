package Parte4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class ShortestPath

{
	static final int INF=Integer.MAX_VALUE;
	class AdjListNode
	{
		private int v;
		private int weight;
		AdjListNode(int vA, int _w) { v = vA;  weight = _w; }
		int getV() { return v; }
		int getWeight()  { return weight; }
	}

	class Graph
	{
		private int numVertices;
		private LinkedList<AdjListNode>adj[];
		Graph(int v)
		{
			numVertices=v;
			adj = new LinkedList[numVertices];
			for (int i=0; i<v; ++i)
				adj[i] = new LinkedList<AdjListNode>();
		}
		void addEdge(int u, int v, int weight)
		{
			AdjListNode node = new AdjListNode(v,weight);
			adj[u].add(node);// Add v to u's list
		}

		//		Función recursiva que sortea el grafo en una pila en orden topológico
		void topologicalSort(int v, Boolean visitado[], Stack pila)
		{
			visitado[v] = true;

			Iterator<AdjListNode> it = adj[v].iterator();
			while (it.hasNext())
			{
				AdjListNode node =it.next();
				if (!visitado[node.getV()])
					topologicalSort(node.getV(), visitado, pila);
			}
			pila.push(new Integer(v));
		}

		//		La función para encontrar el camino más corto del vértice s a todos los vertices.
		//		Usa la recursividad de topologicalSort para organizar los vértices
		void shortestPath(int s)
		{
			Stack stack = new Stack();
			int dist[] = new int[numVertices];

			// inicializa todos los vertices como no visitados
			Boolean visitado[] = new Boolean[numVertices];
			for (int i = 0; i < numVertices; i++)
				visitado[i] = false;

			//			LLama el topologicalSort para todos los vertices del grafo
			for (int i = 0; i < numVertices; i++)
				if (visitado[i] == false)
					topologicalSort(i, visitado, stack);

			// distancias iniciales infinitas
			for (int i = 0; i < numVertices; i++)
				dist[i] = INF;
			// distancia a la fuente como 0
			dist[s] = 0;

			// Ya la pila está en orden topológico
			while (stack.empty() == false)
			{
				int u = (int)stack.pop();

				// Relajar distancias
				Iterator<AdjListNode> it;
				if (dist[u] != INF)
				{
					it = adj[u].iterator();
					while (it.hasNext())
					{
						AdjListNode i= it.next();
						if (dist[i.getV()] > dist[u] + i.getWeight())
							dist[i.getV()] = dist[u] + i.getWeight();
					}
				}
			}
		}
	}
}