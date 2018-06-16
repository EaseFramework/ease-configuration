package ease.configuration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ease.configuration.memory.MemoryConfiguratorSource;
import ease.configuration.utils.StringUtils;

public class ConfigurationBuilderDefault implements ConfigurationBuilder {

	private final List<ConfiguratorSource> sourceList = new LinkedList<>();

	@Override
	public void addSource(ConfiguratorSource source) {
		if (StringUtils.isNullOrEmpty(source.getName())) {
			throw new IllegalArgumentException("Source name is required");
		}
		sourceList.add(source);
	}

	@Override
	public void addMemoryConfig(String name, Map<String, String> configurationValues) {
		sourceList.add(new MemoryConfiguratorSource(name, configurationValues));
	}

	@Override
	public List<ConfiguratorSource> getSources() {
		return Collections.unmodifiableList(sourceList);
	}

	@Override
	public Configuration build() {
		List<Configurator> configuratorList = new LinkedList<>();

		for (ConfiguratorSource source : sourceList) {
			try {
				configuratorList.add(source.create());
			} catch (Throwable t) {
				throw new ConfigurationException("Error creating configurator from source with name "
						+ (StringUtils.isNullOrEmpty(source.getName()) ? "" : source.getName()));
			}
		}

		return new ConfigurationImpl(configuratorList);
	}
}
