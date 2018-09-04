package com.hurong.credit.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class createKey {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RsaHelper.getInstance().getAutoCreateRSA();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
