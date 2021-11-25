package com.lighting.framework.core.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class QueryWrapperBuilder {
	private static final String $QUERY = "$query_";

	public static <T>QueryWrapper<T> buildFromRequest(HttpServletRequest request) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		// 获取从request对象中查询条件参数
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			// 是否是查询参数，规定查询条件参数以 $query开头 格式如：$query_eq_xxxx
			if (parameterName.startsWith($QUERY) && parameterName.split("_").length >= 3) {
				String[] queryParam = parameterName.split("_");
				// 为获取到有效地信息返回
				if (QueryKeywordEnum.toQueryEnum(queryParam[1]) == null) {
					continue;
				}
				// 处理字段名称带下划线的
				String queryCol = queryParam[2];
				if (queryParam.length > 3) {
					queryCol = parameterName.substring(parameterName.indexOf("_")+1);
					queryCol = queryCol.substring(queryCol.indexOf("_")+1);
				}
				// 构建查询queryWrapper
				switch (QueryKeywordEnum.toQueryEnum(queryParam[1])) {
				case EQ:
					queryWrapper.eq(queryCol, request.getParameter(parameterName));
					break;
				case NE:
					queryWrapper.ne(queryCol, request.getParameter(parameterName));
					break;
				case GT:
					queryWrapper.gt(queryCol, request.getParameter(parameterName));
					break;
				case GE:
					queryWrapper.ge(queryCol, request.getParameter(parameterName));
					break;
				case LT:
					queryWrapper.lt(queryCol, request.getParameter(parameterName));
					break;
				case LE:
					queryWrapper.le(queryCol, request.getParameter(parameterName));
					break;
				case BETWEEN_START:
					String betweenEndParamKey = queryParam[0] + "_" + QueryKeywordEnum.BETWEEN_END.expression + "_"
							+ queryCol;
					if (request.getParameter(betweenEndParamKey) != null) {
						queryWrapper.between(queryCol, request.getParameter(parameterName),
								request.getParameter(betweenEndParamKey));
					}
					break;
				case NOTBETWEEN_START:
					String notBetweenEndParamKey = queryParam[0] + "_" + QueryKeywordEnum.BETWEEN_END.expression + "_"
							+ queryCol;
					if (request.getParameter(notBetweenEndParamKey) != null) {
						queryWrapper.notBetween(queryCol, request.getParameter(parameterName),
								request.getParameter(notBetweenEndParamKey));
					}
					break;
				case LIKE:
					queryWrapper.like(queryCol, request.getParameter(parameterName));
					break;
				case NOTLIKE:
					queryWrapper.notLike(queryCol, request.getParameter(parameterName));
					break;
				case LIKELEFT:
					queryWrapper.likeLeft(queryCol, request.getParameter(parameterName));
					break;
				case LIKERIGHT:
					queryWrapper.likeRight(queryCol, request.getParameter(parameterName));
					break;
				case ISNULL:
					queryWrapper.isNull(queryParam[1]);
					break;
				case ISNOTNULL:
					queryWrapper.isNotNull(queryParam[1]);
					break;
				case IN:
					queryWrapper.in(queryCol, request.getParameter(parameterName));
					break;
				case NOTIN:
					queryWrapper.notIn(queryCol, request.getParameter(parameterName));
					break;
				case INSQL:
					queryWrapper.inSql(queryCol, request.getParameter(parameterName));
					break;
				case NOTINSQL:
					queryWrapper.notInSql(queryCol, request.getParameter(parameterName));
					break;
				case ORDERBYASC:
					queryWrapper.orderByAsc(queryParam[1]);
					break;
				case ORDERBYDESC:
					queryWrapper.orderByDesc(queryParam[1]);
					break;
				default:
					break;
				}
			}
		}
		return (QueryWrapper<T>)queryWrapper;
	}
}
