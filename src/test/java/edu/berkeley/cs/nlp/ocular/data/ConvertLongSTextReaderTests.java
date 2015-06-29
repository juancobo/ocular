package edu.berkeley.cs.nlp.ocular.data;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.berkeley.cs.nlp.ocular.data.textreader.BasicTextReader;
import edu.berkeley.cs.nlp.ocular.data.textreader.ConvertLongSTextReader;
import edu.berkeley.cs.nlp.ocular.data.textreader.RemoveDiacriticsTextReader;
import edu.berkeley.cs.nlp.ocular.data.textreader.TextReader;

/**
 * @author Dan Garrette (dhg@cs.utexas.edu)
 */
public class ConvertLongSTextReaderTests {

	private String s1 = "ing th\\~q || | follies of thõsè, who éither ``sæek'' out th\\\"os\\`e wæys \"and\" means, which either are sq̃uccess confession asi lessons";

	@Test
	public void test_readCharacters() {
		TextReader tr = new ConvertLongSTextReader(new BasicTextReader());
		assertEquals(Arrays.asList("t", "h", "\\~o", "|", "\\`e"), tr.readCharacters("thõsè"));
		assertEquals(Arrays.asList("|", "i"), tr.readCharacters("si"));
		assertEquals(Arrays.asList("|", "i", "n"), tr.readCharacters("sin"));
		assertEquals(Arrays.asList("a", "|", "i"), tr.readCharacters("asi"));
		assertEquals(Arrays.asList("|", "s", "i"), tr.readCharacters("ssi"));
		assertEquals(Arrays.asList("a", "|", "s", "i"), tr.readCharacters("assi"));
		assertEquals(Arrays.asList("|", "s", "i", "n"), tr.readCharacters("ssin"));
		assertEquals(Arrays.asList("a", "|", "s", "i", "n"), tr.readCharacters("assin"));
		List<String> r = Arrays.asList("i", "n", "g", " ", "t", "h", "\\~q", " ", "/", "/", " ", "/", " ", "f", "o", "l", "l", "i", "e", "s", " ", "o", "f", " ", "t", "h", "\\~o", "|", "\\`e", ",", " ", "w", "h", "o", " ", "\\'e", "i", "t", "h", "e", "r", " ", "\"", "|", "æ", "e", "k", "\"", " ", "o", "u", "t", " ", "t", "h", "\\\"o", "|", "\\`e", " ", "w", "æ", "y", "s", " ", "\"", "a", "n", "d", "\"", " ", "m", "e", "a", "n", "s", ",", " ", "w", "h", "i", "c", "h", " ", "e", "i", "t", "h", "e", "r", " ", "a", "r", "e", " ", "|", "\\~q", "u", "c", "c", "e", "|", "s", " ", "c", "o", "n", "f", "e", "|", "s", "i", "o", "n", " ", "a", "|", "i", " ", "l", "e", "|", "|", "o", "n", "s");
		assertEquals(r, tr.readCharacters(s1));
	}

	@Test
	public void test_readCharacters_removeDia() {
		TextReader tr = new ConvertLongSTextReader(new RemoveDiacriticsTextReader(new BasicTextReader()));
		List<String> r = Arrays.asList("i", "n", "g", " ", "t", "h", "q", " ", "/", "/", " ", "/", " ", "f", "o", "l", "l", "i", "e", "s", " ", "o", "f", " ", "t", "h", "o", "|", "e", ",", " ", "w", "h", "o", " ", "e", "i", "t", "h", "e", "r", " ", "\"", "|", "æ", "e", "k", "\"", " ", "o", "u", "t", " ", "t", "h", "o", "|", "e", " ", "w", "æ", "y", "s", " ", "\"", "a", "n", "d", "\"", " ", "m", "e", "a", "n", "s", ",", " ", "w", "h", "i", "c", "h", " ", "e", "i", "t", "h", "e", "r", " ", "a", "r", "e", " ", "|", "q", "u", "c", "c", "e", "|", "s", " ", "c", "o", "n", "f", "e", "|", "s", "i", "o", "n", " ", "a", "|", "i", " ", "l", "e", "|", "|", "o", "n", "s");
		assertEquals(r, tr.readCharacters(s1));
	}

}
