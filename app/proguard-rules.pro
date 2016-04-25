# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/simon/sdk/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontshrink
-dontoptimize

#不混淆使用了 annotation的类
-keepattributes *Annotation*
#不混淆javascript
-keepattributes JavascriptInterface
#不混淆 使用反射机制的类
-keepattributes Signature
#忽略警告
-ignorewarnings

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------

##---------------------------Begin: cdn  --------------------------
-keep class com.mato.** { *; }
-dontwarn com.mato.**
-keepattributes Exceptions, Signature, InnerClasses
##---------------------------End: cdn  --------------------------

##---------------Begin: proguard configuration for share------------
-dontwarn org.apache.commons.**
-keep public class com.weibo.sdk.android.**{  *; }
-keep public class com.sina.sso.**{*;}
-keep public class android.net.http.SslError{*;}
-keep public class com.tencent.**{*;}
-keep class com.hujiang.loginmodule.api.model.** { *; }
-keep class com.hujiang.social.sdk.model.** { *; }
##---------------End: proguard configuration for share--------------

-keep class com.hujiang.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn com.umeng.**
-dontwarn org.apache.commons.**
-dontwarn com.tencent.weibo.sdk.**
-keepattributes *Annotation*

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
