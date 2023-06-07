package com.unbosque.utils;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class EncodeSHA256 implements Encode{

	@Override
	public String getStringEncoded(String pwd) throws NoSuchAlgorithmException {
		return DigestUtils.sha256Hex(pwd);
	}

}
