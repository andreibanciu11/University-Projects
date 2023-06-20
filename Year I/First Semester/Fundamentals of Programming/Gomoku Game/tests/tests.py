from domain.board import Board
from domain.validators import GameValidators
import unittest


class TestBoard(unittest.TestCase):

    def test_get_board(self):
        test_board = Board()
        assert test_board._board == test_board.get_board()

    def test_create_board(self):
        test_board = Board()
        test_board.create_board(4)
        assert test_board._board == [[0, 0, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board._board = []
        test_board.create_board(5)
        assert test_board._board == [[0, 0, 0, 0, 0],
                                     [0, 0, 0, 0, 0],
                                     [0, 0, 0, 0, 0],
                                     [0, 0, 0, 0, 0],
                                     [0, 0, 0, 0, 0]]
        test_board._board = []
        test_board.create_board(3)
        assert test_board._board == [[0, 0, 0],
                                     [0, 0, 0],
                                     [0, 0, 0]]

    def test_set_move_human(self):
        test_board = Board()
        test_board.create_board(4)
        test_board.set_move_human(1, 1)
        assert test_board._board == [[1, 0, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board.set_move_human(1, 2)
        assert test_board._board == [[1, 1, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board.set_move_human(2, 4)
        assert test_board._board == [[1, 1, 0, 0],
                                     [0, 0, 0, 1],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board.set_move_human(4, 3)
        assert test_board._board == [[1, 1, 0, 0],
                                     [0, 0, 0, 1],
                                     [0, 0, 0, 0],
                                     [0, 0, 1, 0]]

    def test_set_move_computer(self):
        test_board = Board()
        test_board.create_board(4)
        test_board.set_move_computer(1, 1)
        assert test_board._board == [[0, 0, 0, 0],
                                     [0, 2, 0, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board.set_move_computer(1, 2)
        assert test_board._board == [[0, 0, 0, 0],
                                     [0, 2, 2, 0],
                                     [0, 0, 0, 0],
                                     [0, 0, 0, 0]]
        test_board.set_move_computer(2, 3)
        assert test_board._board == [[0, 0, 0, 0],
                                     [0, 2, 2, 0],
                                     [0, 0, 0, 2],
                                     [0, 0, 0, 0]]
        test_board.set_move_computer(3, 3)
        assert test_board._board == [[0, 0, 0, 0],
                                     [0, 2, 2, 0],
                                     [0, 0, 0, 2],
                                     [0, 0, 0, 2]]


class TestGameValidators(unittest.TestCase):

    def test_check_board_dimension_valid(self):
        test_validators = GameValidators()
        assert test_validators.check_board_dimension_valid(9) is True
        assert test_validators.check_board_dimension_valid(8) is True
        assert test_validators.check_board_dimension_valid(7) is True
        assert test_validators.check_board_dimension_valid(11) is False
        assert test_validators.check_board_dimension_valid(10) is False

    def test_check_move_valid(self):
        test_validators = GameValidators()
        test_board = [[0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_move_valid(test_board, 1, 1) is True
        assert test_validators.check_move_valid(test_board, 1, 1) is True
        assert test_validators.check_move_valid(test_board, 1, 5) is False
        assert test_validators.check_move_valid(test_board, 3, 3) is True
        assert test_validators.check_move_valid(test_board, 4, 3) is True

    def test_check_moves_left(self):
        test_validators = GameValidators()
        test_board = [[0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_moves_left(test_board) is True
        test_board = [[1, 1, 1, 1],
                      [2, 2, 2, 2],
                      [1, 1, 1, 1],
                      [0, 2, 2, 2]]
        assert test_validators.check_moves_left(test_board) is True
        test_board = [[1, 1, 1, 1],
                      [2, 2, 2, 2],
                      [1, 1, 1, 1],
                      [2, 2, 2, 2]]
        assert test_validators.check_moves_left(test_board) is False

    def test_check_game_line(self):
        test_validators = GameValidators()
        test_board = [[1, 0, 0, 0],
                      [0, 1, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_line(test_board, 1, 1, 1, 2) is False
        test_board = [[1, 0, 0, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_line(test_board, 1, 1, 1, 1) is True
        assert test_validators.check_game_line(test_board, 1, 2, 1, 3) is False
        test_board = [[1, 1, 1, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_line(test_board, 1, 2, 1, 3) is True

    def test_check_game_column(self):
        test_validators = GameValidators()
        test_board = [[1, 0, 0, 0],
                      [1, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_column(test_board, 1, 1, 1, 2) is True
        test_board = [[1, 0, 0, 0],
                      [1, 0, 0, 0],
                      [1, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_column(test_board, 1, 2, 1, 2) is False
        assert test_validators.check_game_column(test_board, 3, 1, 1, 3) is True
        test_board = [[1, 1, 1, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_column(test_board, 1, 2, 1, 3) is False

    def test_check_game_main_diagonal(self):
        test_validators = GameValidators()
        test_board = [[1, 0, 0, 0],
                      [1, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_main_diagonal(test_board, 1, 1, 1, 2) is False
        test_board = [[1, 0, 0, 0],
                      [1, 1, 0, 0],
                      [1, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_main_diagonal(test_board, 1, 1, 1, 2) is True
        assert test_validators.check_game_main_diagonal(test_board, 3, 3, 1, 3) is True
        test_board = [[1, 1, 1, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 1]]
        assert test_validators.check_game_main_diagonal(test_board, 2, 2, 1, 4) is True

    def test_check_game_secondary_diagonal(self):
        test_validators = GameValidators()
        test_board = [[1, 0, 0, 1],
                      [1, 0, 1, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game_secondary_diagonal(test_board, 2, 3, 1, 3) is False
        test_board = [[1, 0, 0, 1],
                      [1, 1, 0, 0],
                      [1, 1, 1, 0],
                      [1, 0, 0, 0]]
        assert test_validators.check_game_secondary_diagonal(test_board, 2, 2, 1, 2) is True
        assert test_validators.check_game_secondary_diagonal(test_board, 3, 2, 1, 3) is False
        test_board = [[1, 1, 1, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 1]]
        assert test_validators.check_game_secondary_diagonal(test_board, 1, 3, 1, 2) is True

    def test_check_game(self):
        test_validators = GameValidators()
        test_board = [[1, 0, 0, 1],
                      [1, 0, 1, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game(test_board, 1, 4, 1, 2) is True
        assert test_validators.check_game(test_board, 1, 1, 1, 2) is True
        test_board = [[1, 0, 0, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game(test_board, 3, 3, 1, 3) is True
        assert test_validators.check_game(test_board, 3, 3, 1, 4) is False
        test_board = [[1, 1, 1, 0],
                      [0, 1, 0, 0],
                      [0, 0, 1, 0],
                      [0, 0, 0, 0]]
        assert test_validators.check_game(test_board, 1, 2, 1, 4) is False
        assert test_validators.check_game(test_board, 3, 4, 1, 3) is False

