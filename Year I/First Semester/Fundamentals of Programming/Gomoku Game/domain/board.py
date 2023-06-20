class Board:
    """
    Class which contains the game board stored as a list of lists, dimension 15*15
    """
    def __init__(self):
        self._board = []

    def get_board(self):
        return self._board

    def create_board(self, dimension):
        """
        Creates board with given dimension and fills it with 0
        :return:
        """
        for i in range(dimension):
            line = []
            for j in range(dimension):
                line.append(0)
            self._board.append(line)

    def set_move_human(self, line, column):
        """
        Sets value from given line and column to 1 which represents human's move
        :param line: line from board
        :param column: column from board
        :return:
        """
        self._board[line-1][column-1] = 1

    def set_move_computer(self, line, column):
        """
        Sets value from given line and column to 2 which represents computer's move
        :param line: line from board
        :param column: column from board
        :return:
        """
        self._board[line][column] = 2
