import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		String[] sample = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] result = solution(sample);
		for(int i=0; i<result.length; i++)
			System.out.println(result[i]);
	}
	public static String[] solution(String[] record) {
	    String[] answer = {};
	    String command ="";
	    String id = "";
	    String nickName = "";
	    Map<String,  User> users = new HashMap<String, User>();
	    Queue<Commands> commandList = new LinkedList<Commands>();
	    
	    for(int i=0; i<record.length; i++) {
	        String str = record[i];
	        String[] temp = str.split(" ");
	        command = temp[0]; id = temp[1]; 
	        if(!command.equals("Leave")) nickName = temp[2];
	        
	        User thisUser;
	        
	        if(command.equals("Enter")) {
	            if(users.containsKey(id)) {
	            	thisUser = users.get(id);
	            	thisUser.nickName = nickName;
	            } else {
	            	thisUser = new User(id, nickName);
	            	users.put(id, thisUser);
	            }
	            commandList.offer(new Commands(thisUser, "님이 들어왔습니다."));
	        } else if(command.equals("Leave")) {
	            thisUser = users.get(id);
	            commandList.offer(new Commands(thisUser, "님이 나갔습니다."));
	        } else if(command.equals("Change")) {
	        	if(users.containsKey(id)) {
	            	thisUser = users.get(id);
	            	thisUser.nickName = nickName;
	            }
	        }
	    }
	    answer = new String[commandList.size()];
	    int idx = 0;
	    while(!commandList.isEmpty()) {
	    	answer[idx++] = commandList.poll().toString();
	    }
	    return answer;
	}
}

class User {
	String id;
	String nickName;

	User (String id, String nickName) {
	    this.id = id;
	    this.nickName = nickName;
	}
}

class Commands {
	User user;
	String command;
	
	public Commands(User user, String command) {
		this.user = user;
		this.command = command;
	}

	@Override
	public String toString() {
		return user.nickName + command;
	}

}