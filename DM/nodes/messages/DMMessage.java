package projects.DM.nodes.messages;

import sinalgo.nodes.messages.Message;

public class DMMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	private int procID;
	private int rank;

	public DMMessage(int r, int n) {
		super();
		msgId = msgCounter;
		msgCounter++;
		procID = n;
		rank = r;
	}

	public int getID() {
		return procID;
	}
	
	public int getRank() {
		return rank;
	}

	public Message clone() {
		return new DMMessage(rank, procID);
	}

	public String toString() {
		return "LeLannMessage" + msgId;
	}
}