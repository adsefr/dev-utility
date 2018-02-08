package com.yyfs.dev.utility.io.cache;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteCacheOutputstream extends OutputStream {

	private final static int DEFAULT_CAPCITY = 1024;

	private int capacity;

	private ByteBuffer byteBuffer;

	private OutputStream outputStream;

	public ByteCacheOutputstream() {
		this(null, DEFAULT_CAPCITY);
	}

	public ByteCacheOutputstream(int capacity) {
		this(null, capacity);
	}

	/**
	 *
	 * @param outputStream
	 */
	public ByteCacheOutputstream(OutputStream outputStream) {
		this(outputStream, DEFAULT_CAPCITY);
	}

	/**
	 *
	 * @param outputStream
	 * @param capacity
	 */
	public ByteCacheOutputstream(OutputStream outputStream, int capacity) {
		this.capacity = capacity;
		this.byteBuffer = ByteBuffer.allocate(capacity);
		this.outputStream = outputStream;
	}

	@Override
	public void write(int b) throws IOException {

		if (outputStream != null) {
			outputStream.write(b);
		}

		extendCapacity(1);
		byteBuffer.put((byte) b);
	}

	@Override
	public void write(byte[] b) throws IOException {

		this.write(b, 0, b.length);
	}

	@Override
	public void write(byte[] b, int offset, int length) throws IOException {

		if (outputStream != null) {
			outputStream.write(b, offset, length);
		}

		extendCapacity(length);

		byteBuffer.put(b, offset, length);
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
