package ease.configuration;

import java.util.List;
import java.util.Optional;

class ConfigurationImpl implements Configuration {
	
	private final List<Configurator> configurators;

	public ConfigurationImpl(List<Configurator> configurators) {
		this.configurators = configurators;
	}

	@Override
	public String getOrDefault(String key, String defaultValue) {
		final String value = get(key);

		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	@Override
	public Boolean getBooleanOrDefault(String key, Boolean defaultValue) {
		final String value = get(key);

		if (value == null) {
			return defaultValue;
		}

		return Boolean.valueOf(value);
	}

	@Override
	public Integer getIntOrDefault(String key, Integer defaultValue) {

		final String value = get(key);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			throw new ConfigurationException("Int value cannot be created for value", e);
		}
	}

	@Override
	public Float getFloatOrDefault(String key, Float defaultValue) {
		final String value = get(key);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Float.valueOf(value);
		} catch (Exception e) {
			throw new ConfigurationException("Float value cannot be created for value", e);
		}
	}

	@Override
	public Double getDoubleOrDefault(String key, Double defaultValue) {
		final String value = get(key);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			throw new ConfigurationException("Double value cannot be created for value", e);
		}
	}

	private String get(String key) {
		for (int i = configurators.size() - 1; i >= 0; i--) {
			final Configurator configurator = configurators.get(i);

			Optional<String> optional = configurator.get(key);
			
			if (optional.isPresent()) {
				return optional.get();
			}
		}

		return null;
	}
}
