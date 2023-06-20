from domain.validators import GameServiceException
import random


class GameService:
    """
    Class with methods for computer moves and board update
    """
    def __init__(self, board, validator):
        self._board = board
        self._validator = validator

    def get_board(self):
        return self._board.get_board()

    def validate_board_dimension(self, dimension):
        """
        Validates board dimension
        :param dimension: board dimension
        :return:
        """
        if self._validator.check_board_dimension_valid(dimension) is True:
            raise GameServiceException("Board dimension must be greater or equal with 10! Try again!\n")

    def create_board_service(self, dimension):
        """
        Calls function from domain which creates board of dimension n
        :param dimension: dimension of board
        :return:
        """
        self._board.create_board(dimension)

    def validate_moves_left(self):
        """
        Validates left moves
        :return:
        """
        board = self.get_board()
        return self._validator.check_moves_left(board)

    def set_move_human_service(self, line, column):
        """
        Sets the value from line and column to 1, which represents human's move
        :param line: line from board
        :param column: column from board
        :return:
        """
        self._board.set_move_human(line, column)

    def generate_move_computer(self, dimension):
        """
        Generates random line and column coordinates for computer move
        :return:
        """
        board = self.get_board()
        line = random.randint(0, dimension - 1)
        column = random.randint(0, dimension - 1)
        while board[line][column] != 0:
            line = random.randint(0, dimension - 1)
            column = random.randint(0, dimension - 1)
        return line, column

    def find_first_free_position(self, board, line, column, value):
        """
        Returns closest free position for computer to move
        :param value: number of stones checked
        :param board: game board
        :param line: line from board
        :param column: column from board
        :return:
        """
        dimension = len(self.get_board()) - 1
        if self._validator.check_game_line(board, line, column, 1, value) is True:
            i = line - 1
            j = column - 1
            while j < dimension and board[i][j+1] == 1:
                j += 1
            if j < dimension and board[i][j+1] != 2:
                return i, j+1
            else:
                i = line - 1
                j = column - 1
                while j > 0 and board[i][j-1] == 1:
                    j -= 1
                if j > 0 and board[i][j-1] != 2:
                    return i, j-1
                else:
                    return self.generate_move_computer(dimension+1)
        elif self._validator.check_game_column(board, line, column, 1, value) is True:
            i = line - 1
            j = column - 1
            while i < dimension and board[i+1][j] == 1:
                i += 1
            if i < dimension and board[i+1][j] != 2:
                return i+1, j
            else:
                i = line - 1
                j = column - 1
                while i > 0 and board[i-1][j] == 1:
                    i -= 1
                if i > 0 and board[i-1][j] != 2:
                    return i-1, j
                else:
                    return self.generate_move_computer(dimension+1)
        elif self._validator.check_game_main_diagonal(board, line, column, 1, value) is True:
            i = line - 1
            j = column - 1
            while i < dimension and j < dimension and board[i+1][j+1] == 1:
                i += 1
                j += 1
            if i < dimension and j < dimension and board[i+1][j+1] != 2:
                return i+1, j+1
            else:
                i = line - 1
                j = column - 1
                while i > 0 and j > 0 and board[i-1][j-1] == 1:
                    i -= 1
                    j -= 1
                if i > 0 and j > 0 and board[i-1][j-1] != 2:
                    return i-1, j-1
                else:
                    return self.generate_move_computer(dimension+1)
        elif self._validator.check_game_secondary_diagonal(board, line, column, 1, value) is True:
            i = line - 1
            j = column - 1
            while i < dimension and j > 0 and board[i+1][j-1] == 1:
                i += 1
                j -= 1
            if i < dimension and j > 0 and board[i+1][j-1] != 2:
                return i+1, j-1
            else:
                i = line - 1
                j = column - 1
                while i > 0 and j < dimension and board[i-1][j+1] == 1:
                    i -= 1
                    j += 1
                if i > 0 and j < dimension and board[i-1][j+1] != 2:
                    return i-1, j+1
                else:
                    return self.generate_move_computer(dimension+1)

    def set_move_computer_service(self, line, column):
        """
        Sets the value from line and column to 2, which represents computer's move
        :return:
        """
        i = 0
        j = 0
        board = self.get_board()
        dimension = len(board[0])
        if self._validator.check_game(board, line, column, 1, 1) is True:
            if self._validator.check_game(board, line, column, 1, 2) is True:
                if self._validator.check_game(board, line, column, 1, 3) is True:
                    if self._validator.check_game(board, line, column, 1, 4) is True:
                        i, j = self.find_first_free_position(board, line, column, 4)
                    else:
                        i, j = self.find_first_free_position(board, line, column, 3)
                else:
                    i, j = self.find_first_free_position(board, line, column, 2)
            else:
                i, j = self.find_first_free_position(board, line, column, 1)
        self._board.set_move_computer(i, j)

    def validate_move(self, line, column):
        """
        Validates human's move
        :param line: line from board
        :param column: column from board
        :return:
        """
        board = self.get_board()
        if self._validator.check_move_valid(board, line, column) is False:
            raise GameServiceException("Your move is not valid! Try again!\n")

    def check_game(self, line, column, player, value):
        """
        Checks if game was won
        :param value:
        :param line: line from board
        :param column: column from board
        :param player: 1 or 2, representing human move or computer move
        :return:
        """
        board = self.get_board()
        return self._validator.check_game(board, line, column, player, value)
