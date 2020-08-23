package learn.lco.volley.image;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private MySingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){

        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext);

        return mRequestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){

        if (mInstance == null)
            mInstance = new MySingleton(context);

        return mInstance;
    }

    public void addToRequestQueue(Request request){
        mRequestQueue.add(request);
    }
}
