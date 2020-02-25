/* TODO */

package projects.DM;

import projects.DM.nodes.nodeImplementations.DMNode;
import projects.DM.nodes.nodeImplementations.InitNode;
import sinalgo.nodes.Node;
import sinalgo.runtime.AbstractCustomGlobal;

/**
 * This class holds customized global state and methods for the framework. The
 * only mandatory method to overwrite is <code>hasTerminated</code> <br>
 * Optional methods to override are
 * <ul>
 * <li><code>customPaint</code></li>
 * <li><code>handleEmptyEventQueue</code></li>
 * <li><code>onExit</code></li>
 * <li><code>preRun</code></li>
 * <li><code>preRound</code></li>
 * <li><code>postRound</code></li>
 * <li><code>checkProjectRequirements</code></li>
 * </ul>
 * 
 * @see sinalgo.runtime.AbstractCustomGlobal for more details. <br>
 *      In addition, this class also provides the possibility to extend the
 *      framework with custom methods that can be called either through the menu
 *      or via a button that is added to the GUI.
 */
public class CustomGlobal extends AbstractCustomGlobal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see runtime.AbstractCustomGlobal#hasTerminated()
	 */
	public boolean hasTerminated() {
		return false;
	}

	/** Button to create a ring network. */
	@AbstractCustomGlobal.CustomButton(buttonText = "Build a Ring Network")
	public void ringButton() {
		buildRing(5);
	}

	private void addEdge(Node from, Node to) {
		from.addConnectionTo(to);
	}

	private void buildRing(int numOfNodes) {
		sinalgo.tools.Tools.removeAllNodes();

		// nodes
		DMNode[] theNodes = new DMNode[numOfNodes];

		// the center
		double centerPosX = sinalgo.configuration.Configuration.dimX / 2;
		double centerPosY = sinalgo.configuration.Configuration.dimY / 2;

		// the ring...
		double initAngle = 2 * Math.PI / numOfNodes;
		double range = 200.0;
		double angle = 0;
		for (int i = 0; i < numOfNodes; i++) {
			double posX = centerPosX + range * Math.cos(angle);
			double posY = centerPosY + range * Math.sin(angle);
			DMNode node;
			node = new InitNode();
			node.setPosition(posX, posY, 0);
			node.finishInitializationWithDefaultModels(true);
			if (i > 0)
				addEdge(theNodes[i - 1], node);
			theNodes[i] = node;
			angle += initAngle;
		}
		addEdge(theNodes[numOfNodes - 1], theNodes[0]);
		// Repaint the GUI as we have added some nodes
		sinalgo.tools.Tools.repaintGUI();
	}
}