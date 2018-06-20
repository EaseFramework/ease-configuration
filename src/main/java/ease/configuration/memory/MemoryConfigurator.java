package ease.configuration.memory;

import java.util.Map;

import ease.configuration.ConfiguratorBase;

public class MemoryConfigurator extends ConfiguratorBase {

	private final Map<String, String> defaultConfigurationValues;

	public MemoryConfigurator(Map<String, String> configurationValues) {
		if (configurationValues == null) {
			throw new IllegalArgumentException("Configuration Values cannot be null");
		}

		this.defaultConfigurationValues = configurationValues;
	}

	@Override
	public void configure() {
		for (String key : defaultConfigurationValues.keySet()) {
			set(key, defaultConfigurationValues.get(key));
		}
	}
	
	
	
	
	
	
}
