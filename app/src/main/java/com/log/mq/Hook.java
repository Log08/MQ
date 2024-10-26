package com.log.mq;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Log husy on 2024/10/26.
 *
 * @link https://github.com/Log08
 * @Gdescription:
 */
public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Sp.XSp_init();
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (lpparam.packageName.equals("com.log.mq")){
                    return;
                }
                Context context=(Context) param.args[0];
                ClassLoader classLoader=context.getClassLoader();
                switch (lpparam.packageName){
                    case "com.mumu.launcher"->Launcher_Hook(classLoader);
                    case "com.android.packageinstaller"->Install_Hook(classLoader);
                    case "com.mumu.store"->StoreInstall_Hook(classLoader);
                }
            }
        });
    }
    public void Launcher_Hook(ClassLoader classLoader){
        Class<?> Launcher=XposedHelpers.findClass("com.mumu.launcher.Launcher",classLoader);
        if (Sp.Hook_getBoolean("LauncherAD")) {
            XposedHelpers.findAndHookMethod(Launcher, "x3", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object o = param.thisObject;
                    Field r = XposedHelpers.findField(o.getClass(), "r");
                    r.setAccessible(true);
                    ViewGroup viewGroup = (ViewGroup) r.get(o);
                    viewGroup.setVisibility(View.GONE);
                }
            });
        }
        if (Sp.Hook_getBoolean("LauncherSearch")) {
            XposedHelpers.findAndHookMethod(Launcher, "o3", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object o = param.thisObject;
                    Field field = XposedHelpers.findField(o.getClass(), "H");
                    field.setAccessible(true);
                    View layout = (View) field.get(o);
                    layout.setVisibility(View.GONE);
                }
            });
        }
    }
    public void Install_Hook(ClassLoader classLoader){
        if (Sp.Hook_getBoolean("installTG")) {
            XposedHelpers.findAndHookMethod("com.android.packageinstaller.PackageInstallerActivity", classLoader, "bindUi", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object obj = param.thisObject;
                    Field field = XposedHelpers.findField(obj.getClass(), "mOk");
                    field.setAccessible(true);
                    Button mok = (Button) field.get(obj);
                    mok.performClick();
                }
            });
        }
    }
    public void StoreInstall_Hook(ClassLoader classLoader) {
        if (Sp.Hook_getBoolean("installTG")) {
            XposedHelpers.findAndHookMethod("com.mumu.store.install.RomInstallDialog", classLoader, "showAskInstallDialog", Intent.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object obj = param.thisObject;
                    Field field = XposedHelpers.findField(obj.getClass(), "mLeftButton");
                    field.setAccessible(true);
                    Button mLeftButton = (Button) field.get(obj);
                    mLeftButton.performClick();
                }
            });
        }
    }
}
