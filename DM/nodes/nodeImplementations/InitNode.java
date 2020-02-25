package projects.DM.nodes.nodeImplementations;

import java.awt.Color;
import projects.DM.nodes.messages.DMMessage;
import projects.DM.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends DMNode {

	/* InitNode() { ... } */
	public void init() {
		super.init();
		setColor(Color.GREEN);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
	}

	public void initiate() {
		DMMessage first = new DMMessage(1, this.ID);
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
		DMNode n = (DMNode) i.next().endNode;
		System.out.println(this + " is sending its ID to " + n.toString());
		send(first, n);
	}

	public String toString() {
		return super.toString() + "(init)";
	}
}
