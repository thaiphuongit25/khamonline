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
import java.io.Serializable;

/**
 * @author langhsu
 *
 */
@Entity
@Table(name = "mto_channel")
public class Channel implements Serializable {
	private static final long serialVersionUID = 2436696690653745208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 */
	@Column(length = 32)
	private String name;

	/**
	 */
	@Column(name = "key_", unique = true, length = 32)
	private String key;

	/**
	 */
	@Column(length = 128)
	private String thumbnail;

	/**
	 */
	@Column(length = 5)
	private int status;

	/**
	 */
	private int weight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
