package com.agilefaqs.ip2c;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class WhoIsTest {
	private static final List<String> GEO_IP_DATA = asList(
			"1.1.1.1|100.100.55.255|US",
			"122.169.55.1|122.169.55.255|IN",
			"122.199.0.2|122.199.0.120|AU",
			"122.156.115.100|122.156.115.250|CN",
			"202.0.51.200|202.0.51.255|NZ"
			);
	private static WhoIs whoIs = new WhoIs(GEO_IP_DATA);
	private String countryCode;

	@Test
	public void retrieveCountryCodeBasedOnIPAddress() {
		ip("122.169.55.254").belongsTo("IN");
		ip("122.199.0.12").belongsTo("AU");
		ip("122.156.115.150").belongsTo("CN");
		ip("202.0.51.205").belongsTo("NZ");
	}

	@Test
	public void defaultToUS() {
		ip("127.0.0.1").belongsTo("US");
	}

	@Test
	public void defaultInvalidIPAddressToUS() {
		ip("invalidIP").belongsTo("US");
		ip("1.2.3").belongsTo("US");
	}

	private WhoIsTest ip(String ipAddress) {
		countryCode = whoIs.ipLookUp(ipAddress);
		return this;
	}

	private void belongsTo(String expectedCountryCode) {
		assertEquals(expectedCountryCode, countryCode);
	}
}
