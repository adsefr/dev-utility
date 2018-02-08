package com.yyfs.dev.utility.xml;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.yyfs.dev.utility.exception.XMLParseException;
import com.yyfs.dev.utility.xml.node.DocumentNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface XMLGenerator {

	public void generate(DocumentNode documentNode) throws XMLParseException;

	public void write(OutputStream os) throws IOException;

	public void write(File outputFile) throws IOException;
}
