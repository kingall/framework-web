package com.lighting.framework.security.api;

import com.lighting.framework.security.domain.AbstractMenuShiro;

import java.util.List;

public interface MenuService {
    List<AbstractMenuShiro> getMenuListByRoleId(String id);
}
