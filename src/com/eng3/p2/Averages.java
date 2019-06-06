package com.eng3.p2;

public class Averages {
		
	private double cpu;
	private double mem;
	private double time;
	
	// média para cada usuario
	public Averages(double sumCpu, double sumMem, long sumTime, int terms) {
		this.cpu = sumCpu/terms;
		this.mem = sumMem/terms;
		this.time = sumTime/terms;
	}
	
	// média dos 8 usuarios
	public Averages(double sumCpu, double sumMem, long sumTime) {
		this.cpu = sumCpu/8;
		this.mem = sumMem/8;
		this.time = sumTime/8;
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

	
}
