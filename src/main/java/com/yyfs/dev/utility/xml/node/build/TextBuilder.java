package com.yyfs.dev.utility.xml.node.build;

import com.yyfs.dev.utility.xml.node.TextNode;

public interface TextBuilder extends NodeBuilder<TextBuilder, TextNode> {

	TextBuilder content(String content);
}
