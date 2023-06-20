from domain.validators import BoardGameException
from domain.validators import UiException


class UI:
    """
    Class which contains UI menu
    """

    def __init__(self, game_service):
        self._game_service = game_service

    @staticmethod
    def print_start_message():
        """
        Prints a message which marks the start of the gomoku game
        :return:
        """
        print("Welcome! Let's play gomoku!")
        print("Your move is marked with 1 and computer's move is marked with 2.")
        print("You must form a chain of five stones to win! Good luck!\n")

    def print_board(self):
        """
        Prints the game board
        :return:
        """
        board = self._game_service.get_board()
        dimension = len(board[0])
        for i in range(dimension):
            for j in range(dimension):
                print('-', end=" ")
            print("")
            for j in range(dimension):
                print(board[i][j], end=" ")
            print("")
        print("")

    @staticmethod
    def print_win_message(winner):
        """
        Prints a message when someone wins
        :return:
        """
        if winner == 1:
            print("Human wins!")
        else:
            print("Computer wins!")

    def start(self):
        player_turn = 1
        line = 0
        column = 0
        self.print_start_message()
        while True:
            try:
                try:
                    board_dimension = int(input("Enter board dimension:"))
                except ValueError:
                    raise UiException("Incorrect format of board dimension! Try again!\n")
                self._game_service.validate_board_dimension(board_dimension)
                break
            except BoardGameException as ex:
                print(str(ex))
        self._game_service.create_board_service(board_dimension)
        self.print_board()
        while True:
            try:
                if player_turn == 1:
                    print("It's your turn!")
                    try:
                        line = int(input("Insert the line of your move:"))
                        column = int(input("Insert the column of your move:"))
                    except ValueError:
                        raise UiException("Incorrect format of coordinate! Try again!\n")
                    self._game_service.validate_move(line, column)
                    self._game_service.set_move_human_service(line, column)
                    if self._game_service.check_game(line, column, player_turn, 5) is True:
                        self.print_board()
                        self.print_win_message(player_turn)
                        return
                    else:
                        if self._game_service.validate_moves_left() is False:
                            print("There are no more moves left! Game ends in a tie!")
                            return
                        player_turn = 2
                    self.print_board()
                else:
                    print("It's computer's turn!")
                    self._game_service.set_move_computer_service(line, column)
                    if self._game_service.check_game(line, column, player_turn, 5) is True:
                        self.print_board()
                        self.print_win_message(player_turn)
                        return
                    else:
                        if self._game_service.validate_moves_left() is False:
                            print("There are no more moves left! Game ends in a tie!")
                            return
                        player_turn = 1
                    self.print_board()
            except BoardGameException as ex:
                print(str(ex))
