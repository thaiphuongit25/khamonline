package com.mtons.Khamonline.base.lang;

import lombok.Data;

import java.util.List;

/**
 * @author : landy
 */
@Data
public class Theme {
    /**
     */
    private String path;

    /**
     */
    private String name;

    /**
     */
    private String slogan;

    /**
     */
    private String version;

    /**
     */
    private String author;

    /**
     */
    private String website;

    /**
     */
    private List<String> previews;
}
