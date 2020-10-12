package net.sarhatabaot.configloader.postload;

import java.util.List;

public class AstListAppend implements PostLoad {

	@SuppressWarnings("unchecked")
	@Override
	public void postLoad(Object value) {
		List<String> list = (List<String>) value;
		if (list.isEmpty()) {
			list.add("*");
		}
	}

}
