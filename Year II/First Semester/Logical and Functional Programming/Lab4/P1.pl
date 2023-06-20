nr_occurences([],_,0).
nr_occurences([H|T],E,R):- H=:=E,
    nr_occurences(T,E,R1),
    R is R1+1.
nr_occurences([H|T],E,R):- H=\=E,
    nr_occurences(T,E,R).

check_set([]).
check_set([H|T]):-
    nr_occurences([H|T],H,R),
    R=:=1,
    check_set(T),!.
