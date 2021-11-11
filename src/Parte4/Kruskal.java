package Parte4;

import java.util.*;

public class Kruskal{

	private ArrayList<pareja> edges;

	private int n;

	private int m;

	int fathers[] = new int[1000];

	public int find(int x){
		if(fathers[x] == x){
			return x;
		}
		return find(fathers[x]);
	}

	public void unite(int x, int y){
		int fx = find(x);
		int fy = find(y);
		fathers[fx] = fy;
	}
	public Kruskal(ArrayList<pareja> edges)
	{
		edges = new ArrayList<pareja>();
		this.edges = edges;
	}

	public void kruskal()
	{
		int a,b,w;
		int mst_weight = 0, mst_edges = 0;
		int	mstEje = 0;
		//	Creamos un sorte nuevo que compare los pesos de dos parejas y las organice
		Collections.sort(edges, new Comparator<pareja>() {
			@Override 
			public int compare(pareja p1, pareja p2) {
				return p1.w - p2.w;
			}
		});

		while( ( mst_edges < n-1) || (mstEje < m) ){
			//	guardamos cada valor de la pareja
			a = edges.get(mstEje).a;
			b = edges.get(mstEje).b;
			w = edges.get(mstEje).w;
			//	Si a y b están en árboles diferentes los unimos
			if( find(a) != find(b) ) {
				unite(a,b);
				//			sumamos el peso al peso total del MST
				mst_weight += w;
				//	imprimimos el nuevo eje 
				System.out.println(a + " " + b + " " + w);
				mst_edges++;
			}
			//	siguiente eje a revisar
			mstEje++;
		}
	}

	static class pareja {

		public int w,a,b;

		public pareja(int w,int a, int b){
			this.a =a;
			this.b =b;
			this.w =w;
		}

		public String toString(){
			return "" + this.w;
		}

	}

}


