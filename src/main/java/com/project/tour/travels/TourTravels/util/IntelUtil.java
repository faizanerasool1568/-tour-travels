package com.project.tour.travels.TourTravels.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntelUtil {

	private static final Logger logger = LoggerFactory.getLogger(IntelUtil.class);

	public static boolean pingApi(String host, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(host, port), timeout);
			return true;
		} catch (IOException e) {
			return false; // Either timeout or unreachable or failed DNS lookup.
		}
	}

	public static String getClientIP(HttpServletRequest request) {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader;
	}

	public static String getServerIpAddress() {
		String ipAddress = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			ipAddress = ip.toString();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.info("An error occurred: " + e.getMessage(), e);
		}
		return ipAddress;
	}

	public static String realClientIP(String x_forwarded_for) {
		logger.info("Before log event for x-forwarded log" + x_forwarded_for);
		String[] ips = x_forwarded_for.split(","); // 127.0.0.1 , 25.192.1323.23 , 25.192.1323.23
		String ipAddress = null;
		if (ips.length > 1) {
			ipAddress = ips[ips.length - 1];
			return ipAddress;
		}
		ipAddress = ips[0];
		logger.info("Login activity Ip adress remoteAddtress:::::::new::: " + ipAddress);
		return ipAddress;
	}
}