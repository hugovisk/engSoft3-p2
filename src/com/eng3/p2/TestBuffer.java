package com.eng3.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;

public class TestBuffer {

	public static void main(String[] args) throws IOException {
		
		String filePath = "/home/hugo/Documentos/ADS/4Ciclo/Engsoft3/Prova/p2/p7";
//		String find = "java";		
		long t1, t2;
		ArrayList<ArrayList<ArrayList<String>>> all = new ArrayList<>();
		ArrayList<ArrayList<String>> apache = new ArrayList<>();
		ArrayList<ArrayList<String>> docker = new ArrayList<>();
		ArrayList<ArrayList<String>> java = new ArrayList<>();
		ArrayList<ArrayList<String>> mysql = new ArrayList<>();
		ArrayList<ArrayList<String>> postgres = new ArrayList<>();
		ArrayList<ArrayList<String>> root = new ArrayList<>();
		ArrayList<ArrayList<String>> stack = new ArrayList<>();
		ArrayList<ArrayList<String>> ubuntu = new ArrayList<>();
		
		t1 = System.currentTimeMillis();
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split("\\s+");
				ArrayList<String> values = new ArrayList<String>();
		        for (String part : splited) {
		        	values.add(part);
		            
		        }
//		        if (values.get(0).contentEquals(find)) {
//		        	System.out.println(values);
//		        }
		        
		        switch (values.get(0)) {
		        	case "apache":
		        		apache.add(values);
//		        		System.out.println(values);
		        		break;
		        	case "docker":
		        		docker.add(values);
//			        	System.out.println(values);
			            break;
		        	case "java":
		        		java.add(values);
//			        	System.out.println(values);
			            break;
		        	case "mysql":
		        		mysql.add(values);
//		        		System.out.println(values);
			            break;
		        	case "postgres":
		        		postgres.add(values);
//		        		System.out.println(values);
		        		break;
		        	case "root":
		        		root.add(values);
//			        	System.out.println(values);
			            break;
		        	case "stack":
		        		stack.add(values);
//			        	System.out.println(values);
			            break;
		        	case "ubuntu":
		        		ubuntu.add(values);
//		        		System.out.println(values);
			            break;
		          default:
		            // code block
		        }
		    }
			all.add(apache);
			all.add(docker);
			all.add(java);
			all.add(mysql);
			all.add(postgres);
			all.add(root);
			all.add(stack);
			all.add(ubuntu);
			
//			System.out.println("apache array: " + apache.size());
//			System.out.println("docker array: " + docker.size());
//			System.out.println("java array: " + java.size());
//			System.out.println("mysql array: " + mysql.size());
//			System.out.println("postgres array: " + postgres.size());
//			System.out.println("root array: " + root.size());
//			System.out.println("stack array: " + stack.size());
//			System.out.println("ubuntu array: " + ubuntu.size());
//			int soma = apache.size() + docker.size() + java.size() + mysql.size() + postgres.size() + root.size() + stack.size() + ubuntu.size();
//			System.out.println(soma);
			br.close();
		}
		
		
		
		for(ArrayList<ArrayList<String>> user : all) {
			double sumCpu = 0;
			double sumMem = 0;
			long sumSecs = 0;
			for(ArrayList<String> data : user) {
				double valueCpu = Double.parseDouble(data.get(2));
				sumCpu += valueCpu;
				
				double valueMem = Double.parseDouble(data.get(3));
				sumMem += valueMem;
				
				String[] timeSplited = data.get(4).split(":");
					int timeInSecs = Integer.parseInt(timeSplited[0]) * 60 + Integer.parseInt(timeSplited[1]);
					sumSecs += timeInSecs;
	//				System.out.println("time:" + timeInSecs);
		            
	//	        }
	//			int timeInSecs = ((Integer.parseInt(timeSplited[0])) * 60) + Integer.parseInt(timeSplited[1]);
	//			sumSecs += timeInSecs;
			
			}
//			System.out.println("USER:" + user.get(0).get(0));
			String avgCpu = String.format("%.2f", sumCpu/user.size());
//			System.out.println("avg %CPU: " + avgCpu);
			
			String avgMem = String.format("%.2f", sumMem/user.size());
//			System.out.println("avg %MEM: " + avgMem);
			
			long avgSecs =  sumSecs/user.size();
//			System.out.println("avg TIME: " + avgSecs+"s");
//			System.out.println(" ");
		}
		
		
			
			
		t2 = System.currentTimeMillis();
		System.out.println("Elapsed: " + (t2-t1));
	}

}
