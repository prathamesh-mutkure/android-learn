package learn.lco.json.extractor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mInstance;
    private static Context mContext;
    private RequestQueue requestQueue;

    private MySingleton(Context context){
        mContext = context;
        requestQueue = getRequestQueue();

    }

    private RequestQueue getRequestQueue(){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mContext);

        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        mInstance = new MySingleton(context);

        return mInstance;
    }

    public void addToRequestQueue(Request request){
        requestQueue.add(request);
    }

}
