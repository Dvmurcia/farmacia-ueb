package com.unbosque.test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.unbosque.utils.EncodeSHA256;

public class EncryptTestCase {
	private EncodeSHA256 encoder;
	private final static String contrasena = "Test1234";
	// Hash generado para la cadena "Test1234" -
	// https://emn178.github.io/online-tools/sha256.html
	private final static String HASH = "07480fb9e85b9396af06f006cf1c95024af2531c65fb505cfbd0add1e2f31573";

	@Before
	public void setUp() {
		this.encoder = new EncodeSHA256();
	}

	@Test
	public void testEncriptarSHA256() {
		try {
			String resultadoSHA256 = encoder.getStringEncoded(contrasena);
			assertEquals("El hash generado debe coincidir con el valor estatico", HASH, resultadoSHA256);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
