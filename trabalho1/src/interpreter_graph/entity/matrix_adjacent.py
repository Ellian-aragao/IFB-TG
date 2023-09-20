from __future__ import annotations

import logging
from typing import List

log = logging.getLogger(__name__)


class MatrixAdjacent:
    _total_vertexes: int
    _matrix_adjacent: List[List[int]]

    def __new__(cls, *args, **kwargs):
        return super().__new__(cls)

    def __init__(self, total_vertexes: int):
        if total_vertexes < 1:
            raise RuntimeError("Vertices should be more than 1")

        self._total_vertexes = total_vertexes
        self._matrix_adjacent = [[0] * total_vertexes] * total_vertexes

    def _validate_bounds_vertex(self, vertex: int) -> bool:
        return 0 <= vertex < self._total_vertexes

    def _validate_vertexes_to_edge(self, vertex1: int, vertex2: int) -> None:
        log.debug(f"validating vertexes, entry ({vertex1}, {vertex2})")
        is_valid_vertex1 = self._validate_bounds_vertex(vertex1)
        is_valid_vertex2 = self._validate_bounds_vertex(vertex2)
        if (not is_valid_vertex1) or (not is_valid_vertex2):
            message_error = f"Bounds of vertex shound be major than 0 and minour than {self._total_vertexes}, the entry value was ({vertex1},{vertex2})"
            raise RuntimeError(message_error)

    def add_edge(self, vertex1: int, vertex2: int) -> MatrixAdjacent:
        self._validate_vertexes_to_edge(vertex1, vertex2)

        self._matrix_adjacent[vertex1][vertex2] = 1
        self._matrix_adjacent[vertex2][vertex1] = 1
        return self

    def print_matrix(self) -> None:
        for lines in self._matrix_adjacent:
            print(lines)
