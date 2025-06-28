package com.gc.api.customer.framework.exception

import common.exception.CustomNotFoundException

//fun notFound(message: String): Nothing =
//    throw CustomNotFoundException(message)

fun <T: Any> T?.orNotFound(message: String): T =
    this ?: throw CustomNotFoundException(message)