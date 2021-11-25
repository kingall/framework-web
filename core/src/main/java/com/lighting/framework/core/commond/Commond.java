package com.lighting.framework.core.commond;

/**
 * 命令关键字
 */
public enum Commond {
    GEN("gen"),
    UPDATE("update"),
    REMOVE("remove");

    private final String value;

    Commond(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Commond fromValue(String v){

            for (Commond c : Commond.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
    }
}
