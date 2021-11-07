package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;




import Parte2.BFS;

public class Main {

	
	public final static String pathDatos0 = "./data/distances5.txt";
	public final static String pathDatos1 = "./data/distances100.txt";
	public final static String pathDatos2 = "./data/distances1000_202110.txt";
	public final static String pathDatos3 = "./data/distancesDisconnected.txt";
	
	
	private static int[][] matriz;
	
	private static Dijkstra dijkstra;
	

	private static int numVertices;

	private static BFS bfs;

	
	public Main()
	{
		
	}

	
	public static void cargarDatos(String path) throws Exception {
		
		
		String pathDatos = path;
		File file = new File(pathDatos);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String linea = br.readLine();
		String[] temp = linea.split("\t");
		int tamRow = temp.length;
		int tamCol = tamRow;
		matriz = new int[tamRow][tamCol];
		int i = 0;
		while(linea != null)
		{
			
			temp  = linea.split("\t");
			for(int j = 0 ;j<tamCol; j++)
			{
				matriz[i][j] = Integer.parseInt(temp[j]);
			}
			i++;
			linea = br.readLine();
		}
		numVertices=tamRow;
		
		System.out.println(matriz[tamRow-2][tamCol-1]);
	}
	
	public static void correrAlgoritmo(int tipo)
	{
		if(tipo==0)
		{
			dijkstra = new Dijkstra(matriz, numVertices);
		}
		else if ( tipo == 3) {
			bfs = new BFS(matriz);
			System.out.println(bfs.getGrupos());
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("------- Punto 1 Dalgo -------");
		System.out.println("A continuaci�n elija el archivo del grafo que desea cargar: "
				+ "\n	0: distances5"
				+ "\n	1: distances100"
				+ "\n	2: distances1000_202110"
				+ "\n	3: distancesDisconnected");
		int p = sc.nextInt();
		String pathDatos = ((p==0)?pathDatos0:(p==1)?pathDatos1:(p==2)?pathDatos2:pathDatos3);
		try {
			cargarDatos(pathDatos);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("A continuaci�n elija el algoritmo "
				+ "\n	0: Dijkstra"
				+ "\n	1: Bellman Ford"
				+ "\n	2: Floyd Warshall"
				+ "\n   3: BFS"
				+ "\n   4: DFS");
		int y = sc.nextInt();
		correrAlgoritmo(y);

	}

}

