package net.sarhatabaot.configloader.postload;

import java.io.File;
import java.util.List;

public class DefaultDestFolder implements PostLoad {

	@SuppressWarnings("unchecked")
	@Override
	public void postLoad(Object value) {
		List<String> list = (List<String>) value;
		if (list.isEmpty()) {
			list.add(new File(".").getAbsoluteFile().getParent());
		}
	}

}
