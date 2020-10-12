package net.sarhatabaot.configloader;

import net.sarhatabaot.configloader.postload.NoPostLoad;
import net.sarhatabaot.configloader.postload.PostLoad;
import net.sarhatabaot.configloader.transform.NoTransform;
import net.sarhatabaot.configloader.transform.YamlTransform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigOption {
	/**
	 *
	 * @return
	 */
	String path();

	/**
	 *
	 * @return
	 */
	String[] legacyPath() default {};

	/**
	 *
	 * @return
	 */
	Class<? extends YamlTransform> transform() default NoTransform.class;

	/**
	 *
	 * @return
	 */
	Class<? extends PostLoad> postLoad() default NoPostLoad.class;

}
