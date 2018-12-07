package com.smallsnailtech.smallsnail.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v4.content.SharedPreferencesCompat;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * @apiNote SharedPreferences操作的工具类，使用EasySP的工具类https://github.com/WhiteDG/EasySP
 * TODO  待完善：添加对SharedPreferences的Key、Value的加密存储功能；优化逻辑代码
 */
public class SharedPrefUtils {

    private static final String TAG = "SharedPrefUtil";
    private static SharedPrefUtils sSharedPrefUtil;
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;
    private static SharedPreferencesCompat.EditorCompat editorCompat = SharedPreferencesCompat.EditorCompat.getInstance();
    private static final String DEFAULT_SP_NAME = "sharedPref_config";
    private static final int DEFAULT_INT = 0;
    private static final float DEFAULT_FLOAT = 0.0f;
    private static final String DEFAULT_STRING = "";
    private static final boolean DEFAULT_BOOLEAN = false;
    private static final Set<String> DEFAULT_STRING_SET = new HashSet<>(0);

    private static String mCurSPName = DEFAULT_SP_NAME;
    private static Context mContext;

    private SharedPrefUtils(Context context) {
        this(context, DEFAULT_SP_NAME);
    }

    private SharedPrefUtils(Context context, String spName) {
        mContext = context.getApplicationContext();
        sSharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        mCurSPName = spName;
    }

    public static SharedPrefUtils init(Context context) {
        if (sSharedPrefUtil == null || !mCurSPName.equals(DEFAULT_SP_NAME)) {
            sSharedPrefUtil = new SharedPrefUtils(context.getApplicationContext());
        }
        return sSharedPrefUtil;
    }

    public static SharedPrefUtils init(Context context, String spName) {
        mContext = context.getApplicationContext();
        if (sSharedPrefUtil == null) {
            sSharedPrefUtil = new SharedPrefUtils(mContext, spName);
        } else if (!spName.equals(mCurSPName)) {
            sSharedPrefUtil = new SharedPrefUtils(mContext, spName);
        }
        return sSharedPrefUtil;
    }

    public SharedPrefUtils put(@StringRes int key, Object value) {
        return put(mContext.getString(key), value);
    }

    public SharedPrefUtils put(String key, Object value) {
        if (value instanceof String) {
            sEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            sEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            sEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            sEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            sEditor.putLong(key, (Long) value);
        } else {
            sEditor.putString(key, value.toString());
        }
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public Object get(@StringRes int key, Object defaultObject) {
        return get(mContext.getString(key), defaultObject);
    }

    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sSharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sSharedPreferences.getInt(key, (int) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sSharedPreferences.getBoolean(key, (boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sSharedPreferences.getFloat(key, (float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sSharedPreferences.getLong(key, (long) defaultObject);
        }
        return null;
    }

    public SharedPrefUtils putInt(String key, int value) {
        sEditor.putInt(key, value);
        editorCompat.apply(sEditor);
        return this;
    }

    public SharedPrefUtils putInt(@StringRes int key, int value) {
        return putInt(mContext.getString(key), value);
    }

    public int getInt(@StringRes int key) {
        return getInt(mContext.getString(key));
    }

    public int getInt(@StringRes int key, int defValue) {
        return getInt(mContext.getString(key), defValue);
    }

    public int getInt(String key) {
        return getInt(key, DEFAULT_INT);
    }


    public int getInt(String key, int defValue) {
        return sSharedPreferences.getInt(key, defValue);
    }

    public SharedPrefUtils putFloat(@StringRes int key, float value) {
        return putFloat(mContext.getString(key), value);
    }

    public SharedPrefUtils putFloat(String key, float value) {
        sEditor.putFloat(key, value);
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public float getFloat(String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    public float getFloat(String key, float defValue) {
        return sSharedPreferences.getFloat(key, defValue);
    }

    public float getFloat(@StringRes int key) {
        return getFloat(mContext.getString(key));
    }

    public float getFloat(@StringRes int key, float defValue) {
        return getFloat(mContext.getString(key), defValue);
    }

    public SharedPrefUtils putLong(@StringRes int key, long value) {
        return putLong(mContext.getString(key), value);
    }

    public SharedPrefUtils putLong(String key, long value) {
        sEditor.putLong(key, value);
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public long getLong(String key) {
        return getLong(key, DEFAULT_INT);
    }

    public long getLong(String key, long defValue) {
        return sSharedPreferences.getLong(key, defValue);
    }

    public long getLong(@StringRes int key) {
        return getLong(mContext.getString(key));
    }

    public long getLong(@StringRes int key, long defValue) {
        return getLong(mContext.getString(key), defValue);
    }

    public SharedPrefUtils putString(@StringRes int key, String value) {
        return putString(mContext.getString(key), value);
    }

    public SharedPrefUtils putString(String key, String value) {
        sEditor.putString(key, value);
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public String getString(String key) {
        return getString(key, DEFAULT_STRING);
    }

    public String getString(String key, String defValue) {
        return sSharedPreferences.getString(key, defValue);
    }

    public String getString(@StringRes int key) {
        return getString(mContext.getString(key), DEFAULT_STRING);
    }

    public String getString(@StringRes int key, String defValue) {
        return getString(mContext.getString(key), defValue);
    }

    public SharedPrefUtils putBoolean(@StringRes int key, boolean value) {
        return putBoolean(mContext.getString(key), value);
    }

    public SharedPrefUtils putBoolean(String key, boolean value) {
        sEditor.putBoolean(key, value);
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sSharedPreferences.getBoolean(key, defValue);
    }

    public boolean getBoolean(@StringRes int key) {
        return getBoolean(mContext.getString(key));
    }

    public boolean getBoolean(@StringRes int key, boolean defValue) {
        return getBoolean(mContext.getString(key), defValue);
    }

    public SharedPrefUtils putStringSet(@StringRes int key, Set<String> value) {
        return putStringSet(mContext.getString(key), value);
    }

    public SharedPrefUtils putStringSet(String key, Set<String> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            sEditor.putStringSet(key, value);
            editorCompat.apply(sEditor);
        }
        return sSharedPrefUtil;
    }

    public Set<String> getStringSet(String key) {
        return getStringSet(key, DEFAULT_STRING_SET);
    }


    public Set<String> getStringSet(String key, Set<String> defValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return sSharedPreferences.getStringSet(key, defValue);
        } else {
            return DEFAULT_STRING_SET;
        }
    }

    public Set<String> getStringSet(@StringRes int key) {
        return getStringSet(mContext.getString(key));
    }

    public Set<String> getStringSet(@StringRes int key, Set<String> defValue) {
        return getStringSet(mContext.getString(key), defValue);
    }


    public boolean contains(String key) {
        return sSharedPreferences.contains(key);
    }

    public boolean contains(@StringRes int key) {
        return contains(mContext.getString(key));
    }

    public Map<String, ?> getAll() {
        return sSharedPreferences.getAll();
    }

    public SharedPrefUtils remove(@StringRes int key) {
        return remove(mContext.getString(key));
    }

    public SharedPrefUtils remove(String key) {
        sEditor.remove(key);
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public SharedPrefUtils clear() {
        sEditor.clear();
        editorCompat.apply(sEditor);
        return sSharedPrefUtil;
    }

    public SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

}
