package io.github.glaz_egy;

public class MaxSelect implements BotTemplate {
	public ReversiCell nextPos(ReversiCell[][] data){
		int tmp = 0, max=0;
		ReversiCell re = null;
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){
				tmp = data[x][y].canReverse();
				if(tmp>max && data[x][y].canPut()){
					re = data[x][y];
					max = tmp;
				}
			}
		}
		return re;
	}

	public String description(){
		return "一番取れる石が多い場所を選択します。\n選択されるのは走査した順で一番早いやつになります。";
	}
}
