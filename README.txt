----------------------
OBIETTIVO DEL PROGETTO
----------------------


L'obiettivo del progetto è quello di realizzare un programma
che implementi un filtro antispam.


-------------------
SPECIFICHE DI INPUT
-------------------


1)Il programma può essere composto da più di un file .java ma deve
OBBLIGATORIAMENTE contenere una sola funzione main contenuta in un file chiamato Main.java

la cui classe deve essere chiamata Main



2)Non utilizzare package all'interno dei file  .java del progetto



3)Il programma legge in input da riga di comando un nome di un file di testo (input.txt)
il cui contenuto è il seguente:

 
dictionary.txt
fileOk_ 1 50
fileSpam_ 1 50
fileToAnalyze_ 1 30


	i)  La prima riga indica il nome del file in cui sono presenti le parole in 

    carattere minuscolo che costituiscono il dizionario. Il dizionario contiene
    
60 parole, con una sola parola per ogni riga.


	ii) La seconda riga contiene in sequenza (usando uno spazio come carattere di separazione): 


       -una stringa che rappresenta il prefisso dei file Ok da usare nella fase di apprendimento
       
-un intero che rappresenta il suffisso di partenza usato per la numerazione dei file Ok
       
-un intero che rappresenta il suffisso di arrivo usato per la numerazione dei file Ok

   
La numerazione dei file si intende incrementale a passo uno. L'estensione di tali file 
   è .txt. 
Utilizzando i numeri presenti nella seconda riga si avranno quindi i seguenti file:
   
fileOk_1.txt   fileOk_2.txt   fileOk_3.txt   ...............  fileOk_50.txt
   
Ogni file Ok contiene 100 parole prese esclusivamente dal dizionario fornito, scritte in sequenza 
   
su più righe, ogni parola separata da uno spazio

iii) Analoghe considerazioni valgono per i contenuto della terza e quarta riga
     
relative ai fileSpam e ai fileToAnalyze, rispettivamente.
 




--------------------
SPECIFICHE DI OUTPUT
--------------------


Il programma produce in output (usando il comando System.out.println()) la sequenza dei risultati 
del calcolo delle distanza che intercorre tra ciascuno dei file da analizzare 
e
 ciascuno dei fileOk e fileSpam utilizzati nella fase di apprendimento.
 I valori delle distanze devono essere forniti separati da spazio.


All'inizio di ogni sequenza bisogna stampare:

1)la stringa "si" se il file analizzato è stato 
classificato SPAM


2)la stringa "no" se il  file analizzato è stato classificato OK


Ad esempio una possibile sequenza stampata relativa ad un file da analizzare potrebbe essere la seguente:


si 214 264 278 266 306 228 228 204 146 242 246 206 234 244 286 280 204 192 316 246 216 212 196 244 264 272 268 256 228 224 178 
226 190 268 226 246 242 280 210 270 176 324 310 202 224 216 264 256 242 272 146 152 186 148 132 156 172 130 174 128 146 108 226 164 152 138 112 200 174 104 148 130 88 126 188 152 124 
192 170 98 146 168 122 170 190 180 108 140 98 122 118 156 176 228 164 128 152 154 138 182 

in cui, 
partendo dall'inizio della sequenza:

214 è la distanza che intercorre tra il file in questione analizzato e il file fileOk_1.txt,

264 è la distanza che intercorre tra il file in questione analizzato e il file fileOk_2.txt,
278 è la distanza che intercorre tra il file in questione analizzato e il file fileOk_3.txt, e così via



















