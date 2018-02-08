package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.enums.NodeType;
import com.yyfs.dev.utility.xml.node.ElementNode;
import com.yyfs.dev.utility.xml.node.TextNode;

class TextBuilderImpl implements TextBuilder {

	private TextNodeImpl textNodeImpl = null;

	TextBuilderImpl() {

		textNodeImpl = new TextNodeImpl();
	}

	@Override
	public TextBuilder nodeName(String nodeName) {

		textNodeImpl.setNodeName(nodeName);

		return this;
	}

	@Override
	public TextBuilder level(int level) {

		textNodeImpl.setLevel(level);

		return this;
	}

	@Override
	public TextBuilder rootElementNode(ElementNode rootElementNode) {

		textNodeImpl.setRootElementNode(rootElementNode);

		return this;
	}

	@Override
	public TextBuilder parentElementNode(ElementNode parentElementNode) {

		textNodeImpl.setParentElementNode(parentElementNode);

		return this;
	}

	@Override
	public TextBuilder occurrenceTime(int occurrenceTime) {

		textNodeImpl.setOccurrenceTime(occurrenceTime);

		return this;
	}

	@Override
	public TextNode build() {

		return textNodeImpl;
	}

	@Override
	public TextBuilder content(String content) {

		textNodeImpl.setContent(content);

		return this;
	}

	class TextNodeImpl extends NodeImpl implements TextNode {

		private String content = null;

		TextNodeImpl() {

			setNodeType(NodeType.TEXT);
		}

		/**
		 * @return content
		 */
		public String getContent() {

			return content;
		}

		/**
		 * @param content
		 *            セットする content
		 */
		void setContent(String content) {

			this.content = content;
		}

	}

}
