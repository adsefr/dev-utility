package com.yyfs.dev.utility.io.cache;

import java.io.InputStream;
import java.io.OutputStream;

public class ByteCache implements AutoCloseable {

	private ByteCacheInputstream byteCacheInputstream;

	private ByteCacheOutputstream byteCacheOutputstream;

	public ByteCache(InputStream is) {
		byteCacheInputstream = new ByteCacheInputstream(is);
		byteCacheOutputstream = new ByteCacheOutputstream();
	}

	public ByteCache(InputStream is, int capacity) {
		byteCacheInputstream = new ByteCacheInputstream(is, capacity);
		byteCacheOutputstream = new ByteCacheOutputstream(capacity);
	}

	public ByteCache(InputStream is, OutputStream os) {
		byteCacheInputstream = new ByteCacheInputstream(is);
		byteCacheOutputstream = new ByteCacheOutputstream(os);
	}

	public ByteCache(InputStream is, OutputStream os, int capacity) {
		byteCacheInputstream = new ByteCacheInputstream(is, capacity);
		byteCacheOutputstream = new ByteCacheOutputstream(os, capacity);
	}

	@Override
	public void close() throws Exception {

	}

}
