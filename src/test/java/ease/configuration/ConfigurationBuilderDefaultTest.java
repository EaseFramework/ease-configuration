package ease.configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ease.configuration.memory.MemoryConfigurator;
import ease.configuration.memory.MemoryConfiguratorSource;

public class ConfigurationBuilderDefaultTest {

	private ConfigurationBuilder configurationBuilder;

	@Before
	public void setup() {
		this.configurationBuilder = new ConfigurationBuilderDefault();
	}

	@Test
	public void addSource() {
		configurationBuilder.addSource(new ConfiguratorSource() {

			@Override
			public String getName() {
				return "source1";
			}

			@Override
			public Configurator create() {
				return null;
			}
		});

		List<ConfiguratorSource> sourceList = configurationBuilder.getSources();
		Assert.assertTrue(sourceList.size() == 1);
		Assert.assertEquals(sourceList.get(0).getName(), "source1");
	}

	@Test
	public void addMemoryConfig() {
		configurationBuilder.addMemoryConfig("memoryConfig", new HashMap<>());

		List<ConfiguratorSource> sourceList = configurationBuilder.getSources();
		Assert.assertTrue(sourceList.size() == 1);
		Assert.assertEquals(sourceList.get(0).getName(), "memoryConfig");
	}

	@Test
	public void build() {
		MemoryConfiguratorSource memorySource = mock(MemoryConfiguratorSource.class);
		MemoryConfigurator memoryConfigurator = mock(MemoryConfigurator.class);

		when(memorySource.getName()).thenReturn("memoryConfig");
		when(memorySource.create()).thenReturn(memoryConfigurator);

		configurationBuilder.addSource(memorySource);

		Configuration configuration = configurationBuilder.build();
		Assert.assertNotNull(configuration);

		verify(memorySource).getName();
		verify(memorySource.create());
	}

}
