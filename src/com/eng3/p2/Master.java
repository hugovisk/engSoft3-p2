package com.eng3.p2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Master {
	
	public static void main(String[] args) throws IOException {
		
		Locale.setDefault(Locale.US); // para formatar ponto flutuante com ponto		
		String filePath = "p7";				
		ArrayList<ArrayList<String>> registers = new ArrayList<>();
		ArrayList<ArrayList<ArrayList<String>>> registersProcessed = new ArrayList<>();
		ArrayList<ArrayList> avgCpuMem = new ArrayList<>();
		ArrayList<ArrayList> avgTime = new ArrayList<>();
		long t1, t2;
		int numWorkers = 9;
		
		ExecutorService tpes = Executors.newCachedThreadPool();
		
		Future<ArrayList<ArrayList<ArrayList<String>>>> futures[] = new Future[numWorkers];		
		
//		t1 = System.currentTimeMillis();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {			
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split("\\s+");
				ArrayList<String> values = new ArrayList<String>();
		        for (String part : splited) {
		        	values.add(part);		            
		        }
		        registers.add(values);
			}
			br.close();
		}
//		System.out.println(registers.size());		
//		t2 = System.currentTimeMillis();
//		System.out.println("I/O Elapsed: " + (t2-t1));
		t1 = System.currentTimeMillis();
		int factor = registers.size()/numWorkers;
        for (int i = 0; i < numWorkers; i++) {
            futures[i] = tpes.submit(new Worker(i*factor,(i+1)*factor, registers));
        }
        
        for (int i = 0; i < numWorkers; i++) {

        	try {
        		int j = 0;
				for ( ArrayList<ArrayList<String>> files : futures[i].get()) {
					if (i == 0) {
						registersProcessed.add(files);
						// passa as strings para numeros em uma nova array heterogenea
						if (j > 7 ) {
							for(ArrayList<String> data : registersProcessed.get(j)) {
								ArrayList value = new ArrayList<>();
								if (j == 8) {
									value.add(data.get(0));
									value.add(Double.parseDouble(data.get(1)));
									value.add(Double.parseDouble(data.get(2)));
									avgCpuMem.add(value);
								} else {
									value.add(data.get(0));
									value.add(Integer.parseInt(data.get(1)));
									avgTime.add(value);									
								}
							}
						}
					} else if (j < 8) { // passa os registros de cada usuario
						Stream.of(files).forEach(registersProcessed.get(j)::addAll);
					} else if (j == 8) { // acumula o valor das medias 						
						IntStream.range(0, files.size()).forEach(k -> {
							double value = (double) avgCpuMem.get(k).get(1) + Double.parseDouble(files.get(k).get(1));
							double value2 = (double) avgCpuMem.get(k).get(2) + Double.parseDouble(files.get(k).get(2));

							avgCpuMem.get(k).set(1, value);
							avgCpuMem.get(k).set(2, value2);
							
						});
					} else if (j == 9) {
						IntStream.range(0, files.size()).forEach(k -> {
							int value = (int) avgTime.get(k).get(1) + Integer.parseInt(files.get(k).get(1));
							avgTime.get(k).set(1, value);
						});
					}
					j++;
				}
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
        }
        t2 = System.currentTimeMillis();     
        System.out.println("PROCESS Elapsed: " + (t2-t1));
        // calula a media das medias recebidas dos workers
        if (numWorkers > 1) {
			for(int i = 0; i < 9; i++) {
				Averages avg = new Averages(
					(double) avgCpuMem.get(i).get(1), 
					(double) avgCpuMem.get(i).get(2),
					(int) avgTime.get(i).get(1),
					numWorkers
				);
				registersProcessed.get(8).get(i).set(1, String.format("%.2f", avg.getCpu()));
				registersProcessed.get(8).get(i).set(2, String.format("%.2f", avg.getMem()));
				registersProcessed.get(9).get(i).set(1, String.format("%.0f", avg.getTime()));
			}
		}
        
        tpes.shutdown();        
       
		IntStream.range(0, 10).forEach(i -> {
    		String fileName;
    		if (i < 8) {
    			fileName = registersProcessed.get(i).get(0).get(0);
    		} else {
    			fileName = (i == 8) ? "avgCpuAndMem" : "avgTime";
    		}
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
				for (ArrayList<String> line : registersProcessed.get(i)) {
					for (String data : line) {
			        	bw.write(data + " ");				           
					}
		        	 bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		});
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("userPid"));
			for (ArrayList<String> line : registers) {
		        bw.write(line.get(0) + " " + line.get(1));
	        	bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

