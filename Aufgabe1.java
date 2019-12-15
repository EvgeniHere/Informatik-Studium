public class Aufgabe1 {
    public static void main(String[] args) {
        char buchstabe = StdIn.readChar(); //Buchstabe wird eingelesen
        
        if(buchstabe != 'd' && buchstabe != 'r' && buchstabe != 'l') { //Falls Buchstabe weder d noch r oder l ist dann wiederhole Eingabe
            System.out.println("Ungültige Eingabe");
            System.exit(0);
        }
        
        char kette[]; //Zuweisung erfolgt später
        
        int zahl = StdIn.readInt(); //Zahl wird eingelesen
        switch (buchstabe) {
            case 'r':
                //Falls Buchstabe = r, dann prüfe ob eingegebene Zahl passend gewählt wurde, wenn nicht dann wiederhole Eingabe
                if (zahl < 0 || zahl > 100000) {
                    System.out.println("Ungültige Eingabe");
                    System.exit(0);
                }   
                System.out.println(getRandomKette(zahl)); //Gebe die zufällig generierte Zeichenkette aus
                break;
            case 'd':
                //Falls Buchstabe = d, dann prüfe ob eingegebene Zahl passend gewählt wurde, wenn nicht dann wiederhole Eingabe
                if (zahl < 0 || zahl > 20) {
                    System.out.println("Ungültige Eingabe");
                    System.exit(0);
                }   
                kette = new char[(int) Math.pow(2, (zahl+1))-1]; //Erstelle Array des Typs char mit Länge 2^(k+1) - 1
                String drachenKurveText = getDrachenKurve(zahl); //Speichere die Buchstaben aus getDrachenKurve(zahl) in einem String 
                writeToArray(kette, drachenKurveText); //Schreibe den String in das erstellte Array
                System.out.println(drachenKurveText.length()); //Gebe die Zahl aus
                System.out.println(drachenKurveText); //Gebe den von getDrachenKurve(zahl) generierten Text aus
                break;
            default:
                //Falls Buchstabe = l, dann prüfe ob eingegebene Zahl passend gewählt wurde, wenn nicht dann wiederhole Eingabe
                if (zahl < 0 || zahl > 20) {
                    System.out.println("Ungültige Eingabe");
                    System.exit(0);
                }   
                kette = new char[]{'F'}; //Erstelle Array des Typs char mit dem Buchstaben F
                char newKette[] = getLevyCKurve(kette, zahl);
                int anzahl = getAnzahlZeichen(newKette); //Speichere die Anzahl der Buchstaben und gebe diese aus, ersetze dabei die Zeichen wie in der Aufgabe beschrieben
                System.out.println(anzahl); //Gebe Anzahl aus
                System.out.println(charArrayToString(newKette)); //Gebe die neue Zeichenkette aus
                break;
        }
    }
    
    public static String getRandomKette(int zahl) {
        String kette = "";
        System.out.println(zahl);
        for (int i = 0; i < zahl; i++) { //Es werden k mal ein random zeichen von F,R,L an String kette eingehängt
            int rand = (int) StdRandom.uniform(3);
            switch(rand) {
                case 0:
                    kette += "F";
                    break;
                case 1:
                    kette += "R";
                    break;
                case 2:
                    kette += "L";
                    break;
                default:
                    break;
            }
        }
        return kette;
    }
    
    public static String getDrachenKurve(int zahl) {
        String currentKette = "F"; //Starte mit Buchstaben F
        for (int i = 0; i < zahl; i++) { //Wiederhole Ordnungszahl-mal
            String nextKette = ""; //String für den hinzukommenden Text wird angelegt
            for (int j = 0; j < currentKette.length(); j++) { //Gehe den bestehenden Text durch
                switch(currentKette.charAt(j)) { //Ändere L zu R und R zu L, wobei F gleich bleibt und füge den Buchstaben zum Anfang des hinzukommenden Textes
                    case 'L':
                        nextKette = 'R' + nextKette;
                        break;
                    case 'R':
                        nextKette = 'L' + nextKette;
                        break;
                    default:
                        nextKette = 'F' + nextKette;
                        break;
                }
            }
            currentKette += 'R'; //Füge zuerst R hinzu
            currentKette += nextKette; //Dann den hinzukommenden String
        }
        return currentKette; //return des Ergebnisses
    }
    
    public static char[] getLevyCKurve(char[] kette, int num) {
        if (num == 0) { //Abbruchbedingung
            return kette;
        }
        char[] newKette = new char[kette.length * 2 + 4]; //Neues Array wird angelegt mit der Größe, die aus der vorherigen Größe festgelegt wird
        
        int currentIndex = 0; //Variable die den momentanen Index bestimmt, zum Aufbau der Struktur mit der plus, minus und der vorherigen Array verteilung
        newKette[currentIndex] = '+'; //Setze ein + am Anfang
        currentIndex++;
        
        for (int i = 0; i < kette.length; i++) { //Kopiere die Werte aus dem vorherigen Array an die Stelle ab currentIndex + 1
            newKette[currentIndex] = kette[i];
            currentIndex++;
        }
        
        newKette[currentIndex] = '-'; //Setze die nächsten zwei Werte auf -
        currentIndex++;
        
        newKette[currentIndex] = '-';
        currentIndex++;
        
        for (int i = 0; i < kette.length; i++) { //Kopiere die Werte aus dem vorherigen Array an die Stelle ab currentIndex + 1
            newKette[currentIndex] = kette[i];
            currentIndex++;
        }
        
        newKette[currentIndex] = '+'; //Setze ein + am Ende
        
        return getLevyCKurve(newKette, num-1); //Starte die Methode mit dem neuen Array als Parameter und der Zählvariable - 1
    }
    
    public static int getAnzahlZeichen(char[] kette) {
        int curIndex = 0; //momentaner Index des neu zu setzenden Wertes im Array
        int startIndex = 0; //Index unter dem sich nur die anfangs-Plusse befinden
        int endIndex = kette.length-1; //Index ab dem sich nur die letzten Plusse befinden
        while (kette[startIndex] == '+') { //StartIndex wird zugewiesen
            startIndex++;
        }
        while(kette[endIndex] == '+') { //EndIndex wird zugewiesen
            endIndex--;
        }
        for (int i = startIndex; i < endIndex; i++) {
            int plus = 0; //Zwei variablen, die für die anzahl an plussen und minussen stehen
            int minus = 0;
            while (kette[i] != 'F' && i < endIndex) { //Solange an Stelle i kein F ist && i im gültigen Bereich liegt
                if (kette[i] == '+') { //addiere plus um 1
                    plus++;
                } else { //addiere minus um 1
                    minus++;
                }
                i++;
            }
            if (plus > minus) { //Falls plus größer minus ist, setze den nächsten Wert auf R
                kette[curIndex] = 'R';
                curIndex++;
            } else if (minus > plus) { //Falls minus größer ist, setze den nächsten Wert auf L
                kette[curIndex] = 'L';
                curIndex++;
            }
            kette[curIndex] = 'F'; //Füge ein F hinzu, da die while schleife entweder beendet wurde durch ein f an stelle i, oder stelle endIndex erreicht wurde
            curIndex++;
        }
        for (int i = curIndex; i < kette.length; i++) { //Fülle den Rest des Arrays mit Rs oder Ls, da diese ohne F nicht mehr genutzt werden
            kette[i] = 'R';
        }
        return curIndex;
    }
    
    public static void writeToArray(char[] kette, String zeichen) {
        for (int i = 0; i < kette.length; i++) {
            kette[i] = zeichen.charAt(i);
        }
    }
    
    public static String charArrayToString(char[] array) {
        String text = "";
        for (int i = 0; i < array.length; i++) {
            text += array[i];
        }
        return text;
    }
}
