import sys


def main():
    file_name: str = sys.argv[1]
    with open(file_name, "r") as file:
        line = file.readline()
        total_vertices = int(line)
        matriz_adjacencia = [[0] * total_vertices] * total_vertices
        for aresta_count in range(0, total_vertices):
            line = [parameters.rstrip("") for parameters in file.readline()]
            print(line)
            vertice1 = int(line[0]) - 1
            vertice2 = int(line[2]) - 1
            matriz_adjacencia[vertice1][vertice2] = 1

        for linhas in matriz_adjacencia:
            print(linhas)


if __name__ == "__main__":
    main()
