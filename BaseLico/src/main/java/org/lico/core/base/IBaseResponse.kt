package org.lico.core.base

/**
 * @author: lzp
 * @create：2020/5/25
 * @describe：
 */
interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String
    fun data(): T
    fun isSuccess(): Boolean
}