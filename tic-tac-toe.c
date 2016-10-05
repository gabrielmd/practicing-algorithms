/* Recursive tic-tac-toe */

#include <stdio.h>
#include <stdlib.h>

#define USER_POSITION 0
#define PC_POSITION 1
#define UNUSED_POSITION -1
#define GAME_OVER -2

// support methods:

// print table
void print_table(int * table){
	int i, j;
	for(i = 0; i < 3; i++){
		for(j = 0; j < 3; j++){
			if(j > 0){
				printf("|");
			}

			switch(table[(i*3) + j]){
				case -1: printf("   "); break;
				case 0: printf(" O ");break;
				case 1: printf(" X ");
			}

		}

		if(i < 2){
			printf("\n-----------");
		}
		printf("\n");
	}
}

// check if the game is over
int check_winner(int * table){
	int i, j;

	// case 1: 3 in a row
	for(i = 0; i < 3; i++){
		if(table[i*3] != UNUSED_POSITION && table[i*3] == table[(i*3) + 1] && table[i*3] == table[(i*3) + 2]){
			return table[i*3];
		}
	}

	// case 2: 3 in column
	for(i = 0; i < 3; i++){
		if(table[i] != UNUSED_POSITION && table[i] == table[3 + i] && table[i] == table[6 + i]){
			return table[i];
		}
	}

	// case 3: diagonal 0,4,8
	if(table[0] != UNUSED_POSITION && table[0] == table[4] && table[0] == table[8]){
		return table[0];
	}

	// case 4: reverse diagonal
	if(table[2] != UNUSED_POSITION && table[2] == table[4] && table[2] == table[6]){
		return table[2];
	}

	for(i = 0; i < 9; i++){
		if(table[i] == UNUSED_POSITION){
			return UNUSED_POSITION;
		}
	}

	return GAME_OVER;
}

int rec_next(int * table, int next_movement, int * result, int * deep){
	// base case: is over
	(*result) = check_winner(table);
	if((*result) != UNUSED_POSITION){
		return -1;
	}

	int i;
	int last_deep;
	int best_deep = 20;
	int best_result = GAME_OVER;
	int best_play = -1;
	int next_next_movement;

	// try to play in every unused position
	for(i = 0; i < 9; i++){
		if(table[i] == UNUSED_POSITION){
			table[i] = next_movement;

			last_deep = (*deep) + 1;

			if(next_movement == USER_POSITION){
				next_next_movement = PC_POSITION;
			}else{
				next_next_movement = USER_POSITION;
			}
			rec_next(table, next_next_movement, result, &last_deep);

			// if we did not find anything better than a draw OR
			// if we found some better way to win...
			if((best_result == GAME_OVER && (*result) != USER_POSITION) ||
				(best_result == PC_POSITION && (*result) == PC_POSITION)){

				if(last_deep <= best_deep){

					best_deep = last_deep;
					best_result = (*result);
					best_play = i;
				}
			}

			// remove play from table
			table[i] = UNUSED_POSITION;
		}
	}

	(*deep) = best_deep;

	return best_play;
}

// method to play automatically
int get_next(int * table){
	int result, deep = 0;
	return rec_next(table, PC_POSITION, &result, &deep);
}


// main: start match
int main(int argv, char ** args){

	int move; // user's move
	int next = 0;
	int i;
	int result;

	// initialize table
	int * table = malloc(sizeof(int)*9);
	for(i = 0; i < 9; i++){
		table[i] = -1; // not played yet
	}


	// while is not over:
	while((result = check_winner(table)) == UNUSED_POSITION){
		// 0 = get user input
		if(next == 0){
			scanf("%d", &move);
			while(table[move] != -1){
				printf("The position %d is taken!\n", move);
				scanf("%d", &move);
			}
			table[move] = USER_POSITION;
		}else{
			// play automatically
			move = get_next(table);
			table[move] = PC_POSITION;

			print_table(table);
		}


		// next play...
		next = (next+1) % 2;
	}

	switch(result){
		case GAME_OVER: printf("GAME OVER!\n"); break;
		case USER_POSITION: printf("You win!\n"); break;
		case PC_POSITION: printf("You lose!\n");
	}

	return 1;
}












// recursive method to obtain the best play with the current situation



