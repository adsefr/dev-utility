package com.yyfs.dev.utility.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.yyfs.dev.utility.exception.XMLParseException;
import com.yyfs.dev.utility.xml.node.DocumentNode;
import com.yyfs.dev.utility.xml.node.convert.NodeSupport;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/22
 */
public abstract class W3CXMLParser {

	private boolean namespaceAware;

	private boolean validating;

	public W3CXMLParser() {

	}

	/**
	 * @param namespaceAware
	 *            セットする namespaceAware
	 */
	public void setNamespaceAware(boolean namespaceAware) {

		this.namespaceAware = namespaceAware;
	}

	/**
	 * @param validating
	 *            セットする validating
	 */
	public void setValidating(boolean validating) {

		this.validating = validating;
	}

	/**
	 * @return namespaceAware
	 */
	public boolean isNamespaceAware() {

		return namespaceAware;
	}

	/**
	 * @return validating
	 */
	public boolean isValidating() {

		return validating;
	}

	public DocumentNode parse(String uri) throws XMLParseException {

		InputSource inputSource = new InputSource(uri);

		return parse(inputSource);
	}

	public DocumentNode parse(InputStream is) throws XMLParseException {

		InputSource inputSource = new InputSource(is);

		return parse(inputSource);
	}

	public DocumentNode parse(InputSource inputSource) throws XMLParseException {

		Document document = null;

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(isNamespaceAware());
			documentBuilderFactory.setValidating(isValidating());

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			document = documentBuilder.parse(inputSource);
		} catch (ParserConfigurationException e) {
			throw new XMLParseException(e);
		} catch (SAXException e) {
			throw new XMLParseException(e);
		} catch (IOException e) {
			throw new XMLParseException(e);
		}

		DocumentNode documentNode = (DocumentNode) NodeSupport.convert(document);

		return documentNode;
	}
}
