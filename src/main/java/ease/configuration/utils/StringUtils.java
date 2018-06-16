package ease.configuration.utils;

public class StringUtils {

	public static String join(String delimiter, String... segments) {
		if (segments == null || segments.length == 0) {
			return "";
		}

		return String.join(delimiter, segments);
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}

		return false;
	}
}
