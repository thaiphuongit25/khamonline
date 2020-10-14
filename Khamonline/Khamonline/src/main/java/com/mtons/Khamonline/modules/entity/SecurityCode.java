/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.modules.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author langhsu on 2015/8/14.
 */
@Entity
@Table(name = "mto_security_code")
public class SecurityCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     */
    @Column(name = "key_", unique = true, nullable = false, length = 64)
    private String key;

    /**
     */
    @Column(length = 16, nullable = false)
    private String code;

    /**
     */
    @Column(length = 64)
    private String target;

    /**
     */
    @Column
    private int type;

    /**
     */
    @Column(name = "expired", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expired;

    /**
     */
    @Column(name = "created", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    /**
     */
    @Column
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
