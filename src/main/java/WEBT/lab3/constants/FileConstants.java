package WEBT.lab3.constants;

public enum FileConstants {
	Home("home"),
	DrawBoard("drawer"),
	WebSocketJS("webSocket.js"),
	LongPollJS("longPoll.js"),
	FrequentPollJS("frequentPoll.js");
	private final String value;
	private FileConstants(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return value;
	}
}
// Made by EugeneVV