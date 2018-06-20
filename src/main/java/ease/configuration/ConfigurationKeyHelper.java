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
	
	
	public static String getChildKey(String parentSegment, String key) {
		final String parentPrefix = createParentKeyPrefix(parentSegment);
		
		if(key.startsWith(parentPrefix)) {
			int indexOf = key.indexOf(ConfigurationConstants.KEY_DELIMITER, parentPrefix.length());
			return indexOf == -1 ? key.substring(parentPrefix.length()) : key.substring(parentPrefix.length(), indexOf);
		}
		
		return null;
	}
	
	public static String getDescendentKey(String parentSegment, String key) {
		final String parentPrefix = createParentKeyPrefix(parentSegment);
		
		if(key.startsWith(parentPrefix)) {
			return key.substring(parentPrefix.length());
		}
		
		return null;
	}
	
	private static String createParentKeyPrefix(String parentSegment) {
		return (StringUtils.isNullOrEmpty(parentSegment) ? "" : parentSegment + ConfigurationConstants.KEY_DELIMITER).toLowerCase();
	}
}
