package source;


public class FloydWarshall
{

	public int numVertices;

	public FloydWarshall(int matriz[][], int nVertices)
	{
		numVertices = nVertices;
		int distTo[][] = new int[numVertices][numVertices];



		for (int i = 0; i < numVertices; i++) 
			for (int j = 0; j < numVertices; j++) 
				distTo[i][j] = matriz[i][j];


		for (int k = 0; k < numVertices; k++)
		{
			for (int i = 0; i < numVertices; i++)
			{

				for (int j = 0; j < numVertices; j++)
				{

					if((distTo[i][k]!=-1)&&(distTo[k][j]!=-1)) {
						if ((distTo[i][k] + distTo[k][j] < distTo[i][j])||distTo[i][j]==-1)
							distTo[i][j] = distTo[i][k] + distTo[k][j];
					}
				}
			}
		}

		System.out.println("La tabla con los caminos mas cortos es la siguiente");
		for (int i=0; i<numVertices; ++i)
		{
			for (int j=0; j<numVertices; ++j)
			{
				System.out.print(distTo[i][j]+"   ");
			}
			System.out.println();
		}
	}
}




