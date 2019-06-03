package com.eng3.p2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Master {
	
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		String filePath = "/home/hugo/Documentos/ADS/4Ciclo/Engsoft3/Prova/p2/p7";
		long t1, t2;
		String find = "mysql";
		
		t1 = System.currentTimeMillis();
		try {
			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				List<String> values = new ArrayList<String>();
		        try (Scanner lineSc = new Scanner(line)) {
		            while (lineSc.hasNext()) {//Pattern.compile(find)
//		            	System.out.println(line);
		                values.add(lineSc.next());
//		            	lineSc.next();
		            }
		        }
//		        System.out.println(values);
//				 System.out.println(line);
			}
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
			
			t2 = System.currentTimeMillis();
			System.out.println("Elapsed: " + (t2-t1));
		}	
	}	
}

