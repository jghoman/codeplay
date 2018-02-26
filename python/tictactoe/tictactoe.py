#!/usr/bin/env python

X = 'x'
O = 'o'
EMPTY = ' '

def print_board(board):
    format_str = "{}|{}|{}\n"
    divider_str =  "-+-+-\n"

    board_str = format_str + divider_str + format_str + divider_str + format_str
                    
    print(board_str.format(*board))

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
    if board[2] == player and board[4] == player and board[8] == player:
        return True

    return False

if __name__ == "__main__":
    board = [X, O, X, EMPTY, X, O, EMPTY, EMPTY, X]
    print_board(board)

    print("{}".format(available_spaces(board)))

    print("{} has won = {}".format(X, has_won(board, X)))
    print("{} has won = {}".format(O, has_won(board, O)))

    board = [X, X, X, O, O, O, EMPTY, EMPTY, EMPTY]
    print_board(board)

    print("{} has won = {}".format(X, has_won(board, X)))
    print("{} has won = {}".format(O, has_won(board, O)))
