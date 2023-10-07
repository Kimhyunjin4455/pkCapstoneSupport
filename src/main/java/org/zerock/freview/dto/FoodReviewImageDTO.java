package org.zerock.freview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodReviewImageDTO {

//    private Long inum;
    private String uuid;
    private String imgName;
    private String path;
    public String getImageURL(){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
    public String getThumbnailURL(){
        try{
            return URLEncoder.encode(path+"/s_"+uuid+"_"+imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
