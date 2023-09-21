from interpreter_graph.config.logging_configs import log_config
from interpreter_graph.entrypoint.cli_interpreter import read_matrix_from_cli


def main():
    log_config()
    matriz_adjacencia = read_matrix_from_cli()
    matriz_adjacencia.print_matrix()


if __name__ == "__main__":
    main()
