package com.lighting.framework.core.util;

public enum QueryKeywordEnum {
    EQ("eq", "="),
    NE("ne", "<>"),
    GT("gt", ">"),
    GE("ge", ">="),
    LT("lt", "<"),
    LE("le", "<="),
    BETWEEN_START("betweenStart", "between ? and ?"),
    BETWEEN_END("betweenEnd", "between ? and ?"),
    DATE_START("DateStart", "between ? and ?"),
    DATE_END("DateEnd", "between ? and ?"),
    NOTBETWEEN_START("notBetweenStart", "not between ? and ?"),
    NOTBETWEEN_End("notBetweenEnd", "not between ? and ?"),
    LIKE("like", "like %?%"),
    NOTLIKE("not like", "not like %?%"),
    LIKELEFT("likeLeft", "like %?"),
    LIKERIGHT("likeRight", "like ?%"),
    ISNULL("isNull", "is null"),
    ISNOTNULL("isNotNull", "is not null"),
    IN("in", "in(?,?...)"),
    NOTIN("notIn", "not in(?,?...)"),
    INSQL("inSql", "in(sql)"),
    NOTINSQL("notInSql", "not in(sql)"),
    GROUPBY("groupBy", "group by ?,? ..."),
    ORDERBYASC("orderByAsc", "order by ?,? ... asc"),
    ORDERBYDESC("orderByDesc", "order by ?,? ... desc"),
    ORDERBY("orderBy", "order by ?,? ..."),
    OR("or", "or"),
    AND("and", "and"),
    APPLY("apply", "apply"),
    LAST("last", "last"),
    EXISTS("exists", "exists"),
    NOTEXISTS("notExists", "notExists"),
    NESTED("nested", "nested");

    public String desc;
    public String expression;

    private QueryKeywordEnum(String expression, String desc) {
        this.desc = desc;
        this.expression = expression;
    }

    public static QueryKeywordEnum toQueryEnum(String expression) {
        for (QueryKeywordEnum queryEnum : QueryKeywordEnum.values()) {
            if (queryEnum.expression.equals(expression)) {
                return queryEnum;
            }
        }
        return null;
    }
}
