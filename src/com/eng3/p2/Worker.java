package com.eng3.p2;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Worker implements Callable<ArrayList<ArrayList<ArrayList<String>>>>{
	long t1, t2;
	private int start, stop;
	double avgTime, avgMemory, avgCpu;
	ArrayList<ArrayList<String>> toProcess = new ArrayList<>();
	
	ArrayList<ArrayList<ArrayList<String>>> all = new ArrayList<>();
	ArrayList<ArrayList<String>> apache = new ArrayList<>();
	ArrayList<ArrayList<String>> docker = new ArrayList<>();
	ArrayList<ArrayList<String>> java = new ArrayList<>();
	ArrayList<ArrayList<String>> mysql = new ArrayList<>();
	ArrayList<ArrayList<String>> postgres = new ArrayList<>();
	ArrayList<ArrayList<String>> root = new ArrayList<>();
	ArrayList<ArrayList<String>> stack = new ArrayList<>();
	ArrayList<ArrayList<String>> ubuntu = new ArrayList<>();
	
	public Worker(int start,int stop, ArrayList<ArrayList<String>> toProcess) {
		this.start = start;
		this.stop = stop;
    	this.toProcess = toProcess;
    }
	
	@Override
	public ArrayList<ArrayList<ArrayList<String>>> call() throws Exception {
		t1 = System.currentTimeMillis();
		for (int i=start;i<stop;i++) {        	
        	switch (toProcess.get(i).get(0)) {
        	case "apache":
        		apache.add(toProcess.get(i));
        		break;
        	case "docker":
        		docker.add(toProcess.get(i));
	            break;
        	case "java":
        		java.add(toProcess.get(i));
	            break;
        	case "mysql":
        		mysql.add(toProcess.get(i));
	            break;
        	case "postgres":
        		postgres.add(toProcess.get(i));
        		break;
        	case "root":
        		root.add(toProcess.get(i));
	            break;
        	case "stack":
        		stack.add(toProcess.get(i));
	            break;
        	case "ubuntu":
        		ubuntu.add(toProcess.get(i));
	            break;
        }
			
	}
//		System.out.println("apache array: " + apache.size());
//		System.out.println("docker array: " + docker.size());
//		System.out.println("java array: " + java.size());
//		System.out.println("mysql array: " + mysql.size());
//		System.out.println("postgres array: " + postgres.size());
//		System.out.println("root array: " + root.size());
//		System.out.println("stack array: " + stack.size());
//		System.out.println("ubuntu array: " + ubuntu.size());
//		int soma = apache.size() + docker.size() + java.size() + mysql.size() + postgres.size() + root.size() + stack.size() + ubuntu.size();
//		System.out.println(soma);
		all.add(apache);
		all.add(docker);
		all.add(java);
		all.add(mysql);
		all.add(postgres);
		all.add(root);
		all.add(stack);
		all.add(ubuntu);
		
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
        System.out.println("PROCESS Elapsed: " + (t2-t1));
		return null;
	}
	
}
