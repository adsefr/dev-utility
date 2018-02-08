package com.yyfs.dev.utility.xml.node;

import com.yyfs.dev.utility.xml.enums.NodeType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface Node {

	public String getNodeName();

	NodeType getNodeType();

	ElementNode getRootElementNode();

	Node getParentElementNode();

	int getLevel();

	int getOccurrenceTime();

}
