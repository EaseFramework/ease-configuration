package ease.configuration;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationKeyHelperTest {

	@Test
	public void joinSegments() {
		Assert.assertEquals("test1.test2.test3", ConfigurationKeyHelper.joinSegments("test1", "test2", "test3"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void joinSegments_throwsExceptionWhenNull() {
		String[] segments = null;
		ConfigurationKeyHelper.joinSegments(segments);
	}

	@Test
	public void getParentKey() {
		Assert.assertEquals("test1", ConfigurationKeyHelper.getParentKey("test1.test2"));
	}

	@Test
	public void getParentKey_nullWhenNoParent() {
		Assert.assertNull(ConfigurationKeyHelper.getParentKey("test1"));
	}
}
