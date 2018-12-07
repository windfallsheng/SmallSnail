package com.smallsnailtech.smallsnail.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RetrofitRequestBodyUtils {

    /**
     * 使用的时候传进来的Map的构造为Map<String,Object>,如果传的是普通字符串参数的话，就往map里面put<String,String>
     * 如果传递的是图片或者的话，就put<String,File[]>, key对应的是服务端所要的参数字段名，File[] 所对应的是上传的文件数组
     * 例如服务器需要的是下面的参数 @RequestParam(value = "files") MultipartFile[] postedFiles
     * 我们的map中就需要put("files",File[]);
     *
     * @param resourceMap
     * @return
     */
    public static Map<String, RequestBody> getRequestBodyMap(Map<String, Object> resourceMap) {
        Map<String, RequestBody> params = new HashMap<>();
        Iterator iterator = resourceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue() instanceof String) { //判断值如果是String的话就直接put进去
                RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) entry.getValue());
                params.put((String) entry.getKey(), body);
            } else if (entry.getValue() instanceof File) { //判断当前值是单个文件的话就把key设置成服务端所要的参数子端名加文件名，具体格式可以看下面的
                RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) entry.getValue());
                params.put((String) entry.getKey() + "\";filename=\"" + ((File) entry.getValue()).getName() + "", body);
            } else if (entry.getValue() instanceof File[]) { //判断当前的值是文件数组的话，要把一个个文件拆开再put进去
                File[] files = (File[]) entry.getValue();
                if (files != null && files.length > 0) {
                    for (File f : files) {
                        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), f);
                        params.put((String) entry.getKey() + "\";filename=\"" + f.getName() + "", body);
                    }
                }
            }
        }
        return params;
    }
}

