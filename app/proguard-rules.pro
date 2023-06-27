# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Don't obfuscate any NDK/SDK code. This makes the debugging of stack traces
# in release builds easier.
-keepnames class com.google.vr.ndk.** { *; }
-keepnames class com.google.vr.sdk.** { *; }

# These are part of the Java <-> native interfaces for GVR.
-keepclasseswithmembernames,includedescriptorclasses class com.google.vr.** {
    native <methods>;
}

# The SDK configuration protos use reflection.
-keep class com.google.vr.sdk.proto.** {
    *;
}
-keep class com.google.common.logging.Vr$VREvent$SdkConfigurationParams** {
    *;
}
-keep class com.google.common.logging.nano.Vr$VREvent$SdkConfigurationParams** {
    *;
}

-keep class com.google.vr.cardboard.UsedByNative
-keep @com.google.vr.cardboard.UsedByNative class *
-keepclassmembers class * {
    @com.google.vr.cardboard.UsedByNative *;
}

-keep class com.google.vr.cardboard.annotations.UsedByNative
-keep @com.google.vr.cardboard.annotations.UsedByNative class *
-keepclassmembers class * {
    @com.google.vr.cardboard.annotations.UsedByNative *;
}

-keep class com.google.vr.cardboard.annotations.UsedByReflection
-keep @com.google.vr.cardboard.annotations.UsedByReflection class *
-keepclassmembers class * {
    @com.google.vr.cardboard.annotations.UsedByReflection *;
}

-dontwarn sun.misc.Unsafe
-dontwarn libcore.io.Memory