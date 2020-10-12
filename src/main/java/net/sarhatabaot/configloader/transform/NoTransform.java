package net.sarhatabaot.configloader.transform;

/**
 *
 */
public class NoTransform implements YamlTransform {

	@Override
	public Object toYaml(Object obj) {
		return obj;
	}

	@Override
	public Object fromYaml(Object obj) {
		return obj;
	}

}
