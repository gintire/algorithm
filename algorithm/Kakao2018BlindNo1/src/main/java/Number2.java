import java.util.Comparator;
import java.util.PriorityQueue;

public class Number2 {
	public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        @SuppressWarnings("unused")
		Comparator<? super Integer> comp = pq.comparator();
        
        PriorityQueue<Game> failRatios = new PriorityQueue<Game>();
        
        for(int i=0; i<stages.length; i++) {
        	pq.offer(stages[i]);
        }
        
        int numberOfStage = 1;
        int stageLevel = 1;
        while(stageLevel <= N) {
        	if(pq.isEmpty()) {
        		failRatios.offer(new Game(stageLevel, 0));
        		stageLevel+=1;
        		continue;
        	}
        	int thisStage = pq.peek();
        	if(stageLevel == thisStage) {
        		thisStage = pq.poll();
        		if(!pq.isEmpty()) {
    	        	if(thisStage == pq.peek()) {
    	        		numberOfStage++;
    	        		continue;
    	        	}
            	}
        		
        		int restGamer = pq.size();
        		double failRatioOfthisStage = (double) numberOfStage / (restGamer + numberOfStage);
        		failRatios.offer(new Game(thisStage, failRatioOfthisStage));
        		numberOfStage = 1;
        		stageLevel+=1;
        	} else {
        		failRatios.offer(new Game(stageLevel, 0));
        		stageLevel+=1;
        	}
        }
        
        int idx = 0;
        while(!failRatios.isEmpty()) {
        	answer[idx++] = failRatios.poll().getStage(); 
        }
        return answer;
    }
}

class Game implements Comparable<Game>{
	private int stage;
	private double failRatio;
	
	
	public Game(int stage, double failRatio) {
		this.stage = stage;
		this.failRatio = failRatio;
	}
	public int getStage() {
		return stage;
	}
	public double getFailRatio() {
		return failRatio;
	}
	public int compareTo(Game obj) {
		if(obj.getFailRatio() > this.failRatio) return 1;
		else if(obj.getFailRatio() == this.failRatio) return (int) (this.stage - obj.getStage());
		else return -1;
	}
}