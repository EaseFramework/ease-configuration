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
	
	@Test
	public void getChildKey() {
		Assert.assertEquals("credential", 
				ConfigurationKeyHelper.getChildKey("app.datasource", "app.datasource.credential.username"));
		
		Assert.assertEquals("datasource", 
				ConfigurationKeyHelper.getChildKey("app", "app.datasource.credential.username"));
		
		Assert.assertEquals("app", 
				ConfigurationKeyHelper.getChildKey("", "app.datasource.credential.username"));
		
		Assert.assertNull(ConfigurationKeyHelper.getChildKey("app.services", "app.datasource.credential.username"));
	}
	
	
	@Test
	public void getDescendentKey() {
		Assert.assertEquals("credential.username", 
				ConfigurationKeyHelper.getDescendentKey("app.datasource", "app.datasource.credential.username"));
		
		Assert.assertEquals("datasource.credential.username", 
				ConfigurationKeyHelper.getDescendentKey("app", "app.datasource.credential.username"));
		
		Assert.assertEquals("app.datasource.credential.username", 
				ConfigurationKeyHelper.getDescendentKey("", "app.datasource.credential.username"));
		
		Assert.assertNull(ConfigurationKeyHelper.getDescendentKey("app.services", "app.datasource.credential.username"));
	}
}
