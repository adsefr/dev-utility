package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.enums.NodeType;
import com.yyfs.dev.utility.xml.node.DocumentNode;
import com.yyfs.dev.utility.xml.node.DocumentTypeNode;
import com.yyfs.dev.utility.xml.node.ElementNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/23
 */
class DocumentBuilderImpl implements DocumentBuilder {

	private DocumentNodeImpl documentNodeImpl = null;

	DocumentBuilderImpl() {

		documentNodeImpl = new DocumentNodeImpl();
	}

	@Override
	public DocumentBuilder nodeName(String nodeName) {

		documentNodeImpl.setNodeName(nodeName);

		return this;
	}

	@Override
	public DocumentBuilder level(int level) {

		documentNodeImpl.setLevel(level);

		return this;
	}

	@Override
	public DocumentBuilder rootElementNode(ElementNode rootElementNode) {

		documentNodeImpl.setRootElementNode(rootElementNode);

		return this;

	}

	@Override
	public DocumentBuilder parentElementNode(ElementNode parentElementNode) {

		documentNodeImpl.setParentElementNode(parentElementNode);

		return this;

	}

	@Override
	public DocumentBuilder occurrenceTime(int occurrenceTime) {

		documentNodeImpl.setOccurrenceTime(occurrenceTime);

		return this;

	}

	@Override
	public DocumentNode build() {

		return documentNodeImpl;
	}

	@Override
	public DocumentBuilder version(String version) {

		documentNodeImpl.setVersion(version);

		return this;

	}

	@Override
	public DocumentBuilder encoding(String encoding) {

		documentNodeImpl.setEncoding(encoding);

		return this;

	}

	@Override
	public DocumentBuilder standalone(boolean standalone) {

		documentNodeImpl.setStandalone(standalone);

		return this;

	}

	@Override
	public DocumentBuilder documentTypeNode(DocumentTypeNode documentTypeNode) {

		documentNodeImpl.setDocumentTypeNode(documentTypeNode);

		return this;

	}

	public class DocumentNodeImpl extends NodeImpl implements DocumentNode {

		private String version = "1.0";

		private String encoding = "UTF-8";

		private boolean standalone = true;

		private DocumentTypeNode documentTypeNode = null;

		private ElementNode rootElementNode = null;

		public DocumentNodeImpl() {

			setNodeType(NodeType.DOCMENT);
		}

		/**
		 * @return version
		 */
		public String getVersion() {

			return version;
		}

		/**
		 * @return encoding
		 */
		public String getEncoding() {

			return encoding;
		}

		/**
		 * @return standalone
		 */
		public boolean isStandalone() {

			return standalone;
		}

		/**
		 * @return documentTypeNode
		 */
		public DocumentTypeNode getDocumentTypeNode() {

			return documentTypeNode;
		}

		/**
		 * @return rootElementNode
		 */
		public ElementNode getRootElementNode() {

			return rootElementNode;
		}

		/**
		 * @param version
		 *            セットする version
		 */
		void setVersion(String version) {

			this.version = version;
		}

		/**
		 * @param encoding
		 *            セットする encoding
		 */
		void setEncoding(String encoding) {

			this.encoding = encoding;
		}

		/**
		 * @param standalone
		 *            セットする standalone
		 */
		void setStandalone(boolean standalone) {

			this.standalone = standalone;
		}

		/**
		 * @param documentTypeNode
		 *            セットする documentTypeNode
		 */
		void setDocumentTypeNode(DocumentTypeNode documentTypeNode) {

			this.documentTypeNode = documentTypeNode;
		}

		/**
		 * @param rootElementNode
		 *            セットする rootElementNode
		 */
		void setRootElementNode(ElementNode rootElementNode) {

			this.rootElementNode = rootElementNode;
		}

	}
}
