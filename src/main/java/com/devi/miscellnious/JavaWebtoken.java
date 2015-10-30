package com.devi.miscellnious;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

public class JavaWebtoken {
public static void main(String[] args) {
	Key key = MacProvider.generateKey();

	String s = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();
	System.out.println(s);
	
	System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject().equals("Joe"));
}
}
