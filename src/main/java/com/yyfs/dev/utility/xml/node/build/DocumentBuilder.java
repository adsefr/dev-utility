package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.node.DocumentNode;
import com.yyfs.dev.utility.xml.node.DocumentTypeNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/23
 */
public interface DocumentBuilder extends NodeBuilder<DocumentBuilder, DocumentNode> {

	public DocumentBuilder version(String version);

	public DocumentBuilder encoding(String encoding);

	public DocumentBuilder standalone(boolean standalone);

	public DocumentBuilder documentTypeNode(DocumentTypeNode documentTypeNode);

}
