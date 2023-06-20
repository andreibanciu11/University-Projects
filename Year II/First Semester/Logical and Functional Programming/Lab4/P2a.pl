my_append([],E,[E]).
my_append([H|T],E,[H|R]):-
    my_append(T,E,R).

inv_list([],[]).
inv_list([H|T],R):-
    inv_list(T,RI),
    my_append(RI,H,R).

product([],_,0,[]).
product([],_,C,[C]):-C=\=0.
product([H|T],E,C,[HR|R]):-
    HR is (H*E+C) mod 10,
    NC is (H*E+C-HR)/10,
    product(T,E,NC,R).

product_list(L,E,R):-
    inv_list(L,LI),
    product(LI,E,0,RP),
    inv_list(RP,R).
