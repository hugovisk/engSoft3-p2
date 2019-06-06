package com.eng3.p2;

import java.util.ArrayList;
import java.util.Arrays;

public class Averages {
		
	private double cpu;
	private double mem;
	private double time;
	private String user;
	
	// média para cada usuario
	public Averages(double sumCpu, double sumMem, long sumTime, int terms, String user) {
		this.cpu = sumCpu/terms;
		this.mem = sumMem/terms;
		this.time = sumTime/terms;
		this.user = user;
	}
	
	// média geral
	public Averages(double sumCpu, double sumMem, long sumTime) {
		this.cpu = sumCpu/8;
		this.mem = sumMem/8;
		this.time = sumTime/8;
		this.user = "Total";
	}

	public double getCpu() {
		return cpu;
	}

	public double getMem() {
		return mem;
	}

	public double getTime() {
		return time;
	}
	
	public ArrayList<String> userCpuMem() {
		return new ArrayList<String>(Arrays.asList(
				user, 
				String.format("%.2f", cpu), 
				String.format("%.2f", mem)
			));
	}
	
	public ArrayList<String> userTime() {
		return new ArrayList<String>(Arrays.asList(
				user, 
				String.format("%.0f", time)
			)); 
	}
	
}
