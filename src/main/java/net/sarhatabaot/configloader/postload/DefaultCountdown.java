package net.sarhatabaot.configloader.postload;

import java.util.Collections;
import java.util.List;

public class DefaultCountdown implements PostLoad {

	@SuppressWarnings("unchecked")
	@Override
	public void postLoad(Object value) {
		List<Integer> list = (List<Integer>) value;
		if (list.isEmpty()) {
			list.add(60);
			list.add(30);
			for (int i = 1; i <= 10; i++) {
				list.add(i);
			}
		}
		list.sort(Collections.reverseOrder());
	}

}
