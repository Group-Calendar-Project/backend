package com.gc.common.framework.logging

import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.util.StreamUtils
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class CachingBodyHttpServletRequest(
  request: HttpServletRequest
) : HttpServletRequestWrapper(request) {

  private val cachedBody: ByteArray = StreamUtils.copyToByteArray(request.inputStream)

  override fun getInputStream(): ServletInputStream {
    return CachingBodyServletInputStream(cachedBody)
  }

  override fun getReader(): BufferedReader {
    val byteArrayInputStream = ByteArrayInputStream(cachedBody)
    return BufferedReader(InputStreamReader(byteArrayInputStream))
  }
}