package application;

import java.util.Scanner;


public class BfVirtualMachine {
	protected final int DEFAULT_MEMORY_SIZE = 0x1000;
	
	protected Scanner scanner = new Scanner(System.in);
	
	protected int memAdr = 0;
	protected byte[] memory  = null;
	protected char[] program = null; 
	
	
	public BfVirtualMachine() {
		this.memory = new byte[DEFAULT_MEMORY_SIZE];
		this.program = new char[1];
	}
	
	public BfVirtualMachine(int memorySize) {
		this.memory = new byte[memorySize];
		this.program = new char[1];
	}
	
	protected int eval(final int PROGRAM_COUNTER) {
		int pc = PROGRAM_COUNTER; 
		
		while (program[pc] != ']') {
			switch (program[pc]) {
			case '[':
				pc = eval(pc + 1);
				break;
			case '+':
				memory[memAdr]++;
				break;
			case '-':
				memory[memAdr]--;
				break;
			case '>':
				memAdr++;
				break;
			case '<':
				memAdr--;
				break;
			case '.':
				System.out.print(String.format("%c", memory[memAdr]));
				break;
			case ',':
				memory[memAdr] = scanner.nextByte();
				break;
			default:
				break;
			}
			
			pc++;
			
			if (pc >= program.length) 
				return 0; //sortie du programme principal
		}
		
		if (memory[memAdr] != 0)
			return eval(PROGRAM_COUNTER);
		
		return pc;
	}
	
	public void setProgram(String programString) {
		this.program = programString.toCharArray();
	}
	
	public void run() {
		eval(0);
	}
	
	public static void main(String[] args) {
		final String HELLO_WORLD = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
		
		BfVirtualMachine bfVirtualMachine = new BfVirtualMachine(32);
		bfVirtualMachine.setProgram(HELLO_WORLD);
		bfVirtualMachine.run();
	}

}
