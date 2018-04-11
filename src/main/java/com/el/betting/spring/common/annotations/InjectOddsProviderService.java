package com.el.betting.spring.common.annotations;

import com.el.betting.sdk.v2.provider.BestOddsProvider;
import com.el.betting.sdk.v2.provider.Bookmaker;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectOddsProviderService {

	/**
     * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any
	 */
	String value() default "";

	BestOddsProvider name();
}