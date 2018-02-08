package com.yyfs.dev.utility.io.cache;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author rimeisei
 * @since 2016/11/15
 */
public class ByteCacheInputstream extends InputStream {

	private final static int DEFAULT_CAPCITY = 1024;

	private int capacity;

	private ByteBuffer byteBuffer;

	private InputStream inputStream;

	/**
	 *
	 * @param inputStream
	 */
	public ByteCacheInputstream(InputStream inputStream) {
		this(inputStream, DEFAULT_CAPCITY);
	}

	/**
	 *
	 * @param inputStream
	 * @param capacity
	 */
	public ByteCacheInputstream(InputStream inputStream, int capacity) {
		this.capacity = capacity;
		this.byteBuffer = ByteBuffer.allocate(capacity);
		this.inputStream = inputStream;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException {

		int i = inputStream.read();

		if (i != -1) {
			extendCapacity(1);
			byteBuffer.put((byte) i);
		}

		return i;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b) throws IOException {

		return this.read(b, 0, b.length);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b, int off, int len) throws IOException {

		int length = inputStream.read(b, off, len);

		extendCapacity(length);

		if (length > 0) {
			byteBuffer.put(b, off, length);
		}

		return length;
	}

	private void extendCapacity(int length) {

		if (byteBuffer.remaining() > length) {
			return;
		}

		if (Integer.MAX_VALUE - capacity > length) {
			int newCapacity = 0;
			if ((Integer.MAX_VALUE - capacity - length) > DEFAULT_CAPCITY) {
				newCapacity = capacity + length + DEFAULT_CAPCITY;
			} else {
				newCapacity = Integer.MAX_VALUE;
			}
			ByteBuffer newByteBuffer = ByteBuffer.allocate(newCapacity);
			newByteBuffer.put(byteBuffer);
			byteBuffer.clear();
			byteBuffer = newByteBuffer;
			byteBuffer = null;
		}

		throw new OutOfMemoryError();
	}
}
