#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char **argv) {
    int size, rank;
    srand(time(NULL));

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int globaldata[size];
    int localdata;

    int i;
    if (rank == 0) {
        printf("Before: ");
        for (i = 0; i < size; i++) {
            globaldata[i] = rand() % 100;
            printf("%d ", globaldata[i]);
        }
        printf("\n");
    }

    MPI_Scatter(globaldata, 1, MPI_INT, &localdata, 1, MPI_INT, 0, MPI_COMM_WORLD);

    localdata++;

    MPI_Gather(&localdata, 1, MPI_INT, globaldata, 1, MPI_INT, 0, MPI_COMM_WORLD);

    if (rank == 0) {
    	printf("After: ");
    	for (i = 0; i < size; i++) {
    		printf("%d ", globaldata[i]);
    	}
    	printf("\n");
    }

    MPI_Finalize();
    return 0;
}