package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.node.AttributeNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/23
 */
public interface AttributeBuilder extends NodeBuilder<AttributeBuilder, AttributeNode> {

	public AttributeBuilder namespaceURI(String namespaceURI);

	public AttributeBuilder prefix(String prefix);

	public AttributeBuilder localPart(String localPart);

	public AttributeBuilder qualifiedName(String qualifiedName);

	public AttributeBuilder name(String name);

	public AttributeBuilder value(String value);

}
