package com.test.junit.Testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.test.junit.Palindrome.Palindrome;

public class PalindromeTest {

	@ParameterizedTest
	@ValueSource(strings = { "madam", "radar", "mam", "fahad" })
	public void isPalindromeTest(String s) {
		Palindrome palindrome = new Palindrome();
		boolean ans = palindrome.isPalindrome(s);
		assertTrue(ans);
	}
}
