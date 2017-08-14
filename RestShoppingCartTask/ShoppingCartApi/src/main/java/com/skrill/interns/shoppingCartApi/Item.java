package com.skrill.interns.shoppingCartApi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Item {


    private String name;
    private Integer quantity;

    public Item() {
	}

	public Item(String name, Integer quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	@XmlElement(nillable = false, required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

    @XmlElement(nillable = true, required = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
