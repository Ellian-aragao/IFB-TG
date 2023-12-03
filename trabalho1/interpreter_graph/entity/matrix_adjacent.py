from __future__ import annotations

import logging
from functools import reduce
from typing import List

log = logging.getLogger(__name__)


class MatrixAdjacent:
    _total_vertexes: int
    _matrix_adjacent: List[List[int]]
    _vertexes_degree: List[int]
    _edge_lenght: int
    _pre_compute_degree: bool

    def __init__(self, total_vertexes: int, pre_compute_degree=False):
        if total_vertexes <= 1:
            raise RuntimeError("Vertices should be more than 1")

        self._pre_compute_degree = pre_compute_degree
        self._edge_lenght = 0
        self._vertexes_degree = [0 for _ in range(total_vertexes)]
        self._total_vertexes = total_vertexes
        self._matrix_adjacent = [[0 for _ in range(total_vertexes)] for _ in range(total_vertexes)]

    def _validate_bounds_vertex(self, vertex: int) -> bool:
        return 0 <= vertex < self.get_total_vertexes()

    def _validate_vertexes_to_edge(self, vertex1: int, vertex2: int) -> None:
        log.debug(f"validating vertexes, entry ({vertex1}, {vertex2})")
        is_valid_vertex1 = self._validate_bounds_vertex(vertex1)
        is_valid_vertex2 = self._validate_bounds_vertex(vertex2)
        if (not is_valid_vertex1) or (not is_valid_vertex2):
            message_error = (f"Bounds of vertex shound be major than 0 and minour than {self.get_total_vertexes()}, "
                             f"the entry value was ({vertex1},{vertex2})")
            raise RuntimeError(message_error)

    def add_edge(self, vertex1: int, vertex2: int) -> MatrixAdjacent:
        self._validate_vertexes_to_edge(vertex1, vertex2)
        self._matrix_adjacent[vertex1][vertex2] += 1
        self._matrix_adjacent[vertex2][vertex1] += 1
        self._edge_lenght += 1
        self._check_and_process_bi_vertex_degree(vertex1, vertex2)
        return self

    def print_matrix(self) -> MatrixAdjacent:
        print(f"total vertexes: {self.get_total_vertexes()}")
        print(f"total edges: {self._edge_lenght}")
        for i, vertex_degree in enumerate(self.get_vertexes_degree()):
            print(f"vertex: {i} degree: {vertex_degree}")

        for lines in self._matrix_adjacent:
            print(lines)
        return self

    def get_total_vertexes(self) -> int:
        return self._total_vertexes

    def get_vertexes_degree(self) -> List[int]:
        return self._vertexes_degree

    def pre_compute_degree(self, pre_compute_degree: bool) -> MatrixAdjacent:
        self._pre_compute_degree = pre_compute_degree
        return self

    def _check_and_process_bi_vertex_degree(self, vertex1: int, vertex2: int):
        if self._pre_compute_degree:
            self._vertexes_degree[vertex1] = self._process_and_get_vertex_degree(self._matrix_adjacent[vertex1])
            self._vertexes_degree[vertex2] = self._process_and_get_vertex_degree(self._matrix_adjacent[vertex2])

    def process_all_vertexes_degrees(self) -> MatrixAdjacent:
        self._vertexes_degree = []
        for vertex in self._matrix_adjacent:
            vertex_degree = self._process_and_get_vertex_degree(vertex)
            self._vertexes_degree.append(vertex_degree)

        return self

    def _process_and_get_vertex_degree(self, vertex_list: List[int]) -> int:
        return reduce(lambda a, b: a + b, vertex_list)
