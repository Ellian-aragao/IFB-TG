import sys

from src.interpreter_graph.entity.matrix_adjacent import MatrixAdjacent


def read_matrix_from_cli():
    _validate_args()
    file_name: str = _get_file_name_from_args()
    return _read_file_matrix(file_name)


def _validate_args():
    if len(sys.argv) != 2:
        raise RuntimeError("Should has 1 argument, the input file graph")


def _read_file_matrix(file_name):
    with open(file_name, "r") as file:
        line = file.readline()
        total_vertices = int(line)
        matrix = MatrixAdjacent(total_vertices)
        for aresta_count in range(0, total_vertices):
            # TODO: when read numbers greater than 9 the script break in more positions on array
            line = [parameters.rstrip("") for parameters in file.readline()]
            vertice1 = int(line[0]) - 1
            vertice2 = int(line[2]) - 1
            matrix.add_edge(vertice1, vertice2)
    return matrix


def _get_file_name_from_args():
    return sys.argv[1]
