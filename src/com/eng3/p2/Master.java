package com.eng3.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;



public class Master {
	
	public static void main(String[] args) throws IOException {
		
		String filePath = "p7";				
		ArrayList<ArrayList<String>> registers = new ArrayList<>();
		long t1, t2;
		int numWorkers = 1;
		
		ExecutorService tpes = Executors.newCachedThreadPool();
		
		Future futures[] = new Future[numWorkers];
		
		
		t1 = System.currentTimeMillis();
		
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
		System.out.println(registers.size());		
		t2 = System.currentTimeMillis();
		System.out.println("I/O Elapsed: " + (t2-t1));
//		t1 = System.currentTimeMillis();
		int factor = registers.size()/numWorkers;
        for (int i = 0; i < numWorkers; i++) {
            futures[i] = tpes.submit(new Worker(i*factor,(i+1)*factor, registers));
        }
        
//        for (int i = 0; i < numWorkers; i++) {
//        	series += futures[i].get();
//        }
//        t2 = System.currentTimeMillis();
//        
//        System.out.println("PROCESS Elapsed: " + (t2-t1));
        
        tpes.shutdown();
		
	}
}

