package org.opentripplanner.graph_builder.module;

/**
 *  Connectorfactory for different logic how to make street transit links.
 */
public class VertexConnectorFactory {
	
	public static VertexConnector getVertexConnector(String agency) {
		switch (agency) {
			case "HSL":
				return new HSLVertexConnector();
			default:
				return new DefaultVertexConnector();
		}
	}
	
}
