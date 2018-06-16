package ease.configuration.memory;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MemoryConfiguratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void create_throwsIllegalArgumentException() {
		new MemoryConfigurator(null);
	}

	@Test
	public void get() {
		MemoryConfigurator configurator = create();
		configurator.configure();

		Assert.assertTrue(configurator.get("parent.key1").isPresent());
		Assert.assertEquals("key1", configurator.get("parent.key1").get());

		Assert.assertTrue(configurator.get("parent.key2").isPresent());
		Assert.assertEquals("key1", configurator.get("parent.key2").get());

		Assert.assertFalse(configurator.get("parent.key3").isPresent());
	}

	@Test
	public void set() {
		MemoryConfigurator configurator = create();
		configurator.configure();
		configurator.set("set.key1", "setValue");

		Assert.assertTrue(configurator.get("set.key1").isPresent());
		Assert.assertEquals("setValue", configurator.get("set.key1").get());
	}

	@Test
	public void configure() {
		MemoryConfigurator configurator = create();

		Assert.assertFalse(configurator.get("parent.key2").isPresent());

		configurator.configure();

		Assert.assertTrue(configurator.get("parent.key2").isPresent());
	}

	private MemoryConfigurator create() {
		Map<String, String> configurationValues = new HashMap<>();
		configurationValues.put("parent.key1", "key1");
		configurationValues.put("parent.key2", "key1");

		return new MemoryConfigurator(configurationValues);
	}
}
