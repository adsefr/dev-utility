package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.enums.NodeType;
import com.yyfs.dev.utility.xml.node.CommentNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/22
 */
public class CommentNodeImpl extends NodeImpl implements CommentNode {

	private String content = null;

	public CommentNodeImpl() {

		setNodeType(NodeType.COMMENT);
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
