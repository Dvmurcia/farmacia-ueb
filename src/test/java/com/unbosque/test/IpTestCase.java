package com.unbosque.test;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.unbosque.utils.IdentificadorIP;

public class IpTestCase {
	private IdentificadorIP ipUtil;
	private final static String ip = "192.168.1.12";

	@Before
	public void setUp() throws Exception {
		this.ipUtil = new IdentificadorIP();
	}

	@Test
	public void testIPIdentificador() {
		try {
			String ipCalculada = ipUtil.getIP();
			assertEquals("Deben coincidir las IP estatica y calculada", ip, ipCalculada);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
