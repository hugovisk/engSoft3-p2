package com.eng3.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class Worker implements Callable<ArrayList<ArrayList<ArrayList<String>>>>{
//	long t1, t2;
	private int start, stop;	
	double sumCpu, sumCpuTotal, sumMem, sumMemTotal;
	long sumTime, sumTimeTotal;


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
	ArrayList<ArrayList<String>> avgCpusAndMems = new ArrayList<>();
	ArrayList<ArrayList<String>> avgTimes = new ArrayList<>();
	
	public Worker(int start,int stop, ArrayList<ArrayList<String>> toProcess) {
		this.start = start;
		this.stop = stop;
    	this.toProcess = toProcess;
    }
	
	@Override
	public ArrayList<ArrayList<ArrayList<String>>> call() throws Exception {
//		t1 = System.currentTimeMillis();
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
		// agrega as arrays dos usuarios na array final
		all.addAll(
			Arrays.asList(
				apache, docker, java, mysql, postgres, root, stack, ubuntu
		));

		// inicia a variaveis de soma
		sumCpuTotal = 0;
		sumMemTotal = 0;
		sumTimeTotal = 0;

		for(ArrayList<ArrayList<String>> user : all) {
			// inicia a variaveis de soma
			sumCpu = 0;
			sumMem = 0;
			sumTime = 0;		
			
			for(ArrayList<String> data : user) {
				double valueCpu = Double.parseDouble(data.get(2));
				sumCpu += valueCpu;
				
				double valueMem = Double.parseDouble(data.get(3));
				sumMem += valueMem;
				
				String[] timeSplited = data.get(4).split(":");
					int timeInSecs = Integer.parseInt(timeSplited[0]) * 60 + Integer.parseInt(timeSplited[1]);
					sumTime += timeInSecs;			
			}			
			
			Averages avg = new Averages(sumCpu, sumMem, sumTime, user.size(), user.get(0).get(0));

			
			// popula listas de media do uso de memoria e cpu
			avgCpusAndMems.add(avg.userCpuMem());
			// popula listas de media do tempo
			avgTimes.add(avg.userTime());				
			
			// calcula soma das medias totais de uso de memoria, cpu e tempo
			sumCpuTotal += avg.getCpu();
			sumMemTotal += avg.getMem();
			sumTimeTotal += avg.getTime();			
		}		
		
		Averages avgTotal = new Averages(sumCpuTotal, sumMemTotal, sumTimeTotal);
		
		avgCpusAndMems.add(avgTotal.userCpuMem());
		avgTimes.add(avgTotal.userTime());
		
		all.add(avgCpusAndMems);
		all.add(avgTimes);

//		t2 = System.currentTimeMillis();        
//        System.out.println("PROCESS Elapsed: " + (t2-t1));

        return all;
	}
	
}
