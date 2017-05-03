package com.hy.utils.file;

import java.io.File;
import java.util.UUID;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * Created by wfy on 2016/12/15.
 */
public class ImagesUtil {

    public static  String getSuffix(String imagesName){
        if(StringUtil.isEmpty(imagesName)) return null;
        if (imagesName.lastIndexOf(".") != -1) {
            return imagesName.substring(imagesName.lastIndexOf(".") + 1);
        }
        return null;
    }
    public static  String getUuidName(String imagesName){
        if(StringUtil.isEmpty(imagesName)) return null;
       return   UUID.randomUUID()+"."+ImagesUtil.getSuffix(imagesName);
    }
    public static  String getDateName(String imagesName){
        if(StringUtil.isEmpty(imagesName)) return null;
        return   System.currentTimeMillis()+"."+ImagesUtil.getSuffix(imagesName);
    }


    /**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if ("Image Width".equals(tag.getTagName())){
                        String width=tag.getDescription();
                        width= width.replaceAll(" ","").replaceAll("pixels","");
                        return Integer.valueOf(width);
                    }
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }


    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if ("Image Height".equals(tag.getTagName())){
                        String height=tag.getDescription();
                        height= height.replaceAll(" ","").replaceAll("pixels","");
                        return Integer.valueOf(height);
                    }
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }
        } catch (Exception e) {
           return 0;
        }
     return 0;
    }

    public static void main(String[] args) throws Exception {

        String  url="C:\\Users\\yang\\Desktop\\我的图\\2902999323b8f265.png";
        File file=new File(url);
        getImgHeight(file);
        getImgWidth(file);
         Metadata metadata = ImageMetadataReader.readMetadata(file);
         for (Directory directory : metadata.getDirectories()) {
         for (Tag tag : directory.getTags()) {
         System.out.println(directory.getName()+"*********"+ tag.getTagName()+"*********"+  tag.getDescription());
         }
         if (directory.hasErrors()) {
         for (String error : directory.getErrors()) {
         System.err.format("ERROR: %s", error);
         }
         }
         }


    }

}
