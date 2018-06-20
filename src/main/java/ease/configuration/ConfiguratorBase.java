package ease.configuration;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public abstract class ConfiguratorBase implements Configurator {

	private final ConcurrentHashMap<String, String> configurationMap = new ConcurrentHashMap<>();

	@Override
	public Optional<String> get(String key) {
		return Optional.ofNullable(configurationMap.get(key));
	}
	
	@Override
	public void set(String key, String value) {
		configurationMap.put(key, value);
	}

	@Override
	public void forEach(BiConsumer<String, String> consumer) {
		configurationMap.forEach(consumer);
	}
}
