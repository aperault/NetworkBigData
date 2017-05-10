package fr.upec.ing2.csvFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class App {
    public static void main(String[] args) throws IOException {
        for (int a = 2; a < 7; a++) { // On boucle sur les fichiers de chaque année 2012 =2; 2013 =3 ...
            System.out.println("*********************** 201"+a);
            String csvFile = "D:/Project/NetworkBigData/csv/diabetes201"+a+".csv";
            String output = "D:/Project/NetworkBigData/result201"+a+".csv";
            String output2 = "D:/Project/NetworkBigData/result-file-201"+a+".csv";

            CSVWriter writer = new CSVWriter(new FileWriter(output), ';');
            CSVWriter writer2 = null;
            CSVReader reader = null;
            CSVReader reader2 = null;

            try {
                if (a == 6) {
                    //Le fichier de 2016 est séparé par des points-virgules
                    reader = new CSVReader(new FileReader(csvFile), ';');
                } else {
                    //Les fichiers de 2012 à 2015 sont séparés par des virgules
                    reader = new CSVReader(new FileReader(csvFile), ',');
                }

                List<String[]> content = reader.readAll();


                for (String[] row : content) { //itère sur les lignes
                    if (!row.equals("")) { // On ne lis pas la ligne vide de la fin
                        String authors = row[2];
                        String[] author = authors.split(",");

                        //On ne prends que les lignes contenant Nom espace Initial du prénom
                        if (author[0].matches("[a-zA-z]+[ ]{1}+[a-zA-Z]{1,}")) {
                            System.out.println(authors);
                            writer.writeNext(authors.split(","));
                        }
                    }
                }
                writer.close();
                reader.close();


                writer2 = new CSVWriter(new FileWriter(output2), ';');
                reader2 = new CSVReader(new FileReader(output), ';');

                List<String[]> content2 = reader2.readAll();


                String firstLine = "Source#Target#Type";
                writer2.writeNext(firstLine.split("#"));
                int c = 0;
                for (String[] row : content2) { //itère sur les lignes
                    if (c >= 2000) break;
                    c++;
                    for (int i = 0; i < row.length; i++) {
                        for (int j = i + 1; j <= row.length - 1; j++) { //itère sur les colonnes de la ligne
                            System.out.println(row[i] + " # " + row[j]);
                            String s = row[i] + "#" + row[j] + "#Undirected";
                            writer2.writeNext(s.split("#"));
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            writer2.close();
            reader2.close();
        }
    }
}

