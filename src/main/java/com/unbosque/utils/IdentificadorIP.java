package com.unbosque.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IdentificadorIP {
	public String getIP() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
