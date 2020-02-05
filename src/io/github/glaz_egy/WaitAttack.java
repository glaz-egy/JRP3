package io.github.glaz_egy;

public class WaitAttack implements Runnable {

	//private OthelloMain par;
	private String attackAddr;
	private boolean isAttack;
	public boolean end;

	public WaitAttack(ReversiMain m){
		//par = m;
	}

	public void reset(){
		isAttack = false;
		end = false;
	}

	public boolean isAttack(){
		return isAttack;
	}

	public String attackAddr(){
		return attackAddr;
	}

	private void waiting(){
		while(!isAttack && !end){
			String[] re = Reversi.datagramReceive(Reversi.SERVER_PORT);
			System.out.println("Connect from"+re[1]);
			String inputLine = re[0];
			String[] cmd = inputLine.split(":", 0);
			if(cmd[0].equals("attack")){
				attackAddr = re[1];
				isAttack = true;
			}else if(cmd[0].equals("cmd") && cmd[1].equals("end")){
				end = true;
			}
			System.out.println(inputLine);
		}
	}
	@Override
	public void run() {
		waiting();
	}
}
