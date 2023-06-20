class BoardGameException(Exception):
    pass


class GameServiceException(BoardGameException):
    pass


class UiException(BoardGameException):
    pass


class GameValidators:
    """
    Class containing methods which validate winners or moves
    """

    @staticmethod
    def check_board_dimension_valid(dimension):
        """
        Checks if board dimension is greater or equal with 10
        :param dimension: board dimension
        :return:
        """
        if dimension < 10:
            return True
        else:
            return False

    @staticmethod
    def check_move_valid(board, line, column):
        """
        Checks if move is valid
        :param board: game board
        :param line: line from board
        :param column: column from board
        :return:
        """
        dimension = len(board[0])
        if line < 1 or line > dimension:
            return False
        elif column < 1 or column > dimension:
            return False
        elif board[line-1][column-1] == 1 or board[line-1][column-1] == 2:
            return False
        else:
            return True

    @staticmethod
    def check_moves_left(board):
        """
        Checks if there are left any moves
        :param board: game board
        :return:
        """
        dimension = len(board[0])
        for i in range(dimension):
            for j in range(dimension):
                if board[i][j] == 0:
                    return True
        return False

    @staticmethod
    def check_game_line(board, line, column, player, value):
        """
        Checks if game was won on line
        :param value:
        :param column: column from board
        :param line: line from board
        :param player: 1 or 2, representing value of human o computer
        :param board: game board
        :return:
        """
        dimension = len(board[0]) - 1
        i = line - 1
        j = column - 1
        number_of_stones = 1
        while j < dimension and board[i][j+1] == player and number_of_stones < value:
            j += 1
            number_of_stones += 1
        j = column - 1
        while j > 0 and board[i][j-1] == player and number_of_stones < value:
            j -= 1
            number_of_stones += 1
        if number_of_stones == value:
            return True
        else:
            return False

    @staticmethod
    def check_game_column(board, line, column, player, value):
        """
        Checks if game was won on line
        :param value:
        :param column: column from board
        :param line: line from board
        :param player: 1 or 2, representing value of human o computer
        :param board: game board
        :return:
        """
        dimension = len(board[0]) - 1
        i = line - 1
        j = column - 1
        number_of_stones = 1
        while i < dimension and board[i+1][j] == player and number_of_stones < value:
            i += 1
            number_of_stones += 1
        i = line - 1
        while i > 0 and board[i-1][j] == player and number_of_stones < value:
            i -= 1
            number_of_stones += 1
        if number_of_stones == value:
            return True
        else:
            return False

    @staticmethod
    def check_game_main_diagonal(board, line, column, player, value):
        """
        Checks if game was won on main diagonal
        :param value:
        :param board: game board
        :param line: line from board
        :param column: column from board
        :param player: 1 or 2, value of human move or computer move
        :return:
        """
        dimension = len(board[0]) - 1
        i = line - 1
        j = column - 1
        number_of_stones = 1
        while i < dimension and j < dimension and board[i+1][j+1] == player and number_of_stones < value:
            i += 1
            j += 1
            number_of_stones += 1
        i = line - 1
        j = column - 1
        while i > 0 and j > 0 and board[i-1][j-1] == player and number_of_stones < value:
            i -= 1
            j -= 1
            number_of_stones += 1
        if number_of_stones == value:
            return True
        else:
            return False

    @staticmethod
    def check_game_secondary_diagonal(board, line, column, player, value):
        """
        Checks if game was won on secondary diagonal
        :param value:
        :param board: game board
        :param line: line from board
        :param column: column from board
        :param player: 1 or 2, value of human move or computer move
        :return:
        """
        dimension = len(board[0]) - 1
        i = line - 1
        j = column - 1
        number_of_stones = 1
        while i < dimension and j > 0 and board[i+1][j-1] == player and number_of_stones < value:
            i += 1
            j -= 1
            number_of_stones += 1
        i = line - 1
        j = column - 1
        while i > 0 and j < dimension and board[i-1][j+1] == player and number_of_stones < value:
            i -= 1
            j += 1
            number_of_stones += 1
        if number_of_stones == value:
            return True
        else:
            return False

    def check_game(self, board, line, column, player, value):
        """
        Check if game was won
        :param value:
        :param board: game board
        :param line: line from board
        :param column: column from board
        :param player: 1 or 2, representing human move or computer move
        :return:
        """
        if self.check_game_line(board, line, column, player, value) is True:
            return True
        elif self.check_game_column(board, line, column, player, value) is True:
            return True
        elif self.check_game_main_diagonal(board, line, column, player, value) is True:
            return True
        elif self.check_game_secondary_diagonal(board, line, column, player, value) is True:
            return True
        else:
            return False
