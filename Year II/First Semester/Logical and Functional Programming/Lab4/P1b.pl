remove_k_occ([],_,_,[]).
remove_k_occ(L,_,0,L).
remove_k_occ([H|T],E,K,R):-H=:=E,
    K1 is K-1,
    remove_k_occ(T,E,K1,R).
remove_k_occ([H|T],E,K,[H|R]):-H=\=E,
    remove_k_occ(T,E,K,R).

remove_3_occ(L,E,R):-remove_k_occ(L,E,3,R).
