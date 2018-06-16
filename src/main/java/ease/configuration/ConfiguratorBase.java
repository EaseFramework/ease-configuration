package ease.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ConfiguratorBase implements Configurator {

	private final Map<String, String> configurationMap = new HashMap<>();

	@Override
	public Optional<String> get(String key) {
		return Optional.ofNullable(configurationMap.get(key));
	}

	@Override
	public void set(String key, String value) {
		configurationMap.put(key, value);
	}
}
