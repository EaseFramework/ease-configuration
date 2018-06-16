package ease.configuration;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ease.configuration.memory.MemoryConfigurator;

public class ConfigurationImplTest {

	private Configuration configuration;
	private MemoryConfigurator memoryConfigurator1;
	private MemoryConfigurator memoryConfigurator2;

	@Before
	public void setUp() {
		memoryConfigurator1 = mock(MemoryConfigurator.class);
		memoryConfigurator2 = mock(MemoryConfigurator.class);

		List<Configurator> configuratorList = new LinkedList<>();
		configuratorList.add(memoryConfigurator1);
		configuratorList.add(memoryConfigurator2);

		configuration = new ConfigurationImpl(configuratorList);
	}

	@Test
	public void getOrDefault_returnsValue() {
		when(memoryConfigurator2.get("parent.child1")).thenReturn(Optional.empty());
		when(memoryConfigurator1.get("parent.child1")).thenReturn(Optional.of("value"));

		String value = configuration.getOrDefault("parent.child1", null);

		Assert.assertNotNull(value);
		Assert.assertEquals("value", value);

		verify(memoryConfigurator2).get("parent.child1");
		verify(memoryConfigurator1).get("parent.child1");
	}

	@Test
	public void getOrDefault_returnsDefault() {
		when(memoryConfigurator2.get("parent.child1")).thenReturn(Optional.empty());
		when(memoryConfigurator1.get("parent.child1")).thenReturn(Optional.empty());

		String value = configuration.getOrDefault("parent.child1", "default");

		Assert.assertNotNull(value);
		Assert.assertEquals("default", value);

		verify(memoryConfigurator2).get("parent.child1");
		verify(memoryConfigurator1).get("parent.child1");
	}
}
