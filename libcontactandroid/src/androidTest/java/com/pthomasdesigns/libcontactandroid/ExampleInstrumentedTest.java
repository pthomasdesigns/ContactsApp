package com.pthomasdesigns.libcontactandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private ContactSdk sdk;
    NativeInterface mockNative;

    @Before
    public void testSetup() {
    }

    @Test
    public void testSdkInitialize(){
        NativeInterface mockNative = mock(NativeInterface.class);
        //sdk =  ContactSdk.initialize(mockNative);
        //verify(mockNative, times(1)).nativeInit(sdk);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.pthomasdesigns.libcontact_android.test", appContext.getPackageName());
    }
}
