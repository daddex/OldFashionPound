# OldFashionPound

### Realizzazione calcolatrice per operazioni sterlina in modalita non decimale, anteriore al 1971

### Esecuzione
###### importante non passare argomento (sia a linea di comando sia tramite IDE) senza apici ("\"), apice apertura non deve avere spazi lo stesso per prima della chiusura, ogni elemento deve essere invece separato da massimo uno spazio come indicato sotto <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p 17s 8d + 3p 4s 10d"   -> CORRETTO <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p 17s 8d - 1p 4s 10d"   -> CORRETTO <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p 17s 8d * 2"   -> CORRETTO <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p 17s 8d / 2"   -> CORRETTO <br />
java -jar oldfashionpound-calculator-1.0-1.jar " 5p 17s 8d + 3p 4s 10d"   -> NON CORRETTO (SPAZIO INIZIALE) <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p 17s 8d + 3p 4s 10d "   -> NON CORRETTO  (SPAZIO FINALE) <br />
java -jar oldfashionpound-calculator-1.0-1.jar "5p  17s 8d + 3p 4s 10d"   -> NON CORRETTO   (SPAZIO DI TROPPO DOPO IL PRIMO ELEMENTO 5P) <br />


 
