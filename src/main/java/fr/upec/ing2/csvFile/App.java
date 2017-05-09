package fr.upec.ing2.csvFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws IOException
	{

		String csvFile = "D:/Documents/Miage/ING2/Big Data/coauthors2016_Clean.csv";
		String output = "D:/Documents/Miage/ING2/Big Data/coupleOfAuthors2016_3.csv";
		CSVWriter writer = null;
		CSVReader reader = null;
		int c = 0;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			writer = new CSVWriter(new FileWriter(output));

			List<String[]> content = reader.readAll();
			
			String firstLine = "Source#Target#Type";
			writer.writeNext(firstLine.split("#"));
			for (String[] row : content) { //itère sur les lignes
				if(c >= 2000) break;
				c++;
				for(int i =0;i<row.length;i++) {
				//	System.out.println("valeur de i : " + i );

					for(int j=i+1;j<=row.length-1;j++) { //itère sur les colonnes de la ligne
					//	System.out.println("valeur de j : " + j );
						System.out.println(row[i]
								+ " # " + row[j]
								);
						String s = row[i]+"#"+row[j]+"#Undirected";
						writer.writeNext(s.split("#"));
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.close();
		reader.close();

	}
}
