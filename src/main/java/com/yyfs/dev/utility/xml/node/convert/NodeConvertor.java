package com.yyfs.dev.utility.xml.node.convert;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.yyfs.dev.utility.xml.node.AttributeNode;
import com.yyfs.dev.utility.xml.node.DocumentNode;
import com.yyfs.dev.utility.xml.node.DocumentTypeNode;
import com.yyfs.dev.utility.xml.node.ElementNode;
import com.yyfs.dev.utility.xml.node.TextNode;

/**
 *
 * @author ri.meisei
 * @since 2014/01/21
 */
interface NodeConvertor {

	public DocumentNode convert(Document document);

	public DocumentTypeNode convert(DocumentType documentType);

	public ElementNode convert(Element element);

	public AttributeNode convert(Attr attr);

	public TextNode convert(Text text);
}
