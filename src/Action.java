// Action.java

public enum Action {
	START,
	INSTRUCT,
	CREDIT;

	public void trigger(Action action) {
		if (action == Action.START) {
			// start
		}
		else if (action == Action.INSTRUCT) {
			// instruct
		}
		else if (action == Action.CREDIT) {
			// credit
		}
	}
}