package net.sarhatabaot.configloader.transform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListClone implements YamlTransform {

	@Override
	public Object toYaml(Object obj) {
		return obj;
	}

	@Override
	public Object fromYaml(Object obj) {
		return new ArrayList<Object>((List<?>) obj);
	}

}
