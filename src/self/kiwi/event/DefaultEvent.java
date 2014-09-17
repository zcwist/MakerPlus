package self.kiwi.event;

public class DefaultEvent extends AbstractEvent {

	public DefaultEvent() {
		// TODO Auto-generated constructor stub
	}
	public DefaultEvent(String eventName, String userId, String addExp){
		this.userID = userId;
		this.eventName = eventName;
		this.addExp = addExp;
	}

}
