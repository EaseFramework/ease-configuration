package ease.configuration.memory;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import ease.configuration.Configurator;

public class MemoryConfiguratorSourceTest {

	@Test(expected = IllegalArgumentException.class)
	public void construct_throwsException_InvalidName() {
		new MemoryConfiguratorSource("", new HashMap<>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void construct_throwsException_InvalidValues() {
		new MemoryConfiguratorSource("defaultValues", null);
	}

	@Test
	public void create() {
		MemoryConfiguratorSource source = new MemoryConfiguratorSource("defaultValues", new HashMap<>());
		Assert.assertTrue(source.create() instanceof Configurator);
	}
}
