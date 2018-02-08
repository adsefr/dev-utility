package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.node.ElementNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/23
 */
public interface NodeBuilder<C, N> {

	public C nodeName(String nodeName);

	public C level(int level);

	public C rootElementNode(ElementNode rootElementNode);

	public C parentElementNode(ElementNode parentElementNode);

	public C occurrenceTime(int occurrenceTime);

	public N build();

}
