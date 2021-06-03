package com.mindorks.framework.mvvm.testUtils

import com.nhaarman.mockitokotlin2.whenever

fun <R> mockReturn(toMock: R, returnedValue: R)
{
    whenever(toMock).thenReturn(returnedValue)
}