package de.rieckpil.blog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class Functions {

	public static Iterator<String> splitTagString(String tagString, String delimiter) {
		Logger logger = Logger.getAnonymousLogger();

		if (delimiter == null || delimiter.isEmpty()) {
			delimiter = ">";
		}

		logger.info("Splitting tag string [" + tagString + "] with [" + delimiter + "]");

		List<String> tags = new ArrayList<String>();

		for (String currentTag : tagString.split(delimiter)) {
			tags.add(currentTag.trim());
		}

		return tags.iterator();
	}
}