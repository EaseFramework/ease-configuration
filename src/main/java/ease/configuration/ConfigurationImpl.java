package ease.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import ease.binder.DataBinder;
import ease.configuration.utils.StringUtils;

class ConfigurationImpl implements Configuration {
	
	private final List<Configurator> configurators;

	public ConfigurationImpl(List<Configurator> configurators) {
		this.configurators = configurators;
	}
	
	@Override
	public <T> T getSectionOrDefault(String key, T defaultValue, Class<T> cls) {
		DescendentConfigCollector collector = new DescendentConfigCollector(key);
		collectConfig(collector);
		
		Map<String,String> config = collector.getConfig();
		
		if(config==null || config.size() ==0) {
			return defaultValue;
		}
		
		DataBinder dataBinder = new DataBinder();
		return dataBinder.bind(config, cls);
	}

	@Override
	public String getStringOrDefault(String key, String defaultValue) {
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
	
	
	private void collectConfig(ConfigCollector collector) {
		for(Configurator configurator: configurators) {
			configurator.forEach(collector);
		}
	}
	
	private static interface ConfigCollector extends BiConsumer<String, String> {
		public Map<String,String> getConfig();
		
	}
	
	
	private static class DescendentConfigCollector implements ConfigCollector {
		
		private Map<String,String> configurationMap = new HashMap<>();
		private String parentKey;
		
		
		public DescendentConfigCollector(String parentKey) {
			this.parentKey = parentKey;
		}
		
		@Override
		public void accept(String key, String value) {
			String descendentKey = ConfigurationKeyHelper.getDescendentKey(parentKey, key);
			
			if(!StringUtils.isNullOrEmpty(descendentKey)) {
				configurationMap.put(descendentKey, value);
			}
		}

		@Override
		public Map<String, String> getConfig() {
			return configurationMap;
		}
	}
	
}
