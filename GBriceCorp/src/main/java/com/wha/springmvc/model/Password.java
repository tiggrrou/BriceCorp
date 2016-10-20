package com.wha.springmvc.model;

import java.security.SecureRandom;
import java.math.BigInteger;

public class Password {
	/**
	 * This works by choosing 130 bits from a cryptographically secure random
	 * bit generator, and encoding them in base-32. 128 bits is considered to be
	 * cryptographically strong, but each digit in a base 32 number can encode 5
	 * bits, so 128 is rounded up to the next multiple of 5. This encoding is
	 * compact and efficient, with 5 random bits per character. Compare this to
	 * a random UUID, which only has 3.4 bits per character in standard layout,
	 * and only 122 random bits in total.
	 */

	private static  SecureRandom random = new SecureRandom();

	public static String nextSessionId() {
		return new BigInteger(130, random).toString(3);
	}

	// String uuid = UUID.randomUUID().toString();
	// System.out.println("uuid = " + uuid);
}
