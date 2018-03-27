package com.wblei.converter;

/**
 * Created by weibolei on 27/03/2018.
 */

public interface IConverter<T, P> {
  P convert(T source);
}
