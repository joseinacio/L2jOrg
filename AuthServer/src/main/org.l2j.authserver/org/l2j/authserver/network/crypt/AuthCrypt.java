package org.l2j.authserver.network.crypt;

import org.l2j.commons.crypt.NewCrypt;
import org.l2j.commons.util.Rnd;

import java.io.IOException;

/**
 * @author KenM
 */
public class AuthCrypt {

	private static final byte[] STATIC_BLOWFISH_KEY = {
		(byte) 0x6b,
		(byte) 0x60,
		(byte) 0xcb,
		(byte) 0x5b,
		(byte) 0x82,
		(byte) 0xce,
		(byte) 0x90,
		(byte) 0xb1,
		(byte) 0xcc,
		(byte) 0x2b,
		(byte) 0x6c,
		(byte) 0x55,
		(byte) 0x6c,
		(byte) 0x6c,
		(byte) 0x6c,
		(byte) 0x6c
	};
	
	private final NewCrypt _staticCrypt = new NewCrypt(STATIC_BLOWFISH_KEY);
	private NewCrypt _crypt;
	private boolean _static = true;
	
	public void setKey(byte[] key)
	{
		_crypt = new NewCrypt(key);
	}
	
	public boolean decrypt(byte[] raw, final int offset, final int size) throws IOException
	{
		_crypt.decrypt(raw, offset, size);
		return NewCrypt.verifyChecksum(raw, offset, size);
	}

	public int encryptedSize(int dataSize) {
		if(_static) {
			dataSize += 8;
			dataSize += 8 - (dataSize % 8);
			dataSize += 8;
		} else {
			dataSize += 4;
			dataSize += 8 - (dataSize % 8);
			dataSize += 8;
		}
		return dataSize;

	}
	
	public byte[] encrypt(byte[] raw, final int offset, int size) throws IOException {
        var encryptedSize = encryptedSize(size);
		if (_static) {
			NewCrypt.encXORPass(raw, offset, encryptedSize, Rnd.nextInt());
			_staticCrypt.crypt(raw, offset, encryptedSize);
			_static = false;
		} else {
			NewCrypt.appendChecksum(raw, offset, encryptedSize);
			_crypt.crypt(raw, offset, encryptedSize);
		}
		return raw;
	}
}
