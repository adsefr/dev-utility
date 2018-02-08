package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.node.DocumentTypeNode;

public interface DocumentTypeBuilder extends NodeBuilder<DocumentTypeBuilder, DocumentTypeNode> {

	public DocumentTypeBuilder name(String name);

	public DocumentTypeBuilder systemId(String systemId);

	public DocumentTypeBuilder publicId(String publicId);

	public DocumentTypeBuilder internalSubSet(String internalSubSet);

}
