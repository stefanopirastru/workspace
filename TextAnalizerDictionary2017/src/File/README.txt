il costo dell'algoritmo Nearest Neighbour è:

definendo:
 D numero di parole nel dizionario
 P numero di parole nei files
 fT numero di files Ok + numero file Spam
 fTest numero dei files Test
 M costo modulo o(D) 

costi funzioni:

O(DlogD) lettura e ordinamento dizionario
O(PlogD) lettura files conosciuti fase training
O(D*fT^2) per la ricerca di Delta ottimale
O(fT*fTest*D*M)discriminazione fileTest

costo totale: 
assumendo D costante

O(fT^2)+O(fT*fTest)

domina O(fT^2) se faccio anche la fase di testing
se facessi solo il training il costo è O(PlogD)

Sintesi:

O(PlogD) costo parte training

O(fT^2) costo con testing