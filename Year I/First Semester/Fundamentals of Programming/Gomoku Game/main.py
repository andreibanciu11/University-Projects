from domain.board import Board
from services.game_service import GameService
from ui.ui import UI
from domain.validators import GameValidators

board = Board()

validators = GameValidators()
game_service = GameService(board, validators)

ui = UI(game_service)
ui.start()
