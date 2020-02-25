package projects.DM.nodes.nodeImplementations;

import java.awt.Color;

import projects.DM.nodes.messages.DMMessage;

public class DMNode extends sinalgo.nodes.Node {

	boolean output = false;
	boolean active = true;
	int tag;
	int v1;
	int v2;

	/*
	 * WalkerNode() { // no constructor code, it breaks the way sinalgo builds the
	 * nodes. // instead use the init() method }
	 */
	public void init() {
		setColor(Color.YELLOW);
	}

	public String toString() {
		return ID + "";
	}

	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		while (inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if (msg instanceof DMMessage) {
				DMMessage rmsg = (DMMessage) msg;
				if (!active) {
					sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
					DMNode n = (DMNode) i.next().endNode;
					send(rmsg, n);
				} else if (rmsg.getRank() == 1) {
					forOne(rmsg.getID());
				} else {
					forTwo(rmsg.getID());
				}
			}
		}
	}

	private void forOne(int t) {
		if (t == tag) {
			output = true;
		} else {
			v1 = t;
			sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
			DMNode n = (DMNode) i.next().endNode;
			send(new DMMessage(2, t), n);
		}
	}

	private void forTwo(int t) {
		v2 = t;
		if ((v1 < v2) && (v1 < tag)) {
			tag = v1;
			sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
			DMNode n = (DMNode) i.next().endNode;
			send(new DMMessage(1, tag), n);
		} else {
			active = false;
		}
	}

	public void preStep() {
	};

	public void neighborhoodChange() {
	};

	public void postStep() {
	};

	public void checkRequirements() throws sinalgo.configuration.WrongConfigurationException {
	};

	public void draw(java.awt.Graphics g, sinalgo.gui.transformation.PositionTransformation pt, boolean highlight) {
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, toString(), 20, Color.black);
	}
}