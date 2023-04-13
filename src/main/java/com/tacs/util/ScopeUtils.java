package com.tacs.util;

import com.tacs.config.ScopesEnum;

public class ScopeUtils {
    private ScopeUtils() {}

    public static ScopesEnum getScope() {
        String scopeEnvVar = System.getenv("SCOPE");
        return ScopesEnum.getScopeByValue(scopeEnvVar);
    }
}
