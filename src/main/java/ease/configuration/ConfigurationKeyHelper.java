package ease.configuration;

import static ease.configuration.ConfigurationConstants.KEY_DELIMITER;

import ease.configuration.utils.StringUtils;

public class ConfigurationKeyHelper {

	public static String joinSegments(String... segments) {
		if (segments == null) {
			throw new IllegalArgumentException("Segments cannot be null");
		}

		return StringUtils.join(KEY_DELIMITER, segments);
	}

	public static String getParentKey(String key) {
		if (StringUtils.isNullOrEmpty(key)) {
			return null;
		}

		final int lastIndex = key.lastIndexOf(KEY_DELIMITER);

		return lastIndex == -1 ? null : key.substring(0, lastIndex);
	}
}
