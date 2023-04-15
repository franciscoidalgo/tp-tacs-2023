package com.tacs.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QueryParam {
  private String paramName;
  private Object value;
}
