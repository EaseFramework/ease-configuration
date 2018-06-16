package ease.configuration.memory;

import java.util.Map;

import ease.configuration.Configurator;
import ease.configuration.ConfiguratorSourceBase;
import ease.configuration.utils.StringUtils;

public class MemoryConfiguratorSource extends ConfiguratorSourceBase {

	private final Map<String, String> configurationValues;

	public MemoryConfiguratorSource(String name, Map<String, String> configurationValues) {
		super(name);

		if(StringUtils.isNullOrEmpty(name) ) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}

		if (configurationValues == null) {
			throw new IllegalArgumentException("Configuration Values cannot be null");
		}

		this.configurationValues = configurationValues;
	}

	@Override
	public Configurator create() {
		return new MemoryConfigurator(configurationValues);
	}
}
