#!/usr/bin/env python
import random
from enum import Enum, auto
from collections import Counter
X = 'x'
O = 'o'
EMPTY = ' '

RANDOM = random.SystemRandom()

FORMAT_STR = "{}|{}|{}\n"
DIVIDER_STR =  "-+-+-\n"

BOARD_STR = FORMAT_STR + DIVIDER_STR + FORMAT_STR + DIVIDER_STR + FORMAT_STR

class GameState(Enum):
    PLAYING = auto()
    X_WON = auto()
    O_WON = auto()
    TIED = auto()

def new_board():
    return 9 * [EMPTY]

def print_board(board):
    print(BOARD_STR.format(*board))

def available_spaces(board):
    available = []

    for idx, value in enumerate(board):
        if value == EMPTY:
            available.append(idx)

    return available

def has_won(board, player):

    for i in range(0,3):
        # across
        if board[i * 3 + 0] == player and board[i * 3  + 1] == player and board[i * 3  + 2] == player:
            return True

        # down
        if board[i] == player and board[i + 3] == player and board[i + 6] == player:
            return True

    # diagonal 1 = 0,4,8
    if board[0] == player and board[4] == player and board[8] == player:
        return True

    # diagonal 2 = 2, 4, 6
    if board[2] == player and board[4] == player and board[6] == player:
        return True

    return False

def is_game_done(board):
    if has_won(board, X):
        return GameState.X_WON

    if has_won(board, O):
        return GameState.O_WON

    if available_spaces(board) == []:
        return GameState.TIED

    return GameState.PLAYING

def play_randomly(board, _):
    """
    Play any available location
    """
    available = available_spaces(board)
    return RANDOM.choice(available)

def prefer_center_on_first_move(board, player):
    return 4 if board[4] == EMPTY else play_randomly(board, player)

if __name__ == "__main__":
    results = Counter()
    num_runs = 1000000
    for _ in range(num_runs):
        game_state = GameState.PLAYING

        x_player = prefer_center_on_first_move
        o_player = prefer_center_on_first_move

        board = new_board()

        while game_state == GameState.PLAYING:
            #print_board(board)
            board[x_player(board, X)] = X

            game_state = is_game_done(board)

            if game_state == GameState.PLAYING:
                board[o_player(board, O)] = O
                game_state = is_game_done(board)

        results[game_state] += 1
        #print_board(board)
    
    print("X won = {0:2.2f}".format((results[GameState.X_WON] / num_runs) * 100))
    print("O won = {0:2.2f}".format((results[GameState.O_WON] / num_runs) * 100))
    print("Tied  = {0:2.2f}".format((results[GameState.TIED] / num_runs) * 100))