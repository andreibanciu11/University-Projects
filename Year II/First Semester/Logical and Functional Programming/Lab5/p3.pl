inserare([], E, [E]).
inserare([H|T], E, [E,H|T]).
inserare([H|T], E, [H|R]):-
    inserare(T, E, R).

permutari([], []).
permutari([H|T], R):-
    permutari(T, RP),
    inserare(RP, H, R).

combinari(_, 0, []).
combinari([H|T], K, [H|R]):-
    K>0,
    NK is K-1,
    combinari(T, NK, R).
combinari([_|T], K, R):-
    K>0,
    combinari(T, K, R).

aranjamente(L, K, R):-
    combinari(L, K ,RC),
    permutari(RC, R).

allsolutions(L, N, R):-
    findall(RPartial, aranjamente(L, N, RPartial), R).
