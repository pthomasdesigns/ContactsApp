package com.pthomasdesigns.contactsapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pthomasdesigns.libcontactandroid.ContactSdk;
import com.pthomasdesigns.libcontactandroid.NativeInterfaceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    ContactSdk sdk;

    @Before
    public void initSdk() {
        sdk = ContactSdk.initialize(new NativeInterfaceImpl());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.pthomasdesigns.contactsapp", appContext.getPackageName());
    }
}
