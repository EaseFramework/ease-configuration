package ease.configuration.utils;

import org.junit.Test;

import org.junit.Assert;

public class StringUtilsTest {

	@Test
	public void join() {
		Assert.assertEquals("test1.test2.test3", StringUtils.join(".", "test1","test2","test3"));
	}
	
	@Test
	public void join_nullSegments() {
		Assert.assertEquals("", StringUtils.join("."));
	}
	
	@Test
	public void isNullOrEmpty() {
		Assert.assertTrue(StringUtils.isNullOrEmpty(""));
		Assert.assertTrue(StringUtils.isNullOrEmpty(null));
		Assert.assertFalse(StringUtils.isNullOrEmpty("test"));
	}
}
