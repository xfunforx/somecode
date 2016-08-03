package com.xfunforx.game;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import de.robv.android.xposed.IXposedHookLoadPackage;
public class Game implements IXposedHookLoadPackage{
    //send dealcustom emoji click
    static {
        XposedBridge.log("===============\n========Game========\n===========");
    }
    String TAG="==Game";
    int mygame=0;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.contains("com.tencent.mm")){
            XposedBridge.log("===Game========mm running=====");
//            寻找过程定位函数
//            XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.emoji.ui.smiley.SmileyGrid$1", loadPackageParam.classLoader, "onItemClick", "android.widget.AdapterView", "android.view.View", int.class, long.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log(TAG+"onItemClick ="+param.args[2]+"  ="+param.args[3]);
//                }
//            });
//
//            XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.emoji.ui.smiley.SmileyGrid", loadPackageParam.classLoader, "a", "com.tencent.mm.plugin.emoji.ui.smiley.SmileyGrid","com.tencent.mm.storage.a.c", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log(TAG+"SmileyGrid a ="+param.args[1].toString());
//                }
//            });
//            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.ct", loadPackageParam.classLoader, "i","com.tencent.mm.storage.a.c", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log(TAG+"ct i ="+param.args[0].toString());
//                }
//            });

            XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.be", loadPackageParam.classLoader, "rl",int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log(TAG+"be rl int="+param.args[0].toString());
                    int game = (int)param.args[0];
                    if(game == 5){
                        ;
//                      骰子游戏
//                      在这里读取配置或者参数什么的
//                      弄完设置game的值，下面的0请自己修改为想要的值最大为5，最小为0
                        mygame = 0;
                    }else if (game ==2){
                        ;
//                      石头布游戏
//                      在这里读取配置或者参数什么的
//                      弄完设置game的值，下面的0请自己修改为想要的值最大为2，最小为0
                        mygame = 0;
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log(TAG+"be rl out ="+param.getResult().toString());
//                    骰子游戏
//                    ======
//                    0 1 点
//                    1 2 点
//                    2 3 点
//                    3 4 点
//                    4 5 点
//                    5 6 点
//                    ========
//                    石头布游戏
//                    ========
//                    0 剪刀
//                    1 石头
//                    2 布
//                    举例
//                    int Game=1;
                    param.setResult(mygame);
//                    如果设置结果为1，骰子就会对应的出 “2点”，石头布游戏注对应的出 “石头”
//                    在这里控制游戏输出的结果
                }
            });
        }
    }
}
