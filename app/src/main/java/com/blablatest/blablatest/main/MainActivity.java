package com.blablatest.blablatest.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.blablatest.blablatest.R;
import com.blablatest.blablatest.base.BaseActivity;
import com.blablatest.blablatest.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 2017/5/19.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private final int PERMISSION_REQUEST = 0;
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private final int APPLICATIONS_SETTINGS_REQ_CODE = 2;
    private boolean isFirstReqPermission = true;
    private List<String> permissionList;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkDrawOverlays();
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                isFirstReqPermission = false;
                checkPermission();
                break;
        }
    }

    private void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_box, LoginFragment.newInstance())
                .commit();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 只有android 6.0 以上的版本會call到這裡
     * for android.permission.SYSTEM_ALERT_WINDOW
     */
    private void checkDrawOverlays() {
        // Settings.canDrawOverlays()只有6.0以上能用，因此需加此行判斷式
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            checkPermission();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!canDrawOverlays(this)) {
                showMessageOKCancel("親愛的用戶您好:\n由於Android 6.0 以上的版本在權限上有些更動，我們需要您授權以下的權限，麻煩您開啟權限後按下返回鍵，感謝。",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                        Uri.parse("package:" + getPackageName()));
                                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                            }
                        });
            } else {
                checkPermission();
            }
        }
    }

    public boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Settings.canDrawOverlays(context);
        } else {
            if (Settings.canDrawOverlays(context)) return true;
            try {
                WindowManager mgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                if (mgr == null) return false; //getSystemService might return null
                View viewToAdd = new View(context);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(0, 0, android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
                viewToAdd.setLayoutParams(params);
                mgr.addView(viewToAdd, params);
                mgr.removeView(viewToAdd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * 只有android 6.0 以上的版本會call到這裡
     * for dangerous permission
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionList = new ArrayList<>();
            boolean isNormal =
                    addPermissionList(permissionList, Manifest.permission.GET_ACCOUNTS) &&
                            addPermissionList(permissionList, Manifest.permission.READ_PHONE_STATE) &&
                            addPermissionList(permissionList, Manifest.permission.ACCESS_COARSE_LOCATION) &&
                            addPermissionList(permissionList, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                            addPermissionList(permissionList, Manifest.permission.ACCESS_FINE_LOCATION);

            if (!isNormal) {
                showMessageOKCancel("親愛的用戶您好：Android在6.0(Marshmallow)之後將授權作業調整為在使用時才需要取得授權，新版本的將導致無法正常使用Pokermap會需要取得「聯絡人」、「電話」、「位置」與「儲存」的授權。\\n\\n\n" +
                                "※請注意：若您拒絕其中任何一項的授權，將導致無法正常使用，請您務必特別留意！\\n\\n\n" +
                                "使用上如有任何問題，煩請至「BLABLA 官網」發訊息告訴我們，謝謝。",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.parse("package:" + getPackageName()));
                                startActivityForResult(intent, APPLICATIONS_SETTINGS_REQ_CODE);
                            }
                        });
            } else if (!permissionList.isEmpty()) {
                if (isFirstReqPermission) {
                    showMessageOKCancel("親愛的用戶您好：Android在6.0(Marshmallow)之後將授權作業調整為在使用時才需要取得授權，新版本的將導致無法正常使用Pokermap會需要取得「聯絡人」、「電話」、「位置」與「儲存」的授權。\\n\\n\n" +
                                    "※請注意：若您拒絕其中任何一項的授權，將導致無法正常使用，請您務必特別留意！\\n\\n\n" +
                                    "使用上如有任何問題，煩請至「BLABLA 官網」發訊息告訴我們，謝謝。",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(permissionList.toArray(new String[permissionList.size()]), PERMISSION_REQUEST);
                                    }
                                }
                            });
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissionList.toArray(new String[permissionList.size()]), PERMISSION_REQUEST);
                    }
                }
            } else {
                init();
            }
        }
    }

    /**
     * 如果retuen false，表示該permission 既沒有被granted，系統也判定不需要顯示rationale => 使用者勾選了 「Not ask again」
     *
     * @param permissionList
     * @param permission
     * @return
     */
    private boolean addPermissionList(List<String> permissionList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
                if (!isFirstReqPermission) {
                    return shouldShowRequestPermissionRationale(permission);
                }
            }
        }

        return true;
    }

    @Override
    protected void setupActivityComponent() {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case OVERLAY_PERMISSION_REQ_CODE:
                checkDrawOverlays();
                break;

            case APPLICATIONS_SETTINGS_REQ_CODE:
                checkPermission();
                break;
            default:
                break;

        }
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("處理中...");
        progressDialog.show();
    }

    @Override
    public void completeLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String msg) {

    }

    /**
     * BackPressed Control
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
