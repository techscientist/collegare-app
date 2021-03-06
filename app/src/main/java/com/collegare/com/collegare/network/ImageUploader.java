package com.collegare.com.collegare.network;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;


/**
 * Created by Radhe on 03-03-2016.
 */
public class ImageUploader {

    private static ImageUploader bInstance;
    private Context context;
    public ImageUploader(){}

    public ImageUploader(Context context){
        this.context=context;
    }

    public static ImageUploader getInstance(Context c)
    {
        if(bInstance==null){
            bInstance= new ImageUploader(c);
        }
        return bInstance;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    public void upload(final Bitmap bmp) {
       /* CollegareUser user= DatabaseManager.getInstance(context).getUser();
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 75, bos);
                byte[] data = bos.toByteArray();
                HttpClient httpClient = new DefaultHttpClient();
                String url=App_Config.USER_URL+"?action=setpic&amp;id="+user.id+"&amp;token="+user.token;
                HttpPost postRequest = new HttpPost(url);
                ByteArrayBody bab = new ByteArrayBody(data, user.username+".jpg");
                // File file= new File("/mnt/sdcard/forest.png");
                // FileBody bin = new FileBody(file);
                MultipartEntity reqEntity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);
                reqEntity.addPart("uploaded", bab);
                reqEntity.addPart("photoCaption", new StringBody(user.username));
                postRequest.setEntity(reqEntity);
                HttpResponse response = httpClient.execute(postRequest);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent(), "UTF-8"));
                String sResponse;
                StringBuilder s = new StringBuilder();

                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                Log.e("IU",""+s);
            } catch (Exception e) {
                // handle exception here
                Log.e(e.getClass().getName(), e.getMessage());
            }

    }
*/
    }

}
